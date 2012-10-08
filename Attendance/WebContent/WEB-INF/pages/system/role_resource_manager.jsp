<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基于SSH2框架的高校考勤考核管理系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/role_resource_manager.js"></script>
</head>
<body class="easyui-layout">
	<input type="hidden" id="resourceIds">
	<input type="hidden" id="roleId">
	<div region="west" border="false" split="true" fit="auto" style="width:532px;padding:2px;">
		<table id="list_data"></table>
	</div>
	<div region="center" border="false" style="padding:2px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false">
				<table id="list_data2"></table>	
			</div>
			<div region="south" style="padding:3px; background:#E0ECFF;text-align:right">
				 <a href="javascript:saveEdit()" class="easyui-linkbutton" icon="icon-save">保存权限修改</a>  
                 <a href="javascript:resetEdit()" class="easyui-linkbutton" icon="icon-cancel">重置</a>  
			</div>
		</div>
	</div>
</body>
</html>