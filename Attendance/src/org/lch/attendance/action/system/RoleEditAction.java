package org.lch.attendance.action.system;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.RoleService;

public class RoleEditAction extends BaseAction {

	@javax.annotation.Resource
	private RoleService roleService;
	
	private Role role;
	
	
	private String roleId;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return roleService.doFindById(Integer.parseInt(getRoleId()));
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	//设置页面，让select选中
//	public Integer getTmp() {
//		Classes c = classesService.doFindById(user.getClasses().getClassId());
//		return c.getClassId();
//	}
//
//	public void setTmp(Integer tmp) {
//		this.tmp = tmp;
//	}
}
