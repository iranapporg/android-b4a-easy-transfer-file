Type=StaticCode
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
Sub Process_Globals
Dim su As StringUtils
Dim ul,zips() As String
Dim image,audio,video As Int
Dim filesCount As Int
Dim FileSize As Long
End Sub

'example: get image/* from baby.png or get video/* from me.mp4
Sub getFileType(filename As String) As String

  Dim f1 As String
  f1 = getExtension(filename)
 
  Select f1
   Case "png","jpg","jpeg","gif","bmp"
    Return "image/*"
   Case "mp4","3gp","avi","mkv"
    Return "video/*"
   Case "mp3","wav","ogg"
    Return "audio/*"
   Case "txt","css"
    Return "text/css"
  End Select
  
  Return "application/octet-stream"
  
End Sub

#Region Working with Path
'get filename of path
'example: return a.apk from  /storage/sdcard0/icm/a.apk
Sub GetFileName(FullPath As String) As String
   Dim s As String
   s = FullPath.SubString(FullPath.LastIndexOf("/")+1)
   Return s
End Sub

'get extension of file example: get png from image.png
Sub getExtension(filename As String) As String
Dim m As Matcher = Regex.Matcher("\.([^\.]*)$", filename)
 If m.Find Then
  Return m.Group(1)
 Else
  Return ""
 End If
End Sub

'get path of filename
'example: return /storage/sdcard0/icm/a.apk /storage/sdcard0/icm/
Sub getFullPath(FullPath As String) As String
 Return FullPath.SubString2(0,FullPath.LastIndexOf("/"))
End Sub

'convert uri path to url
Sub GetPathFromContentResult(UriString As String) As String
  If UriString.StartsWith("file:///") Then Return UriString.Replace("file:///","/")
  If UriString.StartsWith("/") Then Return UriString 'If the user used a file manager to choose the image
  Dim Cursor1 As Cursor
  Dim Uri1 As Uri
  Dim Proj() As String = Array As String("_data")
  Dim cr As ContentResolver
  cr.Initialize("")
  If UriString.StartsWith("content://com.android.providers.media.documents") Then
  Dim i As Int = UriString.IndexOf("%3A")
  Dim id As String = UriString.SubString(i + 3)
  Uri1.Parse("content://media/external/images/media")
  Cursor1 = cr.Query(Uri1, Proj, "_id = ?", Array As String(id), "")
  Else
  Uri1.Parse(UriString)
  Cursor1 = cr.Query(Uri1, Proj, "", Null, "")
  End If
  Cursor1.Position = 0
  Dim res As String
  res = Cursor1.GetString("_data")
  Cursor1.Close
  Return res
End Sub

#End Region

Sub createZip As Int
 Dim zip As Archiver
 Try
  Return zip.ZipFiles("",zips,File.DirRootExternal,"download.zip","")
 Catch
  Return 0
 End Try
End Sub

Sub ZipFile(list1 As List)

Dim s1(list1.Size),Path1,filename,newfn,fileT,Tag_A As String

  Try
     ul = ""
	zips = Null
	FileSize = 0
	
	For i = 0 To list1.Size - 1
	 Path1 =getFullPath(GetPathFromContentResult(list1.get(i)))
	 filename = GetFileName(GetPathFromContentResult(list1.get(i)))
	 
	 fileT = getFileType(filename)
	 
'	 If filename.IndexOf(" ") <> -1 Then
'	  filename = filename.Replace(" ","+")
'	 End If
	 
'	 If filename.IndexOf("(") <> -1 OR filename.IndexOf(")") <> -1 OR filename.IndexOf("[") <> -1 OR filename.IndexOf("]") <> -1 Then
'	  If FileRename(Path1 & "/" & filename,Path1 & "/" & filename.Replace("]","_").Replace("[","_").Replace("(","_").Replace(")","_")) = False Then
'	   RenameFile(Path1 & "/" & filename,Path1 & "/" & filename.Replace("]","_").Replace("[","_").Replace("(","_").Replace(")","_"))
'	  End If
'	  filename = filename.Replace("]","_").Replace("[","_").Replace("(","_").Replace(")","_")
'	 End If
	 
	 newfn = Path1 & "/" & filename

	 If filename = "..." Then
	  Return
	 End If
	 
	 s1(i)  = newfn
	 FileSize = FileSize + File.Size(Path1,filename)
	 
	 Tag_A = "<div class=""btn-group mybtn"" role=""group""><a href=""" & newfn & """ class=""col-md-6  btn btn btn-info"">دانلود</a><a id=""delete"" path="" & newfn & "" class=""col-md-6  btn btn btn-info"">حذف عکس</a></div>"
	 
	 Select fileT
	  Case "image/*"
	   ul = ul & "<div class=""col-md-3  col-xs-6 ""><a class="""" href=""" & newfn & """ data-lightbox=""example-1""><img class=""pic thumbnail"" src=""" & newfn & """ alt=""image-1"" /></a>" & Tag_A & "</div>"
	   image = image + 1
	  Case "video/*"
	  Case "application/octet-stream"
	    'ul = ul & "<div class=""col-md-4  col-xs-6 thumbnail""><video src=""" & newfn & """ controls=""controls""></video>" & Tag_A & "</div>"
	    ul = ul & "<div class=""col-md-3  col-xs-6 ""><a class="""" href=""video.jpg"" data-lightbox=""example-1""><img class=""pic thumbnail"" src=""video.jpg"" alt=""image-1"" /></a>" & Tag_A & "</div>"
	   video = video + 1
	  Case "audio/*"
	    'ul = ul & "<div class=""col-md-4  col-xs-6 thumbnail""><audio src=""" & newfn & """ controls=""controls""></audio>" & Tag_A & "</div>"
	    ul = ul & "<div class=""col-md-3  col-xs-6 ""><a class="""" href=""audio.jpg"" data-lightbox=""example-1""><img class=""pic thumbnail"" src=""audio.jpg"" alt=""image-1"" /></a>" & Tag_A & "</div>"
	   audio = audio + 1
	 End Select
	 
	Next

	filesCount = i
	zips = s1
	
  Catch
 
  End Try

End Sub

Sub ParseInputFile(Intent As Intent) As Int

Try
 Dim s1 As String
 Dim l1 As List
 
 l1.Initialize
 l1 = Intent.GetExtra("android.intent.extra.STREAM")
 
 If l1.IsInitialized = True Then
  ZipFile(l1)
  Return l1.Size
  
 Else
  s1 = Intent.ExtrasToString.Replace("Bundle","bundle").Replace("STREAM","stream")
  s1 = s1.Replace("bundle[{android.intent.extra.stream=","").Replace("}]","")
   
   If s1 <> "no extras" Then
    l1.Initialize
    l1.Add(s1)
    ZipFile(l1)
    Return l1.Size
   End If
   
 End If
 
Catch
 Return 0
End Try

End Sub

Sub Font(Label1 As Label)
Label1.Typeface = Typeface.LoadFromAssets("byekan.ttf")
End Sub

Sub GetIP As String
 Dim Socket As ServerSocket
 Socket.Initialize(0,"")
 Return Socket.GetMyIP
End Sub

Sub FileRename(Old As String,New As String) As Boolean
   Dim R As Reflector
   Dim NewObj As Object
   NewObj=R.CreateObject2("java.io.File",Array As Object(New),Array As String("java.lang.String"))
   R.Target=R.CreateObject2("java.io.File",Array As Object(Old),Array As String("java.lang.String"))
   Return R.RunMethod4("renameTo",Array As Object(NewObj),Array As String("java.io.File"))
End Sub

Sub RenameFile(OriginalFileName As String, NewFileName As String) As Boolean
   Dim Result As Int
   Dim StdOut, StdErr As StringBuilder
   StdOut.Initialize
   StdErr.Initialize
   Dim Ph As Phone
   Result = Ph.Shell("mv " & OriginalFileName & " " & NewFileName, Null,  StdOut, StdErr)
   If Result = 0 Then
      Return True
   Else
      Return False
   End If
End Sub

Sub getStorageSize As Long
 Dim p1 As OperatingSystem
 p1.Initialize("")
 Return Round(p1.TotalExternalMemorySize)
End Sub

Sub ForceScan(s_path As String)
Try
	Dim context As JavaObject
	context = context.InitializeStatic("anywheresoftware.b4a.BA").GetField("applicationContext")
	Dim paths() As String = Array As String(s_path) 'files to scan
	Dim mediaScanner As JavaObject
	mediaScanner.InitializeStatic("android.media.MediaScannerConnection")
	mediaScanner.RunMethod("scanFile", Array As Object(context, paths, Null, Null))
Catch
End Try
End Sub
'
'	 Tag_A = "<a class=""col-md-6  btn btn btn-info"" href=""" & newfn & """ style=""position: absolute; bottom: 0; left: 0;"">دانلود</a>" & _ 
'	 	    "<a id=""delete"" class=""col-md-6  btn btn btn-warning"" href="""" data-path=""" & newfn & """ style=""position: absolute; bottom: 0; left: 50%;"">حذف</a>"
'	 
'	 Select fileT
'	  Case "image/*"
'	   ul = ul & "<div class=""col-md-4  col-xs-6 thumbnail""><img src=""" & newfn & """ class=""pic"">" & Tag_A & "</div>"
'	   image = image + 1
'	  Case "video/*"
'	  Case "application/octet-stream"
'	    'ul = ul & "<div class=""col-md-4  col-xs-6 thumbnail""><video src=""" & newfn & """ controls=""controls""></video>" & Tag_A & "</div>"
'	    ul = ul & "<div class=""col-md-4  col-xs-6 thumbnail""><img src=""video.jpg"" class=""pic"">" & Tag_A & "</div>"
'	   video = video + 1
'	  Case "audio/*"
'	    'ul = ul & "<div class=""col-md-4  col-xs-6 thumbnail""><audio src=""" & newfn & """ controls=""controls""></audio>" & Tag_A & "</div>"
'	    ul = ul & "<div class=""col-md-4  col-xs-6 thumbnail""><img src=""audio.jpg"" class=""pic"">" & Tag_A & "</div>"
'	   audio = audio + 1
'	 End Select