//package org.lch.attendance.junit;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.lch.attendance.common.PageView;
//import org.lch.attendance.common.QueryResult;
//import org.lch.attendance.dao.GenericDao;
//import org.lch.attendance.domain.User;
//import org.lch.attendance.service.UserService;
//import org.lch.attendance.vo.UserVo;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class UserManagerTest {
//
//	private static GenericDao genericDao;
//	private static UserService userService;
//	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
//		genericDao = (GenericDao)act.getBean("genericDao");
//		userService = (UserService)act.getBean("userService");
//	}
//
//	@Test
//	public void test() {
//		Long total = genericDao.getTotalCount("User");
//		System.out.println("total:"+total);
//	}
//	
//	@Test
//	public void execute(){
//		Long total = genericDao.getTotalCount("User");
//		total = userService.getTotalCount("User");
//		List<User> users = userService.doFindAll();
//		List<UserVo> resultList = new ArrayList<UserVo>();
//		for(User u : users){
//			UserVo uvo = new UserVo();
//			uvo.setUserBirthday(u.getUserBirthday());
//			uvo.setUserEmail(u.getUserEmail());
//			uvo.setUserGender(u.getUserGender());
//			uvo.setUserHobby(u.getUserHobby());
//			uvo.setUserId(u.getUserId());
//			uvo.setUserIntro(u.getUserIntro());
//			uvo.setUserName(u.getUserName());
//			uvo.setUserNum(u.getUserNum());
//			uvo.setUserPwd(u.getUserPwd());
//			uvo.setUserPortrait(u.getUserPortrait());
//			uvo.setUserQq(u.getUserQq());
//			uvo.setUserTel(u.getUserTel());
//			uvo.setUserClass(u.getClasses().getClassName());
//			
//			resultList.add(uvo);
//		}
//		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
//        jsonMap.put("total", total);//total键 存放总记录数
//        jsonMap.put("rows", resultList);//rows键 存放每页记录 list 
//        
////        JSONObject result = new JSONObject();
////        result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(null, "yyyy-MM-dd"));// 格式化result
//        
//        System.out.println("=========================================");
////        System.out.println(result);
//        System.out.println("=========================================");
//	}
//	
//	
//	@Test
//	public void pagination(){
//		PageView<User> pageView = new PageView<User>();
//		int maxresult=5;											//设置每次显示条数
//		int firstindex=(pageView.getCurrentPage()-1)*maxresult;		//定义分页开始索引
//		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();	//定义排序
//		orderby.put("userId", "desc");
//		
//		QueryResult<User> qr=userService.getScrollData(firstindex,maxresult, null, null, null);
//		pageView.setQueryResult(maxresult,qr);	//把查询结果和每页显示数传递给pageView
//		List<User> users = pageView.getRecords();
//		List<UserVo> resultList = new ArrayList<UserVo>();
//		for(User u : users){
//			UserVo uvo = new UserVo();
//			uvo.setUserBirthday(u.getUserBirthday());
//			uvo.setUserEmail(u.getUserEmail());
//			uvo.setUserGender(u.getUserGender());
//			uvo.setUserHobby(u.getUserHobby());
//			uvo.setUserId(u.getUserId());
//			uvo.setUserIntro(u.getUserIntro());
//			uvo.setUserName(u.getUserName());
//			uvo.setUserNum(u.getUserNum());
//			uvo.setUserPwd(u.getUserPwd());
//			uvo.setUserPortrait(u.getUserPortrait());
//			uvo.setUserQq(u.getUserQq());
//			uvo.setUserTel(u.getUserTel());
//			uvo.setUserClass(u.getClasses().getClassName());
//			
//			resultList.add(uvo);
//		}
//		for(UserVo uv : resultList){
//			System.out.println(uv.getUserName());
//		}
//	}
//
//}
