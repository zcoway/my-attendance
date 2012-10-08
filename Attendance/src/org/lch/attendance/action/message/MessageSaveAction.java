package org.lch.attendance.action.message;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.common.SecurityUserHolder;
import org.lch.attendance.domain.Message;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.MessageService;
import org.lch.attendance.service.UserService;

public class MessageSaveAction extends BaseAction {

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




	@Action(value = "message_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
			 @Result(name="input",location="/WEB-INF/pages/message/message_add.jsp")
			 })	
	@Override
	public String execute() throws Exception {
		List<Message> list= messageService.doFindAll();
		for(int i=0;i<list.size();i++)
		{
			if(message.getMessageTitle().trim().equals(list.get(i).getMessageTitle()))
			{
				addActionMessage("*该标题已存在");
				return INPUT;			
			}
		}
		User userSender = SecurityUserHolder.getCurrentUser();
		message.setUserSender(userSender);
		User userReceiver = userService.doFindByUserName(message.getReceiverName());
		message.setUserReceiver(userReceiver);
		message.setMessageTime(new Date());
		messageService.doCreate(message);
		return SUCCESS;
	}
	
}
