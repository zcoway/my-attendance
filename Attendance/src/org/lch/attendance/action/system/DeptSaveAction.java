package org.lch.attendance.action.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Department;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.DeptService;
import org.lch.attendance.service.RoleService;

public class DeptSaveAction extends BaseAction {

	@javax.annotation.Resource
	private DeptService deptService;
	
	private Department dept;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Action(value = "dept_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/system/dept_add.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		List<Department> list= deptService.doFindAll();
		for(int i=0;i<list.size();i++)
		{
			if(dept.getDeptName().trim().equals(list.get(i).getDeptName()))
			{
				addActionMessage("*该角色已存在");
				return INPUT;			
			}
		}
		deptService.doCreate(dept);
		return SUCCESS;
	}
	
}
