package com.hhz.tms.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期处理类
 * 
 * @author Jian
 */
public class DateOperator extends DateParser {
	// 若客户端产生并发的情况，会有问题。
	// private static final Calendar calendar = Calendar.getInstance();

	public static Date addMonthes(Date date, int monthes) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthes);
		return calendar.getTime();
	}

	public static int getMonthes(Date date1, Date date2) {
		long miu = DateParser.getMinutes(date1, date2);
		return (int) (miu / (60 * 24 * 31));
	}

	public static Date addYears(Date date, int years) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	/**
	 * 取得每月的最后一天
	 * 
	 * @param year
	 * @param month12
	 *            实际月份1-12
	 * @return
	 */
	public static Date getLastDayOfMonth12(int year, int month12) {
		return getLastDayOfMonth11(year, month12 - 1);
	}

	/**
	 * 取得每月的第一天
	 * 
	 * @param year
	 * @param month12
	 *            实际月份1-12
	 * @return
	 */
	public static Date getFirstDayOfMonth12(int year, int month12) {
		return getFirstDayOfMonth11(year, month12 - 1);
	}

	/**
	 * 把一个日期值添加某些天,在查询条件当中如果取两个日期之前进行查询 一般情况下要把截止日期加上1天
	 * 
	 * @param date
	 *            原来的日期
	 * @param days
	 *            要添加的天数
	 * @return 加完后的日期,如果传入的Date为null则也返回null
	 */

	public static Date addHours(Date date, int hour) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

	public static Date addMinutes(Date date, int minutes) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	/**
	 * 取得每月的最后一天
	 * 
	 * @param year
	 * @param month11
	 *            计算机月份0-11
	 * @return
	 */
	public static Date getLastDayOfMonth11(int year, int month11) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month11, 01);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 取得每月的第一天
	 * 
	 * @param year
	 * @param month11
	 *            计算机月份0-11
	 * @return
	 */
	public static Date getFirstDayOfMonth11(int year, int month11) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month11, 01);
		return calendar.getTime();
	}

	public static int compareYear(Date startDate, Date endDate) {
		Long years = new Long(0);
		if (startDate != null && endDate != null) {
			years = ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24 / 365);
		}
		if (years.intValue() == 0)
			return years.intValue();
		if (addYears(startDate, years.intValue()).after(endDate)
				|| addYears(startDate, years.intValue()).equals(endDate))
			return years.intValue();
		else
			return years.intValue() - 1;
	}

	public static int compareMonthes(Date startDate, Date endDate) {
		Long monthes = new Long(0);
		if (startDate != null && endDate != null) {
			monthes = ((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24 / 31);
		}
		if (monthes.intValue() == 0)
			return monthes.intValue();
		if (addMonthes(startDate, monthes.intValue()).before(endDate)
				|| addMonthes(startDate, monthes.intValue()).equals(endDate))
			return monthes.intValue();
		else
			return monthes.intValue() - 1;
	}

	public static int compareMonthes_1(Date startDate, Date endDate) {
		BigDecimal monthes = new BigDecimal(0);
		if (startDate != null && endDate != null) {
			monthes = monthes.valueOf((endDate.getTime() - startDate.getTime()) / 1000f / 60f / 60f / 24f / 31f);
		}
		return monthes.setScale(0, BigDecimal.ROUND_DOWN).intValue();
	}

	public static Long compareWeek(Date startDate, Date endDate) {
		BigDecimal days = new BigDecimal(0);
		if (startDate != null && endDate != null) {
			days = days.valueOf((endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24 / 7);
		}
		return days.setScale(0, BigDecimal.ROUND_UP).longValue();
	}

	public static Long compareDays(Date startDate, Date endDate) {
		return getDays(startDate, endDate);
	}

	/**
	 * 根据指定时区转换时间
	 * 
	 * @param date
	 * @param tz
	 * @return
	 */
	public static Date convert2TimeZone(Date date, TimeZone tz) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(tz);
		return calendar.getTime();
	}

	public static Long compareHours(Date startDate, Date endDate) {
		return getHours(startDate, endDate);
	}

	public static Long compareMinutes(Date startDate, Date endDate) {
		return getMinutes(startDate, endDate);
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取得对应日期的季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getQuarter(Date date) {
		int month = getMonth11(date);
		int quarter = month / 3 + 1;
		return quarter;
	}

	/**
	 * 根据季度取得月份
	 * 
	 * @param quarter
	 * @return
	 */
	public static int[] getMonthsByQuarter(int quarter) {
		int[] months = new int[3];
		switch (quarter) {
		case 1:
			months[0] = 1;
			months[1] = 2;
			months[2] = 3;
			break;
		case 2:
			months[0] = 4;
			months[1] = 5;
			months[2] = 6;
			break;
		case 3:
			months[0] = 7;
			months[1] = 8;
			months[2] = 9;
			break;
		case 4:
			months[0] = 10;
			months[1] = 11;
			months[2] = 12;
			break;
		default:
			break;
		}
		return months;
	}

	/**
	 * @param date
	 * @return 0-11,计算机月份
	 */
	public static int getMonth11(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * @param date
	 * @return 取得当前时间是今年的第几周
	 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * @param date
	 * @return 取得当前时间是本月的第几周
	 */
	public static int getWeekOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * @param date
	 * @return 1-12,实际月份
	 */
	public static int getMonth12(Date date) {
		return getMonth11(date) + 1;
	}

	public static Date addDays(Date date, int days) {
		if (date == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public static Date createDate(int year, int month, int day) {
		return createDate(year, month, day, 0, 0, 0);
	}

	public static Date createDate(int year, int month, int day, int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hourOfDay, minute, second);
		return cal.getTime();
	}

	public static String month2String(int m) {
		if (m < 10)
			return "0" + String.valueOf(m);
		else
			return String.valueOf(m);
	}

	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得对应月份的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(int year, int month11) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month11, 01);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static boolean isTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int minute = calendar.get(Calendar.MINUTE);
		return minute > 0;
	}

	public static boolean isWeekday(Date date) {
		return !(isSaturday(date) || isSunday(date));
	}

	public static boolean isSaturday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_WEEK);
		return index == Calendar.SATURDAY;
	}

	public static boolean isSunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_WEEK);
		return index == Calendar.SUNDAY;
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * 去掉时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date truncDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 取得当天一秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date lastSeDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 取上个月的最后一天
	 * 
	 * @return
	 */

	public static Date getLastDayOfLastMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, -1);
		return DateOperator.getLastDayOfMonth(calendar.getTime());
	}

	/**
	 * 取上个月的第一天
	 * 
	 * @return
	 */

	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.roll(Calendar.MONDAY, -1);// 获得上一个月日期即月份减去一月
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		// calendar.roll(Calendar.DATE, 1); //设置日期为当前月的第一天
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();

	}

	/**
	 * 取本月的最后一天
	 * 
	 * @return
	 */

	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// calendar.roll(Calendar.DATE, 0 - calendar.get(Calendar.DATE));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	/**
	 * 
	 * 
	 * @return
	 */

	public static Long getLastYear(Long year, String month) {

		if (new Integer(month) == 1) {
			year = year - 1;
		}
		return year;
	}

	public static String getLastMonth(Long year, String month) {
		if (new Integer(month) == 1) {
			month = "12";
		} else {
			month = DateOperator.month2String(new Integer(month) - 1);
		}
		return month;
	}

	public static Long getNextYear(Long year, String month) {

		if (new Integer(month) == 12) {
			year = year + 1;
		}
		return year;
	}

	public static String getNextMonth(Long year, String month) {
		if (new Integer(month) == 12) {
			month = "01";
		} else {
			month = DateOperator.month2String(new Integer(month) + 1);
		}
		return month;
	}

	public static String getSecond2Time(long second, boolean isSecond, boolean isChinese) {
		StringBuffer rtnValue = new StringBuffer();
		int h = Double.valueOf(Math.floor(second / 60 / 60)).intValue();
		int m = Double.valueOf(Math.floor((second - 60 * 60 * h) / 60)).intValue();
		int s = Double.valueOf(Math.floor((second - 60 * 60 * h - 60 * m))).intValue();
		if (isChinese) {
			rtnValue.append((h < 10 ? ("0" + h) : h)).append("小时").append((m < 10 ? ("0" + m) : m)).append("分");
			if (isSecond) {
				rtnValue.append((s < 10 ? ("0" + s) : s)).append("秒");
			}
		} else {
			rtnValue.append((h < 10 ? ("0" + h) : h)).append(":").append((m < 10 ? ("0" + m) : m)).append(":");
			if (isSecond) {
				rtnValue.append((s < 10 ? ("0" + s) : s));
			}
		}
		return rtnValue.toString();
	}

	/**
	 * 将秒转化成 00:00:00 或者中文 的 00小时00分00秒
	 * 
	 * @param second
	 * @param isChinese
	 * @return
	 */
	public static String getSecond2Time(long second, boolean isChinese) {
		return getSecond2Time(second, true, isChinese);
	}
}
