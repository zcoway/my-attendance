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
<form id="editForm" name="editForm" action="resource_update.action" method="post">
	<table>
		<tr>
			<td><span>资源名称:</span></td>
			<td><input type="text" name="resource.resourceName" value="${resource.resourceName }"/><label style="color: red;">*</label></td>
			
			<td><span>资源类型:</span></td>
			<td><input type="text" name="resource.resourceType" value="${resource.resourceType }"/></td>
			
			
			<td><span>资源值:</span></td>
			<td><input type="text" name="resource.resourceValue" value="${resource.resourceValue }"/><label style="color: red;">*</label></td>
			
		</tr>
		
		<tr>
			<td><span>父资源ID:</span></td>
			<td><input type="text" name="resource.resourceParentId" value="${resource.resourceParentId }"/><label style="color: red;">*</label></td>
			
			<td><span>是否为叶子:</span></td>
			<td><s:radio name="resource.resourceIsLeaf" list="#{'true':'是','false':'否'}"/><label style="color: red;">*</label></td>
			
			
			<td><span>是否可用:</span></td>
			<td><s:radio name="resource.resourceEnabled" list="#{'true':'是','false':'否'}"/><label style="color: red;">*</label></td>
			
		</tr>
		
		<tr>	
			<td><span>资源描述:</span></td>
			<td><input type="text" name="resource.resourceDesc" value="${resource.resourceDesc }"/></td>
		</tr>
			<div  region="south" border="false" style="margin:10px;text-align:right;height:30px;line-height:30px;">	
		 		<a href="javascript:document.editForm.submit()" class="easyui-linkbutton" icon="icon-ok">提交</a>
		 		<a href="javascript:window.parent.closeWindow()" class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
			</div>
	</table>
	<input type="hidden" name="resource.resourceId" value="${resource.resourceId}">
</form>
</body>
</html>
