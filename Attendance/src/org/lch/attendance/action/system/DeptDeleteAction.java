package org.lch.attendance.action.system;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Department;
import org.lch.attendance.service.DeptService;

public class DeptDeleteAction extends BaseAction {

	@javax.annotation.Resource
	private DeptService deptService;
	
	private Department dept;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String execute() throws Exception {
		Department d = deptService.doFindById(dept.getDeptId());
		if(d!=null)
			deptService.doDelete(d);
		return null;
	}
	
}
