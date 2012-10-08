package org.lch.attendance.action.system;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.ResourceService;
import org.lch.attendance.service.RoleService;

public class ResourceEditAction extends BaseAction {

	@javax.annotation.Resource
	private ResourceService resourceService;
	
	private Resource resource;
	
	public Resource getResource() {
		return resourceService.doFindById(Integer.parseInt(getResourceId()));
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
	private String resourceId;
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
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
