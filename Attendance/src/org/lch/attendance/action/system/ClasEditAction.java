package org.lch.attendance.action.system;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.service.ClasService;

public class ClasEditAction extends BaseAction {

	@javax.annotation.Resource
	private ClasService clasService;
	
	private Clas clas;
	
	private String clasId;

	
	public Clas getClas() {
		return clasService.doFindById(Integer.parseInt(getClasId()));
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}

	public String getClasId() {
		return clasId;
	}

	public void setClasId(String clasId) {
		this.clasId = clasId;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
