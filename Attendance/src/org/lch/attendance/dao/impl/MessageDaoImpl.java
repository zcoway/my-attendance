package org.lch.attendance.dao.impl;

import java.util.List;

import org.lch.attendance.dao.MessageDao;
import org.lch.attendance.domain.Message;
import org.springframework.stereotype.Repository;
@Repository("messageDao")
public class MessageDaoImpl extends GenericDaoImpl<Message> implements MessageDao {

	@javax.annotation.Resource
	private MessageDao messageDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkMessage(String userName) {
		String hql = "select m from Message m where m.userReceiver.userName=? AND m.messageStatus=false";
		List<Message> messages = messageDao.getSuperHibernateTemplate().find(hql,userName);
		if(messages.size()>0){
			return true;
		}else{
			return false;
		}
		
	}


}
