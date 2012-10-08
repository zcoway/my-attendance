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

<title></title>
<script type="text/javascript">
$(function () {
	$('#userName').validatebox({required:true});  
	$('#userNum').numberbox({required:true});  
	$('#userPwd').validatebox({required:true});  
	$('#userGender').validatebox({required:true});  
	$('#userBirthday').datebox({});  
	$('#userEmail').validatebox({required:true});  
	$('#userTel').validatebox({required:true});  
}); 
</script>
</head>
<div style="font-size: 14px;color: red;"><s:actionmessage/></div>
<body>
<form id="addForm" name="addForm" action="user_save.action" method="post">
	<table>
		<tr>
			<td style="text-align: right;"><span>用户名:</span></td>
			<td><input type="text" name="user.userName" id="userName" required="true"/></td>
			
			<td style="text-align: right;"><span>学号:</span></td>
			<td><input type="text" name="user.userNum" id="userNum" required="true"/></td>
			
			<td style="text-align: right;"><span>密码:</span></td>
			<td><input type="password" name="user.userPwd" id="userPwd" required="true"/></td>
		</tr>
		<tr>
			<td style="text-align: right;"><span>性别:</span></td>
			<td><s:radio name="user.userGender" list="#{'1':'男','0':'女'}" id="userGender" required="true"/></td>
			
			<td style="text-align: right;"><span>生日:</span></td>
			<td><input type="text" name="user.userBirthday" readonly="readonly" id="userBirthday"/></td>
			
			<td style="text-align: right;"><span>用户照片:</span></td>
			<td><input type="file" name="user.userPortrait"/></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>邮箱地址:</span></td>
			<td><input type="text" name="user.userEmail" id="userEmail" required="true" validType="email"/></td>
			
			<td style="text-align: right;"><span>QQ:</span></td>
			<td><input type="text" name="user.userQq"/></td>
			
			<td style="text-align: right;"><span>手机号码:</span></td>
			<td><input type="text" name="user.userTel" id="userTel" required="true"/></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>所属班级:</span></td>
			<td><input id="cc" class="easyui-combotree" url="clas_tree_data.action" name="user.clasName" 
							required="true" style="width:150px; margin-left: 30px; padding: 2px"></td>
			
			<td style="text-align: right;"><span>个人爱好:</span></td>
			<td><input type="text" name="user.userHobby"/></td>
			
			
			<td></td>
			<td></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>个人简介:</span></td>
			<td colspan="5"><textarea cols="80" rows="3" name="user.userIntro"></textarea></td>
		</tr>
			<div  region="south" border="false" style="margin:10px;text-align:right;height:30px;line-height:30px;">	
		 		<a href="javascript:document.addForm.submit()" class="easyui-linkbutton" icon="icon-ok">提交</a>
		 		<a href="javascript:window.parent.closeWindow()" class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
			</div>
	</table>
</form>
</body>
</html>
