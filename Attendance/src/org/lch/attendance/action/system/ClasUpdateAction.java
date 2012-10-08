package org.lch.attendance.action.system;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.service.ClasService;

public class ClasUpdateAction extends BaseAction {

	@javax.annotation.Resource
	private ClasService clasService;
	
	private Clas clas;

	public Clas getClas() {
		return clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}


	@Action(value = "clas_update", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/system/clas_edit.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		Clas c = clasService.doFindById(clas.getClasId());
		if(c!=null){
			c.setClasName(clas.getClasName());
			c.setClasDesc(clas.getClasDesc());
			c.setClasGrade(clas.getClasGrade());
			c.setClasDept(clas.getClasDept());
			clasService.doUpdate(c);
		}
		return SUCCESS;
	}
	
}
