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

End Sub

Sub Globals
	Private hotspot As Hotspotlib
	Private lblcountDownload As Label
	Private lbldownloading As Label
	Private pb1 As ProgressBar
	Private pnldownload As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("frmconnected")
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If Msgbox2("آیا میخواهید از سرویس خارج شوید؟","توجه","آری","خیر","",Null) = DialogResponse.POSITIVE Then
		   Try
		    StopService(HttpServers)
		    hotspot.tat
		   Catch
		   End Try
  		End If
		Return False
	End If
End Sub

Sub setDownload
	  lblcountDownload.Text = "تعداد دانلود شده ها : " & HttpServers.downloadCount
	  lblcountDownload.SetVisibleAnimated(1900,True)
End Sub

Sub LoadingState
	 pnldownload.Visible = Not(pnldownload.Visible)
	 DoEvents
End Sub

Sub DeleteFile(delete_filename As String)
	Try
	 If Msgbox2("آیا مایل به حذف این فایل هستید؟","توجه","بله","خیر","",Null) = DialogResponse.POSITIVE Then
	  File.Delete(myLibrary.getFullPath(delete_filename),myLibrary.GetFileName(delete_filename))
	  ToastMessageShow("تصویر مورد نظر حذف شد",False)
	 End If
	Catch
	 ToastMessageShow("متاسفانه فایل قابل حذف نیست",False)
	End Try
End Sub