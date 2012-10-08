package org.lch.attendance.vo;

import java.util.Date;

public class WeekVo {
	private Integer weekId;
	
	private Date weekTime;
	
	private String deptName;
	
	private String clasGrade;
	
	private String clasName;

	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public Date getWeekTime() {
		return weekTime;
	}

	public void setWeekTime(Date weekTime) {
		this.weekTime = weekTime;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getClasGrade() {
		return clasGrade;
	}

	public void setClasGrade(String clasGrade) {
		this.clasGrade = clasGrade;
	}

	public String getClasName() {
		return clasName;
	}

	public void setClasName(String clasName) {
		this.clasName = clasName;
	}
	
}
