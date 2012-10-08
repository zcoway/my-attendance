package org.lch.attendance.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.lch.attendance.dao.UserDao;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.springframework.stereotype.Repository;
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{

	@Override
	public User findByName(String userName) {
		return (User) super.getSession().createCriteria(User.class)
				.add(Restrictions.eq("userName", userName)).list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRolesByUser(User u) {
		List<Role> roles = super.getHibernateTemplate()
				.find("select r from Role r,User u inner join u.roles rs where rs.roleId = r.roleId AND u.userId = ?",
						u.getUserId());
		return roles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findClassmates(Integer clasId) {
		return super.getHibernateTemplate().find("select u from User u where u.clas.clasId=?", clasId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findTeachers(Integer deptId) {
		return super.getHibernateTemplate().find("select u from User u where u.dept.deptId=?", deptId);
	}

	
//	@Override
//	public User findById(Integer id) {
//		User u = super.findById(id);
//		Hibernate.initialize(u.getRoles());
//		return u;
//	}
	
}
