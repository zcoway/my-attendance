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
table td span{
	text-align: right;
}
</style>
<title></title>
</head>
<div style="font-size: 14px;color: red;"><s:actionmessage/></div>
<body>
<form id="editForm" name="editForm" action="message_update.action" method="post">
	<table>
		<tr>
			<td><span>接收者:</span></td>
			<td><input type="text" name="message.userReceiver" value="${message.receiverName }" readonly="readonly"/></td>
		</tr>
		<tr>
			<td><span>主题:</span></td>
			<td><input type="text" name="message.messageTitle" value="${message.messageTitle }" readonly="readonly"/></td>
		</tr>
		<tr>
			<td><span>内容:</span></td>
			<td><textarea cols="50" rows="5" name="message.messageContent" readonly="readonly">${message.messageContent}</textarea></td>
		</tr>	
		
	</table>
</form>
</body>
</html>
