package org.lch.attendance.dao.impl;

import java.util.List;

import org.lch.attendance.dao.DetailDao;
import org.lch.attendance.domain.Detail;
import org.springframework.stereotype.Repository;
@Repository("detailDao")
public class DetailDaoImpl extends GenericDaoImpl<Detail> implements DetailDao {

	@javax.annotation.Resource
	private DetailDao detailDao;
	@Override
	public int countAttUser(Integer weekId) {
		String hql = "select count(distinct d.user) from Detail d where d.week.weekId=?";
		List list = detailDao.getSuperHibernateTemplate().find(hql,weekId);
		Integer tmp = -1; 
		tmp = (Integer) list.get(0);
		return tmp;
	}
	
	public int countAttUser() {
		String hql = "select count(distinct d.user) from Detail d";
		List list = detailDao.getSuperHibernateTemplate().find(hql);
		return Integer.parseInt((list.get(0)).toString()) ;
	}
	

}
