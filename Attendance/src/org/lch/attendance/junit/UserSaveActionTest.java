package org.lch.attendance.junit;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateUtil;
import org.lch.attendance.vo.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserSaveActionTest {

	private static GenericDao genericDao;
	private static UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		genericDao = (GenericDao)act.getBean("genericDao");
		userService = (UserService)act.getBean("userService");
	}
	
	@Test
	public void execute(){
		UserVo userVo = new UserVo();
		userVo.setUserClass("网络工程2班");
		userVo.setUserName("test12");
		userVo.setUserNum("0807");
		userVo.setUserPwd("admin");
		userVo.setUserGender("0");
		userVo.setUserBirthday(DateUtil.Date2String(new Date()));
		userVo.setUserEmail("123@123.com");
		userService.doSaveUserByVo(userVo );
	}
}
