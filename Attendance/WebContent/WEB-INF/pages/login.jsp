<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
<%-- 	<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css"> --%>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/login.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/index.js"></script>
<script type="text/javascript">
	function loginSubmit(){
		var obj = $("#login");
		obj[0].action = "${pageContext.request.contextPath}/j_spring_security_check";
		obj[0].submit();
	}
	
	function loginReset(){
		var obj = $("#login");
		obj[0].reset();
	}
	function loginEnter(event) {
		if(event.keyCode == 13){
			loginSubmit();
		}
	}
</script>
</head>
<%-- <body>
	<!-- <h3>用户登录</h3> -->
	<s:if test="tip!=null">
<div style="border: 1px dashed #FF9900;
	background-color: #99CCFF;
	font-size: 9pt;
	color: #FF0000;">
	<s:property value="tip"/>
</div>
</s:if>
	${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
	<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
		用户名:<input type="text" name="j_username"/><br/>
		密码:<input type="password" name="j_password"><br/>
		<input type="submit" value="登录"/>
	</form>
	
	 <div id="win" class="easyui-window" title="用户登录" style="width:500px;height:300px;">  
        <form style="padding:40px 80px 40px 120px;" id="login" method="post"> 
        	<table style="padding:10px;">
        		<tr>
        			<td>用户名: </td>
        			<td><input type="text" name="j_username"></td>
        		</tr>
        		<tr>
        			<td>密码: </td>
        			<td><input type="password" name="j_password" onkeydown="loginEnter(event)"></td>
        		</tr>
        	</table> 
            <div style="padding:5px;text-align:center;margin-top:10px">  
                <a href="javascript:loginSubmit()" class="easyui-linkbutton" icon="icon-ok">登录</a>  
                <a href="javascript:loginReset()" class="easyui-linkbutton" icon="icon-cancel">重置</a>  
            </div>  
        </form>  
    </div>  
</body> --%>
<body>
	<div class="login_logo"><img  src="images/login_jw2.png"/></div>
	<div class="login_main">
		<div class="login_left"><img class="" src="images/login_pic.png"/></div>
		<div class="login_right">
			<!--table height="340" width="462"></table-->
			<div class="userInfo">
			  <form id="login"  method="post">
			   <dl>
				<dd><span>用户名</span></dd>
				<dd id="space"><input type="text" name="j_username" style="width:200px"/></dd>
				<dd><span>密码</span></dd>
				<dd><input type="password" name="j_password" onkeydown="loginEnter(event)" style="width:200px"></dd>
			  </dl>
			  </form>
			</div>
			<div class="userBtn">
				<input class="login_btn"  type="button" style="color:#fff" value="登录" onclick="javascript:loginSubmit()"/>
			</div>
		</div>
	</div>	
	<div class="login_bottom">
	 	<div style="text-align:center">
	 	<br>
		<span>基于SSH2框架的高校考勤考核管理系统</span>
		<br>
		 <span>CopyRight&copy; 2012 efanhome All Right Reserved. efanhome版权所有</span> </div>
	</div>
</body>
</html>