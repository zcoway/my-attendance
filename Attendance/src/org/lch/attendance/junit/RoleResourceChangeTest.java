package org.lch.attendance.junit;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.ResourceService;
import org.lch.attendance.service.RoleService;
import org.lch.attendance.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RoleResourceChangeTest {

	private static RoleService roleService;
	private static ResourceService resourceService;
//	private static UserDao userDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		resourceService = (ResourceService)act.getBean("resourceService");
		roleService = (RoleService)act.getBean("roleService");
	}

	@Test
	public void execute() {
		System.out.println("------------------RoleResourceChangeTest-------------------------");
		Integer[] resourceIds = {1,2,3,4,5,6,7,8,9,10};
		String roleId = "2";
		Set<Resource> resources = new HashSet<Resource>();
		for(Integer x : resourceIds ){
			resources.add(resourceService.doFindById(x));
		}
		Role role = roleService.doFindById(Integer.parseInt(roleId));
		System.out.println(role.getRoleName());
		role.setResources(resources);
		roleService.doUpdate(role);
	}
	
}
