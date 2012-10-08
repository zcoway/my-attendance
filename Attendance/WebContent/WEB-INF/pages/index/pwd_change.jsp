<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/index.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/comman.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">
	<%-- ajax验证 --%>
	<script type="text/javascript" language="javascript">
		$(document).ready(function(){
			$("#resetBtn").click(function(){
				$("#old_pwd_id").val("");
				$("#new_pwd_id").val("");
				$("#new_pwd_id_2").val("");
			})
			$("#enterBtn").click(function(){
				
				var old_pwd_id = document.getElementById("old_pwd_id");
				var new_pwd_id = document.getElementById("new_pwd_id");
				var new_pwd_id_2 = document.getElementById("new_pwd_id_2");
				
				if((old_pwd_id.value==null)||(old_pwd_id.value=="")){
					alert("请输入旧密码");
				}else if((new_pwd_id.value==null)||(new_pwd_id.value=="")){
					alert("请输入新密码");
				}else if((new_pwd_id_2.value==null)||(new_pwd_id_2.value=="")){
					alert("请输入确认密码");
				}else if(new_pwd_id_2.value!=new_pwd_id.value){
					alert("两次密码输入不一致，请重新输入");
					$("#new_pwd_id").val("");
					$("#new_pwd_id_2").val("");
				}else{
					document.getElementById("form").submit();
				}
			})
		})
	$(document).ready(function() {
		$('td>span').addClass('tab-item');
	})
	</script> 
</head>
<style type="text/css">
	body{overflow:hidden}
</style>
<body>
<s:form action="pwd_change" method="post"  id="form" >
	<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
		<s:hidden key="flag" value="up"/>
		<table>
			<tr>
				<td width="20%"><span><s:text name="system.user.oldPassword" /></span></td>
				<td><s:password name="oldPwd" id="old_pwd_id"></s:password></td>
			</tr>
			<tr>
				<td width="20%"><span><s:text name="system.user.newPassword" /></span></td>
				<td><s:password name="newPwd" id="new_pwd_id"></s:password></td>
			</tr>
			<tr>
				<td width="20%"><span><s:text name="system.user.surePassword" /></span></td>
				<td><s:password name="newPwd_2" id="new_pwd_id_2"></s:password></td>
			</tr>
		</table>	
	</div>
	<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
		<a href="#" id="resetBtn" class="easyui-linkbutton"  iconCls="icon-undo" ><s:text name="system.role.resetBtn" /></a>	
		<a href="#" id="enterBtn" class="easyui-linkbutton" iconCls="icon-ok"><s:text name="system.user.enterBtn" /></a>
	</div>
</s:form>
<s:actionmessage/>
<s:actionerror/>
</body>
</html>