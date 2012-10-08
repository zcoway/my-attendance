package org.lch.attendance.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
@Repository("genericDao")
public class GenericDaoImpl<T extends BaseEntity> extends HibernateDaoSupport implements
		GenericDao<T> {

	/**
	 * @param sessionFactory
	 */
	@Autowired
	public void setHibernateSessionFactory(
			@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void bulkUpdate(String hql) {
		super.getHibernateTemplate().bulkUpdate(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int bulkUpdate(final String hql, final Map<String, ?> params) {
		
		Integer updateCount = (Integer) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws SQLException {
						Query query = session.createQuery(hql);
						query.setProperties(params);
						return new Integer(query.executeUpdate());
					}
				});
		return updateCount.intValue();
	}

	@Override
	public void create(T obj) {
		super.getHibernateTemplate().save(obj);
	}

	@Override
	public void eagerLoad(T obj) {
		super.getHibernateTemplate().initialize(obj);
		
	}

	@Override
	public Filter enableFilter(String name) {
		return getHibernateTemplate().enableFilter(name);
	}

	@Override
	public void evict(T obj) {
		super.getHibernateTemplate().evict(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(T example) {
		return super.getHibernateTemplate().findByExample(example);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
//		List<T> list = super.getHibernateTemplate().loadAll(entityClass);
//		super.getHibernateTemplate().getSessionFactory().getCurrentSession().getTransaction().
//		super.getHibernateTemplate().
//		Hibernate.initialize(list);
		return super.getHibernateTemplate().loadAll(entityClass);
	}

	@Override
	public List<T> findAll(Class<T> T) {
		return super.getHibernateTemplate().loadAll(T);
	}

	@Override
	public T findById(Class<T> T, Integer id) {
		return (T) getHibernateTemplate().load(T, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(Integer id) {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return (T) super.getHibernateTemplate().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByIdNoWaitLock(Integer id) {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return (T) super.getHibernateTemplate().load(entityClass, id,
				LockMode.UPGRADE_NOWAIT);
	}

	@Override
	public List findByNamedQuery(String queryName, Map<String, Object> params) {
		Session session = super.getSession();
		Query query = session.getNamedQuery(queryName);
		query.setProperties(params);
		return query.list();
	}

	@Override
	public void flush() {
		super.getHibernateTemplate().flush();
	}

	@Override
	public void lock(T entity, LockMode lock) {
		super.getHibernateTemplate().lock(entity, lock);
	}

	@Override
	public void merge(T obj) {
		super.getHibernateTemplate().merge(obj);
	}

	@Override
	public void mergeObjects(Collection<T> objs) {
		super.getHibernateTemplate().merge(objs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int nativeQueryCountSQL(final String countSql, final Map<String, ?> params) {
		return ((Integer) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery sqlQuery = session.createSQLQuery(countSql);
						sqlQuery.setProperties(params);
						List list = sqlQuery.list();
						if (list != null && !list.isEmpty()) {
							return ((BigDecimal) list.get(0)).intValue();
						}
						return 0;
					}
				}));
	}

	@Override
	public int queryCount(final String countHql, final Map<String, ?> params) {
		List result_list = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws SQLException {
						Query query = session.createQuery(countHql);
						query.setProperties(params);
						return query.list();
					}
				});
		if (result_list != null && result_list.size() > 0) {
			return ((Integer) result_list.get(0)).intValue();
		} else {
			return 0;
		}
	}
	@Override
	public void remove(Class<T> T, T obj) {
		super.getHibernateTemplate().delete(this.findById(T, obj.getId()));
		
	}

	@Override
	public void remove(T obj) {
		super.getHibernateTemplate().delete(obj);
		
	}

	@Override
	public void removeObjects(Collection<T> objs) {
		super.getHibernateTemplate().deleteAll(objs);
		
	}

	@Override
	public void save(T obj) {
		super.getHibernateTemplate().save(obj);
	}

	@Override
	public void saveObjects(Collection<T> objs) {
		super.getHibernateTemplate().saveOrUpdateAll(objs);
	}

	@Override
	public void update(T obj) {
		super.getHibernateTemplate().update(obj);
	}

	@Override
	public int updateByNamedQuery(String queryName, Map<String, Object> params) {
		Session session = super.getSession();
		Query query = session.getNamedQuery(queryName);
		query.setProperties(params);
		return query.executeUpdate();
	}

	@Override
	public Session getHibernateSession() {
		return super.getSession();
	}

	@Override
	public Session getNewHibernateSession() {
		return super.getSession(true);
	}

	@Override
	public void clear() {
		super.getHibernateTemplate().clear();
	}

	@Override
	public List queryByPage(final String hql, final int page, final int pageSize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List result = session.createQuery(hql)
						.setFirstResult((page - 1) * pageSize)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	@Override
	public List queryByPage(final String hql, final Object value,
			final int page, final int pageSize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				List result = session
						.createQuery(hql)
						.setParameter(0, value)
						.setFirstResult((page - 1) * pageSize)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	@Override
	public List queryByPage(final String hql, final Object[] values,
			final int page, final int pageSize) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				List result = query.setFirstResult((page - 1) * pageSize)
						.setMaxResults(pageSize).list();
				return result;
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryUseParams(String hql,Object... values) {
		return super.getHibernateTemplate().find(hql, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int nativeUpdateSQL(final String sql, final Map<String, ?> params) {
		Integer result = (Integer) super.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery sqlQuery = session.createSQLQuery(sql);
						sqlQuery.setProperties(params);
						return Integer.valueOf(sqlQuery.executeUpdate());
					}
				});
		return result.intValue();
	}

	@Override
	public List query(final String hql, final Map<String, ?> params) {
//	public List query(final String hql, final Object[] values) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				List result = query.setProperties(params).list();
				return result;
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getTotalCount(final String obj) {
		return (Long) getHibernateTemplate().execute(new HibernateCallback()
        {
            public Long doInHibernate(Session session)
                    throws HibernateException, SQLException
            {
                Query query = session.createQuery("SELECT COUNT(*) FROM "+obj);
                return (Long) query.uniqueResult();
            }
        });
	}
	
	/**
	 * 获得分页数据
	 * QueryResult<T> 泛型定义在类上。因为需要返回查询的数据List,和查询的总条数，所以需要自定义类型返回2个数据
	 * @param <T>	泛型
	 * @param entityClass 实体类
	 * @param firstIndex 开始索引 firstIndex和maxResult都为-1时代表不分页
	 * @param maxResult 需要获取的记录数
	 * @param wherejpql where条件语句
	 * @param queryParams 条件语句参数
	 * @param orderby 排序,LinkedHashMap先进先出，使用这个是因为先进去的放到第一位，order by key1 desc,key2 asc	
	 * @return
	 * 
	 * 调用方法
	 * LinkedHashMap<String , String> orderby=new LinkedHashMap<String, String>();
	 * orderby.put("id", "asc");
	 * QueryResult<TestVo> qr=testDAO.getScrollData(TestVo.class, 1, 10,"o.name=? and o.title=?",new Object[]{"a","1"},orderby);
	 * for (TestVo q : qr.getResultlist()) {
	 * 		System.out.println(q.getName());
	 * }
	 * System.out.println(qr.getTotalrecord());
	 */
	@SuppressWarnings("unchecked")	//不检查类型，不然会有黄色线提示错误。
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex, int maxresult,
			String wherejpql,Object[] queryParams,LinkedHashMap<String, String> orderby) {
		
		QueryResult<T> qr = new QueryResult<T>();	//定义保存数据类型
		String entityname = getEntityName(entityClass);	//获取实体类名称，方法下面定义了
		
		String hql="select o from "+entityname+" o " + (wherejpql==null? "" :"where " + wherejpql) + buildOrderby(orderby);
		System.out.println("hql:"+hql);
		qr.setResultlist(getListForPage(hql, firstindex, maxresult, queryParams));	//调用hibernateTemplate的扩张方法进行分页处理
		
		String hql2="select count(o) from "+entityname+" o " + (wherejpql==null? "" :"where " + wherejpql);
		Long total=(Long) getHibernateTemplate().find(hql2,queryParams).get(0);			//查询总记录数
		
		qr.setTotalrecord(total);
		return qr;
	}
	
	
	 /**
	 * HibernateTemplate 只支持 .setMaxResults(int) 方法。
     * 因此，做 Spring+Hibernate 分页处理要使用到一个接口 org.springframework.orm.hibernate3.HibernateCallback
	 * 来灵活操作数据库，该接口中有一个未实现的方法 Object doInHibernate (Session session)，用以获得并利用 session 进行操作(自动创建、销毁)。

     * 分页通用方法
     * @param sql           HQL查询语句
     * @param firstindex    起始记录下标
     * @param maxresult     读取记录数
     * @return              List 结果集
     */
    @SuppressWarnings("unchecked")
	public  List getListForPage(final String hql, final int firstindex, final int maxresult,final Object[] queryParams) {
        try {
            List list = getHibernateTemplate().executeFind(new HibernateCallback() {
				
				@Override
				public Object doInHibernate(Session session) throws HibernateException,SQLException {  
					Query query =  session.createQuery(hql);
					
					if(firstindex!=-1 && maxresult!=-1){	//方便设置-1时不分页
						query.setFirstResult(firstindex);
						query.setMaxResults(maxresult);
					}
					setQueryParams(query, queryParams);		//调用下面方法插入where传递过来的参数
					return  query.list();
				}
			});
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex, int maxresult,LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass,firstindex,maxresult,null,null,orderby);
	}
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex, int maxresult,String wherejpql,Object[] queryParams) {
		return getScrollData(entityClass,firstindex,maxresult,wherejpql,queryParams,null);
	}

	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstindex, int maxresult) {
		return getScrollData(entityClass,firstindex,maxresult,null,null,null);
	}

	public <T> QueryResult<T> getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass,-1,-1);	//主方法定义-1为不分页
	}



	/**
	 * 获取实体类的名称
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	protected <T> String getEntityName(Class<T> entityClass) {
		String entityname=entityClass.getSimpleName();			//如果实体类上面的@Entity(name=xxx)没有指定名称，直接为默认类名称
		Entity entity=entityClass.getAnnotation(Entity.class);	//获取@Entity注解
		System.out.println("entity.name():"+entity.name());
		if(entity.name()!=null&&!"".equals(entity.name())){		//判断注解的name是否为空
			entityname=entity.name();
		}
		return entityname;
	}
	
	
	/**
	 * 组装order by语句
	 * @param orderby
	 * @return  //	order by o.key desc,key2 asc
	 */
	protected String buildOrderby(LinkedHashMap<String, String> orderby){
		StringBuffer orderbyql=new StringBuffer("");
		if(orderby!=null && orderby.size()>0 ){
			orderbyql.append(" order by ");
			for(String key:orderby.keySet()){	//取得Map的Key的集合
				orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");	
			}
			orderbyql.deleteCharAt(orderbyql.length()-1);
		}
		return orderbyql.toString();
	}

	/**
	 * 为Where语句传递参数
	 * @param query
	 * @param queryParams
	 * o.key = ?1 and o.name=?2 这是错误的，JPA的问号索引是从1开始的，而HibernateTemplate是从0开始的，HibernateTemplate执行HQL语句时，HQL的语句中'?'号面是不带数字的
	 * o.key = ? and o.name=? 正确
	 */
	protected void setQueryParams(Query query,Object[] queryParams) {
		if(queryParams!=null && queryParams.length>0){
			for(int i=0;i<queryParams.length;i++){		//JPA的问号索引是从1开始的，而HibernateTemplate是从0开始的	
				query.setParameter(i, queryParams[i]);	//如果是JPA:i+1
			}
		}
	}
	public HibernateTemplate getSuperHibernateTemplate(){
		return super.getHibernateTemplate(); 
	}

	@Override
	public List getStatisticsResult(Integer weekId) {
		List list = null;
		if(weekId!=-1){
			String hql = "select d.user.userName,sum(d.detailLate),sum(d.detailEarly),sum(d.detailQuit)," +
			"sum(d.detailIll),sum(d.detailAffair),sum(d.detailPub) " +
			"from Detail d group by d.user.userName,d.week.weekId having d.week.weekId=?";
			list = getHibernateTemplate().find(hql, weekId);
		}else{
			String hql = "select d.user.userName,sum(d.detailLate),sum(d.detailEarly),sum(d.detailQuit)," +
					"sum(d.detailIll),sum(d.detailAffair),sum(d.detailPub) " +
					"from Detail d group by d.user.userName";
			list = getHibernateTemplate().find(hql);
		}
		return list;
	}
	
	public List findWithParams(Integer weekId,String userName){
		List list = null;
		if(weekId!=-1){
			String hql = "select d.detailClear from Detail d where d.week.weekId=? AND d.user.userName=?";
			list = getHibernateTemplate().find(hql, weekId,userName);
		}
		else{
			String hql = "select d.detailClear from Detail d where d.user.userName=?";
			list = getHibernateTemplate().find(hql, userName);
		}
		return list;
	}
	
}
