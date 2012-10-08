package org.lch.attendance.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateUtil;
import org.lch.attendance.vo.UserVo;

public class UserEditAction extends BaseAction {

	@javax.annotation.Resource
	private UserService userService;
	@javax.annotation.Resource
	private ClasService classesService;
	
	private String userId;
	
	private Integer tmp;
	
	private User user = null;
	
	public String getUserId() {
		return userId;
	}

	private List<Clas> classes;
	
	public List<Clas> getClasses() {
		return userService.getAllClasses();
	}

	public void setClasses(List<Clas> classes) {
		this.classes = classes;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	private UserVo userVo;
	
	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

//	@Action(value = "user_edit", results = { @Result(name = "success", location ="/WEB-INF/pages/system/user_edit.jsp" ),
//			 @Result(name="input",location="/WEB-INF/pages/system/user_edit.jsp")
//			 })	
	@Override
	public String execute() throws Exception {
		userVo = new UserVo();
		if(getUserId()!=null){
			user = userService.doFindById(Integer.parseInt(getUserId()));
		}
		if(user!=null){
			if(user.getUserBirthday()!=null)
				userVo.setUserBirthday(DateUtil.Date2String(user.getUserBirthday()));
			if(user.getClas()!=null)
				userVo.setUserClass(user.getClas().getClasName());
			userVo.setUserEmail(user.getUserEmail());
			if(user.getUserGender()!=null)
				userVo.setUserGender(user.getUserGender()==true? "1":"0");
			userVo.setUserHobby(user.getUserHobby());
			userVo.setUserId(user.getUserId());
			userVo.setUserIntro(user.getUserIntro());
			userVo.setUserName(user.getUserName());
			userVo.setUserNum(user.getUserNum());
//			userVo.setUserPortrait(user.getUserPortrait());
			userVo.setUserPwd(user.getUserPwd());
			userVo.setUserQq(user.getUserQq());
			userVo.setUserTel(user.getUserTel());
			userVo.setUserId(Integer.parseInt(userId));
		}
//		HttpServletRequest request = ServletActionContext.getRequest();
//		request.setAttribute("", userId);
		return SUCCESS;
	}
	
	
//	public Integer getcId() {
////		Classes c = classesService.doFindByName(userVo.getUserClass());
//		return 1;
//	}
//
//	public void setcId(Integer cId) {
//		this.cId = cId;
//	}
	
	//设置页面，让select选中
	public Integer getTmp() {
		Clas c = classesService.doFindById(user.getClas().getClasId());
		return c.getClasId();
	}

	public void setTmp(Integer tmp) {
		this.tmp = tmp;
	}
}
