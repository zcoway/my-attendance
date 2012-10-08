package org.lch.attendance.dao;

import org.lch.attendance.domain.Detail;

public interface DetailDao extends GenericDao<Detail> {
	public int countAttUser(Integer weekId);
	public int countAttUser();
}
