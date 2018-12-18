package app.transfer.receive;

import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class actupload extends Activity implements B4AActivity{
	public static actupload mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isFirst) {
			processBA = new BA(this.getApplicationContext(), null, null, "app.transfer.receive", "app.transfer.receive.actupload");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actupload).");
				p.finish();
			}
		}
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
		BA.handler.postDelayed(new WaitForLayout(), 5);

	}
	private static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "app.transfer.receive", "app.transfer.receive.actupload");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "app.transfer.receive.actupload", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actupload) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actupload) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEvent(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return actupload.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (actupload) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (actupload) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Timer _tim = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlfiles = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlicon = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imgwireless = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _imghotspot = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblhotspot = null;
public anywheresoftware.b4a.object.RippleViewWrapper _re = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblaudio = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblimage = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblvideo = null;
public static int _top = 0;
public static int _left = 0;
public static int _width = 0;
public static int _height = 0;
public com.htsoft.hotspotlib.Hotspotlib _hotspot = null;
public static int _cn = 0;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.acthelp1 _acthelp1 = null;
public app.transfer.receive.acthotspot _acthotspot = null;
public app.transfer.receive.actaction _actaction = null;
public app.transfer.receive.mylibrary _mylibrary = null;
public app.transfer.receive.httpservers _httpservers = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 26;BA.debugLine="Activity.LoadLayout(\"frmupload\")";
mostCurrent._activity.LoadLayout("frmupload",mostCurrent.activityBA);
 //BA.debugLineNum = 27;BA.debugLine="re.Initialize(imghotspot,Colors.RGB(42, 181, 246),300,True)";
mostCurrent._re.Initialize(mostCurrent.activityBA,(android.view.View)(mostCurrent._imghotspot.getObject()),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (42),(int) (181),(int) (246)),(int) (300),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 28;BA.debugLine="re.Initialize(imgwireless,Colors.RGB(42, 181, 246),300,True)";
mostCurrent._re.Initialize(mostCurrent.activityBA,(android.view.View)(mostCurrent._imgwireless.getObject()),anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (42),(int) (181),(int) (246)),(int) (300),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 29;BA.debugLine="top  = pnlicon.top";
_top = mostCurrent._pnlicon.getTop();
 //BA.debugLineNum = 30;BA.debugLine="left = pnlicon.left";
_left = mostCurrent._pnlicon.getLeft();
 //BA.debugLineNum = 31;BA.debugLine="width  = pnlicon.width";
_width = mostCurrent._pnlicon.getWidth();
 //BA.debugLineNum = 32;BA.debugLine="height = pnlicon.height";
_height = mostCurrent._pnlicon.getHeight();
 //BA.debugLineNum = 34;BA.debugLine="pnlicon.width = 0";
mostCurrent._pnlicon.setWidth((int) (0));
 //BA.debugLineNum = 35;BA.debugLine="pnlicon.height = 0";
mostCurrent._pnlicon.setHeight((int) (0));
 //BA.debugLineNum = 37;BA.debugLine="pnlicon.SetLayoutAnimated(750,left,top,width,height)";
mostCurrent._pnlicon.SetLayoutAnimated((int) (750),_left,_top,_width,_height);
 //BA.debugLineNum = 38;BA.debugLine="pnlicon.width = width";
mostCurrent._pnlicon.setWidth(_width);
 //BA.debugLineNum = 39;BA.debugLine="pnlicon.height = height";
mostCurrent._pnlicon.setHeight(_height);
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event";
 //BA.debugLineNum = 105;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 106;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 44;BA.debugLine="lblimage.Text = myLibrary.ParseInputFile(Activity.GetStartingIntent)";
mostCurrent._lblimage.setText((Object)(mostCurrent._mylibrary._parseinputfile(mostCurrent.activityBA,mostCurrent._activity.GetStartingIntent())));
 //BA.debugLineNum = 50;BA.debugLine="pnlfiles.SetVisibleAnimated(1700,True)";
mostCurrent._pnlfiles.SetVisibleAnimated((int) (1700),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 51;BA.debugLine="pnlfiles.Visible = True";
mostCurrent._pnlfiles.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _btnhelp_click() throws Exception{
 //BA.debugLineNum = 110;BA.debugLine="Sub btnhelp_Click";
 //BA.debugLineNum = 111;BA.debugLine="actHelp1.HelpName = \"different\"";
mostCurrent._acthelp1._helpname = "different";
 //BA.debugLineNum = 112;BA.debugLine="StartActivity(actHelp1)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._acthelp1.getObject()));
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static String  _btnhotspot_click() throws Exception{
 //BA.debugLineNum = 72;BA.debugLine="Sub btnhotspot_Click";
 //BA.debugLineNum = 73;BA.debugLine="hotspot.batdau(\"Easy Transfer\",False,\"\")";
mostCurrent._hotspot.batdau(mostCurrent.activityBA,"Easy Transfer",anywheresoftware.b4a.keywords.Common.False,"");
 //BA.debugLineNum = 74;BA.debugLine="ProgressDialogShow2(\"در حال شناسایی شبکه...\",True)";
anywheresoftware.b4a.keywords.Common.ProgressDialogShow2(mostCurrent.activityBA,"در حال شناسایی شبکه...",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 75;BA.debugLine="tim.Initialize(\"tmr\",500)";
_tim.Initialize(processBA,"tmr",(long) (500));
 //BA.debugLineNum = 76;BA.debugLine="tim.Enabled = True";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _btnwireless_click() throws Exception{
String _ip = "";
 //BA.debugLineNum = 58;BA.debugLine="Sub btnwireless_Click";
 //BA.debugLineNum = 59;BA.debugLine="Dim ip As String";
_ip = "";
 //BA.debugLineNum = 61;BA.debugLine="ip = myLibrary.GetIP";
_ip = mostCurrent._mylibrary._getip(mostCurrent.activityBA);
 //BA.debugLineNum = 63;BA.debugLine="If Regex.IsMatch(\"\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}\",ip) = True AND ip <> \"127.0.0.1\" Then";
if (anywheresoftware.b4a.keywords.Common.Regex.IsMatch("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}",_ip)==anywheresoftware.b4a.keywords.Common.True && (_ip).equals("127.0.0.1") == false) { 
 //BA.debugLineNum = 64;BA.debugLine="actHotspot.HelpName = \"next\"";
mostCurrent._acthotspot._helpname = "next";
 //BA.debugLineNum = 65;BA.debugLine="StartActivity(actHotspot)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._acthotspot.getObject()));
 }else {
 //BA.debugLineNum = 67;BA.debugLine="ToastMessageShow(\"خطا : گوشی شما به مودم وایرلس وصل نیست\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("خطا : گوشی شما به مودم وایرلس وصل نیست",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 70;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private pnlfiles As Panel";
mostCurrent._pnlfiles = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private pnlicon As Panel";
mostCurrent._pnlicon = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private imgwireless As ImageView";
mostCurrent._imgwireless = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private imghotspot As ImageView";
mostCurrent._imghotspot = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private lblhotspot As Label";
mostCurrent._lblhotspot = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private re As RippleView";
mostCurrent._re = new anywheresoftware.b4a.object.RippleViewWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private lblaudio As Label";
mostCurrent._lblaudio = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private lblimage As Label";
mostCurrent._lblimage = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Private lblvideo As Label";
mostCurrent._lblvideo = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Private top,left,width,height As Int";
_top = 0;
_left = 0;
_width = 0;
_height = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private hotspot As Hotspotlib";
mostCurrent._hotspot = new com.htsoft.hotspotlib.Hotspotlib();
 //BA.debugLineNum = 22;BA.debugLine="Private cn As Int = 1";
_cn = (int) (1);
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim tim As Timer";
_tim = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _tmr_tick() throws Exception{
String _ip = "";
 //BA.debugLineNum = 79;BA.debugLine="Sub tmr_Tick";
 //BA.debugLineNum = 80;BA.debugLine="Dim ip As String";
_ip = "";
 //BA.debugLineNum = 82;BA.debugLine="ip = myLibrary.GetIP";
_ip = mostCurrent._mylibrary._getip(mostCurrent.activityBA);
 //BA.debugLineNum = 84;BA.debugLine="If cn > 10 Then";
if (_cn>10) { 
 //BA.debugLineNum = 85;BA.debugLine="tim.Enabled = False";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 86;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 87;BA.debugLine="ToastMessageShow(\"خطا : لطفا مجددا تلاش کنید\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("خطا : لطفا مجددا تلاش کنید",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 88;BA.debugLine="cn = 1";
_cn = (int) (1);
 //BA.debugLineNum = 89;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 92;BA.debugLine="If Regex.IsMatch(\"\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}\",ip) = True AND ip <> \"127.0.0.1\" Then";
if (anywheresoftware.b4a.keywords.Common.Regex.IsMatch("\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}",_ip)==anywheresoftware.b4a.keywords.Common.True && (_ip).equals("127.0.0.1") == false) { 
 //BA.debugLineNum = 93;BA.debugLine="tim.Enabled = False";
_tim.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 94;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 95;BA.debugLine="cn = 1";
_cn = (int) (1);
 //BA.debugLineNum = 96;BA.debugLine="actHotspot.HelpName = \"\"";
mostCurrent._acthotspot._helpname = "";
 //BA.debugLineNum = 97;BA.debugLine="StartActivity(actHotspot)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._acthotspot.getObject()));
 };
 //BA.debugLineNum = 100;BA.debugLine="cn = cn + 1";
_cn = (int) (_cn+1);
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
}
