package org.lch.attendance.action.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.DeptService;
import org.lch.attendance.service.RoleService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateUtil;
import org.lch.attendance.util.EncryptUtil;
import org.lch.attendance.vo.UserVo;

public class DeptUpdateAction extends BaseAction {

	@javax.annotation.Resource
	private DeptService deptService;
	
	private Department dept;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}


	@Action(value = "dept_update", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/system/dept_edit.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		Department d = deptService.doFindById(dept.getDeptId());
		if(d!=null){
			d.setDeptDesc(dept.getDeptDesc());
			d.setDeptName(dept.getDeptName());
			deptService.doUpdate(d);
		}
		return SUCCESS;
	}
	
}
