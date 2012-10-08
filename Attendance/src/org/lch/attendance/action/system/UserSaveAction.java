package org.lch.attendance.action.system;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class UserSaveAction extends BaseAction { 
//implements ModelDriven<User>{

	@javax.annotation.Resource
	private UserService userService;
	
	private User user11;
	private String uname;
	
	


//	@Action(value = "user_save", results = { @Result(name = "success", location ="/common/operation_success.jsp" ),
//			 @Result(name="input",location="/WEB-INF/pages/system/user_add.jsp")
//			 })	
	@Override
	public String execute() throws Exception {
		Map<String,Object> map = ActionContext.getContext().getParameters();
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry entry = (Map.Entry) iter.next();
		    Object key = entry.getKey();
		    Object val = entry.getValue();
		    String[] str = new String[1];
		    str = (String[]) val;
		    System.out.println(String.valueOf(key)+": "+str[0]);
		} 
		List<User> list= userService.doFindAll();
		for(int i=0;i<list.size();i++)
		{
			if(user11.getUserName().trim().equals(list.get(i).getUsername()))
			{
				addActionMessage("*该用户名称已存在");
				return INPUT;			
			}
		}
		userService.doCreate(user11);
		return null;
	}



	public void setUser11(User user) {
		this.user11 = user;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}



//	@Override
//	public User getModel() {
//		if (null == user) {
//			return user = new User();
//		}
//		return user;
//	}
	
}
