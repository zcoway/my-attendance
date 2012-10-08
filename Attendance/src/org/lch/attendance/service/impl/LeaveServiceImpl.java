package org.lch.attendance.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.lch.attendance.common.PageResult;
import org.lch.attendance.common.QueryCriteria;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.LeaveDao;
import org.lch.attendance.domain.Leave;
import org.lch.attendance.domain.Leave;
import org.lch.attendance.service.LeaveService;
import org.springframework.stereotype.Service;
@Service("leaveService")
public class LeaveServiceImpl extends GenericServiceImpl<Leave> implements
		LeaveService {

	@javax.annotation.Resource
	private LeaveDao leaveDao;
	
	@Override
	public QueryResult<Leave> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		
		QueryResult<Leave> qr=leaveDao.getScrollData(Leave.class, firstindex, maxresult, wherejpql, queryParams, orderby);
		return qr;	
	}

}
