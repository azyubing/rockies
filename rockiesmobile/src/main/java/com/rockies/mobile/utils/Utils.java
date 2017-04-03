package com.rockies.mobile.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.rockies.mobile.constants.Constants;
import com.rockies.model.User;

public class Utils {
	
    private static SecureRandom random = new SecureRandom();

    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

	/**
	 * is the value valid with the regExpression
	 * 
	 * @param pNumber
	 * @param regEx
	 * @param isCaseSensitive
	 * @return
	 */
	public static boolean isValid(String pNumber, String regEx, boolean isCaseSensitive) {
		Pattern pattern;
		if (isCaseSensitive) {
			pattern = Pattern.compile(regEx);
		} else {
			pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		}
		Matcher matcher = pattern.matcher(pNumber);
		return matcher.find();
	}

	/**
	 * generate the returned ajax result
	 * 
	 * @param status
	 * @param object
	 * @param information
	 * @return
	 */
	public static String getReturnedData(String status, Object object, String information) {
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("body", object);
		map.put("message", information);
		String content = gson.toJson(map);
		return content;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static User getUserInstance(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(Constants.USER);
		// if (null == user) {
		// user = new User();
		// user.setId(75);
		// user.setUsername("junking1983");
		// user.setPhoto("http://www.rockies.cn/images/2.jpg");
		// }
		return user;
	}

	/**
	 * 当月第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getFirstDay(String date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(daystart);
			Date theDate = calendar.getTime();
			GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
			gcLast.setTime(theDate);
			gcLast.set(Calendar.DAY_OF_MONTH, 1);
			String day_first = df.format(gcLast.getTime());
			StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
			return str.toString();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 当月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastDay(String date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(daystart);
			calendar.add(Calendar.MONTH, 1); // 加一个月
			calendar.set(Calendar.DATE, 1); // 设置为该月第一天
			calendar.add(Calendar.DATE, -1);
			Date theDate = calendar.getTime();
			String s = df.format(theDate);
			StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
			return str.toString();
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date getFirstDayOfMonth(String date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(daystart);
			// calendar.add(Calendar.MONTH, 1); // 加一个月
			calendar.set(Calendar.DATE, 1); // 设置为该月第一天
			// calendar.add(Calendar.DATE, -1);
			Date theDate = calendar.getTime();
			return theDate;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date getLastDayOfMonth(String date) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(daystart);
			calendar.add(Calendar.MONTH, 1); // 加一个月
			calendar.set(Calendar.DATE, 1); // 设置为该月第一天
			calendar.add(Calendar.DATE, -1);
			Date theDate = calendar.getTime();
			return theDate;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date getDay(String date, int offset) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date daystart = df.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(daystart);
			// calendar.add(Calendar.MONTH, 1); // 加一个月
			calendar.set(Calendar.DATE, offset); // 设置为该月第一天
			// calendar.add(Calendar.DATE, -1);
			Date theDate = calendar.getTime();
			return theDate;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getDay(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static Date getDay(Date beginDate, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		calendar.add(Calendar.DATE, offset);
		Date theDate = calendar.getTime();
		return theDate;
	}

	public static String convertObjectToJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	public static String getStringValue(Object obj) throws UnsupportedEncodingException {
		String value = String.valueOf(obj);
		return new String(value.getBytes(Constants.ISO8859_1), Constants.UTF8);
	}
}
