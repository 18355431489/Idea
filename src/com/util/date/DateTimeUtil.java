package com.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * @author 唐小甫
 * @createTime 2020-11-21 15:11:22
 */
public final class DateTimeUtil {

    /** 时间类型 */
    public static final int YEAR        = 7;
    public static final int MONTH       = 6;
    public static final int WEEK        = 5;
    public static final int DAY         = 4;
    public static final int HOUR        = 3;
    public static final int MINUTE      = 2;
    public static final int SECOND      = 1;
    public static final int MILLISECOND = 0;

    /** 时间刻度 */
    public static final int MILLISECOND_SCALE           = 1000;
    public static final int MINUTE_SCALE                = 60;
    public static final int SECOND_SCALE                = 60;
    public static final int SECOND_HOUR_SCALE           = 3600;
    public static final int HOUR_DAY_SCALE              = 24;
    public static final int WEEK_SCALE                  = 7;
    public static final int FEBRUARY_SCALE              = 28;
    public static final int LEAP_YEAR_FEBRUARY_SCALE    = 29;
    public static final int MONTH_SCALE                 = 30;
    public static final int BIG_MONTH_SCALE             = 31;
    public static final int MONTH_YEAR_SCALE            = 12;
    public static final int YEAR_SCALE                  = 365;
    public static final int LEAP_YEAR_SCALE             = 366;
    public static final int CENTURY_SCALE               = 100;

    /** 默认日期-时间格式 */
    public static final String DEFAULT_ALL_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";
    /** 默认日期-时间格式1 */
    public static final String DEFAULT_DATETIME_PATTERN     = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期-时间格式2 */
    public static final String DEFAULT_DATETIME_PATTERN2    = "yyyy/MM/dd HH:mm:ss:SSS";
    /** 默认日期-时间格式3 */
    public static final String DEFAULT_DATETIME_PATTERN3    = "yyyy/MM/dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_PATTERN         = "yyyy-MM-dd";
    /** 默认日期格式1 */
    public static final String DEFAULT_DATE_PATTERN1        = "yyyy/MM/dd";
    /** 日期格式 */
    public static final String DATE_PATTERN                 = "yyyy-MM";
    /** 日期格式1 */
    public static final String DATE_PATTERN1                = "yyyy/MM";
    /** 日期格式2 */
    public static final String DATE_PATTERN2                = "MM-dd";
    /** 日期格式3 */
    public static final String DATE_PATTERN3                = "MM/dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_PATTERN         = "HH:mm:ss:SSS";
    /** 默认时间格式1 */
    public static final String DEFAULT_TIME_PATTERN1        = "HH:mm:ss";
    /** 无符号日期-时间格式 */
    public static final String UNSIGN_DATETIME_PATTERN      = "yyyyMMddHHmmssSSS";
    /** 无符号日期-时间格式1 */
    public static final String UNSIGN_DATETIME_PATTERN1     = "yyyyMMddHHmmss";
    /** 无符号日期格式 */
    public static final String UNSIGN_DATE_PATTERN          = "yyyyMMdd";
    /** 无符号日期格式1 */
    public static final String UNSIGN_DATE_PATTERN1         = "yyyyMM";
    /** 无符号日期格式2 */
    public static final String UNSIGN_DATE_PATTERN2         = "MMdd";
    /** 无符号时间格式 */
    public static final String UNSIGN_TIME_PATTERN          = "HHmmssSSS";
    /** 无符号时间格式1 */
    public static final String UNSIGN_TIME_PATTERN1         = "HHmmss";


    /**
     * 获取当前时间的字符串
     *
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-21 15:14:49
     * @see #getFormatString(Date, String)
     */
    public static String getFormatString() {
        return getFormatString(new Date(), DEFAULT_ALL_DATETIME_PATTERN);
    }


    /**
     * 获取当前时间的格式化字符串
     * @param pattern
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-21 15:16:49
     * @see #getFormatString(Date, String)
     */
    public static String getFormatString(String pattern) {
        return getFormatString(new Date(), pattern);
    }


    /**
     * 时间转化字符串
     * @param date
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-21 15:18:49
     * @see #getFormatString(Date, String)
     */
    public static String getFormatString(Date date) {
        return getFormatString(date, DEFAULT_ALL_DATETIME_PATTERN);
    }


    /**
     * 时间转成自定义格式字符串
     * @param date
     * @param format
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-21 15:18:49
     * @see #DEFAULT_ALL_DATETIME_PATTERN
     * @see #DEFAULT_DATETIME_PATTERN
     * @see #DEFAULT_DATETIME_PATTERN2
     * @see #DEFAULT_DATETIME_PATTERN3
     * @see #DEFAULT_DATE_PATTERN
     * @see #DEFAULT_DATE_PATTERN1
     * @see #DATE_PATTERN
     * @see #DATE_PATTERN1
     * @see #DATE_PATTERN2
     * @see #DATE_PATTERN3
     * @see #DEFAULT_TIME_PATTERN
     * @see #DEFAULT_TIME_PATTERN1
     * @see #UNSIGN_DATETIME_PATTERN
     * @see #UNSIGN_DATETIME_PATTERN1
     * @see #UNSIGN_DATE_PATTERN
     * @see #UNSIGN_DATE_PATTERN1
     * @see #UNSIGN_DATE_PATTERN2
     * @see #UNSIGN_TIME_PATTERN
     * @see #UNSIGN_TIME_PATTERN1
     */
    public static String getFormatString(Date date, String format) {
        if (date == null) {
            throw new RuntimeException("date不能为空");
        }
        if (format == null) {
            throw new RuntimeException("format不能为空");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 字符串转化时间
     * @param date
     * @return java.util.Date
     * @author 唐小甫
     * @datetime 2020-11-21 15:17:49
     * @see #DEFAULT_ALL_DATETIME_PATTERN
     * @see #DEFAULT_DATETIME_PATTERN
     * @see #DEFAULT_DATE_PATTERN
     * @see #DATE_PATTERN
     * @see #getDate(String, String)
     */
    public static Date getDate(String date) {
        if (date == null || date.length() < DATE_PATTERN.length()) {
            throw new RuntimeException("时间字符串错误");
        } else if (date.length() < DEFAULT_DATE_PATTERN.length()) {
            return getDate(date, DATE_PATTERN);
        } else if (date.length() < DEFAULT_DATETIME_PATTERN.length()) {
            return getDate(date, DEFAULT_DATE_PATTERN);
        } else if (date.length() < DEFAULT_ALL_DATETIME_PATTERN.length()) {
            return getDate(date, DEFAULT_DATETIME_PATTERN);
        }
        return getDate(date, DEFAULT_ALL_DATETIME_PATTERN);
    }


    /**
     * 字符串解析成时间
     * @param date
     * @param format
     * @return java.util.Date
     * @author 唐小甫
     * @datetime 2020-11-21 15:17:49
     * @see #DEFAULT_ALL_DATETIME_PATTERN
     * @see #DEFAULT_DATETIME_PATTERN
     * @see #DEFAULT_DATETIME_PATTERN2
     * @see #DEFAULT_DATETIME_PATTERN3
     * @see #DEFAULT_DATE_PATTERN
     * @see #DEFAULT_DATE_PATTERN1
     * @see #DATE_PATTERN
     * @see #DATE_PATTERN1
     * @see #DATE_PATTERN2
     * @see #DATE_PATTERN3
     * @see #DEFAULT_TIME_PATTERN
     * @see #DEFAULT_TIME_PATTERN1
     * @see #UNSIGN_DATETIME_PATTERN
     * @see #UNSIGN_DATETIME_PATTERN1
     * @see #UNSIGN_DATE_PATTERN
     * @see #UNSIGN_DATE_PATTERN1
     * @see #UNSIGN_DATE_PATTERN2
     * @see #UNSIGN_TIME_PATTERN
     * @see #UNSIGN_TIME_PATTERN1
     */
    public static Date getDate(String date, String format) {
        if (date == null) {
            throw new RuntimeException("date不能为空");
        }
        if (format == null) {
            throw new RuntimeException("format不能为空");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 校验时间字符串一致性
     * @param dateStr
     * @param format
     * @return boolean
     * @author 唐小甫
     * @datetime 2020-11-21 17:05:49
     * @see #DEFAULT_ALL_DATETIME_PATTERN
     * @see #DEFAULT_DATETIME_PATTERN
     * @see #DEFAULT_DATETIME_PATTERN2
     * @see #DEFAULT_DATETIME_PATTERN3
     * @see #DEFAULT_DATE_PATTERN
     * @see #DEFAULT_DATE_PATTERN1
     * @see #DATE_PATTERN
     * @see #DATE_PATTERN1
     * @see #DATE_PATTERN2
     * @see #DATE_PATTERN3
     * @see #DEFAULT_TIME_PATTERN
     * @see #DEFAULT_TIME_PATTERN1
     * @see #UNSIGN_DATETIME_PATTERN
     * @see #UNSIGN_DATETIME_PATTERN1
     * @see #UNSIGN_DATE_PATTERN
     * @see #UNSIGN_DATE_PATTERN1
     * @see #UNSIGN_DATE_PATTERN2
     * @see #UNSIGN_TIME_PATTERN
     * @see #UNSIGN_TIME_PATTERN1
     * @see #getFormatString(Date, String)
     */
    public static boolean validateFmtDate(String dateStr, String format) {
        Date date = getDate(dateStr, format);
        String fmtDateStr = getFormatString(date, format);
        return dateStr.equals(fmtDateStr);
    }


    /**
     * 平年/闰年
     * @param year
     * @return boolean true: 闰年，false: 平年
     * @author 唐小甫
     * @datetime 2020-11-21 17:23:49
     */
    public static boolean isLeapYear(int year) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, Calendar.FEBRUARY, LEAP_YEAR_FEBRUARY_SCALE);
        return instance.get(Calendar.DATE) == LEAP_YEAR_FEBRUARY_SCALE;
    }


    /**
     * 计算两个时间在指定时间类型上的差值
     * @param date1
     * @param date2
     * @param dateType 时间差类型
     * @return java.lang.Double
     * @author 唐小甫
     * @datetime 2020-11-21 18:39:49
     * @see #YEAR
     * @see #MONTH
     * @see #WEEK
     * @see #DAY
     * @see #HOUR
     * @see #MINUTE
     * @see #SECOND
     * @see #MILLISECOND
     */
    public static Double timeSub(Date date1, Date date2, int dateType) {
        if (date1 == null) {
            throw new RuntimeException("date1不能为空");
        }
        if (date2 == null) {
            throw new RuntimeException("date2不能为空");
        }
        if (date1.after(date2)) {
            throw new RuntimeException("date2必须大于date1");
        }
        Double result = (date2.getTime() - date1.getTime()) / 1.0;
        Calendar instance1 = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        long time;
        int count;
        switch (dateType) {
            case SECOND: return result / MILLISECOND_SCALE;
            case MINUTE: return result / MILLISECOND_SCALE / SECOND_SCALE;
            case HOUR: return result / MILLISECOND_SCALE / SECOND_HOUR_SCALE;
            case DAY: return result / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE;
            case WEEK: return result / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE / WEEK_SCALE;
            case MONTH:
                instance1.setTime(date1);
                instance2.setTime(date2);
                count = 0;
                while (instance1.compareTo(instance2) < 0) {
                    instance1.set(Calendar.MONTH, instance1.get(Calendar.MONTH) + 1);
                    count++;
                }
                time = instance1.getTimeInMillis() - instance2.getTimeInMillis();
                return count - time * 1.0 / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE / MONTH_SCALE;
            case YEAR:
                instance1.setTime(date1);
                instance2.setTime(date2);
                count = 0;
                while (instance1.compareTo(instance2) < 0) {
                    instance1.set(Calendar.YEAR, instance1.get(Calendar.YEAR) + 1);
                    count++;
                }
                time = instance1.getTimeInMillis() - instance2.getTimeInMillis();
                return count - time * 1.0 / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE / YEAR_SCALE;
            default: return result;
        }
    }


    /**
     * 时间差值，将 date1 与 date2 的时间差值转换成 [x天 x时 x分钟 x秒]
     * @param date1
     * @param date2
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020-11-21 20:56:49
     */
    public static String timeSub(Date date1, Date date2) {
        if (date1 == null) {
            throw new RuntimeException("date1不能为空");
        }
        if (date2 == null) {
            throw new RuntimeException("date2不能为空");
        }
        if (date1.after(date2)) {
            throw new RuntimeException("date2必须大于date1");
        }
        long result = (date2.getTime() - date1.getTime()) / MILLISECOND_SCALE;
        StringBuilder strBuilder = new StringBuilder();

        //秒
        long second = result % SECOND_SCALE;
        result /= SECOND_SCALE;
        strBuilder.insert(0, second + "秒");
        //分钟
        if (result > 0) {
            long minute = result % MINUTE_SCALE;
            result /= MINUTE_SCALE;
            strBuilder.insert(0, minute + "分钟 ");
        }
        //小时
        if (result > 0) {
            long hour = result % HOUR_DAY_SCALE;
            result /= HOUR_DAY_SCALE;
            strBuilder.insert(0, hour + "小时 ");
        }
        //天
        if (result > 0) {
            strBuilder.insert(0, result + "天 ");
        }
        return strBuilder.toString();
    }


    /**
     * 时间转换
     * @param number  时间值 int, 必须大于0
     * @param srcType 时间转换前类型
     * @param toType  时间转换后类型
     * @return java.lang.Double
     * @author 唐小甫
     * @datetime 2020-11-21 21:00:49
     * @see #YEAR
     * @see #MONTH
     * @see #WEEK
     * @see #DAY
     * @see #HOUR
     * @see #MINUTE
     * @see #SECOND
     * @see #MILLISECOND
     * @see #getMilliSecond(long, int)
     * @see #getDateTimeNumber(long, int)
     */
    public static Double getTrans(long number, int srcType, int toType) {
        long milliSecond = getMilliSecond(number, srcType);
        return getDateTimeNumber(milliSecond, toType);
    }


    /**
     * 毫秒时间转换指定类型时间
     * @param milliSecond 毫秒数
     * @param toType      指定时间类型
     * @return java.lang.Double
     * @author 唐小甫
     * @datetime 2020-11-21 22:24:49
     * @see #YEAR
     * @see #MONTH
     * @see #WEEK
     * @see #DAY
     * @see #HOUR
     * @see #MINUTE
     * @see #SECOND
     * @see #MILLISECOND
     */
    public static Double getDateTimeNumber(long milliSecond, int toType) {
        if (milliSecond < 0) {
            throw new RuntimeException("milliSecond不能小于0");
        }
        if (toType < MILLISECOND || toType > YEAR) {
            throw new RuntimeException("toType超出限定范围");
        }
        double number = milliSecond * 1.0;
        switch (toType) {
            case SECOND:
                number /= MILLISECOND_SCALE;
                break;
            case MINUTE:
                number = number / MILLISECOND_SCALE / SECOND_SCALE;
                break;
            case HOUR:
                number = number / MILLISECOND_SCALE / SECOND_HOUR_SCALE;
                break;
            case DAY:
                number = number / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE;
                break;
            case WEEK:
                number = number / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE / WEEK_SCALE;
                break;
            case MONTH:
                number = number / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE / MONTH_SCALE;
                break;
            case YEAR:
                number = number / MILLISECOND_SCALE / SECOND_HOUR_SCALE / HOUR_DAY_SCALE / YEAR_SCALE;
                break;
        }
        return number;
    }


    /**
     * 指定时间类型转换为毫秒
     * @param number  时间数
     * @param srcType 时间类型
     * @return long   毫秒值
     * @author 唐小甫
     * @datetime 2020-11-21 22:01:49
     * @see #YEAR
     * @see #MONTH
     * @see #WEEK
     * @see #DAY
     * @see #HOUR
     * @see #MINUTE
     * @see #SECOND
     * @see #MILLISECOND
     */
    public static long getMilliSecond(long number, int srcType) {
        if (number < 0) {
            throw new RuntimeException("number不能小于0");
        }
        if (srcType < MILLISECOND || srcType > YEAR) {
            throw new RuntimeException("srcType超出限定范围");
        }
        long milliSecond = number;
        if (srcType == YEAR) {
            milliSecond *= YEAR_SCALE;
        } else if (srcType == MONTH) {
            milliSecond *= MONTH_SCALE;
        } else if (srcType == WEEK) {
            milliSecond *= WEEK_SCALE;
        }
        switch (srcType) {
            case YEAR:
            case MONTH:
            case WEEK:
            case DAY:
                milliSecond *= HOUR_DAY_SCALE;
            case HOUR:
                milliSecond *= MINUTE_SCALE;
            case MINUTE:
                milliSecond *= SECOND_SCALE;
            case SECOND:
                milliSecond *= MILLISECOND_SCALE;
        }
        return milliSecond;
    }


    /**
     * 计算 date 这一天是该 周/月/年 的 第几天<br/>
     * 计算 date 这一周是该 月/年 的 第几周
     * @param date      日期
     * @param dayOrWeek 天/周
     * @param dateType  周/月/年
     * @return int
     * @author 唐小甫
     * @datetime 2020-11-21 22:42:49
     * @see #DAY
     * @see #YEAR
     * @see #MONTH
     * @see #WEEK
     * @see Calendar#DAY_OF_WEEK
     */
    public static int getDayByField(Date date, int dayOrWeek, int dateType) {
        if (date == null) {
            throw new RuntimeException("date不能为空");
        }
        if (dayOrWeek != DAY && dayOrWeek != WEEK) {
            throw new RuntimeException("dayOrWeek不在范围内");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int num = 0;
        switch (dayOrWeek) {
            case DAY:
                if (dateType < WEEK || dateType > YEAR) {
                    throw new RuntimeException("dateType超出范围");
                }
                switch (dateType) {
                    case WEEK:
                        num = instance.get(Calendar.DAY_OF_WEEK);
                        break;
                    case MONTH:
                        num = instance.get(Calendar.DAY_OF_MONTH);
                        break;
                    case YEAR:
                        num = instance.get(Calendar.DAY_OF_YEAR);
                        break;
                }
                break;
            case WEEK:
                if (dateType < MONTH || dateType > YEAR) {
                    throw new RuntimeException("dateType超出范围");
                }
                switch (dateType) {
                    case MONTH:
                        num = instance.get(Calendar.WEEK_OF_MONTH);
                        break;
                    case YEAR:
                        num = instance.get(Calendar.WEEK_OF_YEAR);
                        break;
                }
                break;
            default:
                throw new RuntimeException("dayOrWeek不在范围内");
        }
        return num;
    }
}