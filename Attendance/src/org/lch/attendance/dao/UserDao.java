package org.lch.attendance.dao;

import java.util.List;

import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;


public interface UserDao extends GenericDao<User>{
	
	public User findByName(String userName);

	/**
	 * 通过用户查找所拥有的角色
	 * @param u
	 * @return
	 */
	public List<Role> getRolesByUser(User u);

	public List<User> findClassmates(Integer clasId);

	public List<User> findTeachers(Integer deptId);
	
}
