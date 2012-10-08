package org.lch.attendance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.ResourceDao;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("resourceService")
public class ResourceServiceImpl extends GenericServiceImpl<Resource> implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@SuppressWarnings("unchecked")
//	public List<Resource> doFindByType(String... resTypes) {
	public List<Resource> doFindByType(String... resTypes) {
		String hql = "from Resource where resourceType in (:type) order by resourceId asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", resTypes);
//		Object[] params = resTypes;
		return resourceDao.query(hql, params);
	}
	

	@Override
	public List<Resource> makeTree(List<Resource> elements,
			List<Resource> structures) {
		List<Resource> root = new ArrayList<Resource>();
		if (elements.size() == 0) {
			return root;
		}

		for (Resource child : elements) {

			// 首先判断是否是清单中最根部的一层节点
			// 是则加入root，不需要再加入其父节点的authorizedChildMenus
			if (child.getResource() == null
					|| !structures.contains(child.getResource())) {
				root.add(child);

			} else {
				// 其他非根节点需要整理入parent的childs中
				// 由于Hibernate Model可能有多个实例，需要从structures中找
				int index = structures.indexOf(child.getResource());
				Resource parent = structures.get(index);
				List<Resource> parentChilds = parent
						.getSortedChildResources();
				if (parentChilds == null) {
					parentChilds = new ArrayList<Resource>();
					parent.setSortedChildResources(parentChilds);
				}
				parentChilds.add(child);
			}
		}
		return root;
	}


	@Override
	public List<Resource> doFindByUserIdAndType(Integer userId,
			String... resourceType) {
		String hql = "select distinct r from User user join user.roles role join role.resources r "
				+ " where user.userId=:userId and r.resourceType in (:resourceType)"
				+ " order by r.resourceId asc"; //依次按父节点，顺序号排序

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("resourceType", resourceType);
		
		return resourceDao.query(hql, params);
	}
	
	@Override
	public List<Resource> doFindAllGradeOrClas(Integer userId, String resourceType,
			String resourceName) {
		String hql = "select distinct r from User user join user.roles role join role.resources r "
				+ " where user.userId=:userId and r.resourceType in (:resourceType) and r.resource.resourceName=:resourceName"
				+ " order by r.resourceId asc"; //依次按父节点，顺序号排序

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("resourceType", resourceType);
		params.put("resourceName", resourceName);
		
		return resourceDao.query(hql, params);
	}
	
	@Override
	public List<Resource> doFindClasByGrade(Integer userId,
			String resourceType, Integer parentId) {
		String hql = "select distinct r from User user join user.roles role join role.resources r "
				+ " where user.userId=:userId and r.resourceType in (:resourceType) and r.resource.resourceId:=parentId"
				+ " order by r.resourceId asc"; //依次按父节点，顺序号排序

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("resourceType", resourceType);
		params.put("parentId", parentId);
		
		return resourceDao.query(hql, params);
	}
	
	@Override
	public QueryResult<Resource> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
			
			QueryResult<Resource> qr=resourceDao.getScrollData(Resource.class, firstindex, maxresult, wherejpql, queryParams, orderby);
			return qr;	
	}


	@Override
	public void doDelete(Resource resource) {
		resourceDao.remove(resource);
	}


//	@Override
//	public Resource doFindById(Integer id) {
//		Resource r = super.doFindById(id);
//		Hibernate.initialize(r);
//		return r;
//	}
	
	
}
