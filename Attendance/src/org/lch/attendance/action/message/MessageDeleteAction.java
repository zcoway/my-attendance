package org.lch.attendance.action.message;

import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Message;
import org.lch.attendance.service.MessageService;

public class MessageDeleteAction extends BaseAction {

	@javax.annotation.Resource
	private MessageService messageService;
	
	private Message message;





	@Override
	public String execute() throws Exception {
		Message m = messageService.doFindById(message.getMessageId());
		if(m!=null){
			System.out.println(m.getSenderName());
			messageService.doDelete(m);
			System.out.println("ok");
		}
			
		return null;
	}
	
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
}
