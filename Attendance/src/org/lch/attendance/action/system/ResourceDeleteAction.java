package org.lch.attendance.action.system;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.service.ResourceService;

public class ResourceDeleteAction extends BaseAction {

	@javax.annotation.Resource
	private ResourceService resourceService;
	
	private Resource resource;
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public String execute() throws Exception {
		Resource r = resourceService.doFindById(resource.getResourceId());
		if(r!=null)
			resourceService.doDelete(r);
		return null;
	}
	
}
