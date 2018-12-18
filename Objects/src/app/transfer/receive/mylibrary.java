package app.transfer.receive;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mylibrary {
private static mylibrary mostCurrent = new mylibrary();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.StringUtils _su = null;
public static String _ul = "";
public static String[] _zips = null;
public static int _image = 0;
public static int _audio = 0;
public static int _video = 0;
public static int _filescount = 0;
public static long _filesize = 0L;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.actupload _actupload = null;
public app.transfer.receive.acthelp1 _acthelp1 = null;
public app.transfer.receive.acthotspot _acthotspot = null;
public app.transfer.receive.actaction _actaction = null;
public app.transfer.receive.httpservers _httpservers = null;
public static int  _createzip(anywheresoftware.b4a.BA _ba) throws Exception{
flm.b4a.archiver.ArchiverForB4A _zip = null;
 //BA.debugLineNum = 82;BA.debugLine="Sub createZip As Int";
 //BA.debugLineNum = 83;BA.debugLine="Dim zip As Archiver";
_zip = new flm.b4a.archiver.ArchiverForB4A();
 //BA.debugLineNum = 84;BA.debugLine="Try";
try { //BA.debugLineNum = 85;BA.debugLine="Return zip.ZipFiles(\"\",zips,File.DirRootExternal,\"download.zip\",\"\")";
if (true) return _zip.ZipFiles((_ba.processBA == null ? _ba : _ba.processBA),"",_zips,anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"download.zip","");
 } 
       catch (Exception e66) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e66); //BA.debugLineNum = 87;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return 0;
}
public static boolean  _filerename(anywheresoftware.b4a.BA _ba,String _old,String _new) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _newobj = null;
 //BA.debugLineNum = 196;BA.debugLine="Sub FileRename(Old As String,New As String) As Boolean";
 //BA.debugLineNum = 197;BA.debugLine="Dim R As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 198;BA.debugLine="Dim NewObj As Object";
_newobj = new Object();
 //BA.debugLineNum = 199;BA.debugLine="NewObj=R.CreateObject2(\"java.io.File\",Array As Object(New),Array As String(\"java.lang.String\"))";
_newobj = _r.CreateObject2("java.io.File",new Object[]{(Object)(_new)},new String[]{"java.lang.String"});
 //BA.debugLineNum = 200;BA.debugLine="R.Target=R.CreateObject2(\"java.io.File\",Array As Object(Old),Array As String(\"java.lang.String\"))";
_r.Target = _r.CreateObject2("java.io.File",new Object[]{(Object)(_old)},new String[]{"java.lang.String"});
 //BA.debugLineNum = 201;BA.debugLine="Return R.RunMethod4(\"renameTo\",Array As Object(NewObj),Array As String(\"java.io.File\"))";
if (true) return BA.ObjectToBoolean(_r.RunMethod4("renameTo",new Object[]{_newobj},new String[]{"java.io.File"}));
 //BA.debugLineNum = 202;BA.debugLine="End Sub";
return false;
}
public static String  _font(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _label1) throws Exception{
 //BA.debugLineNum = 186;BA.debugLine="Sub Font(Label1 As Label)";
 //BA.debugLineNum = 187;BA.debugLine="Label1.Typeface = Typeface.LoadFromAssets(\"byekan.ttf\")";
_label1.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("byekan.ttf"));
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static String  _forcescan(anywheresoftware.b4a.BA _ba,String _s_path) throws Exception{
anywheresoftware.b4j.object.JavaObject _context = null;
String[] _paths = null;
anywheresoftware.b4j.object.JavaObject _mediascanner = null;
 //BA.debugLineNum = 224;BA.debugLine="Sub ForceScan(s_path As String)";
 //BA.debugLineNum = 225;BA.debugLine="Try";
try { //BA.debugLineNum = 226;BA.debugLine="Dim context As JavaObject";
_context = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 227;BA.debugLine="context = context.InitializeStatic(\"anywheresoftware.b4a.BA\").GetField(\"applicationContext\")";
_context.setObject((java.lang.Object)(_context.InitializeStatic("anywheresoftware.b4a.BA").GetField("applicationContext")));
 //BA.debugLineNum = 228;BA.debugLine="Dim paths() As String = Array As String(s_path) 'files to scan";
_paths = new String[]{_s_path};
 //BA.debugLineNum = 229;BA.debugLine="Dim mediaScanner As JavaObject";
_mediascanner = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 230;BA.debugLine="mediaScanner.InitializeStatic(\"android.media.MediaScannerConnection\")";
_mediascanner.InitializeStatic("android.media.MediaScannerConnection");
 //BA.debugLineNum = 231;BA.debugLine="mediaScanner.RunMethod(\"scanFile\", Array As Object(context, paths, Null, Null))";
_mediascanner.RunMethod("scanFile",new Object[]{(Object)(_context.getObject()),(Object)(_paths),anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null});
 } 
       catch (Exception e169) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e169); };
 //BA.debugLineNum = 234;BA.debugLine="End Sub";
return "";
}
public static String  _getextension(anywheresoftware.b4a.BA _ba,String _filename) throws Exception{
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _m = null;
 //BA.debugLineNum = 40;BA.debugLine="Sub getExtension(filename As String) As String";
 //BA.debugLineNum = 41;BA.debugLine="Dim m As Matcher = Regex.Matcher(\"\\.([^\\.]*)$\", filename)";
_m = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
_m = anywheresoftware.b4a.keywords.Common.Regex.Matcher("\\.([^\\.]*)$",_filename);
 //BA.debugLineNum = 42;BA.debugLine="If m.Find Then";
if (_m.Find()) { 
 //BA.debugLineNum = 43;BA.debugLine="Return m.Group(1)";
if (true) return _m.Group((int) (1));
 }else {
 //BA.debugLineNum = 45;BA.debugLine="Return \"\"";
if (true) return "";
 };
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _getfilename(anywheresoftware.b4a.BA _ba,String _fullpath) throws Exception{
String _s = "";
 //BA.debugLineNum = 33;BA.debugLine="Sub GetFileName(FullPath As String) As String";
 //BA.debugLineNum = 34;BA.debugLine="Dim s As String";
_s = "";
 //BA.debugLineNum = 35;BA.debugLine="s = FullPath.SubString(FullPath.LastIndexOf(\"/\")+1)";
_s = _fullpath.substring((int) (_fullpath.lastIndexOf("/")+1));
 //BA.debugLineNum = 36;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public static String  _getfiletype(anywheresoftware.b4a.BA _ba,String _filename) throws Exception{
String _f1 = "";
 //BA.debugLineNum = 10;BA.debugLine="Sub getFileType(filename As String) As String";
 //BA.debugLineNum = 12;BA.debugLine="Dim f1 As String";
_f1 = "";
 //BA.debugLineNum = 13;BA.debugLine="f1 = getExtension(filename)";
_f1 = _getextension(_ba,_filename);
 //BA.debugLineNum = 15;BA.debugLine="Select f1";
switch (BA.switchObjectToInt(_f1,"png","jpg","jpeg","gif","bmp","mp4","3gp","avi","mkv","mp3","wav","ogg","txt","css")) {
case 0:
case 1:
case 2:
case 3:
case 4:
 //BA.debugLineNum = 17;BA.debugLine="Return \"image/*\"";
if (true) return "image/*";
 break;
case 5:
case 6:
case 7:
case 8:
 //BA.debugLineNum = 19;BA.debugLine="Return \"video/*\"";
if (true) return "video/*";
 break;
case 9:
case 10:
case 11:
 //BA.debugLineNum = 21;BA.debugLine="Return \"audio/*\"";
if (true) return "audio/*";
 break;
case 12:
case 13:
 //BA.debugLineNum = 23;BA.debugLine="Return \"text/css\"";
if (true) return "text/css";
 break;
}
;
 //BA.debugLineNum = 26;BA.debugLine="Return \"application/octet-stream\"";
if (true) return "application/octet-stream";
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _getfullpath(anywheresoftware.b4a.BA _ba,String _fullpath) throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub getFullPath(FullPath As String) As String";
 //BA.debugLineNum = 52;BA.debugLine="Return FullPath.SubString2(0,FullPath.LastIndexOf(\"/\"))";
if (true) return _fullpath.substring((int) (0),_fullpath.lastIndexOf("/"));
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _getip(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper _socket = null;
 //BA.debugLineNum = 190;BA.debugLine="Sub GetIP As String";
 //BA.debugLineNum = 191;BA.debugLine="Dim Socket As ServerSocket";
_socket = new anywheresoftware.b4a.objects.SocketWrapper.ServerSocketWrapper();
 //BA.debugLineNum = 192;BA.debugLine="Socket.Initialize(0,\"\")";
_socket.Initialize((_ba.processBA == null ? _ba : _ba.processBA),(int) (0),"");
 //BA.debugLineNum = 193;BA.debugLine="Return Socket.GetMyIP";
if (true) return _socket.GetMyIP();
 //BA.debugLineNum = 194;BA.debugLine="End Sub";
return "";
}
public static String  _getpathfromcontentresult(anywheresoftware.b4a.BA _ba,String _uristring) throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor1 = null;
anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper _uri1 = null;
String[] _proj = null;
anywheresoftware.b4a.objects.ContentResolverWrapper _cr = null;
int _i = 0;
String _id = "";
String _res = "";
 //BA.debugLineNum = 56;BA.debugLine="Sub GetPathFromContentResult(UriString As String) As String";
 //BA.debugLineNum = 57;BA.debugLine="If UriString.StartsWith(\"file:///\") Then Return UriString.Replace(\"file:///\",\"/\")";
if (_uristring.startsWith("file:///")) { 
if (true) return _uristring.replace("file:///","/");};
 //BA.debugLineNum = 58;BA.debugLine="If UriString.StartsWith(\"/\") Then Return UriString 'If the user used a file manager to choose the image";
if (_uristring.startsWith("/")) { 
if (true) return _uristring;};
 //BA.debugLineNum = 59;BA.debugLine="Dim Cursor1 As Cursor";
_cursor1 = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
 //BA.debugLineNum = 60;BA.debugLine="Dim Uri1 As Uri";
_uri1 = new anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper();
 //BA.debugLineNum = 61;BA.debugLine="Dim Proj() As String = Array As String(\"_data\")";
_proj = new String[]{"_data"};
 //BA.debugLineNum = 62;BA.debugLine="Dim cr As ContentResolver";
_cr = new anywheresoftware.b4a.objects.ContentResolverWrapper();
 //BA.debugLineNum = 63;BA.debugLine="cr.Initialize(\"\")";
_cr.Initialize("");
 //BA.debugLineNum = 64;BA.debugLine="If UriString.StartsWith(\"content://com.android.providers.media.documents\") Then";
if (_uristring.startsWith("content://com.android.providers.media.documents")) { 
 //BA.debugLineNum = 65;BA.debugLine="Dim i As Int = UriString.IndexOf(\"%3A\")";
_i = _uristring.indexOf("%3A");
 //BA.debugLineNum = 66;BA.debugLine="Dim id As String = UriString.SubString(i + 3)";
_id = _uristring.substring((int) (_i+3));
 //BA.debugLineNum = 67;BA.debugLine="Uri1.Parse(\"content://media/external/images/media\")";
_uri1.Parse("content://media/external/images/media");
 //BA.debugLineNum = 68;BA.debugLine="Cursor1 = cr.Query(Uri1, Proj, \"_id = ?\", Array As String(id), \"\")";
_cursor1 = _cr.Query(_uri1,_proj,"_id = ?",new String[]{_id},"");
 }else {
 //BA.debugLineNum = 70;BA.debugLine="Uri1.Parse(UriString)";
_uri1.Parse(_uristring);
 //BA.debugLineNum = 71;BA.debugLine="Cursor1 = cr.Query(Uri1, Proj, \"\", Null, \"\")";
_cursor1 = _cr.Query(_uri1,_proj,"",(String[])(anywheresoftware.b4a.keywords.Common.Null),"");
 };
 //BA.debugLineNum = 73;BA.debugLine="Cursor1.Position = 0";
_cursor1.setPosition((int) (0));
 //BA.debugLineNum = 74;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 75;BA.debugLine="res = Cursor1.GetString(\"_data\")";
_res = _cursor1.GetString("_data");
 //BA.debugLineNum = 76;BA.debugLine="Cursor1.Close";
_cursor1.Close();
 //BA.debugLineNum = 77;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return "";
}
public static long  _getstoragesize(anywheresoftware.b4a.BA _ba) throws Exception{
com.rootsoft.oslibrary.OSLibrary _p1 = null;
 //BA.debugLineNum = 218;BA.debugLine="Sub getStorageSize As Long";
 //BA.debugLineNum = 219;BA.debugLine="Dim p1 As OperatingSystem";
_p1 = new com.rootsoft.oslibrary.OSLibrary();
 //BA.debugLineNum = 220;BA.debugLine="p1.Initialize(\"\")";
_p1.Initialize((_ba.processBA == null ? _ba : _ba.processBA),"");
 //BA.debugLineNum = 221;BA.debugLine="Return Round(p1.TotalExternalMemorySize)";
if (true) return anywheresoftware.b4a.keywords.Common.Round(_p1.getTotalExternalMemorySize());
 //BA.debugLineNum = 222;BA.debugLine="End Sub";
return 0L;
}
public static int  _parseinputfile(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
String _s1 = "";
anywheresoftware.b4a.objects.collections.List _l1 = null;
 //BA.debugLineNum = 154;BA.debugLine="Sub ParseInputFile(Intent As Intent) As Int";
 //BA.debugLineNum = 156;BA.debugLine="Try";
try { //BA.debugLineNum = 157;BA.debugLine="Dim s1 As String";
_s1 = "";
 //BA.debugLineNum = 158;BA.debugLine="Dim l1 As List";
_l1 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 160;BA.debugLine="l1.Initialize";
_l1.Initialize();
 //BA.debugLineNum = 161;BA.debugLine="l1 = Intent.GetExtra(\"android.intent.extra.STREAM\")";
_l1.setObject((java.util.List)(_intent.GetExtra("android.intent.extra.STREAM")));
 //BA.debugLineNum = 163;BA.debugLine="If l1.IsInitialized = True Then";
if (_l1.IsInitialized()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 164;BA.debugLine="ZipFile(l1)";
_zipfile(_ba,_l1);
 //BA.debugLineNum = 165;BA.debugLine="Return l1.Size";
if (true) return _l1.getSize();
 }else {
 //BA.debugLineNum = 168;BA.debugLine="s1 = Intent.ExtrasToString.Replace(\"Bundle\",\"bundle\").Replace(\"STREAM\",\"stream\")";
_s1 = _intent.ExtrasToString().replace("Bundle","bundle").replace("STREAM","stream");
 //BA.debugLineNum = 169;BA.debugLine="s1 = s1.Replace(\"bundle[{android.intent.extra.stream=\",\"\").Replace(\"}]\",\"\")";
_s1 = _s1.replace("bundle[{android.intent.extra.stream=","").replace("}]","");
 //BA.debugLineNum = 171;BA.debugLine="If s1 <> \"no extras\" Then";
if ((_s1).equals("no extras") == false) { 
 //BA.debugLineNum = 172;BA.debugLine="l1.Initialize";
_l1.Initialize();
 //BA.debugLineNum = 173;BA.debugLine="l1.Add(s1)";
_l1.Add((Object)(_s1));
 //BA.debugLineNum = 174;BA.debugLine="ZipFile(l1)";
_zipfile(_ba,_l1);
 //BA.debugLineNum = 175;BA.debugLine="Return l1.Size";
if (true) return _l1.getSize();
 };
 };
 } 
       catch (Exception e124) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e124); //BA.debugLineNum = 181;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 184;BA.debugLine="End Sub";
return 0;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 3;BA.debugLine="Dim ul,zips() As String";
_ul = "";
_zips = new String[(int) (0)];
java.util.Arrays.fill(_zips,"");
 //BA.debugLineNum = 4;BA.debugLine="Dim image,audio,video As Int";
_image = 0;
_audio = 0;
_video = 0;
 //BA.debugLineNum = 5;BA.debugLine="Dim filesCount As Int";
_filescount = 0;
 //BA.debugLineNum = 6;BA.debugLine="Dim FileSize As Long";
_filesize = 0L;
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public static boolean  _renamefile(anywheresoftware.b4a.BA _ba,String _originalfilename,String _newfilename) throws Exception{
int _result = 0;
anywheresoftware.b4a.keywords.StringBuilderWrapper _stdout = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _stderr = null;
anywheresoftware.b4a.phone.Phone _ph = null;
 //BA.debugLineNum = 204;BA.debugLine="Sub RenameFile(OriginalFileName As String, NewFileName As String) As Boolean";
 //BA.debugLineNum = 205;BA.debugLine="Dim Result As Int";
_result = 0;
 //BA.debugLineNum = 206;BA.debugLine="Dim StdOut, StdErr As StringBuilder";
_stdout = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
_stderr = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 207;BA.debugLine="StdOut.Initialize";
_stdout.Initialize();
 //BA.debugLineNum = 208;BA.debugLine="StdErr.Initialize";
_stderr.Initialize();
 //BA.debugLineNum = 209;BA.debugLine="Dim Ph As Phone";
_ph = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 210;BA.debugLine="Result = Ph.Shell(\"mv \" & OriginalFileName & \" \" & NewFileName, Null,  StdOut, StdErr)";
_result = _ph.Shell("mv "+_originalfilename+" "+_newfilename,(String[])(anywheresoftware.b4a.keywords.Common.Null),(java.lang.StringBuilder)(_stdout.getObject()),(java.lang.StringBuilder)(_stderr.getObject()));
 //BA.debugLineNum = 211;BA.debugLine="If Result = 0 Then";
if (_result==0) { 
 //BA.debugLineNum = 212;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 214;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 216;BA.debugLine="End Sub";
return false;
}
public static String  _zipfile(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.collections.List _list1) throws Exception{
String[] _s1 = null;
String _path1 = "";
String _filename = "";
String _newfn = "";
String _filet = "";
String _tag_a = "";
int _i = 0;
 //BA.debugLineNum = 91;BA.debugLine="Sub ZipFile(list1 As List)";
 //BA.debugLineNum = 93;BA.debugLine="Dim s1(list1.Size),Path1,filename,newfn,fileT,Tag_A As String";
_s1 = new String[_list1.getSize()];
java.util.Arrays.fill(_s1,"");
_path1 = "";
_filename = "";
_newfn = "";
_filet = "";
_tag_a = "";
 //BA.debugLineNum = 95;BA.debugLine="Try";
try { //BA.debugLineNum = 96;BA.debugLine="ul = \"\"";
_ul = "";
 //BA.debugLineNum = 97;BA.debugLine="zips = Null";
_zips = (String[])(anywheresoftware.b4a.keywords.Common.Null);
 //BA.debugLineNum = 98;BA.debugLine="FileSize = 0";
_filesize = (long) (0);
 //BA.debugLineNum = 100;BA.debugLine="For i = 0 To list1.Size - 1";
{
final int step75 = 1;
final int limit75 = (int) (_list1.getSize()-1);
for (_i = (int) (0); (step75 > 0 && _i <= limit75) || (step75 < 0 && _i >= limit75); _i = ((int)(0 + _i + step75))) {
 //BA.debugLineNum = 101;BA.debugLine="Path1 =getFullPath(GetPathFromContentResult(list1.get(i)))";
_path1 = _getfullpath(_ba,_getpathfromcontentresult(_ba,BA.ObjectToString(_list1.Get(_i))));
 //BA.debugLineNum = 102;BA.debugLine="filename = GetFileName(GetPathFromContentResult(list1.get(i)))";
_filename = _getfilename(_ba,_getpathfromcontentresult(_ba,BA.ObjectToString(_list1.Get(_i))));
 //BA.debugLineNum = 104;BA.debugLine="fileT = getFileType(filename)";
_filet = _getfiletype(_ba,_filename);
 //BA.debugLineNum = 117;BA.debugLine="newfn = Path1 & \"/\" & filename";
_newfn = _path1+"/"+_filename;
 //BA.debugLineNum = 119;BA.debugLine="If filename = \"...\" Then";
if ((_filename).equals("...")) { 
 //BA.debugLineNum = 120;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 123;BA.debugLine="s1(i)  = newfn";
_s1[_i] = _newfn;
 //BA.debugLineNum = 124;BA.debugLine="FileSize = FileSize + File.Size(Path1,filename)";
_filesize = (long) (_filesize+anywheresoftware.b4a.keywords.Common.File.Size(_path1,_filename));
 //BA.debugLineNum = 126;BA.debugLine="Tag_A = \"<div class=\"\"btn-group mybtn\"\" role=\"\"group\"\"><a href=\"\"\" & newfn & \"\"\" class=\"\"col-md-6  btn btn btn-info\"\">دانلود</a><a id=\"\"delete\"\" path=\"\" & newfn & \"\" class=\"\"col-md-6  btn btn btn-info\"\">حذف عکس</a></div>\"";
_tag_a = "<div class=\"btn-group mybtn\" role=\"group\"><a href=\""+_newfn+"\" class=\"col-md-6  btn btn btn-info\">دانلود</a><a id=\"delete\" path=\" & newfn & \" class=\"col-md-6  btn btn btn-info\">حذف عکس</a></div>";
 //BA.debugLineNum = 128;BA.debugLine="Select fileT";
switch (BA.switchObjectToInt(_filet,"image/*","video/*","application/octet-stream","audio/*")) {
case 0:
 //BA.debugLineNum = 130;BA.debugLine="ul = ul & \"<div class=\"\"col-md-3  col-xs-6 \"\"><a class=\"\"\"\" href=\"\"\" & newfn & \"\"\" data-lightbox=\"\"example-1\"\"><img class=\"\"pic thumbnail\"\" src=\"\"\" & newfn & \"\"\" alt=\"\"image-1\"\" /></a>\" & Tag_A & \"</div>\"";
_ul = _ul+"<div class=\"col-md-3  col-xs-6 \"><a class=\"\" href=\""+_newfn+"\" data-lightbox=\"example-1\"><img class=\"pic thumbnail\" src=\""+_newfn+"\" alt=\"image-1\" /></a>"+_tag_a+"</div>";
 //BA.debugLineNum = 131;BA.debugLine="image = image + 1";
_image = (int) (_image+1);
 break;
case 1:
 break;
case 2:
 //BA.debugLineNum = 135;BA.debugLine="ul = ul & \"<div class=\"\"col-md-3  col-xs-6 \"\"><a class=\"\"\"\" href=\"\"video.jpg\"\" data-lightbox=\"\"example-1\"\"><img class=\"\"pic thumbnail\"\" src=\"\"video.jpg\"\" alt=\"\"image-1\"\" /></a>\" & Tag_A & \"</div>\"";
_ul = _ul+"<div class=\"col-md-3  col-xs-6 \"><a class=\"\" href=\"video.jpg\" data-lightbox=\"example-1\"><img class=\"pic thumbnail\" src=\"video.jpg\" alt=\"image-1\" /></a>"+_tag_a+"</div>";
 //BA.debugLineNum = 136;BA.debugLine="video = video + 1";
_video = (int) (_video+1);
 break;
case 3:
 //BA.debugLineNum = 139;BA.debugLine="ul = ul & \"<div class=\"\"col-md-3  col-xs-6 \"\"><a class=\"\"\"\" href=\"\"audio.jpg\"\" data-lightbox=\"\"example-1\"\"><img class=\"\"pic thumbnail\"\" src=\"\"audio.jpg\"\" alt=\"\"image-1\"\" /></a>\" & Tag_A & \"</div>\"";
_ul = _ul+"<div class=\"col-md-3  col-xs-6 \"><a class=\"\" href=\"audio.jpg\" data-lightbox=\"example-1\"><img class=\"pic thumbnail\" src=\"audio.jpg\" alt=\"image-1\" /></a>"+_tag_a+"</div>";
 //BA.debugLineNum = 140;BA.debugLine="audio = audio + 1";
_audio = (int) (_audio+1);
 break;
}
;
 }
};
 //BA.debugLineNum = 145;BA.debugLine="filesCount = i";
_filescount = _i;
 //BA.debugLineNum = 146;BA.debugLine="zips = s1";
_zips = _s1;
 } 
       catch (Exception e102) {
			(_ba.processBA == null ? _ba : _ba.processBA).setLastException(e102); };
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
}
