package org.lch.attendance.service.impl;

import java.util.LinkedHashMap;

import org.hibernate.Hibernate;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.ClasDao;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.service.ClasService;
import org.springframework.stereotype.Service;
@Service("clasService")
public class ClasServiceImpl extends GenericServiceImpl<Clas> implements ClasService{
	@javax.annotation.Resource
	private ClasDao clasDao;

	@Override
	public Clas doFindByName(String className) {
		return clasDao.findByName(className);
	}

	@Override
	public QueryResult<Clas> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		
		QueryResult<Clas> qr=clasDao.getScrollData(Clas.class, firstindex, maxresult, wherejpql, queryParams, orderby);
		return qr;	
	}

	@Override
	public Clas doFindById(Integer id) {
		Clas c =	super.doFindById(id);
		Hibernate.initialize(c);
		return c;
	}

	@Override
	public void doDelete(Clas object) {
		clasDao.remove(object);
	}
	
	
	
	
}
