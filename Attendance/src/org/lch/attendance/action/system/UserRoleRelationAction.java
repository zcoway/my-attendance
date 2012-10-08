package org.lch.attendance.action.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.ResourceService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateJsonValueProcessor;

import com.opensymphony.xwork2.ActionContext;

@ParentPackage("default")
//@Namespace("/system")
//@Action(value="user_manager",
//results={@Result(name="success", location="/WEB-INF/pages/system/user_manager.jsp", type="json", params={"root", "jsonMap"})}
//)

//@Action( 
//		results={@Result( params={"root", "result"})}
//)
public class UserRoleRelationAction extends BaseAction{

	@javax.annotation.Resource
	private UserService userService;
	
	private JSONObject result;
	
	private User user;
	
	private String roleIds;
	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		User u = userService.doFindById(user.getUserId());
		Set<Role> roles = u.getRoles();
		if(roles.size()>0){
			StringBuffer ids = new StringBuffer();
			for(Role r : roles){
				ids.append(r.getRoleId()).append(",");
			}
			roleIds = ids.substring(0, ids.length()-1);
		}else{
			roleIds = "";
		}
		
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("roleIds:"+roleIds);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("roleIds", roleIds);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap);// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}
	
}
