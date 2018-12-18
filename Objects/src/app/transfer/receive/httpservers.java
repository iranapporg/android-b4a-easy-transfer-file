package app.transfer.receive;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class httpservers extends android.app.Service {
	public static class httpservers_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
			android.content.Intent in = new android.content.Intent(context, httpservers.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
			context.startService(in);
		}

	}
    static httpservers mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return httpservers.class;
	}
	@Override
	public void onCreate() {
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "app.transfer.receive", "app.transfer.receive.httpservers");
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        processBA.setActivityPaused(false);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "app.transfer.receive.httpservers", processBA, _service);
		}
        BA.LogInfo("** Service (httpservers) Create **");
        processBA.raiseEvent(null, "service_create");
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		handleStart(intent);
    }
    @Override
    public int onStartCommand(android.content.Intent intent, int flags, int startId) {
    	handleStart(intent);
		return android.app.Service.START_NOT_STICKY;
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (httpservers) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
    			if (intent != null) {
    				if (intent.hasExtra("b4a_internal_intent"))
    					iw.setObject((android.content.Intent) intent.getParcelableExtra("b4a_internal_intent"));
    				else
    					iw.setObject(intent);
    			}
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}
	@Override
	public void onDestroy() {
        BA.LogInfo("** Service (httpservers) Destroy **");
		processBA.raiseEvent(null, "service_destroy");
        processBA.service = null;
		mostCurrent = null;
		processBA.setActivityPaused(true);
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.HttpServer _ht = null;
public static int _downloadcount = 0;
public static int _i1 = 0;
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _phonew = null;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.actupload _actupload = null;
public app.transfer.receive.acthelp1 _acthelp1 = null;
public app.transfer.receive.acthotspot _acthotspot = null;
public app.transfer.receive.actaction _actaction = null;
public app.transfer.receive.mylibrary _mylibrary = null;
public static boolean  _checkdemo() throws Exception{
 //BA.debugLineNum = 132;BA.debugLine="Sub checkDemo As Boolean";
 //BA.debugLineNum = 133;BA.debugLine="Try";
try { //BA.debugLineNum = 134;BA.debugLine="If File.Exists(File.DirRootExternal,\"android\\data\\demo\") OR File.Exists(File.DirInternal,\"demo\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"android\\data\\demo") || anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"demo")) { 
 //BA.debugLineNum = 135;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 137;BA.debugLine="File.WriteString(File.DirRootExternal,\"android\\data\\demo\",\"\")";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"android\\data\\demo","");
 //BA.debugLineNum = 138;BA.debugLine="File.WriteString(File.DirInternal,\"demo\",\"\")";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"demo","");
 //BA.debugLineNum = 139;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e101) {
			processBA.setLastException(e101); //BA.debugLineNum = 142;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 144;BA.debugLine="End Sub";
return false;
}
public static String  _hs_handlerequest(anywheresoftware.b4a.objects.Servlet.ServletRequestWrapper _request,anywheresoftware.b4a.objects.Servlet.ServletResponseWrapper _response) throws Exception{
String _url = "";
String _html = "";
String _delete_filename = "";
String _k1 = "";
String _filetype = "";
String _p1 = "";
String _p2 = "";
 //BA.debugLineNum = 27;BA.debugLine="Sub hs_HandleRequest (Request As ServletRequest, Response As ServletResponse)";
 //BA.debugLineNum = 29;BA.debugLine="Dim url As String";
_url = "";
 //BA.debugLineNum = 31;BA.debugLine="url = Request.RequestURI.ToLowerCase";
_url = _request.getRequestURI().toLowerCase();
 //BA.debugLineNum = 32;BA.debugLine="Log(url)";
anywheresoftware.b4a.keywords.Common.Log(_url);
 //BA.debugLineNum = 33;BA.debugLine="Select True";
switch (BA.switchObjectToInt(anywheresoftware.b4a.keywords.Common.True,(_url).equals("/"),_url.startsWith("/delete/"),(_url).equals("/download.zip"),(_url).equals("/uploads/upload"))) {
case 0:
 //BA.debugLineNum = 36;BA.debugLine="Dim html As String";
_html = "";
 //BA.debugLineNum = 37;BA.debugLine="Response.SetContentType(\"text/html\")";
_response.SetContentType("text/html");
 //BA.debugLineNum = 38;BA.debugLine="html = File.ReadString(File.DirAssets,\"index.html\")";
_html = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"index.html");
 //BA.debugLineNum = 39;BA.debugLine="Response.SendString(html.Replace(\"$ul\",myLibrary.ul))";
_response.SendString(_html.replace("$ul",mostCurrent._mylibrary._ul));
 //BA.debugLineNum = 40;BA.debugLine="StartActivity(actAction)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._actaction.getObject()));
 //BA.debugLineNum = 41;BA.debugLine="i1 = 0";
_i1 = (int) (0);
 //BA.debugLineNum = 42;BA.debugLine="downloadCount = 0";
_downloadcount = (int) (0);
 break;
case 1:
 //BA.debugLineNum = 47;BA.debugLine="Dim delete_filename As String";
_delete_filename = "";
 //BA.debugLineNum = 48;BA.debugLine="delete_filename = myLibrary.su.DecodeUrl(url.SubString(8),\"UTF8\")";
_delete_filename = mostCurrent._mylibrary._su.DecodeUrl(_url.substring((int) (8)),"UTF8");
 //BA.debugLineNum = 49;BA.debugLine="CallSub2(actAction,\"DeleteFile\",delete_filename)";
anywheresoftware.b4a.keywords.Common.CallSubNew2(processBA,(Object)(mostCurrent._actaction.getObject()),"DeleteFile",(Object)(_delete_filename));
 //BA.debugLineNum = 50;BA.debugLine="Response.SendString(\"1\")";
_response.SendString("1");
 //BA.debugLineNum = 51;BA.debugLine="myLibrary.ForceScan(myLibrary.getFullPath(delete_filename))";
mostCurrent._mylibrary._forcescan(processBA,mostCurrent._mylibrary._getfullpath(processBA,_delete_filename));
 break;
case 2:
 //BA.debugLineNum = 54;BA.debugLine="Try";
try { //BA.debugLineNum = 63;BA.debugLine="CallSub(actAction,\"LoadingState\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,(Object)(mostCurrent._actaction.getObject()),"LoadingState");
 //BA.debugLineNum = 64;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 65;BA.debugLine="phoneW.KeepAlive(True)";
_phonew.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 66;BA.debugLine="myLibrary.createZip";
mostCurrent._mylibrary._createzip(processBA);
 //BA.debugLineNum = 67;BA.debugLine="Response.SetContentType(\"application/zip\")";
_response.SetContentType("application/zip");
 //BA.debugLineNum = 68;BA.debugLine="Response.SendFile(File.DirRootExternal,\"download.zip\")";
_response.SendFile(anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),"download.zip");
 //BA.debugLineNum = 69;BA.debugLine="CallSub(actAction,\"LoadingState\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,(Object)(mostCurrent._actaction.getObject()),"LoadingState");
 //BA.debugLineNum = 70;BA.debugLine="phoneW.ReleaseKeepAlive";
_phonew.ReleaseKeepAlive();
 //BA.debugLineNum = 71;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 } 
       catch (Exception e47) {
			processBA.setLastException(e47); //BA.debugLineNum = 73;BA.debugLine="ToastMessageShow(\"فایل قابل دانلود نیست\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("فایل قابل دانلود نیست",anywheresoftware.b4a.keywords.Common.False);
 };
 break;
case 3:
 //BA.debugLineNum = 77;BA.debugLine="Dim k1 As String";
_k1 = "";
 //BA.debugLineNum = 78;BA.debugLine="k1 = Request.GetUploadedFile(\"file1\")";
_k1 = _request.GetUploadedFile("file1");
 //BA.debugLineNum = 79;BA.debugLine="File.Copy(ht.TempFolder,k1,File.DirRootExternal,Request.GetParameter(\"file1\"))";
anywheresoftware.b4a.keywords.Common.File.Copy(_ht.TempFolder,_k1,anywheresoftware.b4a.keywords.Common.File.getDirRootExternal(),_request.GetParameter("file1"));
 break;
default:
 //BA.debugLineNum = 84;BA.debugLine="Dim filetype As String";
_filetype = "";
 //BA.debugLineNum = 85;BA.debugLine="filetype = myLibrary.getFileType(url)";
_filetype = mostCurrent._mylibrary._getfiletype(processBA,_url);
 //BA.debugLineNum = 87;BA.debugLine="If url.IndexOf(\"/...\") <> -1 Then";
if (_url.indexOf("/...")!=-1) { 
 //BA.debugLineNum = 88;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 91;BA.debugLine="If url.IndexOf(\"sdcard\") <> -1 OR url.IndexOf(\"ext\") <> -1 Then";
if (_url.indexOf("sdcard")!=-1 || _url.indexOf("ext")!=-1) { 
 //BA.debugLineNum = 93;BA.debugLine="Try";
try { //BA.debugLineNum = 94;BA.debugLine="phoneW.KeepAlive(True)";
_phonew.KeepAlive(processBA,anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 95;BA.debugLine="Response.SetContentType(filetype)";
_response.SetContentType(_filetype);
 //BA.debugLineNum = 97;BA.debugLine="Dim p1,p2 As String";
_p1 = "";
_p2 = "";
 //BA.debugLineNum = 98;BA.debugLine="p1 = myLibrary.su.DecodeUrl(myLibrary.getFullPath(url),\"UTF8\").Replace(\"extsdcard\",\"extSdCard\").Replace(\"emulated/0\",\"sdcard0\")";
_p1 = mostCurrent._mylibrary._su.DecodeUrl(mostCurrent._mylibrary._getfullpath(processBA,_url),"UTF8").replace("extsdcard","extSdCard").replace("emulated/0","sdcard0");
 //BA.debugLineNum = 99;BA.debugLine="p2 = myLibrary.su.DecodeUrl(myLibrary.GetFileName(url),\"UTF8\")";
_p2 = mostCurrent._mylibrary._su.DecodeUrl(mostCurrent._mylibrary._getfilename(processBA,_url),"UTF8");
 //BA.debugLineNum = 101;BA.debugLine="Response.SendFile(p1,p2)";
_response.SendFile(_p1,_p2);
 //BA.debugLineNum = 102;BA.debugLine="phoneW.ReleaseKeepAlive";
_phonew.ReleaseKeepAlive();
 //BA.debugLineNum = 103;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 } 
       catch (Exception e70) {
			processBA.setLastException(e70); //BA.debugLineNum = 105;BA.debugLine="Response.SetContentType(filetype)";
_response.SetContentType(_filetype);
 //BA.debugLineNum = 106;BA.debugLine="Response.SendFile(File.DirAssets,\"no_image.jpg\")";
_response.SendFile(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"no_image.jpg");
 //BA.debugLineNum = 107;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 };
 //BA.debugLineNum = 110;BA.debugLine="i1 = i1 + 1";
_i1 = (int) (_i1+1);
 //BA.debugLineNum = 112;BA.debugLine="If i1 > 3 Then";
if (_i1>3) { 
 //BA.debugLineNum = 113;BA.debugLine="If checkDemo = True Then";
if (_checkdemo()==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 114;BA.debugLine="ToastMessageShow(\"شما نسخه آزمایشی برنامه را نصب کرده اید\" & CRLF & \"لطفا نسخه اصلی را دانلود کنید\",True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("شما نسخه آزمایشی برنامه را نصب کرده اید"+anywheresoftware.b4a.keywords.Common.CRLF+"لطفا نسخه اصلی را دانلود کنید",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 115;BA.debugLine="Return";
if (true) return "";
 };
 };
 //BA.debugLineNum = 119;BA.debugLine="If i1 > myLibrary.filesCount Then";
if (_i1>mostCurrent._mylibrary._filescount) { 
 //BA.debugLineNum = 120;BA.debugLine="downloadCount = downloadCount + 1";
_downloadcount = (int) (_downloadcount+1);
 //BA.debugLineNum = 121;BA.debugLine="CallSub(actAction,\"setDownload\")";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,(Object)(mostCurrent._actaction.getObject()),"setDownload");
 };
 }else {
 //BA.debugLineNum = 125;BA.debugLine="Response.SetContentType(myLibrary.getFileType(url))";
_response.SetContentType(mostCurrent._mylibrary._getfiletype(processBA,_url));
 //BA.debugLineNum = 126;BA.debugLine="Response.SendFile(File.DirAssets,myLibrary.su.DecodeUrl(url.SubString(1),\"UTF8\"))";
_response.SendFile(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),mostCurrent._mylibrary._su.DecodeUrl(_url.substring((int) (1)),"UTF8"));
 };
 break;
}
;
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim ht As HttpServer";
_ht = new anywheresoftware.b4a.objects.HttpServer();
 //BA.debugLineNum = 7;BA.debugLine="Dim downloadCount,i1 As Int";
_downloadcount = 0;
_i1 = 0;
 //BA.debugLineNum = 8;BA.debugLine="Dim phoneW As PhoneWakeState";
_phonew = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 12;BA.debugLine="Try";
try { //BA.debugLineNum = 13;BA.debugLine="ht.Initialize(\"hs\")";
_ht.Initialize(processBA,"hs");
 //BA.debugLineNum = 14;BA.debugLine="ht.Start(2020)";
_ht.Start((int) (2020));
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); };
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
}
