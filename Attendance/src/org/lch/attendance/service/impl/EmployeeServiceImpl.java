package org.lch.attendance.service.impl;
/*
import java.util.List;

import org.lch.attendance.common.PageInfo;
import org.lch.attendance.common.PageResultSet;
import org.lch.attendance.dao.EmployeeDao;
import org.lch.attendance.domain.Employee;
import org.lch.attendance.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}


	@Override
	public boolean add(Employee employee) {
		employeeDao.save(employee);
		return true;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> emps = employeeDao.list();
		return emps;
	}

	@Override
	public PageResultSet<Employee> queryByPage(int offset, int pageSize) {
		int totalRow = employeeDao.queryRowCount(); // �����ܼ�¼����
		System.out.println("service-offset:"+offset);
		PageInfo pageinfo = new PageInfo(totalRow, pageSize, offset);
		//��ȡ��ҳ�ļ�¼
		List<Employee> list = employeeDao.queryByPage("from Employee", offset, pageSize); 
		PageResultSet<Employee> pageResultSet = new PageResultSet<Employee>();
		pageResultSet.setList(list);
		pageResultSet.setPageInfo(pageinfo);
		return pageResultSet;
	}

}
*/
