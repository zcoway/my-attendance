package org.lch.attendance.dao.impl;

import org.lch.attendance.dao.WeekDao;
import org.lch.attendance.domain.Week;
import org.springframework.stereotype.Repository;
@Repository("weekDao")
public class WeekDaoImpl extends GenericDaoImpl<Week> implements WeekDao{
	
//	@Override
//	public Role findById(Integer id) {
//		Role r = super.findById(id);
//		Hibernate.initialize(r);
//		return r;
//	}
	
	

}
