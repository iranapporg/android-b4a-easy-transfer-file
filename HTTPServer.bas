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
End Sub

Sub Service_Create
Try
 ht.Initialize("hs")
 ht.Start(20000)
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
 
 Select True
	#Region Pages
	Case url = "/"
	     Dim html As String
		Response.SetContentType("text/html")
		html = File.ReadString(File.DirAssets,"index.html")
		Response.SendString(html.Replace("$ul",myLibrary.ul))
		'myLibrary.ul = ""
		'myLibrary.zips = ""

	Case url = "/uploads/"
	     Dim html As String
		Response.SetContentType("text/html")
		html = File.ReadString(File.DirAssets,"a.html")
		Response.SendString(html.Replace("$ul",myLibrary.ul))
     
	Case url = "/download.zip"
	     Log(myLibrary.createZip)
	     
	Case url = "/uploads/upload"
	     Dim k1 As String
	     k1 = Request.GetUploadedFile("file1")
		Request.GetParameter("file1")
		File.Copy(ht.TempFolder,k1,File.DirRootExternal,Request.GetParameter("file1"))
	#End Region

	Case Else
	  Dim filetype As String
	  filetype = myLibrary.getFileType(url)
	  
	  If url.IndexOf("/...") <> -1 Then
	   Return
	  End If
	  
	  Log(url)
	  If url.IndexOf("sdcard") <> -1 Then
	     'for download images that user selectd
		Response.SetContentType(filetype) 
		Response.SendFile(myLibrary.su.DecodeUrl(myLibrary.getFullPath(url),"UTF8"),myLibrary.su.DecodeUrl(myLibrary.GetFileName(url),"UTF8"))		
	  Else
		Response.SetContentType("image/*") 
		Response.SendFile(File.DirAssets,myLibrary.su.DecodeUrl(url.SubString(1),"UTF8"))
	  End If
	  
	End Select
End Sub