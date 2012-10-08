package org.lch.attendance.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.SessionFactory;
import org.lch.attendance.common.PageResult;
import org.lch.attendance.common.PageResultSet;
import org.lch.attendance.common.QueryCriteria;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.model.BaseEntity;
import org.lch.attendance.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("genericService") @Transactional
public class GenericServiceImpl<T extends BaseEntity> implements GenericService<T>{
	@Autowired
	protected GenericDao<T> genericDao; 
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override @SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<T> doFindAll() {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return genericDao.findAll(entityClass);
	}

	@Override @SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public T doFindById(Integer id) {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return (T) genericDao.findById(entityClass, id);
	}

	@Override
	public void doUpdate(T object) {
		genericDao.update(object);
		
	}

	@Override
	public T doCreate(T object) {
		genericDao.create(object);
		return object;
	}

	@Override
	public void doDelete(T object) {
		genericDao.remove(object);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doDeleteAll(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return;
		}
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		String className = entityClass.getName();
		String inClause = StringUtils.join(ids, ',');
		StringBuffer hql = new StringBuffer();
		hql.append("delete from ").append(className).append(" t ");
		hql.append("where t.id in (").append(inClause).append(")");
		genericDao.bulkUpdate(hql.toString());
		genericDao.flush();
	}

	@Override
	public PageResult<T> doFindByCriteria(QueryCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalCount(String obj) {
		return genericDao.getTotalCount(obj);
	}

	@Override
	public QueryResult<T> getScrollData(int firstindex, int maxresult,
			String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return null;
	}
}
