<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基于SSH2框架的高校考勤考核管理系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/index.js"></script>
	
	<%-- menu的CSS样式 --%>
	<link rel="stylesheet" type="text/css"  href="${ctx }/css/menu/menu.css">
	
<script type="text/javascript">

	function edit() {
		$('#winIFrame').attr("src","${ctx}/main/pwd_change.action?flag=header");
		$('#win').window({
			title : '修改密码',
			width : 500,
			height : 200,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			closed : false,
			resizable : false,
			modal : true
		});
	}
</script>
</head>
<body class="easyui-layout">
	
	<%-- 头部div的内容在header.jsp中 --%>
	<div region="north"  split="false" style="height: 95px; width:100%;">
		<iframe src="${ctx}/index/header.action"  height="90px" width="100%"  scrolling="no"  noresize frameborder="0"></iframe>
	</div>

	<%-- 底部div内容在bottom.jsp中 --%>
	<div region="south"  split="true" style="height:60px; ">
		<iframe src="${ctx}/index/bottom.action"  height="50px" width="100%"  scrolling="no" noresize frameborder="0" ></iframe>
	</div>

	<%-- 动态菜单显示 --%>
	${outputTreeStr}
	<div region="center" style="overflow:hidden;">
           <div id="tabs" class="easyui-tabs" fit="true" border="false">
                <div title="首页" style="padding:20px;">
                </div>
           </div>
        </div>
        
<!--         <div id="mm" class="easyui-menu" style="width:150px;">
          <div id="mm-tabclose">关闭</div>
          <div id="mm-tabcloseall">全部关闭</div>
          <div id="mm-tabcloseother">除此之外全部关闭</div>
          <div class="menu-sep"></div>
          <div id="mm-tabcloseright">当前页右侧全部关闭</div>
          <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
          <div class="menu-sep"></div>
          <div id="mm-exit">退出</div>
         </div> -->
	<div id="win">
		<iframe id='winIFrame'   width='100%' height='95%'  frameborder='0'  scrolling='auto' ></iframe>
	</div>
</body>
</html>