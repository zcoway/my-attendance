package org.lch.attendance.common;

public class StringUtils {
	
	public static String avoidNull(String value) {
		return (value == null) ? "" : value;
	}
}
