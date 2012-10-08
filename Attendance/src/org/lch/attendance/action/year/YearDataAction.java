package org.lch.attendance.action.year;

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
import org.lch.attendance.domain.Year;
import org.lch.attendance.service.YearService;
import org.lch.attendance.util.DateJsonValueProcessor;
@ParentPackage("default")
public class YearDataAction extends BaseAction {
	
	@javax.annotation.Resource
	private YearService yearService;
	
	private JSONObject result;
	private String rows;
	private String page;
	private PageView<Year>pageView=new PageView<Year>();
	
	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows); 
        pageView.setCurrentPage(intPage);
		int maxresult=number;											//设置每次显示条数
		int firstindex=(pageView.getCurrentPage()-1)*maxresult;		//定义分页开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();	//定义排序
		orderby.put("yearId", "desc");
		QueryResult<Year> qr=yearService.getScrollData(firstindex,maxresult, null, null, orderby);
		pageView.setQueryResult(maxresult,qr);	//把查询结果和每页显示数传递给pageView
		List<Year> years = pageView.getRecords();

		
		Map<String,Object> jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageView.getTotalRecord());//total键 存放总记录数
		jsonMap.put("rows", years);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(null , "yyyy-MM-dd"));// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}	
	
	// 将数据传给页面
	public JSONObject getResult() {
		return result;
	}
}
