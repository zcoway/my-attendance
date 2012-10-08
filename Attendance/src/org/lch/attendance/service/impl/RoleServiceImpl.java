package org.lch.attendance.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.RoleDao;
import org.lch.attendance.dao.UserDao;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service("roleService") @Transactional
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService{
	
	@javax.annotation.Resource
	private RoleDao roleDao;
	@javax.annotation.Resource
	private UserDao userDao;
	
	@Override
	public void doAddResourcesToRole(Integer roleId,
			Collection<Resource> resources) {
//		Role role = doFindById(roleId);
//		role.getRoleResources();
//		Set<Resource> r = role.get
//		if (r == null) {
//			r = new HashSet<Resource>();
//			role.setRoleResources(r);
//		}
//		r.addAll(resources);
//		roleDao.save(role);
		
	}

	@Override
	public void doAddUsersToRole(Integer roleId, Collection<User> user) {
//		Role role = doFindById(roleId);
//		Set<User> users = role.getUserRoles();
//		if (users == null) {
//			users = new HashSet<User>();
//			role.setUserRoles(users);
//		}
//		users.addAll(user);
//		roleDao.save(role);
		
	}

	@Override
	public void doRemoveResourcesFromRole(Integer roleId,
			Collection<Resource> resources){
//		Role role = doFindById(roleId);
//		Set<Resource> r = role.getRoleResources();
//		if (r != null) {
//			r.removeAll(resources);
//			roleDao.save(role);
//		}
		
	}

	@Override
	public void doRemoveUsersFromRole(Integer roleId, Collection<User> users) {
//		Role role = doFindById(roleId);
//		Set<User> u = role.getUserRoles();
//		if (u != null) {
//			u.removeAll(users);
//			role.setUserRoles(u);
//			roleDao.update(role);
//		}
	}

	@Override
	public void doUpdateRole(Role role) {
		roleDao.update(role);
	}

	@Override
	public void deleteRoleByIds(Integer[] ids) {
		if(ids==null||ids.length<1){
			return;
		}
		String delete_hql="delete Role t where t.roleId in (:ids)";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("ids", ids);
		this.roleDao.bulkUpdate(delete_hql, params);
	}

	@Override
	public void addUserToRole(Integer roleId, List<Integer> userIds) {
//		if(roleId==null||roleId==null||userIds.isEmpty())
//			return;
//		Role role=this.roleDao.findById(roleId);
//		if(role==null)
//			return;
//		Set<User> users=role.getUserRoles();
//		if(users==null){
//			users=new HashSet<User>();
//		}
//		for(Integer userId:userIds){
//			User user=userDao.findById(userId);
//			users.add(user);
//		}
//		this.roleDao.update(role);
		
	}

	@Override
	public void delUserFromRole(Integer roleId, List<Integer> userIds) {
//		if(roleId==null||userIds==null||userIds.isEmpty())
//			return;
//		Role role=this.roleDao.findById(roleId);
//		if(role==null)
//			return;
//		Set<User> users=new HashSet<User>();
//
//		for(Integer userId:userIds){
//			User user=userDao.findById(userId);
//			users.add(user);
//		}
//		role.getUserRoles().removeAll(users);
//		this.roleDao.update(role);
		
	}

	@Override
	public void doAddResourcesToRole(Integer roleId, String resourceIds) {
		String addhql = "insert into re_role_resource (pk_resource_id,pk_role_id)   (select  r.resource_id ," + roleId
				+" from att_resource r where r.resource_id in (" + resourceIds + "))";
		String dethql = " delete re_role_resource  o where  o.pK_role_id =" + roleId;
		System.out.println("dethql:"+dethql);
		System.out.println("addhql:"+addhql);
		roleDao.nativeUpdateSQL(dethql, null);
		roleDao.nativeUpdateSQL(addhql, null);
	}
	
	@Override
	public QueryResult<Role> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
			
			QueryResult<Role> qr=roleDao.getScrollData(Role.class, firstindex, maxresult, wherejpql, queryParams, orderby);
			return qr;	
	}

	@Override
	public void doDelete(Role role) {
		roleDao.remove(role);
	}

//	@Override
//	public Role doFindById(Integer id) {
//		Role r = super.doFindById(id);
//		Hibernate.initialize(r);
//		return r;
//	}
	
	
}
