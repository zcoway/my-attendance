package org.lch.attendance.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lch.attendance.dao.ResourceDao;
import org.lch.attendance.domain.Resource;
import org.springframework.stereotype.Repository;
@Repository("resourceDao")
public class ResourceDaoImpl extends GenericDaoImpl<Resource> implements ResourceDao{
	@Override
	public Resource findByName(String name) {
		return (Resource) super.getSession().createCriteria(Resource.class).add(Restrictions.eq("resourceDesc", name)).list();
	}

//	@Override
//	public List<Resource> findAll() {
//		List<Resource> resources = super.findAll();
//		Hibernate.initialize(resources);
////		for(int i=0;i<resources.size();i++){
////			Hibernate.initialize(resources.get(i).getRoles());
////		}
//		return resources;
//	}
	
	

}
