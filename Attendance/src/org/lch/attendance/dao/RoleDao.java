package org.lch.attendance.dao;

import java.util.List;

import org.lch.attendance.domain.Role;


public interface RoleDao extends GenericDao<Role>{
	public List<Role> getAllRoles();
}
