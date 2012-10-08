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
	<script type="text/javascript" src="${ctx}/js/jquery.formid.js"></script>
	<script type="text/javascript" src="${ctx}/js/year_manager.js"></script>

<script type="text/javascript">
	function getAddUrl(){
		var url = "${ctx}/system/user_add.action";
		return url;
	}
	function getDelUrl() {
		var url = "${ctx}/system/user_delete.action";
		return url;
	}
	function closeCenter() {
		var tt = $(parent.document);
		var p1 = tt.find("#tabs");
		var p2 = tt.tabs('close');
		/* for(var i=0;i<p1.length; i++){
			if(p1[i].tabs('getSelected')){
				alert("good");
			}
		} */
		var pp = tt.tabs('getSelected');
		pp.close;
	}
</script>
</head>
<body
	style="margin: 0; padding: 0; height: 100%; overflow: hidden; background: #F2FBFF">
	<div id="mainlayout" class="easyui-layout" style="height: 400px;" fit="true">
		<div region="north" border="false" style="border-bottom: 1px solid #92B7D0; background: #F2FBFF"">
			<div class="toolbar" style="padding-left: 15px;">
				<table cellpadding="0" cellspacing="0"
					style="width: 95%; height: 50px;" fit="true">
					<tr>
						<td><a href="javascript:addItem()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a> 
							<a href="javascript:editItem()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
							<a href="javascript:removeItem()" class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除</a>
							<a href="javascript:refresh()" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
						<td style="text-align: right">
							<a href="javascript:advanceQuery()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div region="center" border="false" style="height: 600;">
			<table id="list_data"></table>
		</div>
		
		<div id="dlg" class="easyui-dialog" style="width:350px;height:230px;"
			closed="true" modal="true" buttons="#dlg-buttons">
			<div style="text-align:center;">
				<form id="myform" method="post">
					<jsp:include page="_yearForm.jsp"></jsp:include>
				</form>
			</div>
			<div id="dlg-buttons" style="text-align:center">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saveItem()">保存</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>
			</div>
		</div>
</body>
</html>