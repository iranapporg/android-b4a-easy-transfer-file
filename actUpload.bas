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
	Dim tim As Timer
End Sub

Sub Globals
	Private pnlfiles As Panel
	Private pnlicon As Panel
	Private imgwireless As ImageView
	Private imghotspot As ImageView
	Private lblhotspot As Label
	Private re As RippleView
	Private lblaudio As Label
	Private lblimage As Label
	Private lblvideo As Label
	Private top,left,width,height As Int
	Private hotspot As Hotspotlib
	Private cn As Int = 1
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.LoadLayout("frmupload")
re.Initialize(imghotspot,Colors.RGB(42, 181, 246),300,True)
re.Initialize(imgwireless,Colors.RGB(42, 181, 246),300,True)
top  = pnlicon.top
left = pnlicon.left
width  = pnlicon.width
height = pnlicon.height

pnlicon.width = 0
pnlicon.height = 0

pnlicon.SetLayoutAnimated(750,left,top,width,height)
pnlicon.width = width
pnlicon.height = height

End Sub

Sub Activity_Resume
  lblimage.Text = myLibrary.ParseInputFile(Activity.GetStartingIntent)
 
 'lblaudio.Text = myLibrary.audio
 'lblimage.Text = myLibrary.image
 'lblvideo.Text = myLibrary.video
 
 pnlfiles.SetVisibleAnimated(1700,True)
 pnlfiles.Visible = True
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub btnwireless_Click
Dim ip As String

ip = myLibrary.GetIP

If Regex.IsMatch("\d{1,3}.\d{1,3}.\d{1,3}.\d{1,3}",ip) = True AND ip <> "127.0.0.1" Then
 actHotspot.HelpName = "next"
 StartActivity(actHotspot)
Else
 ToastMessageShow("خطا : گوشی شما به مودم وایرلس وصل نیست",False)
End If

End Sub

Sub btnhotspot_Click
 hotspot.batdau("Easy Transfer",False,"")
 ProgressDialogShow2("در حال شناسایی شبکه...",True)
 tim.Initialize("tmr",500)
 tim.Enabled = True
End Sub

Sub tmr_Tick
Dim ip As String

ip = myLibrary.GetIP

If cn > 10 Then
 tim.Enabled = False
 ProgressDialogHide
 ToastMessageShow("خطا : لطفا مجددا تلاش کنید",False)
 cn = 1
 Return
End If

If Regex.IsMatch("\d{1,3}.\d{1,3}.\d{1,3}.\d{1,3}",ip) = True AND ip <> "127.0.0.1" Then
 tim.Enabled = False
 ProgressDialogHide
 cn = 1
 actHotspot.HelpName = ""
 StartActivity(actHotspot)
End If

cn = cn + 1

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		Activity.Finish
	End If
End Sub

Sub btnhelp_Click
	actHelp1.HelpName = "different"
	StartActivity(actHelp1)
End Sub