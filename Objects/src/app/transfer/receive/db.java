package app.transfer.receive;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class db extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "app.transfer.receive.db");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "app.transfer.receive.db",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.actupload _actupload = null;
public app.transfer.receive.acthelp1 _acthelp1 = null;
public app.transfer.receive.acthotspot _acthotspot = null;
public app.transfer.receive.actaction _actaction = null;
public app.transfer.receive.mylibrary _mylibrary = null;
public app.transfer.receive.httpservers _httpservers = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Private Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
