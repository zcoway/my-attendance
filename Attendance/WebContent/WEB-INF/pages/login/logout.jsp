<%@ page language="java" pageEncoding="UTF-8"%>
<head>
<script language="javascript">
	var mainFrame = window.parent; <%--logout 作用对象--%>
	if (mainFrame) {
		var hrf = location.href;
		var ctx = "${pageContext.request.contextPath}";
		var index = hrf.indexOf(ctx);
		var dest = hrf.substring(index+ctx.length);
		parent.location.replace(ctx+"/login.action");
	}
	mainFrame.location.reload();
</script>
</head>
