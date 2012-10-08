package org.lch.attendance.junit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.service.DeptService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeptDeleteTest {

//	private static GenericDao genericDao;
	private static DeptService deptService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
//		genericDao = (GenericDao)act.getBean("genericDao");
		deptService = (DeptService)act.getBean("deptService");
	}

	@Test
	public void execute() {
		Department d = deptService.doFindById(8);
		if(d!=null){
			System.out.println(d.getDeptName());
			deptService.doDelete(d);
			System.out.println("ok");
		}else{
			System.out.println("failed");
		}

	}
}
