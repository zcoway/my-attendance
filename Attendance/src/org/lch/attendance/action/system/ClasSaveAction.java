package org.lch.attendance.action.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.DeptService;
import org.lch.attendance.service.RoleService;

public class ClasSaveAction extends BaseAction {

	@javax.annotation.Resource
	private ClasService clasService;
	
	private Clas clas;

	public Clas getClas() {
		return clas;
	}

	public void setClas(Clas clas) {
		this.clas = clas;
	}


	@Action(value = "clas_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/system/clas_add.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		List<Clas> list= clasService.doFindAll();
		for(int i=0;i<list.size();i++)
		{
			if(clas.getClasName().trim().equals(list.get(i).getClasName()))
			{
				addActionMessage("*该班级已存在");
				return INPUT;			
			}
		}
		clasService.doCreate(clas);
		return SUCCESS;
	}
	
}
