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
import org.lch.attendance.domain.Department;
import org.lch.attendance.service.DeptService;
import org.lch.attendance.util.DateJsonValueProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DeptDataTest {

	private static GenericDao genericDao;
	private static DeptService deptService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		genericDao = (GenericDao)act.getBean("genericDao");
		deptService = (DeptService)act.getBean("deptService");
	}

	@Test
	public void execute() {
		int intPage = 1;   
        int number = 10 ;
        Map<String, Object> jsonMap;
        PageView<Department> pageView=new PageView<Department>();
        JSONObject result;
        pageView.setCurrentPage(intPage);
		int maxresult=number;											//设置每次显示条数
		int firstindex=(pageView.getCurrentPage()-1)*maxresult;		//定义分页开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();	//定义排序
		orderby.put("deptId", "desc");
		
		QueryResult<Department> qr=deptService.getScrollData(firstindex,maxresult, null, null, null);
		pageView.setQueryResult(maxresult,qr);	//把查询结果和每页显示数传递给pageView
		
		List<Department> roles = pageView.getRecords();

		
		
		String[] excludes = {"classeses"};
		
		jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageView.getTotalRecord());//total键 存放总记录数
		jsonMap.put("rows", roles);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes, "yyyy-MM-dd"));// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");

	}
}
