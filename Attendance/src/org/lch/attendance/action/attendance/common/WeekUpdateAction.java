package org.lch.attendance.action.attendance.common;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.domain.User;
import org.lch.attendance.domain.Week;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.DeptService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.service.WeekService;

public class WeekUpdateAction extends BaseAction {

	@javax.annotation.Resource
	private WeekService weekService;
	
	@javax.annotation.Resource
	private UserService userService;
	
	@javax.annotation.Resource
	private ClasService clasService;
	
	@javax.annotation.Resource
	private DeptService deptService;
	
	private Week week;


	@Action(value = "week_update", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/attendance/common/week_edit.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		Week w = weekService.doFindById(week.getWeekId());
		if(w!=null){
			User u = userService.doFindByUserName(week.getUserName());
			w.setUser(u);
			Clas c = clasService.doFindByName(week.getClasName());
			w.setClas(c);
			Department d = deptService.doFindByName(week.getDeptName());
			w.setDepartment(d);
			w.setWeekTime(week.getWeekTime());
			weekService.doUpdate(w);
		}
		return SUCCESS;
	}


	public Week getWeek() {
		return week;
	}
	public void setWeek(Week week) {
		this.week = week;
	}
	
}
