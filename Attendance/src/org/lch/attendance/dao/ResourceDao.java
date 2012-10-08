package org.lch.attendance.dao;

import org.lch.attendance.domain.Resource;

public interface ResourceDao extends GenericDao<Resource>{
	public Resource findByName(String name);
}
