package org.lch.attendance.util;

import java.util.List;

/**
 * 字符串处理通用类
 * @author Administrator
 *
 */
public class StringUtils {
	
	public static String join(List<String> list, String str) {
		if(list == null || list.isEmpty()) return "";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			if(i != 0) buf.append(str);
			buf.append(list.get(i)); 
		}
		return buf.toString();
	}
	
	public static String avoidNull(String value) {
		return (value == null) ? "" : value;
	}
	
	public static Integer[] String2IntegerArray(String str){
		String[] tmp = str.split(",");
		Integer[] val = new Integer[tmp.length];
		for(int i=0; i<tmp.length; i++){
			val[i] = Integer.parseInt(tmp[i]);
		}
		return val;
	}
	public static String formatUUID(String uuid){
		return uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+
				uuid.substring(19,23)+uuid.substring(24);
	}
	public static void main(String[] args) {
		String s = "1";
		Integer[] a = String2IntegerArray(s);
		for(Integer x : a ){
			System.out.println(x);
		}
	}
}
