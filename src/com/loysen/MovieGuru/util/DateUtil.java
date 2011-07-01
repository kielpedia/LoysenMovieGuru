package com.loysen.MovieGuru.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
	
	public static String dateToString(Date date) {
		return formatter.format(date);
	}
	
	public static Date stringToDate(String date) {
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
