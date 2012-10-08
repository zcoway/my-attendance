package org.lch.attendance.action.system;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONObject;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.ResourceService;
import org.lch.attendance.service.RoleService;
import org.lch.attendance.util.StringUtils;

public class RoleResourceChangeAction extends BaseAction{

	@javax.annotation.Resource
	private RoleService roleService;
	
	@javax.annotation.Resource
	private ResourceService resourceService;
	
	private JSONObject result;
	
	private Role role;
	
	private String resourceIds;

	private String roleId;
	

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("$$$$$$$$$$$$$$$$$$$RoleResourceChangeAction$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		Integer[] ids = StringUtils.String2IntegerArray(resourceIds);
		for(Integer x : ids ){
			System.out.print(x+ "  ");
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$RoleResourceChangeAction$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		Set<Resource> resources = new HashSet<Resource>();
		for(Integer x : ids ){
			resources.add(resourceService.doFindById(x));
		}
		Role role = roleService.doFindById(Integer.parseInt(roleId));
		System.out.println(role.getRoleName());
		role.setResources(resources);
		roleService.doUpdate(role);
		return null;
	}
	
}
