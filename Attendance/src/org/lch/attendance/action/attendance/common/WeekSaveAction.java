package org.lch.attendance.action.attendance.common;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.User;
import org.lch.attendance.domain.Week;
import org.lch.attendance.domain.Department;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.service.WeekService;
import org.lch.attendance.service.DeptService;
import org.lch.attendance.service.RoleService;
import org.lch.attendance.util.DateUtil;

public class WeekSaveAction extends BaseAction {

	@javax.annotation.Resource
	private WeekService weekService;
	
	@javax.annotation.Resource
	private UserService userService;
	
	@javax.annotation.Resource
	private ClasService clasService;
	
	@javax.annotation.Resource
	private DeptService deptService;
	
	private Week week;

	@Action(value = "week_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/attendance/common/week_add.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		List<Week> list= weekService.doFindAll();
		for(int i=0;i<list.size();i++){
			if(DateUtil.getWeekNum(DateUtil.fomatter(week.getWeekTime())) == DateUtil.getWeekNum(list.get(i).getWeekTime())){
				addActionMessage("*该周已经存在");
				return INPUT;			
			}
		}
		Week w = new Week();
		User u = userService.doFindByUserName(week.getUserName());
		w.setUser(u);
		Clas c = clasService.doFindByName(week.getClasName());
		w.setClas(c);
		Department d = deptService.doFindByName(week.getDeptName());
		w.setDepartment(d);
		//TODO
//		w.setWeekDepend(week.getWeekDepend());
//		w.setWeekYear(week.getWeekYear());
		w.setWeekTime(week.getWeekTime());
		weekService.doCreate(w);
		return SUCCESS;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}
}
