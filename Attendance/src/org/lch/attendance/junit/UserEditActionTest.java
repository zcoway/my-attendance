package org.lch.attendance.junit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.dao.ClasDao;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class UserEditActionTest {
	private static GenericDao genericDao;
	private static UserService userService;
	private static ClasService classesService;
	private static ClasDao classesDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		genericDao = (GenericDao)act.getBean("genericDao");
		classesService = (ClasService)act.getBean("classesService");
		classesDao = (ClasDao)act.getBean("classesDao");
	}
	
	@Test
	public void execute1(){
		Clas c = classesService.doFindByName("网络工程2班");
		System.out.println("ok");
		int a =  c.getClasId();
		System.out.println("a======================================:"+a);
//		Classes c = classesDao.findByName("网络工程2班");
	}
	@Test
	public void execute2(){
		Clas c = classesDao.findByName("网络工程2班");
		System.out.println("c.getClassId():==============>"+c.getClasId());
	}
}
