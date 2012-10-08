<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
　     <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">

<style type="text/css">
select{
width: 133;
height: 20;
text-align: center;
}
</style>
<script type="text/javascript">


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
<form id="addForm" name="addForm" action="leave_save.action" method="post">
	<table>
		<tr>
			<td><span>姓名:</span></td>
			<td><input type="text" name="leave.leaveUser" value="${leave.leaveUser}" readonly="readonly"/></td>
			<td><span>班级:</span></td>
			<td><input type="text" name="leave.leaveClas" value="${leave.leaveClas}" readonly="readonly"/></td>
			<td><span>学号:</span></td>
			<td><input type="text" name="leave.leaveNum" value="${leave.leaveNum}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td><span>请假时间:</span></td>
			<td colspan="5"><input type="text" name="leave.leaveInfo" style="width:200"/><label style="color: red;">*</label></td>
		</tr>
		<tr>
			<td><span>请假原因:</span></td>
			<td colspan="5"><textarea cols="70" rows="6" name="leave.leaveReason"></textarea><label style="color: red;">*</label></td>
		</tr>
		<tr>
			<td><span>学生代理:</span></td>
			<td><s:select list="students" listKey="userName" listValue="userName" name="leave.leaveDelegate" headerValue="====请选择====" headerKey="unselected"></s:select><label style="color: red;">*</label></td>
			
			<td ><span>学生审核:</span></td>
			<td colspan="2"><s:radio list="studentAuth" name="leave.leaveFlag " disabled="true"/></td>
		</tr>
		<tr>
			<td><span>受理教师:</span></td>
			<td><s:select list="teachers" listKey="userName" listValue="userName" name="leave.leaveTeacher" headerValue="====请选择====" headerKey="unselected"></s:select><label style="color: red;">*</label></td>
			
			<td ><span>教师审核:</span></td>
			<td colspan="2"><s:radio list="teacherAuth" name="leave.leaveStatus" disabled="true"/></td>
		</tr>
		<tr>
			<td ><span>是否给老师发送短信提醒:</span></td>
			<td colspan="2"><s:radio list="shortMessage" name="shortMsg"/></td>
		</tr>	
		
			<div  region="south" border="false" style="margin:10px;text-align:right;height:30px;line-height:30px;">	
		 		<a href="javascript:document.addForm.submit()" class="easyui-linkbutton" icon="icon-ok">提交</a>
		 		<a href="javascript:window.parent.closeWindow()" class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
			</div>
	</table>
</form>
</body>
</html>
