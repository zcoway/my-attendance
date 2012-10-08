package org.lch.attendance.action.message;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.common.SecurityUserHolder;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.MessageService;

@ParentPackage("default")
public class MessageCheckAction extends BaseAction {
	@javax.annotation.Resource
	private MessageService messageService;
	
	private JSONObject result;
	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	private Map<String, Object> jsonMap;	
	
	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		User currentUser = SecurityUserHolder.getCurrentUser();

		boolean flag = messageService.doCheckMessage(currentUser.getUserName());
		
		jsonMap = new HashMap<String, Object>();//定义map 
//		String tag;
//		if(flag)
//			tag = "1";
//		else
//			tag = "0";
		jsonMap.put("flag", flag);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap);// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}
	
}
