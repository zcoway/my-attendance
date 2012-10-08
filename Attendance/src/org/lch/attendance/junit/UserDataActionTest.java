package org.lch.attendance.junit;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.common.PageView;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.dao.GenericDao;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateJsonValueProcessor;
import org.lch.attendance.util.DateUtil;
import org.lch.attendance.vo.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDataActionTest {

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
		int intPage = 1;   
        int number = 10; 
        PageView<User> pageView=new PageView<User>();
        pageView.setCurrentPage(intPage);
		int maxresult=number;											//设置每次显示条数
		int firstindex=(pageView.getCurrentPage()-1)*maxresult;		//定义分页开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();	//定义排序
		orderby.put("userId", "desc");
		QueryResult<User> qr=userService.getScrollData(firstindex,maxresult, " o.clas.clasId=?", new Object[]{1}, orderby);
		pageView.setQueryResult(maxresult,qr);	//把查询结果和每页显示数传递给pageView
		List<User> users = pageView.getRecords();

		
		Map<String,Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageView.getTotalRecord());//total键 存放总记录数
		jsonMap.put("rows", users);//rows键 存放每页记录 list 
		JSONObject result;
		result = new JSONObject();
		String[] excludes = {"dept","clas","roles","weeks","details","authorities","accountNonExpired","userGender","userBirthday",
								"accountNonLocked","credentialsNonExpired","enabled","password","id","username","userEnabled"};
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(excludes);
//		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes , "yyyy-MM-dd"));// 格式化result
		result = JSONObject.fromObject(jsonMap,jc);// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
	}
}
