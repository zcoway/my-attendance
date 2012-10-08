package org.lch.attendance.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.service.UserService;

public class UserAddAction extends BaseAction{

	@Resource
	private UserService userService;
	
	private List<Clas> clases;
	
	public List<Clas> getClasses() {
		return userService.getAllClasses();
	}
	
	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
}
