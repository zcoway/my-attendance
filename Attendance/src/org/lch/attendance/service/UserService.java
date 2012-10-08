package org.lch.attendance.service;

import java.util.Collection;
import java.util.List;

import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.vo.UserVo;

public interface UserService extends GenericService<User>{
	/**
	 * 添加用户角色关联关系到指定用户。
	 * 
	 * @param userId
	 *            指定用户ID
	 * @param tsRoles
	 *            角色数据列表
	 */
	void doAddRolesToUser(Integer userId, Collection<Role> roles);
	/**
	 * 更改指定用户的登录密码。
	 * 
	 * @param userId
	 *            指定用户ID
	 * @param newPassword
	 *            新密码
	 */
	void doChangePassword(Integer userId, String newPassword);
	/**
	 * 创建用户数据记录。
	 * 
	 * @param tsUser
	 *            用户数据
	 * @return 创建成功后用户数据，用户ID已被设置。
	 */
	User doCreate(User user);
	/**
	 * 删除指定的用户数据记录。
	 * 
	 * @param tsUser
	 *            用户数据
	 */
//TODO	void doDelete(User user);

	List<User> doFindAll();

	/**
	 * 根据用户ID查找对应的用户数据记录，无对应数据记录时抛出异常。
	 * 
	 * @param id
	 *            用户ID
	 * @return 用户ID对应的用户数据记录。
	 */
	User doFindById(Integer id);
	/**
	 * 根据用户登录名称查找对应的用户数据记录，无对应数据记录时抛出异常。
	 * 
	 * @param username
	 *            用户登录名称
	 * @return 用户登录名称对应的用户数据记录
	 */
	User doFindByUsername(String username);
	/**
	 * 根据用户ID及角色列表，从用户-角色映射关系中删除指定的角色关联关系。
	 * 
	 * @param userId
	 *            用户ID
	 * @param tsRoles
	 *            需要删除关联关系的角色列表
	 */
	void doRemoveRolesFromUser(Integer userId, Collection<Role> roles);
	/**
	 * 保存用户数据。
	 * 
	 * @param tsUser
	 *            需要保存的用户数据
	 */
	void doUpdate(User user);
	/**
	 * 根据员工工号找用户信息
	 * @param employeeCode 员工工号
	 * @return 存在返回对象否则返回null
	 */
	public User doFindByUserName(String userName);
	/**
	 * 根据用户ID删除用户
	 * @param ids
	 */
	public void deleteUserByIds(Integer[] ids);
	
	public void doUpdateUser(User user);	
	/**
	 * 关联用户角色
	 * @param userId
	 * @param roleIds
	 */
	public void addRoleToUser(Integer userId,List<Integer> roleIds);
	/**
	 * 移除用户角色
	 * @param userId
	 * @param roleIds
	 */
	public void delRoleFromUser(Integer userId,List<Integer> roleIds );

	/**
	 * 获得所有的班级
	 * @return
	 */
	List<Clas> getAllClasses();
	
	/**
	 * 保存页面录入的用户信息
	 * @param userVo
	 */
	void doSaveUserByVo(UserVo userVo);
	Clas doFindClass(Integer tmp);
	
	/**
	 * 删除用户
	 * @param user
	 */
	void doDelete(User user);
	
	/**
	 * 通过用户查找所拥有的角色
	 * @param u
	 * @return
	 */
	List<Role> getRolesByUser(User u);	

	/**
	 *  通过Excel批量导入
	 * @param filepath
	 */
	public boolean doImport(String filepath);
//	/**
//	 * 查询角色关联的用户
//	 * @param queryCriteria
//	 * @return
//	 */
//	public PageResult<Map<String, Object>>queryRoleRelatedUsers(QueryCriteria queryCriteria);
//	/**
//	 * 查询可以关联到角色下的用户
//	 * @param criteria
//	 * @return
//	 */
//	public PageResult<Map<String, Object>> doFindUsersForRole(QueryCriteria criteria);
	List<User> doFindClassmates(Integer clasId);
	List<User> doFindTeachers(Integer deptId);
}
