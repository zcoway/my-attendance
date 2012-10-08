package org.lch.attendance.action.attendance.common;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.service.DetailService;

public class DetailDestroyAction extends BaseAction{

	@javax.annotation.Resource
	private DetailService detailService;
	
	private Integer detailId;
	
	@Override
	public String execute() throws Exception {
		
		Detail d = detailService.doFindById(getDetailId());
		if(d!=null)
			detailService.doDelete(d);
		return null;
		
	}
	
	
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

}
