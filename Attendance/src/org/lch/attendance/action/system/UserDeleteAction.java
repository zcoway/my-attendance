package org.lch.attendance.action.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.UserService;
@ParentPackage("default")
public class UserDeleteAction extends BaseAction {

	@javax.annotation.Resource
	private UserService userService;
	private Map<String,Object> result;
	private Integer userId;
	@Action(results={@Result(name="success",type="json",params={"root", "result"})})	
	@Override
	public String execute() throws Exception {
		User user = userService.doFindById(userId);
		result = new HashMap<String, Object>();
		if(user!=null){
			result.put("success", Boolean.TRUE);
			userService.doDelete(user);
		}else{
			result.put("success", Boolean.FALSE);
		}
		return SUCCESS;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Map<String, Object> getResult() {
		return result;
	}
	
}
