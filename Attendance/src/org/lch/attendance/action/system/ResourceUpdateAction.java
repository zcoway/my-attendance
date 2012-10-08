package org.lch.attendance.action.system;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.service.ResourceService;

public class ResourceUpdateAction extends BaseAction {

	@javax.annotation.Resource
	private ResourceService resourceService;
	
	private Resource resource;
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Action(value = "resource_update", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/system/resource_edit.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		Resource r = resourceService.doFindById(resource.getResourceId());
		if(r!=null){
			Integer partenId = resource.getResourceParentId();
			if(partenId!=0 || partenId!=null){
				Resource parent = resourceService.doFindById(partenId);
				r.setResource(parent);
			}
			r.setResourceDesc(resource.getResourceDesc());
			r.setResourceEnabled(resource.isResourceEnabled());
			r.setResourceIsLeaf(resource.isResourceIsLeaf());
			r.setResourceName(resource.getResourceName());
			r.setResourceParentId(resource.getResourceParentId());
			r.setResourceType(resource.getResourceType());
			r.setResourceValue(resource.getResourceValue());
			resourceService.doUpdate(r);
		}
		return SUCCESS;
	}
	
}
