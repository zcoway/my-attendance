package org.lch.attendance.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.lch.attendance.common.PageResult;
import org.lch.attendance.common.QueryCriteria;
import org.lch.attendance.common.QueryResult;

public interface GenericService<T> {
	
	/**
	 * 查找所有纪录
	 * @return 含有PO的list
	 */
	List<T> doFindAll();

	/**
	 * 根据PK查找对象
	 * @param id PK的值
	 * @return PO对象
	 */
	T doFindById(Integer id);

	/**
	 * 更新PO， 使用merge方法
	 * @param object PO对象
	 */
	void doUpdate(T object);

	/**
	 * 创建新纪录，使用saveOrUpdate, 新建的对象的ID会返回
	 * @param object 需要创建的PO
	 * @return 含有自动生成ID后的PO
	 */
	T doCreate(T object);

	/**
	 * 根据PO， 删除纪录
	 * @param object 需要删除的PO
	 */
	void doDelete(T object);

	/**
	 * 根据页面输入条件进行查询，并进行分页
	 * @param criteria 从页面传入查询条件信息
	 * @return 查询结果，带有分页信息
	 */
	
	/**
	 * 根据PO， 删除纪录
	 * @param object 需要删除的PO
	 */
	void doDeleteAll(Integer[] ids);
	
	/**
	 * 根据页面输入条件进行查询，并进行分页
	 * @param criteria 从页面传入查询条件信息
	 * @return 查询结果，带有分页信息
	 */
	PageResult<T> doFindByCriteria(QueryCriteria criteria);
	
	/**
	 * 获取记录总数
	 * @param obj
	 * @return
	 */
	Long getTotalCount(String obj);
	
	
	public QueryResult<T> getScrollData(int firstindex, int maxresult,String wherejpql,Object[] queryParams,LinkedHashMap<String, String> orderby);
}
