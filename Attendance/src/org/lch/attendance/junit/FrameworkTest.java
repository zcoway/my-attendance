package org.lch.attendance.junit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.common.ResourceSort;
import org.lch.attendance.common.SecurityUserHolder;
import org.lch.attendance.common.WebConstant;
import org.lch.attendance.dao.ResourceDao;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.ResourceService;
import org.lch.attendance.util.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FrameworkTest {

//	private static RoleDao roleDao;
//	private static UserDao userDao;
	private static ResourceDao resourceDao;
	private static ResourceService resourceService;
	
	private static HashMap<String, String> cssLevelMap=new HashMap<String, String>();
	static{
		cssLevelMap.put("1", "nav-top-item");
		cssLevelMap.put("2", "category");
		cssLevelMap.put("3", "nav-item");
	}
	
	private static String outputTreeStr = "<div region=\"west\" split=\"true\" ";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
//		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		resourceDao = (ResourceDao)act.getBean("resourceDao");
		resourceService = (ResourceService) act.getBean("resourceService");
//		userDao = (UserDao)act.getBean("userDao");
//		roleDao = (RoleDao) act.getBean("roleDao");
	}
	
	@Test
	public void testResourceDao(){
		List<Resource> res = resourceDao.findAll();
		for(Resource r : res){
			System.out.println(r.getResourceDesc());
		}
	}
	@Test
	public void testResourceService(){
		String[] resTypes = {"att_menu_category","att_menu"};
		List<Resource> res = resourceService.doFindByType(resTypes);
		System.out.println("==================================");
		for(Resource r : res){
			System.out.println(r.getResourceName());
		}
		System.out.println("==================================");
	}
	@Test
	public void testResourceService2(){
		List<Resource> res = resourceService.doFindByUserIdAndType(1, WebConstant.WEB_MENU_CATEGORY_ITEM);
		System.out.println("==================================");
		for(Resource r : res){
			System.out.println(r.getResourceName());
		}
		System.out.println("==================================");
	}
	@Test
	public void testResourceService3(){
		String[] MENU_CATEGORY_TYPE = {
				WebConstant.WEB_MENU,
				WebConstant.WEB_MENU_CATEGORY};
		List<Resource> categories = resourceService.doFindByType(MENU_CATEGORY_TYPE);
		List<Resource> roots = resourceService.makeTree(categories, categories);
		
		// 2. 获取用户可访问的item叶子
		List<Resource> menuItems = resourceService.doFindByUserIdAndType(
				1, WebConstant.WEB_MENU_CATEGORY_ITEM);
		System.out.println("============================================");
		System.out.println("menuItems:"+menuItems);
		System.out.println("============================================");
		System.out.println("categories:"+categories);
		System.out.println("============================================");
		// 3. 将item叶子插入菜单框架的authorizedChildMenus中。<br/>
		resourceService.makeTree(menuItems, categories);
		System.out.println("=================outputTreeStr===========================");
		System.out.println(outputTreeStr);
		System.out.println("=================outputTreeStr===========================");
	}
	
	@Test
	public void indexAction(){
		String[] MENU_CATEGORY_TYPE = {
				WebConstant.WEB_MENU,
				WebConstant.WEB_MENU_CATEGORY};
		// 1. 获取整体的Web菜单框架，这与权限无关。并且使框架互相间设置好authorizedChildMenus。这一步中也获得rootMenus
		System.out.println("MENU_CATEGORY_TYPE"+MENU_CATEGORY_TYPE);
		List<Resource> categories = resourceService.doFindByType(MENU_CATEGORY_TYPE);
		List<Resource> roots = resourceService.makeTree(categories, categories);
		
		// 2. 获取用户可访问的item叶子
		List<Resource> menuItems = resourceService.doFindByUserIdAndType(
				1, WebConstant.WEB_MENU_CATEGORY_ITEM);
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
	}
	
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
//	@Test
//	public void testUserDao() {
//		List<User> users = userDao.
//		System.out.println(userDao);
////		for(User r : users){
////			System.out.println("getResourceDesc(): "+r.getUserName());
////		}
//		Iterator it = users.iterator();
//		int index = 0;
//		while(it.hasNext()){
//			System.out.println(users.get(index));
////			System.out.println("用户"+index+":"+((User)users.get(index)).getUserName());
//			index++;
//		}
//		System.out.println("ok");
//	}
	
//	@Test public void testRoleDao(){
//		List<Role> roles = roleDao.getAllRoles();
//		System.out.println(roles);
////		List<Role> roles = roleDao.getAllRoles();
////		Iterator it = roles.iterator();
////		int index = 0;
////		while(it.hasNext()){
////			System.out.println(roles.get(index));
//////			System.out.println("用户"+index+":"+((User)users.get(index)).getUserName());
////			index++;
////		}
//		
//		for(Role r : roles){
//			System.out.println(r.getRoleDesc());
//		}
//		System.out.println("ok");
//	}
	
//	@Test public void findUserByName(){
//		List<User> user = userDao.findAll();
//		Object usr=userDao.getHibernateSession().get(User.class, 1);
//	}
}
