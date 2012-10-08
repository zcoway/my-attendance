package org.lch.attendance.action.year;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.domain.Year;
import org.lch.attendance.service.YearService;

@ParentPackage("default")
public class YearDeleteAction extends BaseAction{

	@javax.annotation.Resource
	private YearService yearService;
	
	private Year year;
	private Map<String,Object> result;
	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		result = new HashMap<String, Object>();
		Year y = yearService.doFindById(year.getYearId());
		//TODO 未处理异常
		yearService.doDelete(y); 
		result.put("success", Boolean.TRUE);
		return SUCCESS;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	
	
}
