package org.lch.attendance.action.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.RoleService;

public class RoleSaveAction extends BaseAction {

	@javax.annotation.Resource
	private RoleService roleService;
	
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	@Action(value = "role_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/system/role_add.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		List<Role> list= roleService.doFindAll();
		for(int i=0;i<list.size();i++)
		{
			if(role.getRoleName().trim().equals(list.get(i).getRoleName()))
			{
				addActionMessage("*该角色已存在");
				return INPUT;			
			}
		}
		roleService.doCreate(role);
		return SUCCESS;
	}
	
}
