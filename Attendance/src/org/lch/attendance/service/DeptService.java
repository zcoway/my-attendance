package org.lch.attendance.service;

import java.util.List;

import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;

public interface DeptService extends GenericService<Department> {
	Department doFindByName(String deptName);

	List<String> doFindAllGrade(Integer deptId);

	List<Clas> doFindClasByGrade(Integer deptId, String grade);
}
