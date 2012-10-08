package org.lch.attendance.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.lch.attendance.dao.DeptDao;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.springframework.stereotype.Repository;
@Repository("deptDao")
public class DeptDaoImpl extends GenericDaoImpl<Department> implements DeptDao{

	@SuppressWarnings("unchecked")
	@Override
	public Department findByName(String deptName) {
		List<Department> depts =  super.getSession().createCriteria(Department.class)
				.add(Restrictions.eq("deptName", deptName)).list();
		return depts.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllGrade(Integer deptId) {
		String hql = "select distinct c.clasGrade from Clas c where c.department.deptId=?";
		return super.getSuperHibernateTemplate().find(hql ,deptId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Clas> findClasByGrade(Integer deptId, String grade) {
		String hql = "select c from Clas c where c.department.deptId=? AND c.clasGrade=?";
		return super.getSuperHibernateTemplate().find(hql, deptId,grade);
	}
	
	
}
