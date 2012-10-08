package org.lch.attendance.junit;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.RoleService;
import org.lch.attendance.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserRoleChangeTest {

	private static RoleService roleService;
	private static UserService userService;
//	private static UserDao userDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		userService = (UserService)act.getBean("userService");
		roleService = (RoleService)act.getBean("roleService");
	}

	@Test
	public void execute() {
		System.out.println("-------------------UserRoleChangeTest-------------------------------");
		Integer[] ids = {1,2,3};
		String userId = "16";
		Set<Role> roles = new HashSet<Role>();
		for(Integer x : ids ){
			roles.add(roleService.doFindById(x));
		}
		User user = userService.doFindById(Integer.parseInt(userId));
		System.out.println(user.getUserName());
		user.setRoles(roles);
		userService.doUpdate(user);
	}
	
}
