package org.lch.attendance.action.system;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Department;
import org.lch.attendance.service.DeptService;

public class DeptEditAction extends BaseAction {

	@javax.annotation.Resource
	private DeptService deptService;
	
	private Department dept;
	
	private String deptId;

	public Department getDept() {
		return deptService.doFindById(Integer.parseInt(getDeptId()));
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
