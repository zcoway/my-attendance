package org.lch.attendance.junit;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lch.attendance.common.WebConstant;
import org.lch.attendance.domain.Clas;
import org.lch.attendance.domain.Department;
import org.lch.attendance.domain.Resource;
import org.lch.attendance.service.DeptService;
import org.lch.attendance.service.ResourceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tree {

	private static DeptService deptService;
	private static ResourceService resourceService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-security.xml");
		deptService = (DeptService)act.getBean("deptService");
		resourceService = (ResourceService)act.getBean("resourceService");
	}

//	@Test
//	public void test() {
//		JSONArray result = new JSONArray();
//		
//		//一级树节点
//		EasyUITree root = new EasyUITree();
//		root.setId(0);
//		root.setText("厦门理工学院");
//		//一级树
//		List<EasyUITree> tree = new ArrayList<EasyUITree>();
//		tree.add(root);
//		
//
//		//二级树
//		List<EasyUITree> dept = new ArrayList<EasyUITree>();
//		List<Department> depts = deptService.doFindAll();
//		if(depts.size()!=0){
//			//二级树节点
//			for(Department d : depts){
//				EasyUITree deptCategory = new EasyUITree();
//				deptCategory.setId(d.getDeptId());
//				deptCategory.setState("closed");
//				deptCategory.setText(d.getDeptName());
//				
//				List<String> grades = deptService.doFindAllGrade(d.getDeptId());
//				if(grades.size()!=0){
//					//三级树
//					List<EasyUITree> grade = new ArrayList<EasyUITree>();
//					//三级树节点
//					for(String gradeName : grades){
//						EasyUITree gradeCategory = new EasyUITree();
//						gradeCategory.setId(-1);
//						gradeCategory.setState("closed");
//						gradeCategory.setText(gradeName);
//						
//						List<Clas> clases = deptService.doFindClasByGrade(d.getDeptId(),gradeName);
//						if(clases.size()!=0){
//							//四级数
//							List<EasyUITree> clas = new ArrayList<EasyUITree>();
//							//四级树节点
//							for(Clas c : clases){
//								EasyUITree clasCategory = new EasyUITree();
//								clasCategory.setId(c.getClasId());
//								clasCategory.setState("open");
//								clasCategory.setText(c.getClasName());
//								
//								clas.add(clasCategory);
//							}
//							gradeCategory.setChildren(clas);
//						}else{
//							gradeCategory.setState("open");
//						}
//						grade.add(gradeCategory);
//					}
//					deptCategory.setChildren(grade);
//				}else{
//					deptCategory.setState("open");
//				}
//				dept.add(deptCategory);
//			}
//			root.setChildren(dept);
//		}
//
//		
//
//
//
//		// Map<String,Object> jsonMap = new HashMap<String, Object>();
//		// jsonMap.put("id",0);
//
//		JsonConfig jc = new JsonConfig();
//		String[] excludes = {};
//		jc.setExcludes(excludes);
//		result = JSONArray.fromObject(tree);
//
//		System.out.println("===========================================");
//		System.out.println(result);
//		System.out.println("===========================================");
//	}

	@Test
	public void findAllDept(){
		List<Department> depts = deptService.doFindAll();
		for(Department d : depts){
			System.out.println(d.getDeptName());
		}
	}
	@Test
	public void findDeptGrade(){
		Integer deptId = 10;
		List<String> list = deptService.doFindAllGrade(deptId);
		for(String s : list){
			System.out.println(s);
		}
		System.out.println("ok");
	}
	@Test
	public void findClasByGrade(){
		Integer deptId = 10;
		String grade = "2008级";
		List<Clas> clases = deptService.doFindClasByGrade(deptId,grade);
	}
	@Test
	public void buildTree(){
		String[] MENU_CATEGORY_TYPE = {"att_tree","att_tree_dept"};
		List<Resource> categories = resourceService.doFindByType(MENU_CATEGORY_TYPE);
		List<Resource> roots = resourceService.makeTree(categories, categories);
		List<Resource> menuItems = resourceService.doFindByUserIdAndType(
				20, "att_tree_dept_grade","att_tree_dept_grade_clas");
		// 3. 将item叶子插入菜单框架的authorizedChildMenus中。<br/>
		resourceService.makeTree(menuItems, categories);
		System.out.println("ok");
	}
	@Test
	public void userManagerTree(){
		Integer userId = 20;
		JSONArray result = new JSONArray();
		
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
	}
	@Test
	public void simple(){
		List<Resource> res = resourceService.doFindClasByGrade(19, "att_tree_dept_grade_clas",55);
		System.out.println("===============================================");
		for(Resource r : res){
			System.out.println(r.getResourceName());
		}
		System.out.println("===============================================");
		
//		List<Resource> res2 = resourceService.doFindByUserIdAndType(19, "att_tree_dept","att_tree_dept_grade");
//		System.out.println("===============================================");
//		for(Resource r : res2){
//			System.out.println(r.getResourceName());
//		}
//		System.out.println("===============================================");
		
	}
}
