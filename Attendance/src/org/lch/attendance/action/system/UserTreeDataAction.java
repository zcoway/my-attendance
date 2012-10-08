package org.lch.attendance.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.lch.attendance.action.BaseAction;
import org.lch.attendance.common.PageView;
import org.lch.attendance.common.SecurityUserHolder;
import org.lch.attendance.common.WebConstant;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.domain.User;
import org.lch.attendance.junit.EasyUITree;
import org.lch.attendance.service.ResourceService;
import org.lch.attendance.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("default")
public class UserTreeDataAction extends BaseAction{

	@Autowired
	private ResourceService resourceService;
	
	private JSONArray result;
	
	@Action(results={@Result(name="success",type="json",params={"root", "result"})})
	@Override
	public String execute() throws Exception {
		result = new JSONArray();
		User user = SecurityUserHolder.getCurrentUser();
		Integer userId = user.getUserId();
		//一级树节点
		EasyUITree root = new EasyUITree();
		root.setId("0");
		root.setText("厦门理工学院");
		//一级树
		List<EasyUITree> tree = new ArrayList<EasyUITree>();
		tree.add(root);
		

		//二级树
		List<EasyUITree> dept = new ArrayList<EasyUITree>();
//		List<Department> depts = deptService.doFindAll();
		List<Resource> depts = resourceService.doFindByUserIdAndType(userId, WebConstant.WEB_TREE_DEPT);
		if(depts.size()!=0){
			//二级树节点
//			for(Department d : depts){
			for(Resource r2 : depts){
				EasyUITree deptCategory = new EasyUITree();
				deptCategory.setId(r2.getResourceValue());
				deptCategory.setState("closed");
				deptCategory.setText(r2.getResourceName());
				
//				List<String> grades = deptService.doFindAllGrade(d.getDeptId());
				List<Resource> grades = resourceService.doFindAllGradeOrClas(userId, WebConstant.WEB_TREE_DEPT_GRADE,r2.getResourceName());
				if(grades.size()!=0){
					//三级树
					List<EasyUITree> grade = new ArrayList<EasyUITree>();
					//三级树节点
					for(Resource r3 : grades){
						EasyUITree gradeCategory = new EasyUITree();
						gradeCategory.setId(r3.getResourceValue());
						gradeCategory.setState("closed");
						gradeCategory.setText(r3.getResourceDesc());
						
//						List<Clas> clases = deptService.doFindClasByGrade(d.getDeptId(),gradeName);
						List<Resource> clases = resourceService.doFindAllGradeOrClas(userId, WebConstant.WEB_TREE_DEPT_GRADE_CLAS,r3.getResourceName());
						if(clases.size()!=0){
							//四级数
							List<EasyUITree> clas = new ArrayList<EasyUITree>();
							//四级树节点
							for(Resource r4 : clases){
								EasyUITree clasCategory = new EasyUITree();
								clasCategory.setId(r4.getResourceValue());
								clasCategory.setState("open");
								clasCategory.setText(r4.getResourceName());
								
								clas.add(clasCategory);
							}
							gradeCategory.setChildren(clas);
						}else{
							gradeCategory.setState("open");
						}
						grade.add(gradeCategory);
					}
					deptCategory.setChildren(grade);
				}else{
					deptCategory.setState("open");
				}
				dept.add(deptCategory);
			}
			root.setChildren(dept);
		}

		



		// Map<String,Object> jsonMap = new HashMap<String, Object>();
		// jsonMap.put("id",0);

		JsonConfig jc = new JsonConfig();
		String[] excludes = {};
		jc.setExcludes(excludes);
		result = JSONArray.fromObject(tree);

		System.out.println("===========================================");
		System.out.println(result);
		System.out.println("===========================================");
		return SUCCESS;
	}

	public JSONArray getResult() {
		return result;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}
	
}
