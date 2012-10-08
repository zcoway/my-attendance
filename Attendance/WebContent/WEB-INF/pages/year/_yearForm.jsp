<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div style="padding:20px;">	
	<table style="padding: 0;border-spacing: 0">
		<tr>
			<td style="text-align: right;"><span>学年:</span></td>
			<td><input id="yearName" name="year.yearName" class="easyui-validatebox" required="true" style="width:200px"/>
				<input id="yearId" type="hidden" name="year.yearId"/></td>
		</tr>
		
		<tr>	
			<td style="text-align: right;"><span>学期:</span></td>
			<td><s:radio name="year.yearDetail" list="#{'up':'上学期','down':'下学期'}" id="yearDetail"/></td>
		</tr>
			
		<tr>
			<td style="text-align: right;"><span>开学时间</span></td>
			<td><input id="yearTime" name="year.yearTime" class="easyui-datebox" readonly="readonly" style="width:205px"/></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>学年描述</span></td>
			<td><textarea name="year.yearDesc" style="height:40px;width:200px" id="yearDesc"></textarea></td>
		</tr>
	</table>
</div>