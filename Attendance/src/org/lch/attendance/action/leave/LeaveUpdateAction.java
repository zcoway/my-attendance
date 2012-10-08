package org.lch.attendance.action.leave;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Leave;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.LeaveService;
import org.lch.attendance.service.UserService;

public class LeaveUpdateAction extends BaseAction {

	@javax.annotation.Resource
	private LeaveService leaveService; 
	@javax.annotation.Resource
	private UserService userService;
	
	private Leave leave;
	
	private Integer leaveId;


	@Action(value = "leave_update", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/leave/leave_edit.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		Leave l = leaveService.doFindById(getLeaveId());
		if(leave.getLeaveFlag()!=null && leave.getLeaveFlag())
			l.setLeaveFlag(true);
		
		if(leave.getLeaveStatus()!= null && leave.getLeaveStatus())
			l.setLeaveStatus(true);
		leaveService.doUpdate(l);
//		if(m!=null){
//			m.setMessageContent(message.getMessageContent());
//			m.setMessageTime(new Date());
//			m.setMessageTitle(message.getMessageTitle());
//			User receiver = userService.doFindByUserName(message.getSenderName());
//			m.setUserReceiver(receiver);
//			
//		}
		return SUCCESS;
	}


	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
	
}
