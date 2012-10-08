package org.lch.attendance.action.leave;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.common.SecurityUserHolder;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.domain.Leave;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.UserService;

public class LeaveAddAction extends BaseAction {

	@javax.annotation.Resource
	private UserService userService;
	
	private User currentUser = SecurityUserHolder.getCurrentUser();
	private Clas c = currentUser.getClas();
	private Leave leave = new Leave();
	@Override
	public String execute() throws Exception {
		leave.setLeaveUser(currentUser.getUserName());
		leave.setLeaveNum(currentUser.getUserNum());
		leave.setLeaveClas(c.getClasName());
		return SUCCESS;
	}

	public Leave getLeave() {
		return leave;
	}
	public List<User> getStudents(){
		List<User> students = userService.doFindClassmates(c.getClasId());
		for(User u: students){
			if(currentUser.getUserName().equalsIgnoreCase(u.getUserName())){
				students.remove(u);
				break;
			}
		}
		return students;
	}
	public List<User> getTeachers(){
		Department d = c.getDepartment();
		return userService.doFindTeachers(d.getDeptId());
	}
	
	private Map<Boolean,String> studentAuth=new HashMap<Boolean,String>();
	public Map<Boolean,String> getStudentAuth() {
		studentAuth.put(true,"已审核");
		return studentAuth;
	}
	private Map<Boolean,String> teacherAuth=new HashMap<Boolean,String>();
	public Map<Boolean,String> getTeacherAuth() {
		teacherAuth.put(true,"已审核");
		return studentAuth;
	}
	
	private Map<Boolean,String> shortMessage=new HashMap<Boolean,String>();
	public Map<Boolean,String> getShortMessage() {
		shortMessage.put(false,"否");
		shortMessage.put(true,"是");
		return shortMessage;
	}
}
