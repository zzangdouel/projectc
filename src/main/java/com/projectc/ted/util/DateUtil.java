package com.projectc.ted.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil
{
	public static String getCurrentTimeMinute()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

		String strTime = sdf.format(cal.getTime());
		return strTime;
	}
	
	public static String getCurrentTimeDay()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String strTime = sdf.format(cal.getTime());
		return strTime;
	}
	
	public static String curDate(int dateType) {
		
		String formatText = "";
		
		if(dateType == 1) {
			formatText = "yyyy-MM-dd HH:mm:ss";
		}else {
			formatText = "yyyyMMddHHmmss";
		}
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(formatText, Locale.KOREA);
		String curTime = sdf.format(dt);
		return curTime;
	}
	

}