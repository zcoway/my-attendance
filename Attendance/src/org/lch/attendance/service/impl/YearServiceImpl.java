package org.lch.attendance.service.impl;

import java.util.LinkedHashMap;

import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.YearDao;
import org.lch.attendance.domain.Year;
import org.lch.attendance.service.YearService;
import org.springframework.stereotype.Service;
@Service("yearService")
public class YearServiceImpl extends GenericServiceImpl<Year> implements
		YearService {
	
	@javax.annotation.Resource
	private YearDao yeardao;
	
	@Override
	public QueryResult<Year> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		QueryResult<Year> qr=yeardao.getScrollData(Year.class, firstindex, maxresult, wherejpql, queryParams, orderby);
		return qr;	
	}

}
