package org.lch.attendance.action.attendance.common;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.common.PageView;
import org.lch.attendance.common.QueryResult;
import org.lch.attendance.domain.Detail;
import org.lch.attendance.service.DetailService;
import org.lch.attendance.util.DateJsonValueProcessor;
@ParentPackage("default")
public class DetailQueryAction extends BaseAction {

	@javax.annotation.Resource
	private DetailService detailService; 
	
	private String userName;
	private Integer weekId;
	
	private JSONObject result;
	private String rows;
	private String page;
	private PageView<Detail> pageView=new PageView<Detail>();
	
	
	@Action(results={@Result(name="success",type="json",params={"root","result"})})
	@Override
	public String execute() throws Exception {
		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows); 
        pageView.setCurrentPage(intPage);
		int maxresult=number;											//设置每次显示条数
		int firstindex=(pageView.getCurrentPage()-1)*maxresult;		//定义分页开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();	//定义排序
		orderby.put("detailId", "desc");
		QueryResult<Detail> qr=detailService.getScrollData(firstindex,maxresult, 
				"o.week.weekId=? and o.user.userName=?",new Object[]{getWeekId(),getUserName()}, orderby);
		pageView.setQueryResult(maxresult,qr);	//把查询结果和每页显示数传递给pageView
		
		List<Detail> details = pageView.getRecords();
		
		String[] excludes = {"week","user"};
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageView.getTotalRecord());//total键 存放总记录数
		jsonMap.put("rows", details);//rows键 存放每页记录 list 
//		jsonMap.put("weekId", weekId);
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes, "yyyy-MM-dd"));// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}

	//getter & setter
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public PageView<Detail> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<Detail> pageView) {
		this.pageView = pageView;
	}
}
