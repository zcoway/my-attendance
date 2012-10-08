package org.lch.attendance.action.system;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.service.ClasService;

public class ClasDeleteAction extends BaseAction {

	@javax.annotation.Resource
	private ClasService clasService;
	
	private Clas clas;

	public Clas getClas() {
		return clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}

	@Override
	public String execute() throws Exception {
		Clas c = clasService.doFindById(clas.getClasId());
		if(c!=null){
			System.out.println(c.getClasName());
			clasService.doDelete(c);
			System.out.println("ok");
		}
			
		return null;
	}
	
}
