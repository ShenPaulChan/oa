package cn.com.bizunited.cp.oa.utils;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static Logger logger = Logger.getLogger(DateUtils.class);
    public static final long ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000;
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DAY_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_MONTH_PATTERN = "yyyy年MM月";
    public static final String DEFAULT_MONTH_NO_CH_PATTERN = "yyyyMM";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm";
    public static final String DEFAULT_DATE_ALL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_ALL_PATTERN = "HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    public static final String DEFAULT_DATE_TIME_NYR = "yyyy/MM/dd";
    public static final String DEFAULT_DATE_STRTIME_NYR = "yyyyMMdd";
    public static final String DEFAULT_DATE_YMR_CH = "yyyy年MM月dd日";


    public static String getDefaultFormatDateStr(Date date) {
        return getFormatDateStr(date, DEFAULT_TIME_ALL_PATTERN);
    }

    public static String getFormatDateStr(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        DateFormat dtFmt = new SimpleDateFormat(pattern, Locale.US);
        return dtFmt.format(date);
    }

    public static Calendar getOffsetDateForGivenDate(Date givenDate, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(givenDate);
        cal.add(Calendar.DATE, offset);
        return cal;
    }

    public static Calendar getOffsetMinuteForGivenDate(Date givenDate, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(givenDate);
        cal.add(Calendar.MINUTE, offset);
        return cal;
    }

    public static String getWeekDay(Date time) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 传入时间返回时间戳(秒) **
     */
    public static long convertTimeStamp(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime() / 1000;
    }

    /**
     * 传入时间戳(秒)返回时间 **
     */
    public static Date convertDate(long millSec) {
        return new Date(millSec);
    }

    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static String convertDateStr(long millSec) {
        DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd");
        return fmtDateTime.format(new Date(millSec));
    }

    public static Date extractionDate(long millSec) {
        Date in = new Date(millSec * 1000);
        DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd");
        String text = fmtDateTime.format(in);
        ParsePosition pos = new ParsePosition(0);
        return fmtDateTime.parse(text, pos);
    }

    public static int[] monthToYear(int month) {
        int[] ret = new int[]{0, 0};
        ret[0] = month / 12;
        if (month % 12 != 0) {
            ret[1] = month % 12;
        }
        return ret;
    }

    public static String monthToYearStr(int month) {
        int[] ret = monthToYear(month);
        String str = ret[0] + "岁";
        if (ret[0] <= 5) {
            if (ret[1] != 0) {
                str += ret[1] + "个月";
            }
        }
        return str;
    }

    /**
     * ** 根据传入的时间和格式获得字符串 *****
     */
    public static String getDateStrByFormat(Date date, String format) {
        if (date == null)
            return "";
        DateFormat dtFmt = new SimpleDateFormat(format);
        return dtFmt.format(date);
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        long between = Math.abs(date2.getTime() - date1.getTime());

        if (between > ONE_DAY_MILLISECONDS) {
            return false;
        } else if (between == ONE_DAY_MILLISECONDS) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            if (cal1.get(Calendar.HOUR_OF_DAY) == 0 && cal2.get(Calendar.HOUR_OF_DAY) == 0) {
                return true;
            }
            if (cal1.get(Calendar.HOUR_OF_DAY) == 24 && cal2.get(Calendar.HOUR_OF_DAY) == 24) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * ** 根据传入的时间字符串和格式获得Date对象 *****
     */
    public static Date getDateByFormat(String date, String format) {
        if (date == null)
            return null;
        DateFormat dtFmt = new SimpleDateFormat(format);
        Date dateTime = null;
        try {
            dateTime = dtFmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * *** 传入时间戳(秒),计算2个时间差多少天(自然天，不是24小时，如今天凌晨0点1分和昨天23点59分相差为1天) *******
     */
    public static long getDistanceDay(long date1, long date2) {
        String dateStr1 = getDateStrByFormat(new Date(date1 * 1000), "yyyy-MM-dd");
        String dateStr2 = getDateStrByFormat(new Date(date2 * 1000), "yyyy-MM-dd");

        Date date3 = getDateByFormat(dateStr1, "yyyy-MM-dd");
        Date date4 = getDateByFormat(dateStr2, "yyyy-MM-dd");

        long newDate1 = convertTimeStamp(date3);
        long newDate2 = convertTimeStamp(date4);
        long diff;
        if (newDate1 >= newDate2) {
            diff = newDate1 - newDate2;
        } else {
            diff = newDate2 - newDate1;
        }
        return diff / (60 * 60 * 24);
    }

    /**
     * *** 传入时间戳,计算2个时间差多少秒 *******
     */
    public static int getDistanceSecond(long date1, long date2) {
        long diff = date1 - date2;
        double diffDouble = diff / 1000;
        return (int) diffDouble;
    }


    /**
     * *** 根据生日算年龄 *******
     */
    public static Integer getAge(Date birthday) {
        if (birthday == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int yearBirth = calendar.get(Calendar.YEAR);
        if (yearNow - yearBirth < 0) {
            return null;
        }
        return yearNow - yearBirth;
    }

    /**
     * *** 传人两个时间求之间天数 *******
     */
    public static int getDayCount(Date startDate, Date endDate) {
        if (!startDate.before(endDate)) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int startDayCount = (int) calendar.getTimeInMillis() / 86400000;
        calendar.setTime(endDate);
        int endDayCount = (int) calendar.getTimeInMillis() / 86400000;
        return endDayCount - startDayCount;
    }

    /**
     * 获得周一到周七的文本 **
     */
    public static String getWeekDayStr(Integer weekDay) {
        String str = "周";
        switch (weekDay) {
            case 1:
                str += "一";
                break;
            case 2:
                str += "二";
                break;
            case 3:
                str += "三";
                break;
            case 4:
                str += "四";
                break;
            case 5:
                str += "五";
                break;
            case 6:
                str += "六";
                break;
            case 7:
                str += "日";
                break;
        }
        return str;
    }

    /**
     * 获得周上午下午晚上的文本 **
     */
    public static String getDayTypeStr(Integer dayType) {
        String str = "";
        switch (dayType) {
            case 1:
                str += "上午";
                break;
            case 2:
                str += "下午";
                break;
            case 3:
                str += "晚上";
                break;
        }
        return str;
    }

    /**
     * *** 根据传入时间判断是否为当天 *******
     */
    public static boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int startDayCount = (int) calendar.getTimeInMillis() / 86400000;
        calendar.setTime(date);
        int endDayCount = (int) calendar.getTimeInMillis() / 86400000;
        return endDayCount == startDayCount;
    }

    /**
     * *** 根据传入时间和相差天数计算结果Date *******
     */
    public static Date getDayByDateDistance(Date date, int distanceDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, distanceDay); // 日期减1
        Date lastDay = calendar.getTime(); // 结果
        return lastDay;
    }

    /**
     * *** 根据传入时间和相差秒数计算结果Date *******
     */
    public static Date getTimeByTimeAndDiffSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        Date lastDay = calendar.getTime(); // 结果
        return lastDay;
    }

    public static int getCurrentTime() {
        return Long.valueOf(new Date().getTime() / 1000).intValue();
    }

    public static String getDateStrByTimeInt(Integer time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Long timeLong = Long.parseLong(time + "");
        Date dt = new Date(timeLong * 1000);
        String sDateTime = sdf.format(dt);
        return sDateTime.toString();
    }

    public static Date increaseDate(Date time, int unit, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(unit, calendar.get(unit) + value);
        return calendar.getTime();
    }

    public static Date increaseDate(Date time, int value) {
        return increaseDate(time, Calendar.MINUTE, value);
    }



    public static boolean isOverlap(Date dt1Start, Date dt1End, Date dt2Start, Date dt2End) {
        if (dt1Start == null || dt1End == null || dt2Start == null || dt2End == null) {
            throw new IllegalArgumentException("param error");
        }
        long dt1StartLong = dt1Start.getTime();
        long dt1EndLong = dt1End.getTime();
        long dt2StartLong = dt2Start.getTime();
        long dt2EndLong = dt2End.getTime();

        if (dt1StartLong >= dt1EndLong) {
            throw new IllegalArgumentException("param error,param1 must before param2");
        }
        if (dt2StartLong >= dt2EndLong) {
            throw new IllegalArgumentException("param error,param3 must before param4");
        }
        if (dt1EndLong <= dt2StartLong) {
            return false;
        } else if (dt1StartLong >= dt2EndLong) {
            return false;
        }
        return true;
    }

    public static boolean isDateBetween(Date start, Date end, Date paramDate) {
        if (start == null || end == null || paramDate == null) {
            throw new IllegalArgumentException("param error");
        }
        long startLong = start.getTime();
        long endLong = end.getTime();
        long paramLong = paramDate.getTime();

        if (paramLong < startLong) {
            return false;
        } else if (paramLong > endLong) {
            return false;
        }
        return true;
    }

    /**
     * ***** 比较一个时间是否比另一个大 *******
     */
    public static boolean equalIsDateMoreThanAnother(Date date1, Date date2) {
        if (date1.getTime() >= date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDateExpired(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTime().before(new Date());
    }

    public static Date getTodayStartTime(Date date) {
        if(date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime();
    }

    public static Date getTodayEndTime(Date date) {
        if(date == null) {
            date = new Date();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }

    /**
     * 根据一个时间获得年、月、日字符串数组
     * @param date
     * @return
     */
    public static String[] getYearMonthDayByDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month =  calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new String[]{year+"",month+"",day+""};
    }


}
