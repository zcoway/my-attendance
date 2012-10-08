package org.lch.attendance.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class TreeTest {
	public static void main(String[] args) {
		JSONArray result = new JSONArray();
		

// -----四级级树
		EasyUITree clasCategory = new EasyUITree();
		clasCategory.setId("2");
		clasCategory.setText("网络工程1班");
		clasCategory.setState("open");
		clasCategory.setChildren(null);

		List<EasyUITree> clas = new ArrayList<EasyUITree>();
		clas.add(clasCategory);
		
		


//-----三级级树		
		EasyUITree yearCategory = new EasyUITree();
		yearCategory.setId("2");
		yearCategory.setText("2008级");
		yearCategory.setChildren(clas);

		List<EasyUITree> year = new ArrayList<EasyUITree>();
		year.add(yearCategory);
		
		
		
//-----二级树		
		EasyUITree deptCategory = new EasyUITree();
		deptCategory.setId("1");
		deptCategory.setText("计算机科学与技术系");
		deptCategory.setChildren(year);
		
		List<EasyUITree> dept = new ArrayList<EasyUITree>();
		dept.add(deptCategory);
		
//-----一级树		
		EasyUITree root = new EasyUITree();
		root.setId("0");
		root.setText("厦门理工学院");
		root.setChildren(dept);
		
		List<EasyUITree> tree = new ArrayList<EasyUITree>();
		tree.add(root);
		
//		Map<String,Object> jsonMap = new HashMap<String, Object>();
//		jsonMap.put("id",0);
		
		JsonConfig jc = new JsonConfig();
		String[] excludes = {};
		jc.setExcludes(excludes);
		result = JSONArray.fromObject(tree);
		
		System.out.println("===========================================");
		System.out.println(result);
		System.out.println("===========================================");
	}
}

