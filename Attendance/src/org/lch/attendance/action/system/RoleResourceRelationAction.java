package org.lch.attendance.action.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.Role;
import org.lch.attendance.service.RoleService;
@ParentPackage("default")
public class RoleResourceRelationAction extends BaseAction{

	@javax.annotation.Resource
	private RoleService roleService;
	
	private JSONObject result;
	
	private Role role;
	
	private String resourceIds;
	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		Role r = roleService.doFindById(role.getRoleId());
		Set<Resource> resources = r.getResources();
		StringBuffer ids = new StringBuffer();
		for(Resource res : resources){
			ids.append(res.getResourceId()).append(",");
		}
		resourceIds = ids.substring(0, ids.length()-1);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("resourceIds:"+resourceIds);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("resourceIds", resourceIds);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap);// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}
	
}
