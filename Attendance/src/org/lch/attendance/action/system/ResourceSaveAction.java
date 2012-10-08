package org.lch.attendance.action.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.service.ResourceService;

public class ResourceSaveAction extends BaseAction {

	@javax.annotation.Resource
	private ResourceService resourceService;
	
	private Resource resource;
	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Action(value = "resource_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/system/resource_add.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		List<Resource> list= resourceService.doFindAll();
		for(int i=0;i<list.size();i++)
		{
			if(resource.getResourceName().trim().equals(list.get(i).getResourceName()))
			{
				addActionMessage("*该资源已存在");
				return INPUT;			
			}
		}
		Integer partenId = resource.getResourceParentId();
		if(partenId!=0 || partenId!=null){
			Resource r = resourceService.doFindById(partenId);
			resource.setResource(r);
		}
		resourceService.doCreate(resource);
		return SUCCESS;
	}
	
}
