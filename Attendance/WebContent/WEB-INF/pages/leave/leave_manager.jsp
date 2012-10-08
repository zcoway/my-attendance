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
	<script type="text/javascript" src="${ctx}/js/leave_manager.js"></script>

<script type="text/javascript">
	function getAddUrl(){
		var url = "${ctx}/leave/leave_add.action";
		return url;
	}
	function getDelUrl() {
		var url = "${ctx}/leave/leave_delete.action";
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
<body class="easyui-layout">
	<div region="west" border="false" split="true" fit="auto" style="padding:2px;">
		<table id="list_data"></table>
	</div>
	<div region="center" style="display:none width:0">
	</div>
</body>
</html>