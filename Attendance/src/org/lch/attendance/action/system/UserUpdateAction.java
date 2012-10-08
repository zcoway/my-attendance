package org.lch.attendance.action.system;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.ClasService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateUtil;
import org.lch.attendance.util.EncryptUtil;
import org.lch.attendance.vo.UserVo;

public class UserUpdateAction extends BaseAction {

	@javax.annotation.Resource
	private UserService userService;
	@javax.annotation.Resource
	private ClasService clasService;
	
	private User user;
	
	
//	@Action(value = "user_update", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
//			 @Result(name="input",location="/WEB-INF/pages/system/user_edit.jsp")
//			 })	
	@Override
	public String execute() throws Exception {
		User u = userService.doFindById(user.getUserId());
		
		if(user.getClasName()!=null){
			Clas c = clasService.doFindByName(user.getClasName());
			u.setClas(c);
		}
		u.setUserName(user.getUserName());
		u.setUserPwd(user.getUserPwd());
		u.setUserNum(user.getUserNum());
		u.setUserGender("男".equals(user.getGenderText())?true:false);
		if(user.getBirthdayText()!=null){
			u.setUserBirthday(DateUtil.string2Date(user.getBirthdayText()));
		}
		u.setUserQq(user.getUserQq());
		u.setUserEmail(user.getUserEmail());
		u.setUserHobby(user.getUserHobby());
		u.setUserIntro(user.getUserIntro());
		u.setUserTel(user.getUserTel());
		
		userService.doUpdateUser(u);
		
		return null;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
