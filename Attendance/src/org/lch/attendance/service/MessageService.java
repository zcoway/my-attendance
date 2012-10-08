package org.lch.attendance.service;

import org.lch.attendance.domain.Message;

public interface MessageService extends GenericService<Message> {

	boolean doCheckMessage(String userName);

}
