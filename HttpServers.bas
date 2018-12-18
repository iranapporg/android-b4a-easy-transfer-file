Type=Service
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
#End Region

Sub Process_Globals
Dim ht As HttpServer
Dim downloadCount,i1 As Int
Dim phoneW As PhoneWakeState
End Sub

Sub Service_Create
Try
 ht.Initialize("hs")
 ht.Start(2020)
Catch
End Try
End Sub

Sub Service_Start (StartingIntent As Intent)

End Sub

Sub Service_Destroy

End Sub

Sub hs_HandleRequest (Request As ServletRequest, Response As ServletResponse)
 
 Dim url As String
 
 url = Request.RequestURI.ToLowerCase
 Log(url)
 Select True
	#Region Pages
	Case url = "/"
	     Dim html As String
		Response.SetContentType("text/html")
		html = File.ReadString(File.DirAssets,"index.html")
		Response.SendString(html.Replace("$ul",myLibrary.ul))
		StartActivity(actAction)
		i1 = 0
		downloadCount = 0
		'myLibrary.ul = ""
		'myLibrary.zips = ""
     
	Case url.StartsWith("/delete/")
		Dim delete_filename As String
		delete_filename = myLibrary.su.DecodeUrl(url.SubString(8),"UTF8")
		CallSub2(actAction,"DeleteFile",delete_filename)
		Response.SendString("1")
		myLibrary.ForceScan(myLibrary.getFullPath(delete_filename))
		
	Case url = "/download.zip"
	   Try
	     
'		If myLibrary.FileSize > myLibrary.getStorageSize Then
'		 ToastMessageShow("خطا : متاسفانه حافظه گوشی شما کم است.مقداری از فضا را خالی کنید و دوباره تلاش کنید",False)
'		 Response.SetContentType("text/html")
'		 Response.SendString("<b>خطا : متاسفانه حافظه گوشی شما کم است.مقداری از فضا را خالی کنید و دوباره تلاش کنید</b>")
'		 Return
'		End If
		
	     CallSub(actAction,"LoadingState")
		DoEvents
		phoneW.KeepAlive(True)
	     myLibrary.createZip
		Response.SetContentType("application/zip")
		Response.SendFile(File.DirRootExternal,"download.zip")
		CallSub(actAction,"LoadingState")
		phoneW.ReleaseKeepAlive
		DoEvents
	   Catch
	     ToastMessageShow("فایل قابل دانلود نیست",False)
	   End Try
	     
	Case url = "/uploads/upload"
	     Dim k1 As String
	     k1 = Request.GetUploadedFile("file1")
		File.Copy(ht.TempFolder,k1,File.DirRootExternal,Request.GetParameter("file1"))
		
	#End Region

	Case Else
	  Dim filetype As String
	  filetype = myLibrary.getFileType(url)
	  
	  If url.IndexOf("/...") <> -1 Then
	   Return
	  End If

	  If url.IndexOf("sdcard") <> -1 OR url.IndexOf("ext") <> -1 Then
	     'for download images that user selectd
		Try
		 phoneW.KeepAlive(True)
		 Response.SetContentType(filetype) 
		 
		 Dim p1,p2 As String
		 p1 = myLibrary.su.DecodeUrl(myLibrary.getFullPath(url),"UTF8").Replace("extsdcard","extSdCard").Replace("emulated/0","sdcard0")
		 p2 = myLibrary.su.DecodeUrl(myLibrary.GetFileName(url),"UTF8")
		 
		 Response.SendFile(p1,p2)
		 phoneW.ReleaseKeepAlive
		 DoEvents
		Catch
		 Response.SetContentType(filetype)
		 Response.SendFile(File.DirAssets,"no_image.jpg")
		 DoEvents
		End Try
		
		i1 = i1 + 1
		
		If i1 > 3 Then
		 If checkDemo = True Then
			ToastMessageShow("شما نسخه آزمایشی برنامه را نصب کرده اید" & CRLF & "لطفا نسخه اصلی را دانلود کنید",True)
			Return
		 End If
		End If
		
		If i1 > myLibrary.filesCount Then
			downloadCount = downloadCount + 1
			CallSub(actAction,"setDownload")
		End If
		
	  Else
		Response.SetContentType(myLibrary.getFileType(url))
		Response.SendFile(File.DirAssets,myLibrary.su.DecodeUrl(url.SubString(1),"UTF8"))
	  End If
	  
	End Select
End Sub

Sub checkDemo As Boolean
	Try
		If File.Exists(File.DirRootExternal,"android\data\demo") OR File.Exists(File.DirInternal,"demo") Then
			Return True
		Else
			File.WriteString(File.DirRootExternal,"android\data\demo","")
			File.WriteString(File.DirInternal,"demo","")
			Return False
		End If
	Catch
		Return True
	End Try
End Sub