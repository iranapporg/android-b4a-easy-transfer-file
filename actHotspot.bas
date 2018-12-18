Type=Activity
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Dim HelpName As String
End Sub

Sub Globals
	Private sv1 As ScrollView
	Dim Help As Help
	Private lbltitle As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("frmhotspot")
     
	StartService(HttpServers)
	
	If HelpName = "" Then
		lbltitle.Text = "اتصال به هات اسپات"
		Help.Initialize(sv1,Me)
		Help.AddLabel("گوشی شما برای انتقال فایل آماده است. در مرحله بعد باید از کامپیوتر و یا گوشی گیرنده نیز به گوشی فرستند وصل شوید. برای این کار نیاز است از طریق وایرلس دستگاه گیرنده، به شبکه EasyTransfer وصل شوید. اگر تا بحال این کار را نکرده اید و با روش انجام آن آشنا نیستید بر روی دکمه راهنمای اتصال به وایرلس کلیک کنید.",18,Colors.Black)
		Help.Margin = 30dip
		Help.AddLabelButton("راهنمای اتصال به برنامه در ویندوز 8","btnhelp8")
		Help.Margin = 10dip
		Help.AddLabelButton("راهنمای اتصال به برنامه در ویندوز 7","btnhelp7")
		Help.Margin = 40dip
		Help.AddLabel("اگر قبلا به وایرلس EasyTransfer متصل شده اید به مرحله بعد بروید.",18,Colors.Black)
		Help.AddLabelButton("مرحله بعد","btnnext")
	     Help.ApplyScrollHeight
	Else If HelpName = "next" Then
		lbltitle.Text = "واردکردن آی پی برنامه"
		Help.Initialize(sv1,Activity)
		Help.AddLabel("مرورگر کامپیوتر خود را باز کنید و متن داخل کادر زیر را در مرورگر تایپ کنید و دکمه اینتر را بزنید.",18,Colors.Black)
		Help.AddImage("w1.jpg",myLibrary.GetIP & ":2020",False)
		Help.Margin = 30dip
		Help.AddLabel(File.ReadString(File.DirAssets,"help1.txt"),18,Colors.Black)
		Help.Margin = 45dip
	     Help.ApplyScrollHeight
	End If
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)
  If UserClosed = True Then
	Dim hotspot As Hotspotlib
	hotspot.tat
	Activity.Finish
  Else
  	Activity.Finish
  End If
End Sub

Sub btnnext_Click
	sv1.Panel.RemoveAllViews
	HelpName = "next"
	Activity_Create(False)
End Sub

Sub btnhelp8_Click
	actHelp1.HelpName = "win8"
	StartActivity(actHelp1)
End Sub

Sub btnhelp7_Click
	actHelp1.HelpName = "win7"
	StartActivity(actHelp1)
End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
 If KeyCode = KeyCodes.KEYCODE_BACK Then
  StopService(HttpServers)
  Activity.Finish
 End If
End Sub