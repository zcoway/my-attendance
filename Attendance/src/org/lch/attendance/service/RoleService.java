package org.lch.attendance.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.lch.attendance.common.QueryResult;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;

public interface RoleService {
	
	/**
	 * 根据指定的角色ID，将传入的系统资源列表与对应的角色建立映射关系。
	 * 
	 * @param roleId
	 *            角色ID
	 * @param resources
	 *            系统资源列表
	 */
	void doAddResourcesToRole(Integer roleId, Collection<Resource> resources);

	/**
	 * 根据指定的角色ID，将传入的用户列表与对应的角色建立映射关系。
	 * 
	 * @param roleId
	 *            角色ID
	 * @param user
	 *            用户列表
	 */
	void doAddUsersToRole(Integer roleId, Collection<User> user);

	/**
	 * 创建角色数据记录。
	 * 
	 * @param role
	 *            角色数据
	 * @return 创建成功则返回对应记录，失败则抛出异常。
	 */
	Role doCreate(Role role);

	/**
	 * 删除指定的角色数据记录。
	 * 
	 * @param role
	 *            角色数据
	 */
	void doDelete(Role role);

	/**
	 * 检索所有角色数据。
	 * 
	 * @return 所有角色数据的集合
	 */
	List<Role> doFindAll();

	/**
	 * 根绝角色ID查找角色数据，无对应数据时抛出异常。
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 角色ID对应角色数据
	 */
	Role doFindById(Integer roleId);

	/**
	 * 从角色资源映射关系中删除传入的角色ID和资源列表对应的映射关系。
	 * 
	 * @param roleId
	 *            角色ID
	 * @param resources
	 *            资源集合
	 */
	void doRemoveResourcesFromRole(Integer roleId,
			Collection<Resource> resources);

	/**
	 * 从用户角色映射关系中删除传入的角色ID和用户列表对应的映射关系。
	 * 
	 * @param roleId
	 * @param users
	 */
	void doRemoveUsersFromRole(Integer roleId, Collection<User> users);

	/**
	 * 更新指定的角色数据。
	 * 
	 * @param role
	 *            角色数据。
	 */
	
	void doUpdate(Role role);
	
	public void doUpdateRole(Role roleVo);
	
	/**
	 * 根据用户ID删除用户
	 * @param ids
	 */
	public void deleteRoleByIds(Integer[] ids);
	
	/**
	 * 关联用户角色
	 * @param roleId
	 * @param userIds
	 */
	public void addUserToRole(Integer roleId,List<Integer> userIds );
	
	/**
	 * 移除用户角色
	 * @param userId
	 * @param roleIds
	 */
	public void delUserFromRole(Integer roleId,List<Integer> userIds );
	
	
	public void doAddResourcesToRole(Integer roleId, String resourceIds);
	
	/**
	 * 分页查询
	 * @param firstindex
	 * @param maxresult
	 * @param wherejpql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public QueryResult<Role> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) ;
}
