package org.lch.attendance.action.message;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Message;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.MessageService;
import org.lch.attendance.service.UserService;

public class MessageUpdateAction extends BaseAction {

	@javax.annotation.Resource
	private MessageService messageService;
	@javax.annotation.Resource
	private UserService userService;
	
	private Message message;
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}


	@Action(value = "message_update", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/message/message_edit.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		Message m = messageService.doFindById(message.getMessageId());
		if(m!=null){
			m.setMessageContent(message.getMessageContent());
			m.setMessageTime(new Date());
			m.setMessageTitle(message.getMessageTitle());
			User receiver = userService.doFindByUserName(message.getSenderName());
			m.setUserReceiver(receiver);
			
		}
		return SUCCESS;
	}
	
}
