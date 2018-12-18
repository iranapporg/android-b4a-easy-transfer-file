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

public class acthelp1 extends Activity implements B4AActivity{
	public static acthelp1 mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "app.transfer.receive", "app.transfer.receive.acthelp1");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (acthelp1).");
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
		activityBA = new BA(this, layout, processBA, "app.transfer.receive", "app.transfer.receive.acthelp1");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "app.transfer.receive.acthelp1", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (acthelp1) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (acthelp1) Resume **");
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
		return acthelp1.class;
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
        BA.LogInfo("** Activity (acthelp1) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            BA.LogInfo("** Activity (acthelp1) Resume **");
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
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
public app.transfer.receive.help _help = null;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.actupload _actupload = null;
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
 //BA.debugLineNum = 17;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 18;BA.debugLine="Activity.LoadLayout(\"frmhelp\")";
mostCurrent._activity.LoadLayout("frmhelp",mostCurrent.activityBA);
 //BA.debugLineNum = 19;BA.debugLine="Help.Initialize(ScrollView1,Me)";
mostCurrent._help._initialize(mostCurrent.activityBA,mostCurrent._scrollview1,acthelp1.getObject());
 //BA.debugLineNum = 21;BA.debugLine="Activity.AddMenuItem(\"درباره ما\",\"btnAbout\")";
mostCurrent._activity.AddMenuItem("درباره ما","btnAbout");
 //BA.debugLineNum = 23;BA.debugLine="If HelpName = \"\" Then";
if ((_helpname).equals("")) { 
 //BA.debugLineNum = 24;BA.debugLine="lbltitle.Text = \"راهنمای استفاده از برنامه\"";
mostCurrent._lbltitle.setText((Object)("راهنمای استفاده از برنامه"));
 //BA.debugLineNum = 25;BA.debugLine="Help.AddLabel(\"برنامه با موفقیت اجرا شد. برای استفاده از برنامه، باید از این صفحه خارج شوید و داخل گالری گوشی خود شوید. با نگهداشتن انگشت، عکسهایی که میخواهید منتقل شود را انتخاب کنید\",18,Colors.Black)";
mostCurrent._help._addlabel("برنامه با موفقیت اجرا شد. برای استفاده از برنامه، باید از این صفحه خارج شوید و داخل گالری گوشی خود شوید. با نگهداشتن انگشت، عکسهایی که میخواهید منتقل شود را انتخاب کنید",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 26;BA.debugLine="Help.AddImage(\"w2.jpg\",\"\",False)";
mostCurrent._help._addimage("w2.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 27;BA.debugLine="Help.AddLabel(\"همانطوری که در تصویر بالا با شماره دو امده است از قسمت نوار بالای گوشیتون روی آیکن اشتراک کلیک کنید. ممکن است جای قرار گرفتن این آیکن در گوشی های مختلف متفاوت باشد اما از روی آیکن آن به راحتی میتوانید آنرا پیدا کنید. آیکن اشتراک گذاری سه نقطه است که به هم وصل شده است.\",18,Colors.Black)";
mostCurrent._help._addlabel("همانطوری که در تصویر بالا با شماره دو امده است از قسمت نوار بالای گوشیتون روی آیکن اشتراک کلیک کنید. ممکن است جای قرار گرفتن این آیکن در گوشی های مختلف متفاوت باشد اما از روی آیکن آن به راحتی میتوانید آنرا پیدا کنید. آیکن اشتراک گذاری سه نقطه است که به هم وصل شده است.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 28;BA.debugLine="Help.Margin = 13dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13)));
 //BA.debugLineNum = 29;BA.debugLine="Help.AddImage(\"w3.jpg\",\"\",False)";
mostCurrent._help._addimage("w3.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 30;BA.debugLine="Help.AddLabel(\"یه لیست ظاهر میشه. باید بتونید اسم و آیکن برنامه انتقال آسان را پیدا کنید. اگر در لیست نبود بر روی گزینه See all کلیک کنید تا لیست کامل بیاید و بتوانید روی ایکن انتقال آسان کلیک کنید\",18,Colors.Black)";
mostCurrent._help._addlabel("یه لیست ظاهر میشه. باید بتونید اسم و آیکن برنامه انتقال آسان را پیدا کنید. اگر در لیست نبود بر روی گزینه See all کلیک کنید تا لیست کامل بیاید و بتوانید روی ایکن انتقال آسان کلیک کنید",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 31;BA.debugLine="Help.AddLabel(\"با انجام این کار دوباره به محیط برنامه برخواهید گشت و برای بقیه مراحل انتقال راهنمایی خواهید شد.\",18,Colors.Black)";
mostCurrent._help._addlabel("با انجام این کار دوباره به محیط برنامه برخواهید گشت و برای بقیه مراحل انتقال راهنمایی خواهید شد.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 32;BA.debugLine="Help.AddImage(\"w4.jpg\",\"\",False)";
mostCurrent._help._addimage("w4.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 33;BA.debugLine="Help.AddLabel(\"توجه بفرمایید که لازم نیست حتما وارد گالری بشوید تا بتوانید از برنامه استفاده کنید. گزینه اشتراک در اکثر برنامه هست و مثلا همانطور که در تصویر بالا آمده است میتوانید یک صوت یا عکس را در برنامه whatsapp انتخاب کنید و به کامپیوتر بفرستید\",18,Colors.Black)";
mostCurrent._help._addlabel("توجه بفرمایید که لازم نیست حتما وارد گالری بشوید تا بتوانید از برنامه استفاده کنید. گزینه اشتراک در اکثر برنامه هست و مثلا همانطور که در تصویر بالا آمده است میتوانید یک صوت یا عکس را در برنامه whatsapp انتخاب کنید و به کامپیوتر بفرستید",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 34;BA.debugLine="Help.Margin = 13dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13)));
 //BA.debugLineNum = 35;BA.debugLine="Help.AddLabelButton(\"باشه\",\"btnOK\")";
mostCurrent._help._addlabelbutton("باشه","btnOK");
 }else if((_helpname).equals("different")) { 
 //BA.debugLineNum = 38;BA.debugLine="lbltitle.Text = \"تفاوت روش های انتقال\"";
mostCurrent._lbltitle.setText((Object)("تفاوت روش های انتقال"));
 //BA.debugLineNum = 39;BA.debugLine="Help.AddLabel(\"شما با دو روش میتونید فایلها رو منتقل کنید که معایب و مزایای هر کدام به شرح زیر است:\",18,Colors.Black)";
mostCurrent._help._addlabel("شما با دو روش میتونید فایلها رو منتقل کنید که معایب و مزایای هر کدام به شرح زیر است:",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 40;BA.debugLine="Help.AddImage(\"hotspot.jpg\",\"\",True)";
mostCurrent._help._addimage("hotspot.jpg","",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 41;BA.debugLine="Help.AddLabel(\"هات اسپات:\" & CRLF & _ 			\"- این روش باطری بیشتری مصرف میکند.\" & CRLF & _ 			\"- سرعت بسیار بیشتری در انتقال فایل دارد. در تستهای ما بین 3 تا 4 مگ در ثانیه توسط این روش منتقل میشود. ممکن است در گوشی های قدیمی این سرعت کاهش پیدا کند.\" & CRLF & _ 			\"- مراحل این روش بیشتر است و باید مودم وایرلس کامپیوتر و یا گوشی گیرنده به گوشی شما وصل شد. در ضمن کار راهنمایی خواهید شد.\",18,Colors.Black)";
mostCurrent._help._addlabel("هات اسپات:"+anywheresoftware.b4a.keywords.Common.CRLF+"- این روش باطری بیشتری مصرف میکند."+anywheresoftware.b4a.keywords.Common.CRLF+"- سرعت بسیار بیشتری در انتقال فایل دارد. در تستهای ما بین 3 تا 4 مگ در ثانیه توسط این روش منتقل میشود. ممکن است در گوشی های قدیمی این سرعت کاهش پیدا کند."+anywheresoftware.b4a.keywords.Common.CRLF+"- مراحل این روش بیشتر است و باید مودم وایرلس کامپیوتر و یا گوشی گیرنده به گوشی شما وصل شد. در ضمن کار راهنمایی خواهید شد.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 45;BA.debugLine="Help.AddLine";
mostCurrent._help._addline();
 //BA.debugLineNum = 46;BA.debugLine="Help.AddImage(\"wireless.jpg\",\"\",True)";
mostCurrent._help._addimage("wireless.jpg","",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 47;BA.debugLine="Help.AddLabel(\"مودم وایرلس:\" & CRLF & _ 			\"-سرعت کمتری نسبت به روش قبل دارد اما باطری کمتری نیز استفاده میکند.\" & CRLF & _ 			\"-از پیش باید هم گوشی و هم کامپیوتر به یه مودم وایرلس واحد وصل باشه. \" & CRLF & _ 			\"-توجه داشته باشید نیازی به اینترنت نیست و اگه مودم وایرلس به اینترنت هم وصل نباشه میتونید از این روش استفاده کنید.\",18,Colors.Black)";
mostCurrent._help._addlabel("مودم وایرلس:"+anywheresoftware.b4a.keywords.Common.CRLF+"-سرعت کمتری نسبت به روش قبل دارد اما باطری کمتری نیز استفاده میکند."+anywheresoftware.b4a.keywords.Common.CRLF+"-از پیش باید هم گوشی و هم کامپیوتر به یه مودم وایرلس واحد وصل باشه. "+anywheresoftware.b4a.keywords.Common.CRLF+"-توجه داشته باشید نیازی به اینترنت نیست و اگه مودم وایرلس به اینترنت هم وصل نباشه میتونید از این روش استفاده کنید.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 51;BA.debugLine="Help.Margin = 15dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)));
 //BA.debugLineNum = 52;BA.debugLine="Help.AddLabelButton(\"باشه\",\"btnOK\")";
mostCurrent._help._addlabelbutton("باشه","btnOK");
 }else if((_helpname).equals("win8")) { 
 //BA.debugLineNum = 55;BA.debugLine="lbltitle.Text = \"اتصال به وایرلس برنامه\"";
mostCurrent._lbltitle.setText((Object)("اتصال به وایرلس برنامه"));
 //BA.debugLineNum = 56;BA.debugLine="Help.AddLabel(\"برای قسمتی که در عکس زیر مشخص شده است کلیک کنید:\",18,Colors.Black)";
mostCurrent._help._addlabel("برای قسمتی که در عکس زیر مشخص شده است کلیک کنید:",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 57;BA.debugLine="Help.AddImage(\"win8-01.jpg\",\"\",False)";
mostCurrent._help._addimage("win8-01.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 58;BA.debugLine="Help.AddLabel(\"اگر همانند تصویر زیر بخش مشخص شده در حالت off قرار داشت، روی آن کلیک کنید تا به حالت On تغییر پیدا کند. بدین ترتیب وایرلس دستگاه شما روشن میشود. اگر بصورت پیش فرض بر روی on قرار داشت روی آن کلیک نکنید:\",18,Colors.Black)";
mostCurrent._help._addlabel("اگر همانند تصویر زیر بخش مشخص شده در حالت off قرار داشت، روی آن کلیک کنید تا به حالت On تغییر پیدا کند. بدین ترتیب وایرلس دستگاه شما روشن میشود. اگر بصورت پیش فرض بر روی on قرار داشت روی آن کلیک نکنید:",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 59;BA.debugLine="Help.AddImage(\"win8-02.jpg\",\"\",False)";
mostCurrent._help._addimage("win8-02.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 60;BA.debugLine="Help.AddLabel(\"مانند تصویر زیر تیک قسمت Connect automatically رو فعال کنید و روی دکمه connect کلیک کنید\",18,Colors.Black)";
mostCurrent._help._addlabel("مانند تصویر زیر تیک قسمت Connect automatically رو فعال کنید و روی دکمه connect کلیک کنید",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 61;BA.debugLine="Help.AddImage(\"win8-03.jpg\",\"\",False)";
mostCurrent._help._addimage("win8-03.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 62;BA.debugLine="Help.AddLabel(\"در نهایت باید مثل تصویر زیر به وایرلس وصل شده باشید و گزینه Connected در زیر EasyTransfer نوشته باشه\",18,Colors.Black)";
mostCurrent._help._addlabel("در نهایت باید مثل تصویر زیر به وایرلس وصل شده باشید و گزینه Connected در زیر EasyTransfer نوشته باشه",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 63;BA.debugLine="Help.AddImage(\"win8-04.jpg\",\"\",False)";
mostCurrent._help._addimage("win8-04.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 64;BA.debugLine="Help.Margin = 15dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)));
 //BA.debugLineNum = 65;BA.debugLine="Help.AddLabelButton(\"باشه\",\"btnOK\")";
mostCurrent._help._addlabelbutton("باشه","btnOK");
 }else if((_helpname).equals("win7")) { 
 //BA.debugLineNum = 68;BA.debugLine="lbltitle.Text = \"اتصال به وایرلس برنامه\"";
mostCurrent._lbltitle.setText((Object)("اتصال به وایرلس برنامه"));
 //BA.debugLineNum = 69;BA.debugLine="Help.AddLabel(\"از نوار پایین ویندوزتون روی قسمتی که در عکس زیر مشخص شده کلیک کنید:\",18,Colors.Black)";
mostCurrent._help._addlabel("از نوار پایین ویندوزتون روی قسمتی که در عکس زیر مشخص شده کلیک کنید:",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 70;BA.debugLine="Help.AddImage(\"win7-01.jpg\",\"\",False)";
mostCurrent._help._addimage("win7-01.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 71;BA.debugLine="Help.AddLabel(\"باید یه گزینه مثل تصویر زیر اضافه شده باشه. توی تصویر با فلش قرمز مشخص شده است. اگر آنرا نمیبینید روی گزینه Refresh کلیک کنید.\",18,Colors.Black)";
mostCurrent._help._addlabel("باید یه گزینه مثل تصویر زیر اضافه شده باشه. توی تصویر با فلش قرمز مشخص شده است. اگر آنرا نمیبینید روی گزینه Refresh کلیک کنید.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 72;BA.debugLine="Help.AddImage(\"win7-02.jpg\",\"\",False)";
mostCurrent._help._addimage("win7-02.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 73;BA.debugLine="Help.AddLabel(\"مانند تصویر، تیک قسمت Connect automatically رو بنزید و بعد دکمه Connect رو بزنید تا به شبکه برنامه وصل بشید.\",18,Colors.Black)";
mostCurrent._help._addlabel("مانند تصویر، تیک قسمت Connect automatically رو بنزید و بعد دکمه Connect رو بزنید تا به شبکه برنامه وصل بشید.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 74;BA.debugLine="Help.AddImage(\"win7-03.jpg\",\"\",False)";
mostCurrent._help._addimage("win7-03.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 75;BA.debugLine="Help.AddLabel(\"بعد از کمی صبر باید مثل شکل زیر، ایکن وایرلس کامپیوتر شما به حالت متصل درآمده باشد.\",18,Colors.Black)";
mostCurrent._help._addlabel("بعد از کمی صبر باید مثل شکل زیر، ایکن وایرلس کامپیوتر شما به حالت متصل درآمده باشد.",(int) (18),anywheresoftware.b4a.keywords.Common.Colors.Black);
 //BA.debugLineNum = 76;BA.debugLine="Help.AddImage(\"win7-04.jpg\",\"\",False)";
mostCurrent._help._addimage("win7-04.jpg","",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 77;BA.debugLine="Help.Margin = 15dip";
mostCurrent._help._setmargin(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)));
 //BA.debugLineNum = 78;BA.debugLine="Help.AddLabelButton(\"باشه\",\"btnOK\")";
mostCurrent._help._addlabelbutton("باشه","btnOK");
 };
 //BA.debugLineNum = 81;BA.debugLine="Help.ApplyScrollHeight";
mostCurrent._help._applyscrollheight();
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event";
 //BA.debugLineNum = 109;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 110;BA.debugLine="ScrollView1.ScrollPosition = ScrollView1.Panel.Height";
mostCurrent._scrollview1.setScrollPosition(mostCurrent._scrollview1.getPanel().getHeight());
 //BA.debugLineNum = 111;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 112;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 }else if(_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_MENU) { 
 //BA.debugLineNum = 114;BA.debugLine="Activity.OpenMenu";
mostCurrent._activity.OpenMenu();
 //BA.debugLineNum = 115;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 116;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 //BA.debugLineNum = 117;BA.debugLine="DoEvents";
anywheresoftware.b4a.keywords.Common.DoEvents();
 };
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 104;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 100;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 102;BA.debugLine="End Sub";
return "";
}
public static String  _btnabout_click() throws Exception{
String _a = "";
 //BA.debugLineNum = 84;BA.debugLine="Sub btnAbout_Click";
 //BA.debugLineNum = 85;BA.debugLine="Dim a As String";
_a = "";
 //BA.debugLineNum = 86;BA.debugLine="a = \"گروه برنامه نویسی کویک لرن به تازگی شکل گرفته است\" & CRLF & _ \"و قصد دارد نرم افزارهای با کیفیتی را عرضه کند و در \" & CRLF & _ \"این راستا پیشنهادات و انتقادات شما هموطنهای عزیز بسیار راهگشا است.\" & CRLF & _ \"اعضای گروه:\" & CRLF & _ \"امید آقاخانی (iranapp.org)\" & CRLF & _ \"محمد معلی(quicklearn.ir)\" & CRLF & _ \"با تشکر از اقای حسن الماسی (hasanalmasi.ir) بخاطر مشاوره های گرافیکیشون و همچنین تشکر ویژه از پشتیبانی کاربران خوب و صمیمی تالار سایت کویک لرن(quicklearn.ir/forums)\"";
_a = "گروه برنامه نویسی کویک لرن به تازگی شکل گرفته است"+anywheresoftware.b4a.keywords.Common.CRLF+"و قصد دارد نرم افزارهای با کیفیتی را عرضه کند و در "+anywheresoftware.b4a.keywords.Common.CRLF+"این راستا پیشنهادات و انتقادات شما هموطنهای عزیز بسیار راهگشا است."+anywheresoftware.b4a.keywords.Common.CRLF+"اعضای گروه:"+anywheresoftware.b4a.keywords.Common.CRLF+"امید آقاخانی (iranapp.org)"+anywheresoftware.b4a.keywords.Common.CRLF+"محمد معلی(quicklearn.ir)"+anywheresoftware.b4a.keywords.Common.CRLF+"با تشکر از اقای حسن الماسی (hasanalmasi.ir) بخاطر مشاوره های گرافیکیشون و همچنین تشکر ویژه از پشتیبانی کاربران خوب و صمیمی تالار سایت کویک لرن(quicklearn.ir/forums)";
 //BA.debugLineNum = 93;BA.debugLine="Msgbox(a,\"درباره ما\")";
anywheresoftware.b4a.keywords.Common.Msgbox(_a,"درباره ما",mostCurrent.activityBA);
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
public static String  _btnok_click() throws Exception{
 //BA.debugLineNum = 96;BA.debugLine="Sub btnOK_Click";
 //BA.debugLineNum = 97;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 98;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private ScrollView1 As ScrollView";
mostCurrent._scrollview1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private lbltitle As Label";
mostCurrent._lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private Help As Help";
mostCurrent._help = new app.transfer.receive.help();
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
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
