package org.lch.attendance.action.system;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.RoleService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.StringUtils;

@ParentPackage("default")
//@Namespace("/system")
//@Action(value="user_manager",
//results={@Result(name="success", location="/WEB-INF/pages/system/user_manager.jsp", type="json", params={"root", "jsonMap"})}
//)

//@Action( 
//		results={@Result( params={"root", "result"})}
//)
public class UserRoleChangeAction extends BaseAction{

	@javax.annotation.Resource
	private RoleService roleService;
	
	@javax.annotation.Resource
	private UserService userService;
	
	private JSONObject result;
	
	private User user;
	
	private String roleIds;

	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String execute() throws Exception {
		System.out.println("$$$$$$$$$$$$$$$$$$$UserRoleChangeAction$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		Integer[] ids = StringUtils.String2IntegerArray(roleIds);
		for(Integer x : ids ){
			System.out.print(x+ "  ");
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$UserRoleChangeAction$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		Set<Role> roles = new HashSet<Role>();
		for(Integer x : ids ){
			roles.add(roleService.doFindById(x));
		}
		User user = userService.doFindById(Integer.parseInt(userId));
		System.out.println(user.getUserName());
		user.setRoles(roles);
		userService.doUpdate(user);
//		User u = userService.doFindById(user.getUserId());
//		Set<Role> roles = u.getRoles();
//		StringBuffer ids = new StringBuffer();
//		for(Role r : roles){
//			ids.append(r.getRoleId()).append(",");
//		}
////		roleIds.substring(0, roleIds.length()-1);
//		roleIds = ids.substring(0, ids.length()-1);
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		System.out.println("roleIds:"+roleIds);
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		
//		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
//		jsonMap.put("roleIds", roleIds);//rows键 存放每页记录 list 
//		
//		result = new JSONObject();
//		result = JSONObject.fromObject(jsonMap);// 格式化result
//		System.out.println("=========================================");
//		System.out.println(result);
//		System.out.println("=========================================");
		return null;
	}
	
}
