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

	Private ScrollView1 As ScrollView
	Private lbltitle As Label
	Private Help As Help
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("frmhelp")
	Help.Initialize(ScrollView1,Me)
	
	Activity.AddMenuItem("درباره ما","btnAbout")
	
	If HelpName = "" Then
		lbltitle.Text = "راهنمای استفاده از برنامه"
		Help.AddLabel("برنامه با موفقیت اجرا شد. برای استفاده از برنامه، باید از این صفحه خارج شوید و داخل گالری گوشی خود شوید. با نگهداشتن انگشت، عکسهایی که میخواهید منتقل شود را انتخاب کنید",18,Colors.Black)
		Help.AddImage("w2.jpg","",False)
		Help.AddLabel("همانطوری که در تصویر بالا با شماره دو امده است از قسمت نوار بالای گوشیتون روی آیکن اشتراک کلیک کنید. ممکن است جای قرار گرفتن این آیکن در گوشی های مختلف متفاوت باشد اما از روی آیکن آن به راحتی میتوانید آنرا پیدا کنید. آیکن اشتراک گذاری سه نقطه است که به هم وصل شده است.",18,Colors.Black)
		Help.Margin = 13dip
		Help.AddImage("w3.jpg","",False)
		Help.AddLabel("یه لیست ظاهر میشه. باید بتونید اسم و آیکن برنامه انتقال آسان را پیدا کنید. اگر در لیست نبود بر روی گزینه See all کلیک کنید تا لیست کامل بیاید و بتوانید روی ایکن انتقال آسان کلیک کنید",18,Colors.Black)
		Help.AddLabel("با انجام این کار دوباره به محیط برنامه برخواهید گشت و برای بقیه مراحل انتقال راهنمایی خواهید شد.",18,Colors.Black)
		Help.AddImage("w4.jpg","",False)
		Help.AddLabel("توجه بفرمایید که لازم نیست حتما وارد گالری بشوید تا بتوانید از برنامه استفاده کنید. گزینه اشتراک در اکثر برنامه هست و مثلا همانطور که در تصویر بالا آمده است میتوانید یک صوت یا عکس را در برنامه whatsapp انتخاب کنید و به کامپیوتر بفرستید",18,Colors.Black)
		Help.Margin = 13dip
		Help.AddLabelButton("باشه","btnOK")
	
	Else If HelpName = "different" Then
		lbltitle.Text = "تفاوت روش های انتقال"
		Help.AddLabel("شما با دو روش میتونید فایلها رو منتقل کنید که معایب و مزایای هر کدام به شرح زیر است:",18,Colors.Black)
		Help.AddImage("hotspot.jpg","",True)
		Help.AddLabel("هات اسپات:" & CRLF & _
			"- این روش باطری بیشتری مصرف میکند." & CRLF & _
			"- سرعت بسیار بیشتری در انتقال فایل دارد. در تستهای ما بین 3 تا 4 مگ در ثانیه توسط این روش منتقل میشود. ممکن است در گوشی های قدیمی این سرعت کاهش پیدا کند." & CRLF & _
			"- مراحل این روش بیشتر است و باید مودم وایرلس کامپیوتر و یا گوشی گیرنده به گوشی شما وصل شد. در ضمن کار راهنمایی خواهید شد.",18,Colors.Black)
		Help.AddLine
		Help.AddImage("wireless.jpg","",True)
		Help.AddLabel("مودم وایرلس:" & CRLF & _
			"-سرعت کمتری نسبت به روش قبل دارد اما باطری کمتری نیز استفاده میکند." & CRLF & _
			"-از پیش باید هم گوشی و هم کامپیوتر به یه مودم وایرلس واحد وصل باشه. " & CRLF & _
			"-توجه داشته باشید نیازی به اینترنت نیست و اگه مودم وایرلس به اینترنت هم وصل نباشه میتونید از این روش استفاده کنید.",18,Colors.Black)
		Help.Margin = 15dip
		Help.AddLabelButton("باشه","btnOK")
	
	Else If HelpName = "win8" Then
		lbltitle.Text = "اتصال به وایرلس برنامه"
		Help.AddLabel("برای قسمتی که در عکس زیر مشخص شده است کلیک کنید:",18,Colors.Black)
		Help.AddImage("win8-01.jpg","",False)
		Help.AddLabel("اگر همانند تصویر زیر بخش مشخص شده در حالت off قرار داشت، روی آن کلیک کنید تا به حالت On تغییر پیدا کند. بدین ترتیب وایرلس دستگاه شما روشن میشود. اگر بصورت پیش فرض بر روی on قرار داشت روی آن کلیک نکنید:",18,Colors.Black)
		Help.AddImage("win8-02.jpg","",False)
		Help.AddLabel("مانند تصویر زیر تیک قسمت Connect automatically رو فعال کنید و روی دکمه connect کلیک کنید",18,Colors.Black)
		Help.AddImage("win8-03.jpg","",False)
		Help.AddLabel("در نهایت باید مثل تصویر زیر به وایرلس وصل شده باشید و گزینه Connected در زیر EasyTransfer نوشته باشه",18,Colors.Black)
		Help.AddImage("win8-04.jpg","",False)
		Help.Margin = 15dip
		Help.AddLabelButton("باشه","btnOK")
		
	Else If HelpName = "win7" Then
		lbltitle.Text = "اتصال به وایرلس برنامه"
		Help.AddLabel("از نوار پایین ویندوزتون روی قسمتی که در عکس زیر مشخص شده کلیک کنید:",18,Colors.Black)
		Help.AddImage("win7-01.jpg","",False)
		Help.AddLabel("باید یه گزینه مثل تصویر زیر اضافه شده باشه. توی تصویر با فلش قرمز مشخص شده است. اگر آنرا نمیبینید روی گزینه Refresh کلیک کنید.",18,Colors.Black)
		Help.AddImage("win7-02.jpg","",False)
		Help.AddLabel("مانند تصویر، تیک قسمت Connect automatically رو بنزید و بعد دکمه Connect رو بزنید تا به شبکه برنامه وصل بشید.",18,Colors.Black)
		Help.AddImage("win7-03.jpg","",False)
		Help.AddLabel("بعد از کمی صبر باید مثل شکل زیر، ایکن وایرلس کامپیوتر شما به حالت متصل درآمده باشد.",18,Colors.Black)
		Help.AddImage("win7-04.jpg","",False)
		Help.Margin = 15dip
		Help.AddLabelButton("باشه","btnOK")
	End If
	
     Help.ApplyScrollHeight	
End Sub

Sub btnAbout_Click
Dim a As String
a = "گروه برنامه نویسی کویک لرن به تازگی شکل گرفته است" & CRLF & _
"و قصد دارد نرم افزارهای با کیفیتی را عرضه کند و در " & CRLF & _
"این راستا پیشنهادات و انتقادات شما هموطنهای عزیز بسیار راهگشا است." & CRLF & _
"اعضای گروه:" & CRLF & _
"امید آقاخانی (iranapp.org)" & CRLF & _
"محمد معلی(quicklearn.ir)" & CRLF & _
"با تشکر از اقای حسن الماسی (hasanalmasi.ir) بخاطر مشاوره های گرافیکیشون و همچنین تشکر ویژه از پشتیبانی کاربران خوب و صمیمی تالار سایت کویک لرن(quicklearn.ir/forums)"
Msgbox(a,"درباره ما")
End Sub

Sub btnOK_Click
	Activity.Finish	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub Activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	If KeyCode = KeyCodes.KEYCODE_BACK Then
	 ScrollView1.ScrollPosition = ScrollView1.Panel.Height
	 DoEvents
	 Return True
	Else If KeyCode = KeyCodes.KEYCODE_MENU Then
	 Activity.OpenMenu
	 DoEvents
	 DoEvents
	 DoEvents
	End If
End Sub

