Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
Dim DB As DB
Dim l1 As List
Private ScrollView1 As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
DB.Initialize
l1.Initialize
Dim top As Int = 0
Activity.LoadLayout("frmhelp")

l1 = DB.Item

 For i = 0 To l1.Size - 1
  Dim m1 As Map
  m1.Initialize
   
  m1 = l1.get(i)
  
  If m1.GetValueAt(0) = "Title" Then
   Dim lb As Label
   lb = getLabel(m1.GetValueAt(1),True)
   ScrollView1.Panel.AddView(lb,3%x,top,95%x,10dip)
   lb.Height = myLibrary.su.MeasureMultilineTextHeight(lb,lb.Text)
   top = top + lb.Height + 10dip
  
  Else If m1.GetValueAt(0) = "Content" Then
   Dim lb As Label
   lb = getLabel(m1.GetValueAt(1),False)
   ScrollView1.Panel.AddView(lb,3%x,top+25dip,95%x,10dip)
   lb.Height = myLibrary.su.MeasureMultilineTextHeight(lb,lb.Text)
   top = top + lb.Height + 30dip
   
  Else If m1.GetValueAt(0) = "Picture" Then
   Dim img As ImageView
   Dim b1 As Bitmap
   
   img = getImage(m1.GetValueAt(1))
   b1 = img.Bitmap
   
   If b1.Height > b1.Width Then
     ScrollView1.Panel.AddView(img,3%x,top + 10dip,100%x * b1.Width / b1.Height,100%x)
	top = top + 100%x
   Else
     ScrollView1.Panel.AddView(img,3%x,top + 10dip,100%x,100%x * b1.Height / b1.Width)
	top = top + 100%x * b1.Height / b1.Width
   End If
  
  img.Visible = False
  img.SetVisibleAnimated(1200,True)
  
  End If
  
 Next
 
 ScrollView1.Panel.Height = top + 30dip
 
End Sub

Sub getLabel(text As String,blnTitle As Boolean) As Label
 Dim lbl1 As Label
 lbl1.Initialize("")
 lbl1.Typeface = Typeface.LoadFromAssets("byekan.ttf")
 lbl1.Gravity = Bit.AND(Gravity.TOP,Gravity.RIGHT)
 lbl1.text = text
 If blnTitle = True Then
  lbl1.TextSize = 22
  lbl1.TextColor = Colors.Red
 Else
  lbl1.TextSize = 19
  lbl1.TextColor = Colors.Black
 End If
 lbl1.Color = Colors.Transparent
 Return lbl1
 
End Sub

Sub getImage(filename As String) As ImageView
Dim im As ImageView
Dim b1 As Bitmap
b1.Initialize(File.DirAssets,filename)
im.Initialize("")
im.Gravity = Gravity.FILL
im.SetBackgroundImage(b1)
Return im
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
 Activity.Finish
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
 If KeyCode = KeyCodes.KEYCODE_BACK Then
  Activity.Finish
 End If
End Sub
