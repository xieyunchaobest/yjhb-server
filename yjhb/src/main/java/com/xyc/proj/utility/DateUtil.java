package com.xyc.proj.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	
	public static String getNow() {
		Calendar cal = Calendar.getInstance();
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal
				.getTime());
		return now;
	}
	
	public static String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		return yesterday;
	}
	
	public static String getToday() {
		Calendar cal = Calendar.getInstance();
		String today = new SimpleDateFormat("yyyy-MM-dd").format(cal
				.getTime());
		return today;
	}

}
