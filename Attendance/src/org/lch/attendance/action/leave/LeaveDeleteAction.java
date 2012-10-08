
package org.lch.attendance.action.leave;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Leave;
import org.lch.attendance.domain.Message;
import org.lch.attendance.service.LeaveService;
import org.lch.attendance.service.MessageService;

public class LeaveDeleteAction extends BaseAction {

	@javax.annotation.Resource
	private LeaveService leaveService;
	
	private Leave leave;
	
	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}


	@Override
	public String execute() throws Exception {
		Leave l = leaveService.doFindById(leave.getLeaveId());
		if(l!=null){
			leaveService.doDelete(l);
			System.out.println("delete leave ok");
		}
			
		return null;
	}
}
