package org.lch.attendance.action.message;

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
import org.lch.attendance.common.SecurityUserHolder;
import org.lch.attendance.domain.Message;
import org.lch.attendance.domain.Role;
import org.lch.attendance.domain.User;
import org.lch.attendance.service.MessageService;
import org.lch.attendance.service.UserService;
import org.lch.attendance.util.DateJsonValueProcessor;
@ParentPackage("default")
public class MessageDataAction extends BaseAction {
	@javax.annotation.Resource
	private MessageService messageService;
	@javax.annotation.Resource
	private UserService userService;
	
	private JSONObject result;
	
	private String rows;
	private String page;
	
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


	private PageView<Message> pageView=new PageView<Message>();	//这里必须要构造新对象，不然刚打开没有currentPage参数传递过来，如果不新建也行，第一次打开必须传递currentPage参数过来
	
	public PageView<Message> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<Message> pageView) {
		this.pageView = pageView;
	}
	
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
//		String where = "o.userSender.userName=? OR o.userReceiver.userName=?";
//		Object[] params={currentUser.getUserName(),currentUser.getUserName()};
		String where = null;
		Object[] params = new Object[2];
		String roleName = null;
		//筛选得到权限最大的角色
		List<Role> roles = userService.getRolesByUser(currentUser);
		for(Role r : roles){
			if("ROLE_ADMIN".equalsIgnoreCase(r.getRoleName())){
				roleName = "ROLE_ADMIN";
				break;
			}
			if("ROLE_TEACHER".equalsIgnoreCase(r.getRoleName())){
				roleName = "ROLE_TEACHER";
			}
			if("ROLE_STUDENT".equalsIgnoreCase(r.getRoleName())){
				if(!"ROLE_TEACHER".equalsIgnoreCase(roleName)){
					roleName = "ROLE_STUDENT";
				}
			}
			
		}
		if(roleName==null){
			roleName = "ROLE_ATTENDANCE";
			where = "o.userSelf.userName=?";
			params[0] = currentUser.getUserName();
		}
		
		//设置相应角色的查询条件
		if("ROLE_ADMIN".equals(roleName)){
			where = null;
			params = null;	
		}else{
			where = "o.userSender.userName=? OR o.userReceiver.userName=?";
			params[0]=currentUser.getUserName();
			params[1]=currentUser.getUserName();
		}
		
		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page);   
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows); 
        pageView.setCurrentPage(intPage);
		int maxresult=number;											//设置每次显示条数
		int firstindex=(pageView.getCurrentPage()-1)*maxresult;		//定义分页开始索引
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();	//定义排序
		orderby.put("messageId", "desc");
		QueryResult<Message> qr;
		if(currentUser.equals("admin")){
			qr=messageService.getScrollData(firstindex,maxresult,null,null, orderby);
		}else{
			qr=messageService.getScrollData(firstindex,maxresult,where, params, orderby);
		}
		pageView.setQueryResult(maxresult,qr);	//把查询结果和每页显示数传递给pageView
		
		List<Message> messages = pageView.getRecords();

		
		
		String[] excludes = {"userSender","userReceiver"};
		
		jsonMap = new HashMap<String, Object>();//定义map 
		jsonMap.put("total", pageView.getTotalRecord());//total键 存放总记录数
		jsonMap.put("rows", messages);//rows键 存放每页记录 list 
		
		result = new JSONObject();
		result = JSONObject.fromObject(jsonMap,DateJsonValueProcessor.configJson(excludes, "yyyy-MM-dd"));// 格式化result
		System.out.println("=========================================");
		System.out.println(result);
		System.out.println("=========================================");
		return SUCCESS;
	}
	
}
