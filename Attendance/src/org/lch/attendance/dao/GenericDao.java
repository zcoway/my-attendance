package org.lch.attendance.dao;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Filter;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.model.BaseEntity;
import org.springframework.orm.hibernate3.HibernateTemplate;

public interface GenericDao<T extends BaseEntity> {
	/**
	 * 以HQL语句方式进行insert, update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录
	 * 批量更新符合某个条件的记录等等。
	 * 
	 * @param hql
	 *            进行insert, update, delete操作的HQL语句
	 */
	public void bulkUpdate(String hql);

	/**
	 * 以HQL语句方式进行insert, update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录
	 * 批量更新符合某个条件的记录等等。
	 * 
	 * @param hql
	 *            进行insert, update, delete操作的HQL语句
	 * @param params
	 *            HQL语句中参数的值
	 * @return 该语句操作涉及到的数据库记录数
	 */
	public int bulkUpdate(final String hql, final Map<String, ?> params);

	/**
	 * 创建指定T类型的数据库记录 更新obj的主键ID为最新的值
	 * 
	 * @param obj
	 *            T类型的数据库记录实例
	 */
	public void create(T obj);

	/**
	 * 强制Hibernate立刻加载该obj对象数据实例
	 * 
	 * @param obj
	 */
	public void eagerLoad(T obj);

	/**
	 * 开启Hibernate过滤条件
	 * 
	 * @param name
	 * @return
	 */
	public Filter enableFilter(String name);

	/**
	 * 将指定的obj对象数据实例从Hibernate Session关联中脱离
	 * 
	 * @param obj
	 */
	public void evict(T obj);

	/**
	 * 根据样例查找符合条件的T类型数据库记录
	 * 
	 * @param example
	 *            T类型的样例，根据其属性中不为null的信息进行相等的过滤查找
	 * @return 返回符合样例过滤条件的数据库记录
	 */
	public List<T> find(T example);

	/**
	 * 查找所有类型为T的数据库记录
	 * 
	 * @return 所有数据库中指定为T类型的表记录
	 */
	public List<T> findAll();

	/**
	 * 查找所有类型为T的数据库记录
	 * 
	 * @param T
	 *            class类型
	 * @return 所有数据库中指定为T类型的表记录
	 */
	public List<T> findAll(Class<T> T);

	/**
	 * 根据指定ID返回数据库记录
	 * 
	 * @param T
	 *            class类型
	 * @param id
	 *            记录ID，类型为Long，最小为0，最大为999999999
	 * @return 符合指定id的唯一数据库记录，如果没找到会抛出异常
	 */
	public T findById(Class<T> T, Integer id);

	/**
	 * 根据指定ID返回数据库记录
	 * 
	 * @param id
	 *            记录ID，类型为Long，最小为0，最大为999999999
	 * @return 符合指定id的唯一数据库记录，如果没找到会抛出异常
	 */
	public T findById(Integer id);

	/**
	 * 根据指定ID返回数据库记录，但是用一个NO_WAIT锁的方式进行锁定。
	 * 适用于当针对某个对象进行业务操作时（往往具有流程性），在方法入口处就使用该方法读取记录。
	 * 
	 * @param id
	 *            记录ID，类型为Long，最小为0，最大为999999999
	 * @return 符合指定id的唯一数据库记录，如果没找到会抛出异常
	 */
	public T findByIdNoWaitLock(Integer id);

	/**
	 * 使用Hibernate Named Query查找数据库对象
	 * 
	 * @param queryName
	 *            命名在hbm.xml配置文件中的查询语句
	 * @param params
	 *            查询语句的参数值
	 * @return 匹配符合条件的记录列表
	 */

	public List findByNamedQuery(String queryName, Map<String, Object> params);

	/**
	 * 手工主动flush Hibernate缓存数据到数据库中
	 */
	public void flush();

	/**
	 * 将指定的实体加锁
	 * 
	 * @param entity
	 *            实体
	 * @param lock
	 *            Hibernate的锁模式
	 */
	public void lock(T entity, LockMode lock);

	/**
	 * 保存或更新指定T类型的数据库记录，根据obj的主键ID是否为null来自动判断采用insert或者update方法
	 * 如果是insert方法，ID值不会返回
	 * 
	 * @param obj
	 *            T类型的数据库记录实例
	 */
	public void merge(T obj);

	/**
	 * 批量保存或更新指定T类型的数据库记录，根据obj的主键ID是否为null来自动判断采用insert或者update方法
	 * 如果是insert方法，不会更新obj的主键ID为最新的值，因为使用merge()
	 * 
	 * @param objs
	 *            T类型的数据库记录实例集合
	 */
	public void mergeObjects(Collection<T> objs);

	/**
	 * 建议使用HQL模式的queryCount方法，但是某些特殊的数据库操作以SQL语句方式进行更有效率
	 * 根据指定的查找记录数量的SQL语句，返回找到的符合条件的数据库记录数
	 * 
	 * @param countSql
	 *            类似于 select count(*) from ....
	 *            类型的SQL语句，可以通过SqlUtils.parseSqlCount方法生成
	 * @param params
	 *            countSql中对应参数的值
	 * @return 返回符合条件的数据库记录数
	 */
	public int nativeQueryCountSQL(final String countSql,
			final Map<String, ?> params);



	/**
	 * 根据指定的查找记录数量的HQL语句，返回找到的符合条件的数据库记录数
	 * 
	 * @param countHql
	 *            类似于 select count(*) from ....
	 *            类型的SQL语句，可以通过HqlUtils.parseHqlCount方法生成
	 * @param params
	 *            countHql中对应参数的值
	 * @return 返回符合条件的数据库记录数
	 */
	public int queryCount(final String countHql, final Map<String, ?> params);

	/**
	 * 删除指定的数据库记录
	 * 
	 * @param T
	 *            class 类型
	 * @param obj
	 *            数据库记录实例，obj的主键ID不可为null
	 */
	public void remove(Class<T> T, T obj);

	/**
	 * 删除指定的数据库记录
	 * 
	 * @param obj
	 *            数据库记录实例，obj的主键ID不可为null
	 */
	public void remove(T obj);

	/**
	 * 批量删除指定的数据库记录
	 * 
	 * @param objs
	 *            数据库记录实例集合，集合中的每个对象主键ID不可为null
	 */
	public void removeObjects(Collection<T> objs);

	/**
	 * 插入指定T类型的新数据库记录，忽略已有obj的主键ID
	 * 采用insert方法，更新obj的主键ID为最新的值
	 * 如果是带版本管理的对象，则在Flush或事务Commit后自动更新版本为初始版本
	 * 
	 * @param obj
	 *            T类型的数据库记录实例
	 */
	public void save(T obj);
	
	
	/**
	 * 批量保存或更新指定T类型的数据库记录，根据obj的主键ID是否为null来自动判断采用insert或者update方法
	 * 如果是insert方法，则会更新obj的主键ID为最新的值
	 * 
	 * @param objs
	 *            T类型的数据库记录实例集合
	 */
	public void saveObjects(Collection<T> objs);

	/**
	 * 更新指定T类型的数据库记录 如果
	 * 
	 * @param obj
	 *            T类型的数据库记录实例
	 */
	public void update(T obj);

	/**
	 * 使用Hibernate Named Query更新数据库对象
	 * 
	 * @param queryName
	 *            命名在hbm.xml配置文件中的查询语句
	 * @param params
	 *            查询语句的参数值
	 * @return 匹配符合条件的记录列表
	 */
	public int updateByNamedQuery(String queryName, Map<String, Object> params);

	/**
	 * 得到当前hibernate session Factory
	 * 
	 * @return
	 */
	public Session getHibernateSession();

	/**
	 * 得到新的hibernate session Factory
	 * 
	 * @return
	 */
	public Session getNewHibernateSession();

	/**
	 * 清楚cache内容
	 */
	public void clear();
	
	public List<T> queryUseParams(String hql,Object... values);
	
	public List queryByPage(final String hql, final int page, final int pageSize);
	/**
	 * 使用hql语句进行分页查询
	 * @param hql 需要查询的hql语句
	 * @param value 如果hql有一个参数需要传入，value就是传入hql语句的参数
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List queryByPage(final String hql , final Object value , final int page, final int pageSize);
	/**
	 * 使用hql语句进行分页查询
	 * @param hql 需要查询的hql语句
	 * @param values 如果hql有多个个参数需要传入，values就是传入hql的参数数组
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List queryByPage(final String hql, final Object[] values, final int page, final int pageSize);

	int nativeUpdateSQL(String sql, Map<String, ?> params);
	
	/**
	 * @param hql
	 *            查询用的HQL语句
	 * @param params
	 *            HQL语句对应的参数
	 * @return 根据查询条件返回的记录内容。
	 */
	@SuppressWarnings("unchecked")
	public List query(final String hql, final Map<String, ?> params);
//	public List query(final String hql, final Object[] values);
	
	/**
	 * 获取总记录数
	 * @param obj
	 * 		obj为对应的实体类名
	 * @return
	 */
	public Long getTotalCount(final String obj);
	
	
	/**
	 * 获得分页数据
	 * QueryResult<T> 泛型定义在类上。因为需要返回查询的数据List,和查询的总条数，所以需要自定义类型返回2个数据
	 * @param <T>	泛型
	 * @param entityClass 实体类
	 * @param firstIndex 开始索引
	 * @param maxResult 需要获取的记录数
	 * @param wherejpql where条件语句
	 * @param queryParams 条件语句参数
	 * @param orderby 排序,LinkedHashMap先进先出，使用这个是因为先进去的放到第一位，order by key1 desc,key2 asc	
	 * @return
	 */
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult,	String wherejpql,Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult,String wherejpql,Object[] queryParams);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult,LinkedHashMap<String, String> orderby);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass,int firstIndex, int maxResult);
	
	public <T> QueryResult<T> getScrollData(Class<T> entityClass);
	
	public HibernateTemplate getSuperHibernateTemplate();
	
	/**
	 * 统计考勤
	 * @param weekId 参数值-1代表统计全部
	 * @return
	 */
	public List getStatisticsResult(Integer weekId);
	
	public List findWithParams(Integer weekId,String userName);
}
