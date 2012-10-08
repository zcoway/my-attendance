package org.lch.attendance.junit;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserRoleRelationTest {

//	private static ResourceService resourceService;
	private static UserService userService;
//	private static UserDao userDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		userService = (UserService)act.getBean("userService");
//		userDao = (UserDao)act.getBean("userDao");
	}

	@Test
	public void execute() {
//		User u = userDao.findById(1);
//		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//		List<Role> roles = userDao.getRolesByUser(u);
//		System.out.println("////////////////////////////////////////////////////");
//		for(Role r : roles){
//			System.out.println(r.getRoleDesc());
//		}
		User u = userService.doFindById(1);
		Set<Role> roles = u.getRoles();
		for(Role r : roles){
			System.out.println(r.getRoleDesc());
		}
	}
	
}
