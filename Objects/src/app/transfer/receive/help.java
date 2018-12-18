package app.transfer.receive;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class help extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "app.transfer.receive.help");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            if (BA.isShellModeRuntimeCheck(ba)) {
			    ba.raiseEvent2(null, true, "CREATE", true, "app.transfer.receive.help",
                    ba);
                return;
		    }
        }
        ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv1 = null;
public int _top = 0;
public Object _module = null;
public anywheresoftware.b4a.objects.drawable.ColorDrawable _bgcolor = null;
public float _spacelabel = 0f;
public app.transfer.receive.main _main = null;
public app.transfer.receive.actmenu _actmenu = null;
public app.transfer.receive.actupload _actupload = null;
public app.transfer.receive.acthelp1 _acthelp1 = null;
public app.transfer.receive.acthotspot _acthotspot = null;
public app.transfer.receive.actaction _actaction = null;
public app.transfer.receive.mylibrary _mylibrary = null;
public app.transfer.receive.httpservers _httpservers = null;
public String  _addbutton(String _text,anywheresoftware.b4a.objects.drawable.StateListDrawable _statepress,String _event) throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _l1 = null;
 //BA.debugLineNum = 126;BA.debugLine="Sub AddButton(Text As String,StatePress As StateListDrawable,Event As String)";
 //BA.debugLineNum = 127;BA.debugLine="Dim l1 As Button";
_l1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 129;BA.debugLine="l1.Initialize(\"Button\")";
_l1.Initialize(ba,"Button");
 //BA.debugLineNum = 130;BA.debugLine="l1.Text = Text";
_l1.setText((Object)(_text));
 //BA.debugLineNum = 131;BA.debugLine="l1.Tag = Event";
_l1.setTag((Object)(_event));
 //BA.debugLineNum = 132;BA.debugLine="l1.TextSize = 17";
_l1.setTextSize((float) (17));
 //BA.debugLineNum = 133;BA.debugLine="l1.TextColor = Colors.White";
_l1.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 135;BA.debugLine="If StatePress.IsInitialized = False Then";
if (_statepress.IsInitialized()==__c.False) { 
 //BA.debugLineNum = 136;BA.debugLine="l1.Background = getState";
_l1.setBackground((android.graphics.drawable.Drawable)(_getstate().getObject()));
 }else {
 //BA.debugLineNum = 138;BA.debugLine="l1.Background = StatePress";
_l1.setBackground((android.graphics.drawable.Drawable)(_statepress.getObject()));
 };
 //BA.debugLineNum = 141;BA.debugLine="myLibrary.Font(l1)";
_mylibrary._font(ba,(anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_l1.getObject())));
 //BA.debugLineNum = 143;BA.debugLine="sv1.Panel.AddView(l1,0,top,sv1.Width,75)";
_sv1.getPanel().AddView((android.view.View)(_l1.getObject()),(int) (0),_top,_sv1.getWidth(),(int) (75));
 //BA.debugLineNum = 145;BA.debugLine="top = top + 75";
_top = (int) (_top+75);
 //BA.debugLineNum = 146;BA.debugLine="l1.SetVisibleAnimated(850,True)";
_l1.SetVisibleAnimated((int) (850),__c.True);
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return "";
}
public String  _addimage(String _filename,String _watermark,boolean _center) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _im = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _b2 = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _b1 = null;
anywheresoftware.b4a.objects.LabelWrapper _lb1 = null;
 //BA.debugLineNum = 75;BA.debugLine="Sub AddImage(filename As String,Watermark As String,Center As Boolean)";
 //BA.debugLineNum = 76;BA.debugLine="Dim im As Panel";
_im = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Dim b2 As BitmapDrawable";
_b2 = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 78;BA.debugLine="Dim b1 As Bitmap";
_b1 = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 80;BA.debugLine="b2.Initialize(LoadBitmap(File.DirAssets,filename))";
_b2.Initialize((android.graphics.Bitmap)(__c.LoadBitmap(__c.File.getDirAssets(),_filename).getObject()));
 //BA.debugLineNum = 81;BA.debugLine="b1.Initialize(File.DirAssets,filename)";
_b1.Initialize(__c.File.getDirAssets(),_filename);
 //BA.debugLineNum = 82;BA.debugLine="im.Initialize(\"\")";
_im.Initialize(ba,"");
 //BA.debugLineNum = 84;BA.debugLine="If Center = True Then";
if (_center==__c.True) { 
 //BA.debugLineNum = 85;BA.debugLine="b2.Gravity = Gravity.Center";
_b2.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 86;BA.debugLine="im.Background = b2";
_im.setBackground((android.graphics.drawable.Drawable)(_b2.getObject()));
 }else {
 //BA.debugLineNum = 88;BA.debugLine="im.SetBackgroundImage(b1)";
_im.SetBackgroundImage((android.graphics.Bitmap)(_b1.getObject()));
 };
 //BA.debugLineNum = 91;BA.debugLine="If b1.Height > b1.Width Then";
if (_b1.getHeight()>_b1.getWidth()) { 
 //BA.debugLineNum = 92;BA.debugLine="sv1.Panel.AddView(im,1%x,top + 10dip,sv1.Width * b1.Width / b1.Height,sv1.Width)";
_sv1.getPanel().AddView((android.view.View)(_im.getObject()),__c.PerXToCurrent((float) (1),ba),(int) (_top+__c.DipToCurrent((int) (10))),(int) (_sv1.getWidth()*_b1.getWidth()/(double)_b1.getHeight()),_sv1.getWidth());
 //BA.debugLineNum = 93;BA.debugLine="im.Left = (sv1.Width / 2) - (im.Width / 2)";
_im.setLeft((int) ((_sv1.getWidth()/(double)2)-(_im.getWidth()/(double)2)));
 //BA.debugLineNum = 94;BA.debugLine="top = top + sv1.Width + 27dip";
_top = (int) (_top+_sv1.getWidth()+__c.DipToCurrent((int) (27)));
 }else {
 //BA.debugLineNum = 96;BA.debugLine="sv1.Panel.AddView(im,1%x,top + 10dip,sv1.Width,sv1.Width * b1.Height / b1.Width)";
_sv1.getPanel().AddView((android.view.View)(_im.getObject()),__c.PerXToCurrent((float) (1),ba),(int) (_top+__c.DipToCurrent((int) (10))),_sv1.getWidth(),(int) (_sv1.getWidth()*_b1.getHeight()/(double)_b1.getWidth()));
 //BA.debugLineNum = 97;BA.debugLine="im.Left = (sv1.Width / 2) - (im.Width / 2)";
_im.setLeft((int) ((_sv1.getWidth()/(double)2)-(_im.getWidth()/(double)2)));
 //BA.debugLineNum = 98;BA.debugLine="top = top + sv1.Width * b1.Height / b1.Width + 27dip";
_top = (int) (_top+_sv1.getWidth()*_b1.getHeight()/(double)_b1.getWidth()+__c.DipToCurrent((int) (27)));
 };
 //BA.debugLineNum = 101;BA.debugLine="im.Visible = False";
_im.setVisible(__c.False);
 //BA.debugLineNum = 102;BA.debugLine="im.SetVisibleAnimated(1200,True)";
_im.SetVisibleAnimated((int) (1200),__c.True);
 //BA.debugLineNum = 105;BA.debugLine="If Watermark <> Null Then";
if (_watermark!= null) { 
 //BA.debugLineNum = 106;BA.debugLine="Dim lb1 As Label";
_lb1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 107;BA.debugLine="lb1.Initialize(\"\")";
_lb1.Initialize(ba,"");
 //BA.debugLineNum = 108;BA.debugLine="lb1.TextSize = 25";
_lb1.setTextSize((float) (25));
 //BA.debugLineNum = 109;BA.debugLine="lb1.TextColor = Colors.Black";
_lb1.setTextColor(__c.Colors.Black);
 //BA.debugLineNum = 110;BA.debugLine="lb1.Text = Watermark";
_lb1.setText((Object)(_watermark));
 //BA.debugLineNum = 111;BA.debugLine="lb1.Gravity = Gravity.Center";
_lb1.setGravity(__c.Gravity.CENTER);
 //BA.debugLineNum = 112;BA.debugLine="lb1.Color = Colors.Transparent";
_lb1.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 113;BA.debugLine="im.AddView(lb1,0,(im.Height / 2),im.Width,50dip)";
_im.AddView((android.view.View)(_lb1.getObject()),(int) (0),(int) ((_im.getHeight()/(double)2)),_im.getWidth(),__c.DipToCurrent((int) (50)));
 };
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public String  _addlabel(String _text,int _fontsize,int _fontcolor) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _l1 = null;
int _height = 0;
 //BA.debugLineNum = 27;BA.debugLine="Sub AddLabel(Text As String,FontSize As Int,FontColor As Int)";
 //BA.debugLineNum = 28;BA.debugLine="Dim l1 As Label";
_l1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim height As Int";
_height = 0;
 //BA.debugLineNum = 31;BA.debugLine="l1.Initialize(\"\")";
_l1.Initialize(ba,"");
 //BA.debugLineNum = 32;BA.debugLine="l1.Text = Text";
_l1.setText((Object)(_text));
 //BA.debugLineNum = 33;BA.debugLine="l1.TextSize = FontSize";
_l1.setTextSize((float) (_fontsize));
 //BA.debugLineNum = 34;BA.debugLine="l1.TextColor = FontColor";
_l1.setTextColor(_fontcolor);
 //BA.debugLineNum = 35;BA.debugLine="myLibrary.Font(l1)";
_mylibrary._font(ba,_l1);
 //BA.debugLineNum = 37;BA.debugLine="sv1.Panel.AddView(l1,0,top,sv1.Width,10dip)";
_sv1.getPanel().AddView((android.view.View)(_l1.getObject()),(int) (0),_top,_sv1.getWidth(),__c.DipToCurrent((int) (10)));
 //BA.debugLineNum = 38;BA.debugLine="l1.Visible = False";
_l1.setVisible(__c.False);
 //BA.debugLineNum = 39;BA.debugLine="l1.SetVisibleAnimated(1500,True)";
_l1.SetVisibleAnimated((int) (1500),__c.True);
 //BA.debugLineNum = 41;BA.debugLine="LabelSpace(l1)";
_labelspace((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_l1.getObject())));
 //BA.debugLineNum = 43;BA.debugLine="height = myLibrary.su.MeasureMultilineTextHeight(l1,Text)";
_height = _mylibrary._su.MeasureMultilineTextHeight((android.widget.TextView)(_l1.getObject()),_text);
 //BA.debugLineNum = 44;BA.debugLine="l1.Gravity = Bit.AND(Gravity.top,Gravity.RIGHT)";
_l1.setGravity(__c.Bit.And(__c.Gravity.TOP,__c.Gravity.RIGHT));
 //BA.debugLineNum = 45;BA.debugLine="l1.Color = Colors.Transparent";
_l1.setColor(__c.Colors.Transparent);
 //BA.debugLineNum = 46;BA.debugLine="l1.height = height * SpaceLabel";
_l1.setHeight((int) (_height*_spacelabel));
 //BA.debugLineNum = 47;BA.debugLine="top = top + height + 27dip";
_top = (int) (_top+_height+__c.DipToCurrent((int) (27)));
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public String  _addlabelbutton(String _text,String _event) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _l1 = null;
int _height = 0;
 //BA.debugLineNum = 51;BA.debugLine="Sub AddLabelButton(Text As String,Event As String)";
 //BA.debugLineNum = 52;BA.debugLine="Dim l1 As Label";
_l1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Dim height As Int";
_height = 0;
 //BA.debugLineNum = 55;BA.debugLine="l1.Initialize(\"LabelButton\")";
_l1.Initialize(ba,"LabelButton");
 //BA.debugLineNum = 56;BA.debugLine="l1.Text = Text";
_l1.setText((Object)(_text));
 //BA.debugLineNum = 57;BA.debugLine="l1.Tag = Event";
_l1.setTag((Object)(_event));
 //BA.debugLineNum = 58;BA.debugLine="l1.TextSize = 19";
_l1.setTextSize((float) (19));
 //BA.debugLineNum = 59;BA.debugLine="l1.TextColor = Colors.White";
_l1.setTextColor(__c.Colors.White);
 //BA.debugLineNum = 60;BA.debugLine="l1.Color = Colors.RGB(255, 143, 0)";
_l1.setColor(__c.Colors.RGB((int) (255),(int) (143),(int) (0)));
 //BA.debugLineNum = 61;BA.debugLine="myLibrary.Font(l1)";
_mylibrary._font(ba,_l1);
 //BA.debugLineNum = 63;BA.debugLine="sv1.Panel.AddView(l1,0,top,sv1.Width,10dip)";
_sv1.getPanel().AddView((android.view.View)(_l1.getObject()),(int) (0),_top,_sv1.getWidth(),__c.DipToCurrent((int) (10)));
 //BA.debugLineNum = 64;BA.debugLine="l1.Visible = False";
_l1.setVisible(__c.False);
 //BA.debugLineNum = 65;BA.debugLine="l1.SetVisibleAnimated(1500,True)";
_l1.SetVisibleAnimated((int) (1500),__c.True);
 //BA.debugLineNum = 67;BA.debugLine="LabelSpace(l1)";
_labelspace((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(_l1.getObject())));
 //BA.debugLineNum = 69;BA.debugLine="height = myLibrary.su.MeasureMultilineTextHeight(l1,Text)";
_height = _mylibrary._su.MeasureMultilineTextHeight((android.widget.TextView)(_l1.getObject()),_text);
 //BA.debugLineNum = 70;BA.debugLine="l1.Gravity = Bit.OR(Gravity.CENTER_HORIZONTAL,Gravity.CENTER_VERTICAL)";
_l1.setGravity(__c.Bit.Or(__c.Gravity.CENTER_HORIZONTAL,__c.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 71;BA.debugLine="l1.height = height * SpaceLabel + 10dip";
_l1.setHeight((int) (_height*_spacelabel+__c.DipToCurrent((int) (10))));
 //BA.debugLineNum = 72;BA.debugLine="top = top + height + 27dip";
_top = (int) (_top+_height+__c.DipToCurrent((int) (27)));
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return "";
}
public String  _addline() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _l1 = null;
 //BA.debugLineNum = 118;BA.debugLine="Sub AddLine";
 //BA.debugLineNum = 119;BA.debugLine="Dim l1 As Label";
_l1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 120;BA.debugLine="l1.Initialize(\"\")";
_l1.Initialize(ba,"");
 //BA.debugLineNum = 121;BA.debugLine="l1.Color = Colors.ARGB(70,0,0,0)";
_l1.setColor(__c.Colors.ARGB((int) (70),(int) (0),(int) (0),(int) (0)));
 //BA.debugLineNum = 122;BA.debugLine="sv1.Panel.AddView(l1,3dip,top,sv1.Width - 3dip,1dip)";
_sv1.getPanel().AddView((android.view.View)(_l1.getObject()),__c.DipToCurrent((int) (3)),_top,(int) (_sv1.getWidth()-__c.DipToCurrent((int) (3))),__c.DipToCurrent((int) (1)));
 //BA.debugLineNum = 123;BA.debugLine="top = top + 10dip";
_top = (int) (_top+__c.DipToCurrent((int) (10)));
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public String  _applyscrollheight() throws Exception{
 //BA.debugLineNum = 185;BA.debugLine="Public Sub ApplyScrollHeight";
 //BA.debugLineNum = 186;BA.debugLine="sv1.Panel.Height = top";
_sv1.getPanel().setHeight(_top);
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return "";
}
public String  _button_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b1 = null;
String _event = "";
 //BA.debugLineNum = 150;BA.debugLine="Sub Button_Click";
 //BA.debugLineNum = 151;BA.debugLine="Dim b1 As Button";
_b1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 152;BA.debugLine="b1 = Sender";
_b1.setObject((android.widget.Button)(__c.Sender(ba)));
 //BA.debugLineNum = 154;BA.debugLine="Dim event As String";
_event = "";
 //BA.debugLineNum = 155;BA.debugLine="event = b1.Tag";
_event = BA.ObjectToString(_b1.getTag());
 //BA.debugLineNum = 157;BA.debugLine="If SubExists(Module,event & \"_Click\") Then";
if (__c.SubExists(ba,_module,_event+"_Click")) { 
 //BA.debugLineNum = 158;BA.debugLine="CallSub(Module,event & \"_Click\")";
__c.CallSubNew(ba,_module,_event+"_Click");
 };
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Private Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private sv1 As ScrollView";
_sv1 = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 4;BA.debugLine="Private top As Int";
_top = 0;
 //BA.debugLineNum = 5;BA.debugLine="Private Module As Object";
_module = new Object();
 //BA.debugLineNum = 6;BA.debugLine="Private bgColor As ColorDrawable";
_bgcolor = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 7;BA.debugLine="Private SpaceLabel As Float = 1.3";
_spacelabel = (float) (1.3);
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.drawable.StateListDrawable  _getstate() throws Exception{
anywheresoftware.b4a.objects.drawable.StateListDrawable _sd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _press = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _active = null;
 //BA.debugLineNum = 174;BA.debugLine="Private Sub getState As StateListDrawable";
 //BA.debugLineNum = 175;BA.debugLine="Dim sd As StateListDrawable";
_sd = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 176;BA.debugLine="Dim press,active As ColorDrawable";
_press = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
_active = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 177;BA.debugLine="press.Initialize(Colors.RGB(251, 187, 104),0)";
_press.Initialize(__c.Colors.RGB((int) (251),(int) (187),(int) (104)),(int) (0));
 //BA.debugLineNum = 178;BA.debugLine="active.Initialize(Colors.RGB(255, 143, 0),0)";
_active.Initialize(__c.Colors.RGB((int) (255),(int) (143),(int) (0)),(int) (0));
 //BA.debugLineNum = 179;BA.debugLine="sd.Initialize";
_sd.Initialize();
 //BA.debugLineNum = 180;BA.debugLine="sd.AddState(sd.State_Pressed,press)";
_sd.AddState(_sd.State_Pressed,(android.graphics.drawable.Drawable)(_press.getObject()));
 //BA.debugLineNum = 181;BA.debugLine="sd.AddCatchAllState(active)";
_sd.AddCatchAllState((android.graphics.drawable.Drawable)(_active.getObject()));
 //BA.debugLineNum = 182;BA.debugLine="Return sd";
if (true) return _sd;
 //BA.debugLineNum = 183;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1,Object _smodule) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 10;BA.debugLine="Public Sub Initialize(ScrollView1 As ScrollView,sModule As Object)";
 //BA.debugLineNum = 11;BA.debugLine="sv1 = ScrollView1";
_sv1 = _scrollview1;
 //BA.debugLineNum = 12;BA.debugLine="Module = sModule";
_module = _smodule;
 //BA.debugLineNum = 13;BA.debugLine="bgColor.Initialize(Colors.White,0)";
_bgcolor.Initialize(__c.Colors.White,(int) (0));
 //BA.debugLineNum = 14;BA.debugLine="sv1.Panel.Background = bgColor";
_sv1.getPanel().setBackground((android.graphics.drawable.Drawable)(_bgcolor.getObject()));
 //BA.debugLineNum = 15;BA.debugLine="sv1.Background = bgColor";
_sv1.setBackground((android.graphics.drawable.Drawable)(_bgcolor.getObject()));
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public String  _labelbutton_click() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _b1 = null;
String _event = "";
 //BA.debugLineNum = 162;BA.debugLine="Sub LabelButton_Click";
 //BA.debugLineNum = 163;BA.debugLine="Dim b1 As Label";
_b1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 164;BA.debugLine="b1 = Sender";
_b1.setObject((android.widget.TextView)(__c.Sender(ba)));
 //BA.debugLineNum = 166;BA.debugLine="Dim event As String";
_event = "";
 //BA.debugLineNum = 167;BA.debugLine="event = b1.Tag";
_event = BA.ObjectToString(_b1.getTag());
 //BA.debugLineNum = 169;BA.debugLine="If SubExists(Module,event & \"_Click\") Then";
if (__c.SubExists(ba,_module,_event+"_Click")) { 
 //BA.debugLineNum = 170;BA.debugLine="CallSub(Module,event & \"_Click\")";
__c.CallSubNew(ba,_module,_event+"_Click");
 };
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public String  _labelspace(anywheresoftware.b4a.objects.ConcreteViewWrapper _view1) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _obj1 = null;
 //BA.debugLineNum = 189;BA.debugLine="Sub LabelSpace(view1 As View)";
 //BA.debugLineNum = 190;BA.debugLine="Dim Obj1 As Reflector";
_obj1 = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 191;BA.debugLine="Obj1.Target = view1";
_obj1.Target = (Object)(_view1.getObject());
 //BA.debugLineNum = 192;BA.debugLine="Obj1.RunMethod3(\"setLineSpacing\", 1, \"java.lang.float\", SpaceLabel, \"java.lang.float\")";
_obj1.RunMethod3("setLineSpacing",BA.NumberToString(1),"java.lang.float",BA.NumberToString(_spacelabel),"java.lang.float");
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public String  _setmargin(int _value) throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Public Sub setMargin(Value As Int)";
 //BA.debugLineNum = 20;BA.debugLine="top = top + Value";
_top = (int) (_top+_value);
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public String  _setspacebetweenline(float _value) throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Public Sub setSpaceBetweenLine(Value As Float)";
 //BA.debugLineNum = 24;BA.debugLine="SpaceLabel = Value";
_spacelabel = _value;
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
