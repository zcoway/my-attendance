<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
　     <meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/calendar.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
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
<form id="editForm" name="editForm" action="user_update.action" method="post">
	<table>
		<tr>
			<td><span>用户名:</span></td>
			<td><input type="text" name="userVo.userName" value="${userVo.userName}"/><label style="color: red;">*</label></td>
			
			<td><span>学号:</span></td>
			<td><input type="text" name="userVo.userNum" value="${userVo.userNum}"/><label style="color: red;">*</label></td>
			
			<td><span>密码:</span></td>
			<td><input type="password" name="userVo.userPwd" value="${userVo.userPwd}"/><label style="color: red;">*</label></td>
		</tr>
		
		<tr>
			<td><span>性别:</span></td>
			<td><s:radio name="userVo.userGender" list="#{'1':'男','0':'女'}"/><label style="color: red;">*</label></td>
			
			<td><span>生日:</span></td>
			<td><input type="text" name="userVo.userBirthday"  value="${userVo.userBirthday}" readonly="readonly" onclick="calendar.show(this)"/></td>
			
			<td><span>用户照片:</span></td>
			<td><input type="file" name="userVo.userPortrait"/></td>
		</tr>
		
		<tr>
			<td><span>邮箱地址:</span></td>
			<td><input type="text" name="userVo.userEmail" value="${userVo.userEmail}"/><label style="color: red;">*</label></td>
			
			<td><span>QQ:</span></td>
			<td><input type="text" name="userVo.userQq" value="${userVo.userQq}"/></td>
			
			<td><span>手机号码:</span></td>
			<td><input type="text" name="userVo.userTel" value="${userVo.userTel}"/><label style="color: red;">*</label></td>
		</tr>
		
		<tr>
			<td><span>所属班级:</span></td>
			<td><s:select  name="tmp"  headerKey="" headerValue="======请选择=====" 
					list="classes" listKey="classId" listValue="className" cssStyle="width:133px;"/></td>
			
			<td><span>个人爱好:</span></td>
			<td><input type="text" name="userVo.userHobby" value="${userVo.userHobby}"/></td>
			
			
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td><span>个人简介:</span></td>
			<td colspan="5"><textarea cols="80" rows="3"  name="userVo.userIntro">${userVo.userIntro}</textarea></td>
		</tr>
			<div  region="south" border="false" style="margin:10px;text-align:right;height:30px;line-height:30px;">	
		 		<a href="javascript:document.editForm.submit()" class="easyui-linkbutton" icon="icon-ok">提交</a>
		 		<a href="javascript:window.parent.closeWindow()" class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
			</div>
	</table>
	<input type="hidden" name="userVo.userId" value="${userVo.userId}">
</form>
</body>
</html>
