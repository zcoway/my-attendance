package org.lch.attendance.action.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.RoleService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateUtil;
import org.lch.attendance.util.EncryptUtil;
import org.lch.attendance.vo.UserVo;

public class RoleDeleteAction extends BaseAction {

	@javax.annotation.Resource
	private RoleService roleService;
	
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String execute() throws Exception {
		Role r = roleService.doFindById(role.getRoleId());
		if(r!=null)
			roleService.doDelete(r);
		return null;
	}
	
}
