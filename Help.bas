Type=Class
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Class module
Private Sub Class_Globals
 Private sv1 As ScrollView	
 Private top As Int
 Private Module As Object
 Private bgColor As ColorDrawable
 Private SpaceLabel As Float = 1.3
End Sub

Public Sub Initialize(ScrollView1 As ScrollView,sModule As Object)
 sv1 = ScrollView1
 Module = sModule
 bgColor.Initialize(Colors.White,0)
 sv1.Panel.Background = bgColor
 sv1.Background = bgColor
' margin_top = margin
End Sub

Public Sub setMargin(Value As Int)
 top = top + Value
End Sub

Public Sub setSpaceBetweenLine(Value As Float)
SpaceLabel = Value
End Sub

Sub AddLabel(Text As String,FontSize As Int,FontColor As Int)
Dim l1 As Label
Dim height As Int
 
 l1.Initialize("")
 l1.Text = Text
 l1.TextSize = FontSize
 l1.TextColor = FontColor
 myLibrary.Font(l1)
 
 sv1.Panel.AddView(l1,0,top,sv1.Width,10dip)
 l1.Visible = False
 l1.SetVisibleAnimated(1500,True)
 
 LabelSpace(l1)
 
 height = myLibrary.su.MeasureMultilineTextHeight(l1,Text)
 l1.Gravity = Bit.AND(Gravity.top,Gravity.RIGHT)
 l1.Color = Colors.Transparent
 l1.height = height * SpaceLabel
 top = top + height + 27dip
 
End Sub

Sub AddLabelButton(Text As String,Event As String)
Dim l1 As Label
Dim height As Int
 
 l1.Initialize("LabelButton")
 l1.Text = Text
 l1.Tag = Event
 l1.TextSize = 19
 l1.TextColor = Colors.White
 l1.Color = Colors.RGB(255, 143, 0)
 myLibrary.Font(l1)
 
 sv1.Panel.AddView(l1,0,top,sv1.Width,10dip)
 l1.Visible = False
 l1.SetVisibleAnimated(1500,True)
 
 LabelSpace(l1)
 
 height = myLibrary.su.MeasureMultilineTextHeight(l1,Text)
 l1.Gravity = Bit.OR(Gravity.CENTER_HORIZONTAL,Gravity.CENTER_VERTICAL)
 l1.height = height * SpaceLabel + 10dip
 top = top + height + 27dip
End Sub

Sub AddImage(filename As String,Watermark As String,Center As Boolean)
Dim im As Panel
Dim b2 As BitmapDrawable
Dim b1 As Bitmap

 b2.Initialize(LoadBitmap(File.DirAssets,filename))
 b1.Initialize(File.DirAssets,filename)
 im.Initialize("")
 
 If Center = True Then
  b2.Gravity = Gravity.Center
  im.Background = b2
 Else
  im.SetBackgroundImage(b1)
 End If
 
 If b1.Height > b1.Width Then
     sv1.Panel.AddView(im,1%x,top + 10dip,sv1.Width * b1.Width / b1.Height,sv1.Width)
	im.Left = (sv1.Width / 2) - (im.Width / 2)
	top = top + sv1.Width + 27dip
 Else
     sv1.Panel.AddView(im,1%x,top + 10dip,sv1.Width,sv1.Width * b1.Height / b1.Width)
	im.Left = (sv1.Width / 2) - (im.Width / 2)
	top = top + sv1.Width * b1.Height / b1.Width + 27dip
 End If

 im.Visible = False
 im.SetVisibleAnimated(1200,True)
 
 'create watermark for panel
 If Watermark <> Null Then
  Dim lb1 As Label
  lb1.Initialize("")
  lb1.TextSize = 25
  lb1.TextColor = Colors.Black
  lb1.Text = Watermark
  lb1.Gravity = Gravity.Center
  lb1.Color = Colors.Transparent
  im.AddView(lb1,0,(im.Height / 2),im.Width,50dip)
 End If
 
End Sub

Sub AddLine
	 Dim l1 As Label
	 l1.Initialize("")
	 l1.Color = Colors.ARGB(70,0,0,0)
	 sv1.Panel.AddView(l1,3dip,top,sv1.Width - 3dip,1dip)
	 top = top + 10dip
End Sub

Sub AddButton(Text As String,StatePress As StateListDrawable,Event As String)
Dim l1 As Button
 
 l1.Initialize("Button")
 l1.Text = Text
 l1.Tag = Event
 l1.TextSize = 17
 l1.TextColor = Colors.White
 
 If StatePress.IsInitialized = False Then
  l1.Background = getState
 Else
  l1.Background = StatePress
 End If
 
 myLibrary.Font(l1)
 
 sv1.Panel.AddView(l1,0,top,sv1.Width,75)
 
 top = top + 75
 l1.SetVisibleAnimated(850,True)
 
End Sub

Sub Button_Click
 Dim b1 As Button
 b1 = Sender
 
  Dim event As String
  event = b1.Tag
  
 If SubExists(Module,event & "_Click") Then
  CallSub(Module,event & "_Click")
 End If
End Sub

Sub LabelButton_Click
 Dim b1 As Label
 b1 = Sender
 
  Dim event As String
  event = b1.Tag
  
 If SubExists(Module,event & "_Click") Then
  CallSub(Module,event & "_Click")
 End If
End Sub

Private Sub getState As StateListDrawable
Dim sd As StateListDrawable
Dim press,active As ColorDrawable
press.Initialize(Colors.RGB(251, 187, 104),0)
active.Initialize(Colors.RGB(255, 143, 0),0)
sd.Initialize
sd.AddState(sd.State_Pressed,press)
sd.AddCatchAllState(active)
Return sd
End Sub

Public Sub ApplyScrollHeight
sv1.Panel.Height = top
End Sub

Sub LabelSpace(view1 As View)
Dim Obj1 As Reflector
Obj1.Target = view1
Obj1.RunMethod3("setLineSpacing", 1, "java.lang.float", SpaceLabel, "java.lang.float")
End Sub