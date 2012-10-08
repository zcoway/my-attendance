package org.lch.attendance.dao;

import org.lch.attendance.domain.Message;

public interface MessageDao extends GenericDao<Message> {

	boolean checkMessage(String userName);

}
