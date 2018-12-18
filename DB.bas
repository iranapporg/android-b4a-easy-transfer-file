Type=Class
Version=4
ModulesStructureVersion=1
B4A=true
@EndOfDesignText@
'Class module
Private Sub Class_Globals
'Private SQL   As SQL
End Sub
'
''Initializes the object. You can add parameters to this method if needed.
'Public Sub Initialize
'If File.Exists(File.DirInternal,"help.db") = False Then
' File.Copy(File.DirAssets,"help.db",File.DirInternal,"help.db")
'End If
'SQL.Initialize(File.DirInternal,"help.db",False)
'End Sub
'
'Sub getItem As List
'  
'  
'  Dim l1 As List
'  Dim cu As Cursor
'  
'  
'  l1.Initialize
'  
'  cu = SQL.ExecQuery("SELECT * FROM tbl_help")
'  
'  For i = 0 To cu.RowCount - 1
'   cu.Position = i
'   Dim m1 As Map
'   m1.Initialize
'   m1.Put("sTitle"  ,cu.GetString("sTitle"))
'   m1.Put("sContent",cu.GetString("sContent"))
'   l1.Add(m1)
'  Next
'  
'  Return l1
'  
'End Sub