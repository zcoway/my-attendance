package org.lch.attendance.action.message;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Message;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.MessageService;

public class MessageEditAction extends BaseAction {

	@javax.annotation.Resource
	private MessageService messageService;
	
	private Message message;
	
	private Integer messageId;
	
	


	@Override
	public String execute() throws Exception {
		message = messageService.doFindById(getMessageId());
		message.setMessageStatus(true);
		messageService.doUpdate(message);
		return SUCCESS;
	}
	
	//getter & setter
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
}
