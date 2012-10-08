package org.lch.attendance.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.DeptDao;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.service.DeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("deptService") @Transactional
public class DeptServiceImpl extends GenericServiceImpl<Department> implements
		DeptService {
	@javax.annotation.Resource
	private DeptDao deptDao;
	
	
	@Override
	public QueryResult<Department> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
			
			QueryResult<Department> qr=deptDao.getScrollData(Department.class, firstindex, maxresult, wherejpql, queryParams, orderby);
			return qr;	
	}

	@Override
	public Department doFindById(Integer id) {
		Department d = super.doFindById(id);;
		Hibernate.initialize(d);
		return d;
	}

	@Override
	public Department doFindByName(String deptName) {
		return deptDao.findByName(deptName);
	}

	@Override
	public List<String> doFindAllGrade(Integer deptId) {
		return 	deptDao.findAllGrade(deptId);
	}

	@Override
	public List<Clas> doFindClasByGrade(Integer deptId, String grade) {
		return deptDao.findClasByGrade(deptId,grade);
	}

	
//	@Override
//	public void doDelete(Department object) {
//		deptDao.remove(object);
//	}

	
	
}
