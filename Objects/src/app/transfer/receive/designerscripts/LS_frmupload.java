package app.transfer.receive.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_frmupload{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0d);
views.get("pnlfiles").vw.setLeft((int)((50d / 100 * width) - (views.get("pnlfiles").vw.getWidth() / 2)));
//BA.debugLineNum = 5;BA.debugLine="pnl1.HorizontalCenter = 50%x"[frmupload/General script]
views.get("pnl1").vw.setLeft((int)((50d / 100 * width) - (views.get("pnl1").vw.getWidth() / 2)));
//BA.debugLineNum = 6;BA.debugLine="pnlicon.VerticalCenter = 70%y"[frmupload/General script]
views.get("pnlicon").vw.setTop((int)((70d / 100 * height) - (views.get("pnlicon").vw.getHeight() / 2)));
//BA.debugLineNum = 7;BA.debugLine="pnlicon.HorizontalCenter = 50%x"[frmupload/General script]
views.get("pnlicon").vw.setLeft((int)((50d / 100 * width) - (views.get("pnlicon").vw.getWidth() / 2)));

}
}