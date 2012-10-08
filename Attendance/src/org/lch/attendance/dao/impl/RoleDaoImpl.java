package org.lch.attendance.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.lch.attendance.dao.RoleDao;
import org.lch.attendance.domain.Role;
import org.springframework.stereotype.Repository;
@Repository("roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
	
	public List<Role> getAllRoles(){
//		Criteria cri = super.getSession().createCriteria("from Role");
//		return cri.list();
		SQLQuery sqlq = super.getSession().createSQLQuery("select * from att_role");
		return (List<Role>)sqlq.list();
	}

//	@Override
//	public Role findById(Integer id) {
//		Role r = super.findById(id);
//		Hibernate.initialize(r);
//		return r;
//	}
	
	

}
