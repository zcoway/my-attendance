package org.lch.attendance.service;

import java.util.List;

import org.lch.attendance.domain.Resource;


/**
 * 系统资源管理
 * @author cgx
 * @version 1.0
 */
public interface ResourceService extends GenericService<Resource>{

//	/**
//	 * 创建一个新资源
//	 * 
//	 * @param tsResource
//	 *            新资源
//	 * @return 创建后的资源
//	 */
//	public Resource doCreate(Resource resource);
//
//	/**
//	 * 批量删除指定资源
//	 * 
//	 * @param resourceIDs
//	 *            资源的数据库ID集合
//	 */
//	public void doDelete(Collection<Integer> resourceIDs);
//
//	/**
//	 * 删除指定资源
//	 * 
//	 * @param resourceID
//	 *            资源的数据库ID
//	 */
//	public void doDelete(Integer resourceID);
//
//	/**
//	 * 查找所有的资源
//	 * 
//	 * @return 所有资源的集合
//	 */
//	public List<Resource> doFindAll();
//
//	/**
//	 * 查找指定ID的资源
//	 * 
//	 * @param resourceID
//	 *            资源的数据库ID
//	 * @return 指定的资源
//	 */
//	public Resource doFindById(Integer resourceID);

	/**
	 * 根据资源类型RESTYPE查找资源，请参见
	 * 
	 * @param resTypes
	 *            资源类型的常量定义字符串
	 * @return 符合条件的资源集合
	 */
	public List<Resource> doFindByType(String... resTypes);

//	/**
//	 * 根据父资源ID，找到所有子资源信息
//	 * 
//	 * @param parentResourceId
//	 *            父资源的数据库ID
//	 * @return 符合条件的子资源集合
//	 */
//	public List<Resource> doFindByParentId(Integer parentResourceId);

//	/**
//	 * 根据父资源ID，找到所有指定类型的子资源信息
//	 * 
//	 * @param parentResourceId
//	 *            父资源的数据库ID
//	 * @param resourceType
//	 *            资源类型 参见<code>itaf.framework.constant.CodeListConstant</code>
//	 * @return 符合条件的子资源集合
//	 */
//	public List<Resource> doFindByParentIdAndType(Integer parentResourceId,
//			String resourceType);

//	/**
//	 * 根据父资源ID，找到所有指定类型的子资源信息
//	 * 
//	 * @param parentResourceId
//	 *            父资源的数据库ID
//	 * @param resourceType
//	 *            资源类型 参见<code>itaf.framework.constant.CodeListConstant</code>
//	 * @return 符合条件的子资源集合
//	 */
//	public List<Resource> doFindByParentIdAndTypes(Integer parentResourceId,
//			String[] resourceType);

	/**
	 * 根据指定用户ID与资源类型找到符合条件的资源集合
	 * 
	 * @param userId
	 *            用户的数据库ID
	 * @param resourceType
	 *            资源类型，参见
	 * @return 符合条件的资源集合
	 */
	public List<Resource> doFindByUserIdAndType(Integer userId,
			String... resourceType);

//	/**
//	 * 更新一个资源
//	 * 
//	 * @param tsResource
//	 *            资源
//	 * @return 更新后的资源
//	 */
//	public void doUpdate(Resource tsResource);
	
	/**
	 * 
	 * 将elements中的元素根据其parentResource,插入其parentResource的sortedChildResources中。<br/>
	 * parentResource需要从structures中找到，如果找不到或者parentResource是null，则将该元素放入根清单中返回<br/>
	 * 需要注意，childMenus需要满足以下规则：同一个父节点的元素应在一起，且已按顺序号排序<br/>
	 * 该方法不会影响两个List的内容，但会影响structures中元素的sortedChildResources
	 * 
	 * @param elements 待分类的元素
	 * @param structures 插入的对象
	 * @return 没有找到根节点或者根节点为空的元素集合
	 */
	public List<Resource> makeTree(
			final List<Resource> elements, final List<Resource> structures);

	public void doDelete(Resource resource);

	public List<Resource> doFindAllGradeOrClas(Integer userId, String resourceType,
			String resourceName);

	public List<Resource> doFindClasByGrade(Integer userId, String resourceType,
			Integer parentId);
	
//	/**
//	 * 取得userId是否拥有resPath的资源
//	 * 
//	 * @param userId
//	 *            用户ID
//	 * @param resPath
//	 *            资源路径
//	 * @return 存在则返回true,否则false
//	 */
//	public boolean doGetHasResourceWithUserIdAndResPath(Integer userId,
//			String resPath);
	
	
//	/**
//	 * 查询资源树的root接点
//	 * @return
//	 */
//	public List<Resource> doFindRootResources();
	
}
