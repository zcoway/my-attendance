<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户登录</title>
</head>
<body>
	<h3>用户登录</h3>
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
</body>
</html>