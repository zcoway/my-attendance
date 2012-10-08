<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
　     <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
<script type="text/javascript">
$(document).ready(function () {
    $('#dd').datebox({  
        required:true  
    });  
    $('#vv').validatebox({  
        required:true  
    });  
    $('#dd2').datebox({  
        required:true  
    }); 
});

function resetFields() 
{   
 var forms = document.forms;
 for ( var i = 0; i < forms.length; i++) 
	{
	for ( var j = 0; j < forms[i].elements.length; j++) 
	{
		var element = forms[i].elements[j];
		if (element.type == "text")
		 element.value="";
	 }
  }
}

</script>

<style type="text/css">
table td span{
	text-align: right;
}
</style>
<title></title>
</head>
<div style="font-size: 14px;color: red;"><s:actionmessage/></div>
<body>
<form id="addForm" name="addForm" action="week_save.action" method="post">
	<table>
		<tr>
			<td><span>创建者姓名:</span></td>
			<td><input id="vv" type="text" name="week.userName" editor="{type:'text',options:{required:true}}"/><label style="color: red;">*</label></td>
			
			<td><span>创建时间:</span></td>
			<td><input id="dd" type="text" name="week.weekTime" editor="{type:'datebox',options:{required:true}}"/><label style="color: red;">*</label></td>
			
			<td></td>
		</tr>
		<tr>
			<td><span>学年学期:</span></td>
			<td><input id="vv2" type="text" name="week.weekYear" editor="{type:'text',options:{required:true}}"/><label style="color: red;">*</label></td>
			
			<td><span>开学时间:</span></td>
			<td><input id="dd2" type="text" name="week.weekDepend" editor="{type:'datebox',options:{required:true}}"/><label style="color: red;">*</label></td>
			
			<td></td>
		</tr>
		<tr>
			<td><span>所在系:</span></td>
			<td><input type="text" name="week.deptName" editor="{type:'text',options:{required:true}}"/><label style="color: red;">*</label></td>
			
			<td><span>所在年级:</span></td>
			<td><input type="text" name="week.clasGrade" editor="{type:'text',options:{required:true}}"/><label style="color: red;">*</label></td>
			
			<td><span>所在班级:</span></td>
			<td><input type="text" name="week.clasName" editor="{type:'text',options:{required:true}}"/><label style="color: red;">*</label></td>
		</tr>
		
			<div  region="south" border="false" style="margin:10px;text-align:right;height:30px;line-height:30px;">	
		 		<a href="javascript:document.addForm.submit()" class="easyui-linkbutton" icon="icon-ok">提交</a>
		 		<a href="javascript:window.parent.closeWindow()" class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
			</div>
	</table>
</form>
</body>
</html>
