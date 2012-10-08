<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工表</title>
</head>
<body>
	<table width="80%" border="0" align="center" cellspacing="1"
		bgcolor="#cccccc">
		<tr>
			<td>姓名</td>
			<td>性别</td>
			<td>密码</td>
		</tr>
		<tr>
			<s:iterator value="pageResultSet.list" status="index">
				<s:if test="#index.odd == true">
					<tr style="background-color: #dddddd" class="pt9" height="24">
				</s:if>
				<s:else>
					<tr class="pt9" height="24">
				</s:else>
				<td><s:property value="username" /></td>
				<td><s:property value="gender" /></td>
				<td><s:property value="password" /></td>
			</s:iterator>
		</tr>
		</table>
		<div>
			   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="line_table">
                <tr>
                   <td align="left">选择： 全选 - 反选 - 不选 </td>
                   <td align="right">
                                                     页码:&nbsp;&nbsp;<s:property value="pageResultSet.pageInfo.currentPage" />&nbsp;/&nbsp;
                                    <s:property value="pageResultSet.pageInfo.totalPage"/> &nbsp;&nbsp;&nbsp;                                       
                   <s:if test="pageResultSet.pageInfo.bof">
                                                             首页    第一页
                   </s:if>
                   <s:else>
                     <a href="viewEmp.action?offset=1">首页</a>
                     <a href="viewEmp.action?offset=<s:property value="pageResultSet.pageInfo.currentPage-1"/>">上一页</a>
                   </s:else>
                   <s:if test="pageResultSet.pageInfo.eof">
                                                         下一页   末页
                   </s:if>
                   <s:else>                                                              
                      <a href="viewEmp.action?offset=<s:property value="pageResultSet.pageInfo.currentPage+1"/>">下一页</a> 
                      <a href="viewEmp.action?offset=<s:property value="pageResultSet.pageInfo.totalPage"/>">末页</a>
                   </s:else>
                   </td>
                </tr>
                </table>
       </div>
<%-- 			<div>
				<ul>
					<li><a href='viewEmp.action?offset=1'>首页</a></li>
					<li><a
						href='viewEmp.action?offset=<s:property value="%{offset-1}"/>'>
							上一页</a></li>
					<li><a
						href='viewEmp.action?offset=<s:property value="%{offset+1}"/>'>下一页</a></li>
					<li><a href='softlist.action?offset=2>'>末页</a></li>
					<li><span class="pageinfo">第<strong><s:property
									value="#request.pageNow" /></strong>页
					</span></li>
					<li><span class="pageinfo">共<strong><s:property
									value="#request.pageCount" /></strong>页
					</span></li>
				</ul>
			</div>
 --%>
</body>
</html>