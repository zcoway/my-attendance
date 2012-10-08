package org.lch.attendance.junit;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.common.PageView;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.util.DateJsonValueProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassesDeleteTest {

//	private static GenericDao genericDao;
	private static ClasService clasService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
//		genericDao = (GenericDao)act.getBean("genericDao");
		clasService = (ClasService)act.getBean("clasService");
	}

	@Test
	public void execute() {
		Clas c = clasService.doFindById(2);
		if(c!=null){
			System.out.println(c.getClasName());
			clasService.doDelete(c);
			System.out.println("ok");
		}else{
			System.out.println("failed");
		}

	}
}
