package org.lch.attendance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static Date fomatter(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String d = sdf.format(date);
		return string2Date(d);
	}
	public static Date string2Date(String d)
	{
		if(d==null || d.trim().length()==0)
		{
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			return sdf.parse(d);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static String Date2String(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
		
	}
	
	  /**
	 * 根据日期计算属于第几周
	 * @param date 格式 yyyy-MM-dd
	 * @throws ParseException
	 */
	public static int getWeekOfYear2(String date){
	 
	    try {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
	        //cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
	        cal.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天
	        cal.setTime(df.parse(date));
	        return cal.get(Calendar.WEEK_OF_YEAR);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}	
	public static int getWeekNum(Date date){
		int week = 0;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			cal.setTime(format.parse(format.format(date)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;
	}
	public static void main(String[] args) throws ParseException {
//		int i = getWeekOfYear2("2012-05-02");
//		System.out.println(i);
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			cal.setTime(format.parse("2012-05-02"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int week = cal.get(Calendar.WEEK_OF_YEAR);
//		System.out.println(getWeekNum(new Date()));
		
		String myString1 = "2012-05-01";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date d1 = sdf1.parse(myString1);
		
		String myString2 = "2012-05-02";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date d2 = sdf2.parse(myString2);
		boolean flag = d2.after(d1);
		if(flag)
		System.out.print("早于今天");
		else
		System.out.print("晚于今天");
		
	}
	
}
