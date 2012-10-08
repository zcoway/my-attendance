package org.lch.attendance.dao;

import java.util.List;

import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;

public interface DeptDao extends GenericDao<Department> {
	public Department findByName(String deptName);

	public List<String> findAllGrade(Integer deptId);

	public List<Clas> findClasByGrade(Integer deptId, String grade);
}
