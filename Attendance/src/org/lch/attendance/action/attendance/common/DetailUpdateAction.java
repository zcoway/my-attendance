package org.lch.attendance.action.attendance.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.DetailService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.service.WeekService;
import org.lch.attendance.util.DateJsonValueProcessor;
@ParentPackage("default")
public class DetailUpdateAction extends BaseAction{

	@javax.annotation.Resource
	private DetailService detailService;
	@javax.annotation.Resource
	private UserService userService;
	
	private JSONObject result;
	
	private Integer detailId;
	private String detailClear;
	private Integer detailEarly;
	private Integer detailLate;
	private Integer detailQuit;
	private Integer detailIll;
	private Integer detailAffair;
	private Integer detailPub;
	private String userName;
	private Date detailTime;
	
	


	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		Detail d = detailService.doFindById(getDetailId());
		User u = userService.doFindByUserName(getUserName());
		d.setUser(u);
		d.setDetailAffair(getDetailAffair());
		d.setDetailClear(getDetailClear());
		d.setDetailEarly(getDetailEarly());
		d.setDetailIll(getDetailIll());
		d.setDetailLate(getDetailLate());
		d.setDetailPub(getDetailPub());
		d.setDetailQuit(getDetailQuit());
		d.setDetailTime(getDetailTime());
		detailService.doUpdate(d);
		String[] excludes = {"week","user"};

		result = new JSONObject();
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("detailId", d.getDetailId());
		jsonMap.put("detailClear", d.getDetailClear());
		jsonMap.put("detailEarly", d.getDetailEarly());
		jsonMap.put("detailLate", d.getDetailLate());
		jsonMap.put("detailQuit", d.getDetailQuit());
		jsonMap.put("detailIll", d.getDetailIll());
		jsonMap.put("detailAffair", d.getDetailAffair());
		jsonMap.put("detailPub", d.getDetailPub());
		jsonMap.put("userName", d.getUserName());
		jsonMap.put("detailTime", d.getDetailTime());
		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes, "yyyy-MM-dd"));// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDetailTime() {
		return detailTime;
	}
	public void setDetailTime(Date detailTime) {
		this.detailTime = detailTime;
	}
	public DetailService getDetailService() {
		return detailService;
	}
	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
	}
	public String getDetailClear() {
		return detailClear;
	}
	public void setDetailClear(String detailClear) {
		this.detailClear = detailClear;
	}
	public Integer getDetailEarly() {
		return detailEarly;
	}
	public void setDetailEarly(Integer detailEarly) {
		this.detailEarly = detailEarly;
	}
	public Integer getDetailLate() {
		return detailLate;
	}
	public void setDetailLate(Integer detailLate) {
		this.detailLate = detailLate;
	}
	public Integer getDetailQuit() {
		return detailQuit;
	}
	public void setDetailQuit(Integer detailQuit) {
		this.detailQuit = detailQuit;
	}
	public Integer getDetailIll() {
		return detailIll;
	}
	public void setDetailIll(Integer detailIll) {
		this.detailIll = detailIll;
	}
	public Integer getDetailAffair() {
		return detailAffair;
	}
	public void setDetailAffair(Integer detailAffair) {
		this.detailAffair = detailAffair;
	}
	public Integer getDetailPub() {
		return detailPub;
	}
	public void setDetailPub(Integer detailPub) {
		this.detailPub = detailPub;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}

	
}
