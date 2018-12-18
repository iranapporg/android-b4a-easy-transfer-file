package app.transfer.receive.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_frmconnected{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 1;BA.debugLine="pnlhelp.HorizontalCenter = 50%x"[frmconnected/General script]
views.get("pnlhelp").vw.setLeft((int)((50d / 100 * width) - (views.get("pnlhelp").vw.getWidth() / 2)));
//BA.debugLineNum = 2;BA.debugLine="pnlhelp.VerticalCenter = 55%y"[frmconnected/General script]
views.get("pnlhelp").vw.setTop((int)((55d / 100 * height) - (views.get("pnlhelp").vw.getHeight() / 2)));
//BA.debugLineNum = 3;BA.debugLine="AutoScaleRate(0)"[frmconnected/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0d);
//BA.debugLineNum = 4;BA.debugLine="AutoScaleAll"[frmconnected/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}