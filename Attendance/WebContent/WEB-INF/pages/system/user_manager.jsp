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
	<script type="text/javascript" src="${ctx}/js/user_manager.js"></script>

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
<body>
<s:form id="myform" action="../system/user_save.action" method="post">
<s:textarea name="user11.userIntro" style="height:40px;width:200px"/>
<input id="userName" name="user11.userName" />
				<input type="button" name="btn" value="test" onclick="document.getElementById('myform').submit()"/>
					
				</s:form>
</body>
</html>