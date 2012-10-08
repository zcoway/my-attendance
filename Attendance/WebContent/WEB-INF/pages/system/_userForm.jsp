<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div style="padding:20px;">	
	<table style="padding: 0;border-spacing: 0">
		<tr>
			<td style="text-align: right;"><span>用户名:</span></td>
			<td><input id="userName" name="user.userName" class="easyui-validatebox" required="true" style="width:200px"/>
				<input id="id" type="hidden" name="user.userId"/></td>
		</tr>
		
		<tr>	
			<td style="text-align: right;"><span>学号:</span></td>
			<td><input id="userNum" name="user.userNum"  class="easyui-validatebox" style="width:200px" required="true"/></td>
		</tr>
			
		<tr>
			<td style="text-align: right;"><span>密码:</span></td>
			<td><input id="userPwd" type="password" name="user.userPwd"  class="easyui-validatebox" style="width:200px" required="true"/></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>性别:</span></td>
			<td><s:radio name="user.genderText" list="#{'男':'男','女':'女'}" id="genderText"/></td>
		</tr>
		
		<tr>	
			<td style="text-align: right;"><span>生日:</span></td>
			<td><input id="birthdayText" name="user.birthdayText" class="easyui-datebox" readonly="readonly" style="width:205px"/></td>
		</tr>
		
		<tr>	
			<td style="text-align: right;"><span>QQ:</span></td>
			<td><input id="userQq" name="user.userQq" class="easyui-numberbox" style="width:200px"/></td>
		</tr>
			
		<tr>
			<td style="text-align: right;"><span>邮箱地址:</span></td>
			<td><input id="userEmail" name="user.userEmail" class="easyui-validatebox" required="true" validType="email" style="width:200px"/></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>手机号码:</span></td>
			<td><input id="userTel" name="user.userTel" class="easyui-numberbox" required="true" style="width:200px"/></td>
		</tr>
		
		<tr>
			<td style="text-align: right;"><span>所属班级:</span></td>
			<td><input id="clasName" class="easyui-combotree" name="user.clasName" required="true" style="width:205px"></td>
		</tr>	
		<tr>			
			<td style="text-align: right;"><span>用户照片:</span></td>
			<td><input type="file" name="user.userPortrait" style="width:205px"/></td>
		</tr>
		<tr>
			<td style="text-align: right;"><span>用户爱好:</span></td>
			<td><input type="text" name="user.userHobby" class="easyui-validatebox" style="width:200px"/></td>
		</tr>
		<tr>
			<td style="text-align: right;"><span>个人简介:</span></td>
			<td><textarea name="user.userIntro" style="height:40px;width:200px"></textarea></td>
		</tr>
	</table>
</div>