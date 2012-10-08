package org.lch.attendance.action.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.service.UserService;
import org.lch.attendance.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("default")
//@Namespace("/system")
//@Action(value="user_manager",
//results={@Result(name="success", location="/WEB-INF/pages/system/user_manager.jsp", type="json", params={"root", "jsonMap"})}
//)

//@Action( 
//		results={@Result( params={"root", "result"})}
//)
public class UserManagerAction extends BaseAction{

	@Autowired
	private UserService userService;
	
	private UserVo userVo;
	
	private List<Clas> classes = new ArrayList<Clas>();
	
	
	public List<Clas> getClasses() {
		return userService.getAllClasses();
	}

	public void setClasses(List<Clas> classes) {
		this.classes = classes;
	}


	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	//	@Action(value="user_manager",results={@Result(name="success",type="json",params={"root", "jsonMap"})})
	@Override
	public String execute() throws Exception {
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
//		jsonMap = new HashMap<String, Object>();//定义map 
//		jsonMap.put("total", pageView.getTotalRecord());//total键 存放总记录数
//		jsonMap.put("rows", resultList);//rows键 存放每页记录 list 
//		
//		result = new JSONObject();
//		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(null, "yyyy-MM-dd"));// 格式化result
		
		
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
//			
//			resultList.add(uvo);
//		}
//		
//		jsonMap = new HashMap<String, Object>();//定义map 
//        jsonMap.put("total", total);//total键 存放总记录数
//        jsonMap.put("rows", resultList);//rows键 存放每页记录 list 
//		
//        result = new JSONObject();
//        result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(null, "yyyy-MM-dd"));// 格式化result
//		System.out.println("I'm UserManagerAction");
		return SUCCESS;
	}
	
}
