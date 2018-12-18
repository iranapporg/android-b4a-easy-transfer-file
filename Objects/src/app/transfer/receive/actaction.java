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

public class actaction extends Activity implements B4AActivity{
	public static actaction mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "app.transfer.receive", "app.transfer.receive.actaction");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (actaction).");
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
		activityBA = new BA(this, layout, processBA, "app.transfer.receive", "app.transfer.receive.actaction");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "app.transfer.receive.actaction", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (actaction) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (actaction) Resume **");
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
		return actaction.class;
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
        BA.LogInfo("** Activity (actaction) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (actaction) Resume **");
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
public com.htsoft.hotspotlib.Hotspotlib _hotspot = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcountdownload = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldownloading = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _pb1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnldownload = null;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.actupload _actupload = null;
public app.transfer.receive.acthelp1 _acthelp1 = null;
public app.transfer.receive.acthotspot _acthotspot = null;
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
 //BA.debugLineNum = 18;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 19;BA.debugLine="Activity.LoadLayout(\"frmconnected\")";
mostCurrent._activity.LoadLayout("frmconnected",mostCurrent.activityBA);
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event";
 //BA.debugLineNum = 31;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 32;BA.debugLine="If Msgbox2(\"آیا میخواهید از سرویس خارج شوید؟\",\"توجه\",\"آری\",\"خیر\",\"\",Null) = DialogResponse.POSITIVE Then";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا میخواهید از سرویس خارج شوید؟","توجه","آری","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 33;BA.debugLine="Try";
try { //BA.debugLineNum = 34;BA.debugLine="StopService(HttpServers)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._httpservers.getObject()));
 //BA.debugLineNum = 35;BA.debugLine="hotspot.tat";
mostCurrent._hotspot.tat(mostCurrent.activityBA);
 } 
       catch (Exception e23) {
			processBA.setLastException(e23); };
 };
 //BA.debugLineNum = 39;BA.debugLine="Return False";
if (true) return anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 26;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _deletefile(String _delete_filename) throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub DeleteFile(delete_filename As String)";
 //BA.debugLineNum = 54;BA.debugLine="Try";
try { //BA.debugLineNum = 55;BA.debugLine="If Msgbox2(\"آیا مایل به حذف این فایل هستید؟\",\"توجه\",\"بله\",\"خیر\",\"\",Null) = DialogResponse.POSITIVE Then";
if (anywheresoftware.b4a.keywords.Common.Msgbox2("آیا مایل به حذف این فایل هستید؟","توجه","بله","خیر","",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA)==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
 //BA.debugLineNum = 56;BA.debugLine="File.Delete(myLibrary.getFullPath(delete_filename),myLibrary.GetFileName(delete_filename))";
anywheresoftware.b4a.keywords.Common.File.Delete(mostCurrent._mylibrary._getfullpath(mostCurrent.activityBA,_delete_filename),mostCurrent._mylibrary._getfilename(mostCurrent.activityBA,_delete_filename));
 //BA.debugLineNum = 57;BA.debugLine="ToastMessageShow(\"تصویر مورد نظر حذف شد\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("تصویر مورد نظر حذف شد",anywheresoftware.b4a.keywords.Common.False);
 };
 } 
       catch (Exception e43) {
			processBA.setLastException(e43); //BA.debugLineNum = 60;BA.debugLine="ToastMessageShow(\"متاسفانه فایل قابل حذف نیست\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow("متاسفانه فایل قابل حذف نیست",anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private hotspot As Hotspotlib";
mostCurrent._hotspot = new com.htsoft.hotspotlib.Hotspotlib();
 //BA.debugLineNum = 12;BA.debugLine="Private lblcountDownload As Label";
mostCurrent._lblcountdownload = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private lbldownloading As Label";
mostCurrent._lbldownloading = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private pb1 As ProgressBar";
mostCurrent._pb1 = new anywheresoftware.b4a.objects.ProgressBarWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private pnldownload As Panel";
mostCurrent._pnldownload = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static String  _loadingstate() throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Sub LoadingState";
 //BA.debugLineNum = 49;BA.debugLine="pnldownload.Visible = Not(pnldownload.Visible)";
mostCurrent._pnldownload.setVisible(anywheresoftware.b4a.keywords.Common.Not(mostCurrent._pnldownload.getVisible()));
 //BA.debugLineNum = 50;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _setdownload() throws Exception{
 //BA.debugLineNum = 43;BA.debugLine="Sub setDownload";
 //BA.debugLineNum = 44;BA.debugLine="lblcountDownload.Text = \"تعداد دانلود شده ها : \" & HttpServers.downloadCount";
mostCurrent._lblcountdownload.setText((Object)("تعداد دانلود شده ها : "+BA.NumberToString(mostCurrent._httpservers._downloadcount)));
 //BA.debugLineNum = 45;BA.debugLine="lblcountDownload.SetVisibleAnimated(1900,True)";
mostCurrent._lblcountdownload.SetVisibleAnimated((int) (1900),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
}
