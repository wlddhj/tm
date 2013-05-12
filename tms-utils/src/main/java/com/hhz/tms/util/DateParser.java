/*
 * Created on 2005-6-20
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.hhz.tms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.GenericValidator;

/**
 * 日期转化类
 * 
 * @author huangj 2009-12-16
 */
public class DateParser {
	
	private static final Calendar calendar = Calendar.getInstance();
	
	public static String FORMAT_STR = "yyyy-MM-dd";
	
	public static String FORMAT_STR_WITH_TIME = "yyyy-MM-dd HH:mm";

	public static String FORMAT_STR_WITH_TIME_S = "yyyy-MM-dd HH:mm:ss";
	
	public static Locale defaultLocale = Locale.getDefault();
	
	public static SimpleDateFormat defaultDateFormaterWithTime = new SimpleDateFormat(FORMAT_STR_WITH_TIME);

	public static SimpleDateFormat defaultDateFormatter = new SimpleDateFormat(FORMAT_STR);

	public static SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy/MM/dd");
	
	public static SimpleDateFormat dateFormatterMd = new SimpleDateFormat("MM-dd");

	public static SimpleDateFormat dateFormatter3 = new SimpleDateFormat("yyyy\\MM\\dd");

	/**
	 * 将字符串转化为日期，允许字符串以-或\或/来分隔年月日
	 */
	public static Date parse(String dateString) throws RuntimeException {
		if (dateString == null)
			return null;
		Date date = null;
		if (isValidateDateString(dateString)) {
			try {
				if (dateString.indexOf("-") > 0) {
					if (GenericValidator.isDate(dateString, "yy-MM-dd", true)) {
						date = new SimpleDateFormat("yy-MM-dd")
								.parse(dateString);
					} else if (GenericValidator.isDate(dateString, "dd-MM-yy",
							true)) {
						date = new SimpleDateFormat("dd-MM-yy").parse(dateString);
					} else {
						date = defaultDateFormatter.parse(dateString);
					}
				}
				if (dateString.indexOf("/") > 0) {
					date = dateFormatter2.parse(dateString);
				}
				if (dateString.indexOf("\\") > 0) {
					date = dateFormatter3.parse(dateString);
				}

			} catch (ParseException e) {
				try {
					return parse(dateString, FORMAT_STR_WITH_TIME);
				} catch (RuntimeException ex) {
					throw new RuntimeException("error.dateFormatError");
				}
			}
		}
		return date;
	}

	/**
	 * 将字符串转化为日期，日期格式只允许-来分隔年月日
	 * 
	 * @param dateStr
	 *            要被转化的日期（或和时间）
	 * @param isMustTime
	 *            是否包含时间
	 * @return 被转化后的日期，如果无法转化则返回null
	 */
	public static Date parse(String dateStr, boolean isMustTime){
		try {
			if (isMustTime)
				return defaultDateFormaterWithTime.parse(dateStr);
			else
				return defaultDateFormatter.parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 按照指定的格式将字符串转化为日期，如果无法转化则会抛出异常
	 */
	public static Date parse(String dateString, String formatString) {
		if (StringUtils.isBlank(dateString))
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				formatString != null ? formatString : FORMAT_STR, defaultLocale);
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException ex1) {
			try {
				dateFormat.applyLocalizedPattern(FORMAT_STR);
				return dateFormat.parse(dateString);
			} catch (ParseException ex2) {
				try {
					dateFormat.applyLocalizedPattern(FORMAT_STR_WITH_TIME);
					return dateFormat.parse(dateString);
				} catch (ParseException ex) {
					throw new RuntimeException("error.dateFormatError");
				}
			}
		}
	}

	/**
	 * 判断一个字符串是否能转换成日期,空串也会返回 true (空串解析成日期时返回 null)
	 */
	public static boolean isValidateDateString(String dateString) {
		boolean isDate = true;
		if (!StringUtils.isEmpty(dateString)) {
			isDate = GenericValidator.isDate(dateString, "yyyy-MM-dd", true)
					|| GenericValidator.isDate(dateString, "yyyy-MM-d", true)
					|| GenericValidator.isDate(dateString, "yyyy-M-dd", true)
					|| GenericValidator.isDate(dateString, "yy-MM-dd", true)
					|| GenericValidator.isDate(dateString, "yyyy/MM/dd", true)
					|| GenericValidator.isDate(dateString, "yyyy/MM/d", true)
					|| GenericValidator.isDate(dateString, "yyyy/M/dd", true)
					|| GenericValidator.isDate(dateString, "yyyy/M/d", true)
					|| GenericValidator
							.isDate(dateString, "yyyy\\MM\\dd", true)
					|| GenericValidator.isDate(dateString, "yyyy\\MM\\d", true)
					|| GenericValidator.isDate(dateString, "yyyy\\M\\dd", true)
					|| GenericValidator.isDate(dateString, "yyyy\\M\\d", true);
		}
		if (isDate==false){ 
			try {
				defaultDateFormatter.parse(dateString);
				isDate = true;
			} catch (ParseException e) {
				throw new RuntimeException("error.dateFormatError");
			}
		}

		return isDate;
	}

	/**
	 * 将一个日期转化为字符串
	 * 
	 * @param date
	 *            要被转化的日期，不包含时间
	 */
	public static String formatDate(Date date){
		return formatDate(date, false);
	}

	/**
	 * 将一个日期转化为字符串，包含时间
	 */
	public static String formatDate(Date date, boolean withTime){
		if (withTime)
			return formatDate(date, defaultDateFormaterWithTime);
		else
			return formatDate(date,defaultDateFormatter);
	}

	/**
	 * 按照指定的格式将日期（允许包含时间）转化为字符串
	 * 
	 * @return 转化后的字符串
	 */
	public static String formatDate(Date date, String formatString){
		return formatDate(date,new SimpleDateFormat(formatString, defaultLocale));
	}

	/**
	 * 按照指定的格式将日期（允许包含时间）转化为字符串，如果年份小于1000或都年份大于3000则认为是非法数据返回空串。
	 * 
	 * @return 转化后的字符串
	 */
	public static String formatDate(Date date, DateFormat dateFormat){
		if (date != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			// 如果年份小于1000或都年份大于3000则认为是非法数据返回空串
			if (year < 1000 || year > 3000)
				return "";
			return dateFormat.format(date);
		}
		return "";
	}

	/**
	 * 把日期添加1天,在设查询条件的时候使用
	 * 
	 * @deprecated 命名与功能有问题，功能为加上一个小时，命名为加上一天，不建议再使用
	 */
	@Deprecated
	public static Date addOneDay(Date date){
		return addDays(date, 1);
	}

	/**
	 * 得到小于当前时间一个月的时间
	 */
	public static Date getDateLeNow(){
		Calendar calendarIn = Calendar.getInstance();
		calendarIn.setTime(new Date());
		calendarIn.roll(Calendar.MONTH,-1);
		return calendarIn.getTime();
	}

	/**
	 * 得到当前月的时间
	 */
	public static Date getDateNow(){
		Calendar calendarIn = Calendar.getInstance();
		calendarIn.setTime(new Date());
		calendarIn.roll(Calendar.MONTH,0);
		return calendarIn.getTime();
	}

	/**
	 * 把时间转化为月日格式的字符串
	 */
	public static String formateDate2Md(Date date){
		return dateFormatterMd.format(date);
	}

	/**
	 * 把一个日期值加上相应的小时数
	 * 
	 * @deprecated use DateParser#addHours instead
	 * @param date
	 *            原来的日期
	 * @param days
	 *            要添加的小时数
	 * @return 加完后的日期,如果传入的Date为null则也返回null
	 */
	@Deprecated
	public static Date addDays(Date date, int days){
		if (date == null)
			return null;
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, days);
		return calendar.getTime();
	}

	/**
	 * 把时间加上相应的小时数
	 * 
	 * @return
	 */
	public static Date addHours(Date date, int num){
		return addDays(date, num);
	}

	/**
	 * 时间相加,加上具体的天
	 * 
	 * @deprecated use DateParser#addDate instead
	 * @return
	 */
	@Deprecated
	public static Date addDaysByDays(Date date, int days){
		if (date == null)
			return null;
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * 时间相加,加上具体的天
	 */
	public static Date addDate(Date date, int num){
		return addDaysByDays(date, num);
	}

	/**
	 * 计算从开始的日期到结束的日期之间有几天
	 * 
	 * @param startDate
	 *            开始的日期
	 * @param endDate
	 *            结束的日期
	 * @return 两个日期之间的天数
	 */
	public static Long getDays(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			return null;
		Long num = ( endDate.getTime() - startDate.getTime() ) / (24*60*60*1000);
		// 如果两个日期包含时间则可能出现误差,所以需要修正
		Date temp = new Date(startDate.getTime() + 24*60*60*1000*num);
		if (DateUtils.isSameDay(temp, endDate)) {
			if (endDate.compareTo(temp) > 0) {
				num++;
			}
			if (endDate.compareTo(temp) < 0) {
				num--;
			}
		}
		return num;
	}

	/**
	 * 计算从开始的时间到结束的时间之间有几个小时
	 * 
	 * @param startDate
	 *            开始的时间
	 * @param endDate
	 *            结束的时间
	 * @return 两个时间之间的小时数
	 */
	public static Long getHours(Date startDate, Date endDate) {
		Long hours = new Long(0);
		if (startDate != null && endDate != null) {
			hours = ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60);
		}
		return hours;
	}

	/**
	 * 计算从开始的时间到结束的时间之间有几分钟
	 * 
	 * @param startDate
	 *            开始的时间
	 * @param endDate
	 *            结束的时间
	 * @return 两个时间之间的分钟数
	 */
	public static Long getMinutes(Date startDate, Date endDate) {
		Long minute = new Long(0);
		if (endDate != null && startDate != null) {
			minute = ((startDate.getTime() - endDate.getTime()) / 1000 / 60);
		}
		return minute;
	}

	/**
	 * 将年月日和时间合并成带具体时间的方法<br>
	 * 为了将Ts的时间和日期分开存储 转换成一个时间对象存储
	 * 
	 * @param date
	 *            具体的日期，格式为：yyyy-MM-dd
	 * @param longTime
	 *            具体的时间，格式为：958或2109
	 * @return
	 */
	public static Date getDateTime(Date date,Long longTime){
		Calendar calendar=Calendar.getInstance();
		if (date == null)
			return null;
		calendar.setTime(date);
		int longHour=new Long(longTime/100).intValue();
		int longMinute=new Long(longTime%100).intValue();
		calendar.set(Calendar.HOUR_OF_DAY, longHour);
		calendar.set(Calendar.MINUTE, longMinute); 
		return calendar.getTime();
	}

	/**
	 * 给时间对象加月日
	 * 
	 * @param date
	 *            要被加上月日的时间
	 * @param strMd
	 *            格式为MM-dd
	 * @return
	 */
	public static Date getDateReplaceMd(Date date, String strMd){
		Calendar calendar=Calendar.getInstance();
		if (date == null || strMd == null)
			return null;
		calendar.setTime(date);
		
		Calendar calendarMd=Calendar.getInstance();
		Date dateMd = parse(strMd, "MM-dd");
		calendarMd.setTime(dateMd);
		int intMonth = calendarMd.get(Calendar.MONTH);
		int intDay = calendarMd.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.MONTH,intMonth);
		calendar.set(Calendar.DAY_OF_MONTH,intDay);
		return calendar.getTime();
	}
	
	public static void setUsLocale() {
		FORMAT_STR = "dd-MMM-yy";
		FORMAT_STR_WITH_TIME = "dd-MMM-yy HH:mm";
		defaultLocale = Locale.US;
		defaultDateFormaterWithTime = new SimpleDateFormat(FORMAT_STR_WITH_TIME, defaultLocale);
		defaultDateFormatter = new SimpleDateFormat(FORMAT_STR, defaultLocale);
	}

	/**
	 * 比较两个时间,如果oneDate大于twoDate返回true否则返回false wujia by 2008-05-05
	 * 
	 * @param oneDate
	 * @param twoDate
	 * @return
	 */
	public static boolean validateMaxDate(Date oneDate,Date twoDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date obj = sdf.parse(formatDate(oneDate));
			Date obj2 = sdf.parse(formatDate(twoDate));
			int i = obj.compareTo(obj2);
			if (i == 1)
				return true;
			else
				return false;
		}catch (ParseException pe) {
		}
		return false;
	}
	
}
