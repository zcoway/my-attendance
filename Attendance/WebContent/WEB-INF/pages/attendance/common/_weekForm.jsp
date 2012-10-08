<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<div style="padding:20px;">	
	<table style="padding: 0;border-spacing: 0">
		<tr>
			<td style="text-align: right;"><span>学年学期:</span></td>
			<td><input id="yearName" name="week.yearName" class="easyui-validatebox" required="true" style="width:200px"/>
				<input id="id" type="hidden" name="week.weekId"/></td>
		</tr>
		
		<tr>	
			<td style="text-align: right;"><span>周起始时间:</span></td>
			<td><input id="weekTime" name="week.weekTime" class="easyui-datebox" readonly="readonly" style="width:205px"/></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>班级:</span></td>
			<td><input id="clasName" name="week.clasName"  class="easyui-validatebox" style="width:200px" required="true"/></td>
		</tr>
	</table>
</div>