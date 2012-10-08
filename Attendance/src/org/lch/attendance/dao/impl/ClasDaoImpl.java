package org.lch.attendance.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lch.attendance.dao.ClasDao;
import org.lch.attendance.domain.Clas;
import org.springframework.stereotype.Repository;
@Repository("clasDao")
public class ClasDaoImpl extends GenericDaoImpl<Clas> implements ClasDao{

	@SuppressWarnings("unchecked")
	@Override
	public Clas findByName(String clasName) {
		List<Clas> cs =  super.getSession().createCriteria(Clas.class)
								.add(Restrictions.eq("clasName", clasName)).list();
		return cs.get(0);
	}

	@Override
	public void remove(Clas obj) {
		super.getHibernateTemplate().delete(obj);
	}
	
}
