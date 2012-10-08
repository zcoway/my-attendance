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
import org.lch.attendance.domain.Week;
import org.lch.attendance.service.WeekService;
import org.lch.attendance.util.DateJsonValueProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WeekDataTest {

//	private static GenericDao genericDao;
	private static WeekService weekService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
//		genericDao = (GenericDao)act.getBean("genericDao");
		weekService = (WeekService)act.getBean("weekService");
	}

	@Test
	public void execute() {
		PageView<Week> pageView=new PageView<Week>();
		Map<String, Object> jsonMap;
		JSONObject result;
		
		int intPage = 1;   
        int number = 10; 
        pageView.setCurrentPage(intPage);
		int maxresult=number;											//设置每次显示条数
		int firstindex=(pageView.getCurrentPage()-1)*maxresult;		//定义分页开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();	//定义排序
		orderby.put("weekId", "desc");
		
		QueryResult<Week> qr=weekService.getScrollData(firstindex,maxresult, null, null, orderby);
		pageView.setQueryResult(maxresult,qr);	//把查询结果和每页显示数传递给pageView
		
		List<Week> weeks = pageView.getRecords();

		for(Week w : weeks){
			System.out.println("w.getUser().getUserName():"+w.getUser().getUserName());
		}
		
		String[] excludes = {"details"};
		
		jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageView.getTotalRecord());//total键 存放总记录数
		jsonMap.put("rows", weeks);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes, "yyyy-MM-dd"));// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");

	}
}
