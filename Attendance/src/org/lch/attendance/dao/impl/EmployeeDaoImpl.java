package org.lch.attendance.dao.impl;
/*
import java.util.List;

import org.lch.attendance.common.MyHibernateDaoSupport;
import org.lch.attendance.dao.EmployeeDao;
import org.lch.attendance.domain.Employee;

public class EmployeeDaoImpl extends MyHibernateDaoSupport implements EmployeeDao{

	@Override
	public void update(Employee employee) {
		getHibernateTemplate()
		.update(employee);
	}

	@Override
	public Employee find(String username) {
		List<Employee> emps = (List<Employee>)getHibernateTemplate()
				.find("from Employee where username = ? " , username);
			if (emps!= null && emps.size() >= 1)
			{
				return emps.get(0);
			}
			return null;
	}

	@Override
	public void delete(String... usernames) {
		for(String username : usernames){
			Employee emp = find(username);
			getHibernateTemplate().delete(emp);
		}
	}

	@Override
	public List<Employee> list() {
		return (List<Employee>)getHibernateTemplate()
				.find("from Employee");
	}

	@Override
	public void save(Employee employee) {
		getHibernateTemplate()
				.persist(employee);
	}

	@Override
	public int queryRowCount() {
		int count = list().size();
		System.out.println("count:"+count);
		return count;
	}
}
*/
