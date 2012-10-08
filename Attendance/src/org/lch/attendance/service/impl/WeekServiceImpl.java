package org.lch.attendance.service.impl;

import java.util.LinkedHashMap;

import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.DetailDao;
import org.lch.attendance.dao.WeekDao;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.domain.Week;
import org.lch.attendance.service.WeekService;
import org.springframework.stereotype.Service;
@Service("weekService")
public class WeekServiceImpl extends GenericServiceImpl<Week> implements
		WeekService {

	@javax.annotation.Resource
	private WeekDao weekDao;
	
	@javax.annotation.Resource
	private DetailDao detailDao;
	
	
	@Override
	public QueryResult<Week> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		
		QueryResult<Week> qr=weekDao.getScrollData(Week.class, firstindex, maxresult, wherejpql, queryParams, orderby);
		return qr;	
	}


	@Override
	public Detail doFindDetailByWeekId(Integer weekId) {
		return detailDao.findById(weekId);
	}

}
