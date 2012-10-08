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
<form id="editForm" name="editForm" action="clas_update.action" method="post">
	<table>
		<tr>
			<td><span>班级名称:</span></td>
			<td><input type="text" name="clas.clasName" value="${clas.clasName}"/><label style="color: red;">*</label></td>
			
			<td><span>所属年级:</span></td>
			<td><input type="text" name="clas.clasGrade" value="${clas.clasGrade }"/><label style="color: red;">*</label></td>
			
			<td><span>所属系部:</span></td>
			<td><input type="text" name="clas.clasDept" value="${clas.clasDept}"/><label style="color: red;">*</label></td>
		</tr>
		<tr>
			<td><span>班级描述:</span></td>
			<td><input type="text" name="clas.clasDesc" value="${clas.clasDesc}"/></td>
		</tr>
			<div  region="south" border="false" style="margin:10px;text-align:right;height:30px;line-height:30px;">	
		 		<a href="javascript:document.editForm.submit()" class="easyui-linkbutton" icon="icon-ok">提交</a>
		 		<a href="javascript:window.parent.closeWindow()" class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
			</div>
	</table>
	<input type="hidden" name="clas.clasId" value="${clas.clasId}">
</form>
</body>
</html>
