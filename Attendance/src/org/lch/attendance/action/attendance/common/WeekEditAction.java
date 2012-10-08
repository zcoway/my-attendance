package org.lch.attendance.action.attendance.common;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Week;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.WeekService;

public class WeekEditAction extends BaseAction {

	@javax.annotation.Resource
	private WeekService weekService;
	
	private Week week;
	
	private Integer weekId;
	@Override
	public String execute() throws Exception {
//		week = weekService.doFindById(getWeekId());
//		System.out.println(week.getUserName());
//		System.out.println(week.getClasName());
//		System.out.println(week.getDeptName());
		return SUCCESS;
	}
	public Week getWeek() {
		return weekService.doFindById(getWeekId());
	}
	public void setWeek(Week week) {
		this.week = week;
	}
	public Integer getWeekId() {
		return weekId;
	}
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
}
