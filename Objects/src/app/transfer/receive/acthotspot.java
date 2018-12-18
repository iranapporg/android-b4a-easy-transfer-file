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

public class acthotspot extends Activity implements B4AActivity{
	public static acthotspot mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "app.transfer.receive", "app.transfer.receive.acthotspot");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (acthotspot).");
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
		activityBA = new BA(this, layout, processBA, "app.transfer.receive", "app.transfer.receive.acthotspot");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "app.transfer.receive.acthotspot", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (acthotspot) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (acthotspot) Resume **");
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
		return acthotspot.class;
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
        BA.LogInfo("** Activity (acthotspot) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (acthotspot) Resume **");
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
public static String _helpname = "";
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv1 = null;
public app.transfer.receive.help _help = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.actupload _actupload = null;
public app.transfer.receive.acthelp1 _acthelp1 = null;
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
 //BA.debugLineNum = 16;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 17;BA.debugLine="Activity.LoadLayout(\"frmhotspot\")";
mostCurrent._activity.LoadLayout("frmhotspot",mostCurrent.activityBA);
 //BA.debugLineNum = 19;BA.debugLine="StartService(HttpServers)";
anywheresoftware.b4a.keywords.Common.StartService(mostCurrent.activityBA,(Object)(mostCurrent._httpservers.getObject()));
 //BA.debugLineNum = 21;BA.debugLine="If HelpName = \"\" Then";
if ((_helpname).equals("")) { 
 //BA.debugLineNum = 22;BA.debugLine="lbltitle.Text = \"اتصال به هات اسپات\"";
mostCurrent._lbltitle.setText((Object)("اتصال به هات اسپات"));
 //BA.debugLineNum = 23;BA.debugLine="Help.Initialize(sv1,Me)";
mostCurrent._help._initialize(mostCurrent.activityBA,mostCurrent._sv1,acthotspot.getObject());
 //BA.debugLineNum = 24;BA.debugLine="Help.AddLabel(\"گوشی شما برای انتقال فایل آماده است. در مرحله بعد باید از کامپیوتر و یا گوشی گیرنده نیز به گوشی فرستند وصل شوید. برای این کار نیاز است از طریق وایرلس دستگاه گیرنده، به شبکه EasyTransfer وصل شوید. اگر تا بحال این کار را نکرده اید و با روش انجام آن آشنا نیستید بر روی دکمه راهنمای اتصال به وایرلس کلیک کنید.\",18,Colors.Black)";
mostCurrent._help._addlabel("گوشی شما برای انتقال فایل آماده است. در مرحله بعد باید از کامپیوتر و یا گوشی گیرنده نیز به گوشی فرستند وصل شوید. برای این کار نیاز است از طریق وایرلس دستگاه گیرنده، به شبکه EasyTransfer وصل شوید. اگر تا بحال این کار را نکرده اید و با روش انجام آن آشنا نیستید بر روی دکمه راهنمای اتصال به وایرلس کلیک کنید.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 25;BA.debugLine="Help.Margin = 30dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 26;BA.debugLine="Help.AddLabelButton(\"راهنمای اتصال به برنامه در ویندوز 8\",\"btnhelp8\")";
mostCurrent._help._addlabelbutton("راهنمای اتصال به برنامه در ویندوز 8","btnhelp8");
 //BA.debugLineNum = 27;BA.debugLine="Help.Margin = 10dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 28;BA.debugLine="Help.AddLabelButton(\"راهنمای اتصال به برنامه در ویندوز 7\",\"btnhelp7\")";
mostCurrent._help._addlabelbutton("راهنمای اتصال به برنامه در ویندوز 7","btnhelp7");
 //BA.debugLineNum = 29;BA.debugLine="Help.Margin = 40dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 30;BA.debugLine="Help.AddLabel(\"اگر قبلا به وایرلس EasyTransfer متصل شده اید به مرحله بعد بروید.\",18,Colors.Black)";
mostCurrent._help._addlabel("اگر قبلا به وایرلس EasyTransfer متصل شده اید به مرحله بعد بروید.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 31;BA.debugLine="Help.AddLabelButton(\"مرحله بعد\",\"btnnext\")";
mostCurrent._help._addlabelbutton("مرحله بعد","btnnext");
 //BA.debugLineNum = 32;BA.debugLine="Help.ApplyScrollHeight";
mostCurrent._help._applyscrollheight();
 }else if((_helpname).equals("next")) { 
 //BA.debugLineNum = 34;BA.debugLine="lbltitle.Text = \"واردکردن آی پی برنامه\"";
mostCurrent._lbltitle.setText((Object)("واردکردن آی پی برنامه"));
 //BA.debugLineNum = 35;BA.debugLine="Help.Initialize(sv1,Activity)";
mostCurrent._help._initialize(mostCurrent.activityBA,mostCurrent._sv1,(Object)(mostCurrent._activity.getObject()));
 //BA.debugLineNum = 36;BA.debugLine="Help.AddLabel(\"مرورگر کامپیوتر خود را باز کنید و متن داخل کادر زیر را در مرورگر تایپ کنید و دکمه اینتر را بزنید.\",18,Colors.Black)";
mostCurrent._help._addlabel("مرورگر کامپیوتر خود را باز کنید و متن داخل کادر زیر را در مرورگر تایپ کنید و دکمه اینتر را بزنید.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 37;BA.debugLine="Help.AddImage(\"w1.jpg\",myLibrary.GetIP & \":2020\",False)";
mostCurrent._help._addimage("w1.jpg",mostCurrent._mylibrary._getip(mostCurrent.activityBA)+":2020",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 38;BA.debugLine="Help.Margin = 30dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 39;BA.debugLine="Help.AddLabel(File.ReadString(File.DirAssets,\"help1.txt\"),18,Colors.Black)";
mostCurrent._help._addlabel(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"help1.txt"),(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 40;BA.debugLine="Help.Margin = 45dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
 //BA.debugLineNum = 41;BA.debugLine="Help.ApplyScrollHeight";
mostCurrent._help._applyscrollheight();
 };
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event";
 //BA.debugLineNum = 77;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 78;BA.debugLine="StopService(HttpServers)";
anywheresoftware.b4a.keywords.Common.StopService(mostCurrent.activityBA,(Object)(mostCurrent._httpservers.getObject()));
 //BA.debugLineNum = 79;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
com.htsoft.hotspotlib.Hotspotlib _hotspot = null;
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 51;BA.debugLine="If UserClosed = True Then";
if (_userclosed==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 52;BA.debugLine="Dim hotspot As Hotspotlib";
_hotspot = new com.htsoft.hotspotlib.Hotspotlib();
 //BA.debugLineNum = 53;BA.debugLine="hotspot.tat";
_hotspot.tat(mostCurrent.activityBA);
 //BA.debugLineNum = 54;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 56;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _btnhelp7_click() throws Exception{
 //BA.debugLineNum = 71;BA.debugLine="Sub btnhelp7_Click";
 //BA.debugLineNum = 72;BA.debugLine="actHelp1.HelpName = \"win7\"";
mostCurrent._acthelp1._helpname = "win7";
 //BA.debugLineNum = 73;BA.debugLine="StartActivity(actHelp1)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._acthelp1.getObject()));
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
public static String  _btnhelp8_click() throws Exception{
 //BA.debugLineNum = 66;BA.debugLine="Sub btnhelp8_Click";
 //BA.debugLineNum = 67;BA.debugLine="actHelp1.HelpName = \"win8\"";
mostCurrent._acthelp1._helpname = "win8";
 //BA.debugLineNum = 68;BA.debugLine="StartActivity(actHelp1)";
anywheresoftware.b4a.keywords.Common.StartActivity(mostCurrent.activityBA,(Object)(mostCurrent._acthelp1.getObject()));
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public static String  _btnnext_click() throws Exception{
 //BA.debugLineNum = 60;BA.debugLine="Sub btnnext_Click";
 //BA.debugLineNum = 61;BA.debugLine="sv1.Panel.RemoveAllViews";
mostCurrent._sv1.getPanel().RemoveAllViews();
 //BA.debugLineNum = 62;BA.debugLine="HelpName = \"next\"";
_helpname = "next";
 //BA.debugLineNum = 63;BA.debugLine="Activity_Create(False)";
_activity_create(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private sv1 As ScrollView";
mostCurrent._sv1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Dim Help As Help";
mostCurrent._help = new app.transfer.receive.help();
 //BA.debugLineNum = 13;BA.debugLine="Private lbltitle As Label";
mostCurrent._lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim HelpName As String";
_helpname = "";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
