package org.lch.attendance.action.index;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.common.AppContext;
import org.lch.attendance.common.ResourceSort;
import org.lch.attendance.common.SecurityUserHolder;
import org.lch.attendance.common.WebConstant;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.ResourceService;
import org.lch.attendance.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends BaseAction implements ServletRequestAware{
	
	private HttpServletRequest request;
	private static HashMap<String, String> cssLevelMap=new HashMap<String, String>();
	static{
		cssLevelMap.put("1", "nav-top-item");
		cssLevelMap.put("2", "category");
		cssLevelMap.put("3", "nav-item");
	}
	
	private String outputTreeStr ;

	@Autowired
	private ResourceService resourceService;

	private String contextPath;
	
//	private boolean showChinese = true;
	/**
	 * 框架类型，用于抓取总体框架
	 */
	private final String[] MENU_CATEGORY_TYPE = {
			WebConstant.WEB_MENU,
			WebConstant.WEB_MENU_CATEGORY};

	@Override
	public String execute() throws Exception {
		outputTreeStr = "<div region=\"west\" split=\"true\" ";

		contextPath = request.getContextPath();
		// 获取当前用户身份
		User user = SecurityUserHolder.getCurrentUser();
		if (user == null)
			return ERROR;
		
		// 1. 获取整体的Web菜单框架，这与权限无关。并且使框架互相间设置好authorizedChildMenus。这一步中也获得rootMenus
		System.out.println("MENU_CATEGORY_TYPE"+MENU_CATEGORY_TYPE);
		List<Resource> categories = resourceService.doFindByType(MENU_CATEGORY_TYPE);
		List<Resource> roots = resourceService.makeTree(categories, categories);
		
		// 2. 获取用户可访问的item叶子
		List<Resource> menuItems = resourceService.doFindByUserIdAndType(
				user.getId(), WebConstant.WEB_MENU_CATEGORY_ITEM);
		System.out.println("============================================");
		System.out.println("menuItems:"+menuItems);
		System.out.println("============================================");
		System.out.println("categories:"+categories);
		System.out.println("============================================");
		// 3. 将item叶子插入菜单框架的authorizedChildMenus中。<br/>
		resourceService.makeTree(menuItems, categories);
		
		// 4. 使用递归，清除没有子节点的Category
		for(Resource root: roots){
			filterCategory(root);
		}
		int indexLevel=1;
		// 生成输出文件字符串，以后要改成jstl-el的控制方式
		for (Resource resource : roots) {
			String resPath = StringUtils.avoidNull(resource.getResourceValue());
			String resName = StringUtils.avoidNull(resource.getResourceName());
//			if(user.getPreferredLocale()!=null&&!Locale.CHINESE.getLanguage().equals(user.getPreferredLocale().getLanguage())){
//				resName = StringUtils.avoidNull(resource.getNameEn());
//				showChinese=false;
//			}
			String cssClass=cssLevelMap.get(indexLevel+"");
			
			if(resource.getResourceType().equalsIgnoreCase(WebConstant.WEB_MENU)){
				outputTreeStr = outputTreeStr+"class=\""+cssClass+"\" title=\""+resName+"\"><div  class=\"easyui-accordion\" fit=\"true\" border=\"false\">";
			}
			if(checkChildAvailable(resource)){
				processChildResource(resource,indexLevel);
			}
			outputTreeStr = outputTreeStr + "</div>";
		}
		System.out.println("============================================");
		System.out.println(outputTreeStr);
		System.out.println("============================================");
//		outputTreeStr = outputTreeStr.substring(0, outputTreeStr.length()-6);
		return SUCCESS;
	}
	
	/**
	 * 检查该资源是否有子菜单项，如果没有则不需要输出
	 * @param resource
	 * @return
	 */
	private boolean checkChildAvailable(Resource resource){
		if(resource.getResourceType().equalsIgnoreCase(WebConstant.WEB_MENU_CATEGORY_ITEM)) return true;
		boolean result = false;
		List<Resource> child_list = resource.getSortedChildResources();
		if (null != child_list) {
			for (int i = 0; i < child_list.size(); i++) {
				Resource child_resource = child_list.get(i);
				if (child_resource.getResourceType().equalsIgnoreCase(WebConstant.WEB_MENU_CATEGORY)) {
					//也是一个目录节点，直接访问下一层
					result = checkChildAvailable(child_resource);
					if(result)break;
				}else{
					result = true;
					break;
				}
			}
		}
		return result;
	}

	private void processChildResource(Resource resource,int indexLevel) {
		indexLevel++;
		
//		outputTreeStr=outputTreeStr+" <ul class=\"sub-item\">";
		List<Resource> child_list = resource.getSortedChildResources();
		if (null != child_list) {
			Collections.sort(child_list, new ResourceSort(ResourceSort.UP));
			for (int i = 0; i < child_list.size(); i++) {
				Resource child_resource = child_list.get(i);
				String resourcePath = StringUtils.avoidNull(child_resource.getResourceValue());
				String resName="";
				resName= StringUtils.avoidNull(child_resource.getResourceName());
				String cssClass=cssLevelMap.get(indexLevel+"");
				if(child_resource.getResourceType().equalsIgnoreCase(WebConstant.WEB_MENU_CATEGORY )){
					outputTreeStr=outputTreeStr+"<div class=\""+cssClass+"\"  title=\""+resName+"\" >";
				}else{
					outputTreeStr=outputTreeStr+"<div class=\""+cssClass+"\"><a href=\""+resourcePath+"\"><span><li class=\"liyashi\">"+resName+"</li></span></a></div>" ;
				}
				if(checkChildAvailable(child_resource)){
					processChildResource(child_resource,indexLevel);
				}
			}
			outputTreeStr=outputTreeStr+"</div>";
		}
		indexLevel--;
	}

	/**
	 * 该方法不删除自己，而是做以下事：<br/>
	 * 1. 判断子分类是否可删除，若子分类宣称自己可删除，则将其删除
	 * 2. 判断自己是否可删除，如果在第一步后已没有任何子分类和子节点，则返回自己可删除
	 * 3. 否则认为自己不可删除
	 * 
	 * @param category 需要判断的分类节点
	 * @return 这个节点是否可删除
	 */
	private boolean filterCategory(Resource category) {
		List<Resource> childs = category.getSortedChildResources();
		// 1. 判断子分类是否可删除，若子分类宣称自己可删除，则将其删除
		if (childs != null) {
			for (Iterator<Resource> childIter = childs.iterator(); childIter
					.hasNext();) {
				Resource child = childIter.next();
				if (child.getResourceType().equals(
						WebConstant.WEB_MENU_CATEGORY)
						&& filterCategory(child)) { // 是子分类，且可删除
					childIter.remove();
				}
			}
		}

		// 2. 判断自己是否可删除，如果在第一步后已没有任何子分类和子节点，则返回自己可删除
		if (childs == null || childs.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getOutputTreeStr() {
		return outputTreeStr;
	}

	public void setOutputTreeStr(String outputTreeStr) {
		this.outputTreeStr = outputTreeStr;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
