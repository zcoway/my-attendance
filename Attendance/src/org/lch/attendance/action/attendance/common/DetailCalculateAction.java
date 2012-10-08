package org.lch.attendance.action.attendance.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.common.PageView;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.domain.Week;
import org.lch.attendance.service.DetailService;
import org.lch.attendance.service.WeekService;
@ParentPackage("default")
public class DetailCalculateAction extends BaseAction{

	@javax.annotation.Resource
	private DetailService detailService;
	
	private Integer weekId;
	
	private JSONObject result;
	
	private Map<String, Object> jsonMap;
//	private String rows;
//	private String page;
	
	@Action(results={@Result(name="success",type="json",params={"root", "jsonMap"})})
	@Override
	public String execute() throws Exception {
		List<Detail> details = detailService.doStatistics(getWeekId());
		
		String[] excludes = {"week","user"};
		JsonConfig jc = new JsonConfig();
		jc.setExcludes(excludes);
		jsonMap = new HashMap<String, Object>();//定义map
		int count = detailService.doCount();
//		jsonMap.put("weekId", getWeekId());
		jsonMap.put("total", count);//total键 存放总记录数
		jsonMap.put("rows", details);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap,jc);// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}
	
	//getter & setter	
	public Integer getWeekId() {
		return weekId;
	}
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}
	
//	public String getRows() {
//		return rows;
//	}
//	public void setRows(String rows) {
//		this.rows = rows;
//	}
//	public String getPage() {
//		return page;
//	}
//	public void setPage(String page) {
//		this.page = page;
//	}
}
