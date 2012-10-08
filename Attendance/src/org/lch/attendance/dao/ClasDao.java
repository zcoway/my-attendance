package org.lch.attendance.dao;

import org.lch.attendance.domain.Clas;

public interface ClasDao extends GenericDao<Clas> {

	/**
	 * 通过班级名称查询 
	 * @param userClass
	 * @return
	 */
	Clas findByName(String userClass);

}
