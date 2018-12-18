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

End Sub

Sub Globals

End Sub

Sub Activity_Create(FirstTime As Boolean)
ToastMessageShow("از برنامه خارج شوید و فایلهایی که میخواهید منتقل شود را از طریق گزینه اشتراک به این برنامه بفرستید",True)
Activity.Finish
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


