<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>基于SSH2框架的高校考勤考核管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css">
<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
<%-- <script type="text/javascript" src="${ctx}/js/index.js"></script> --%>

<%-- 时间显示器 --%>
<script type="text/javascript" src="${ctx }/js/jquery.timer.js"></script>

<script>
<%-- 设置时间的格式  --%>
$(document).ready(function(){
	   $('#timer').timer({format: "HH:MM:ss yy年mm月dd日 W"});
});

</script>

	<%-- body的CSS --%>
<style type="text/css">
	body{
		background: url(../images/top.jpg);
		background-repeat: repeat-x;
		margin:0px;
		padding:0px;
	}
	.toptext{
		font-family:微软雅黑,宋体; 
		color:#15428b; 
		font-weigth:bold; 
		size:10px;
	}

</style>

</head>
<body>
		<table width="100%" border="0" cellspacing="0">
		<tr>
			<td ><img src="../images/myLogo2.png"></td>
			<td class="toptext" valign="bottom"> 欢迎用户：<sec:authentication property="name"/><br>
												 当前时间：<span id="timer"></span>
			</td>
			<td valign="bottom">
							<a href="#" class="easyui-linkbutton" onClick="openDiv();">
							<s:text name="修改密码"/>
							</a>
							<br>
							<s:a value="../j_logout" cssClass="easyui-linkbutton">
							<s:text name="退出登录" ></s:text>
							</s:a>
			</td>
		</tr>

		</table>
		
</body>
</html>
