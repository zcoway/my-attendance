package org.lch.attendance.junit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Leave;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LeaveAddTest {

	private static UserService userService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		userService = (UserService)act.getBean("userService");
	}

	@Test
	public void test() {
		User currentUser = userService.doFindByUserName("admin");
		Clas c = currentUser.getClas();
		
		Leave leave = new Leave();
		leave.setLeaveUser(currentUser.getUserName());
		leave.setLeaveNum(currentUser.getUserNum());
		leave.setLeaveClas(c.getClasName());
//		List<User> students = userService.doFindClassmates(c.getClasId());
//		for(User u: students){
//			if(currentUser.getUserName().equalsIgnoreCase(u.getUserName())){
//				students.remove(u);
//				break;
//			}
//		}
//		Department d = c.getDepartment();
//		List<User> teachers =  userService.doFindTeachers(d.getDeptId());
//		
//		System.out.println("=============================================");
//		for(User u: students){
//			System.out.println(u.getUserName());
//		}
//		System.out.println("=============================================");
//		System.out.println("=============================================");
//		for(User u: teachers){
//			System.out.println(u.getUserName());
//		}
//		System.out.println("=============================================");
	}

}
