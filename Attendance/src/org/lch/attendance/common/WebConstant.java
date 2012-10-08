package org.lch.attendance.common;

public class WebConstant {
	
	public static final String WEB_NAME = "att";
	
	public static final String WEB_SEPARATOR = "_";
	
	public static final String WEB_MENU = WEB_NAME + WEB_SEPARATOR + "menu";
	
	public static final String WEB_MENU_CATEGORY = WEB_MENU + WEB_SEPARATOR +"category";
	
	public static final String WEB_MENU_CATEGORY_ITEM = WEB_MENU_CATEGORY + WEB_SEPARATOR +"item";
	
	public final static String ANONYMOUS_USER_NAME = "anonymous";
	
	public static final String WEB_TREE = WEB_NAME + WEB_SEPARATOR + "tree";

	public static final String WEB_TREE_DEPT = WEB_TREE + WEB_SEPARATOR + "dept";
	
	public static final String WEB_TREE_DEPT_GRADE = WEB_TREE_DEPT + WEB_SEPARATOR + "grade";

	public static final String WEB_TREE_DEPT_GRADE_CLAS = WEB_TREE_DEPT_GRADE + WEB_SEPARATOR + "clas";

	public static final String WEB_TREE_DEPT_GRADE_CLAS_YEAR = WEB_TREE_DEPT_GRADE_CLAS + WEB_SEPARATOR + "year";
	
	public static final String WEB_TREE_DEPT_GRADE_CLAS_YEAR_SEMESTER = WEB_TREE_DEPT_GRADE_CLAS + WEB_SEPARATOR + "semester";
	
	public static final String WEB_TREE_DEPT_GRADE_CLAS_YEAR_SEMESTER_WEEK = WEB_TREE_DEPT_GRADE_CLAS + WEB_SEPARATOR + "week";
	
	public final static int PAGE_SIZE = 10;
}
