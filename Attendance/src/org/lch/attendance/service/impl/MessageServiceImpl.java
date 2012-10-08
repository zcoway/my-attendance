package org.lch.attendance.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.lch.attendance.common.PageResult;
import org.lch.attendance.common.QueryCriteria;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.ClasDao;
import org.lch.attendance.dao.MessageDao;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Message;
import org.lch.attendance.service.MessageService;
import org.springframework.stereotype.Service;
@Service("messageService")
public class MessageServiceImpl extends GenericServiceImpl<Message> implements
		MessageService {
	
	@javax.annotation.Resource
	private MessageDao messageDao;
	
	@Override
	public QueryResult<Message> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		
		QueryResult<Message> qr=messageDao.getScrollData(Message.class, firstindex, maxresult, wherejpql, queryParams, orderby);
		return qr;	
	}

	@Override
	public boolean doCheckMessage(String userName) {
		return messageDao.checkMessage(userName);
	}

	
}
