package com.util;

import java.util.Calendar;
import java.util.Arrays;

/**
 * @author tangxingfu
 * @datetime 2020/11/20 15:32
 */
public class IdCardUtil {

    /** 中国公民身份证号码最小长度 */
    public static final int CHINA_ID_MIN_LENGTH = 15;

    /** 中国公民身份证号码最大长度 */
    public static final int CHINA_ID_MAX_LENGTH = 18;

    /** 中国公民身份证号码最远年份 */
    public static final int MIN_ID_YEAR = 1930;

    /** 空格 */
    public static final String SPACE = " ";

    /** 空字符串 */
    public static final String EMPTY_STRING = "";

    /** 18位身份证正则表达式 */
    public static final String ID_18_REGEX = "^[1-9]\\d{5}(19|20)\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}[0-9xX]$";

    /** 省、直辖市代码表 */
    public static final String CITY_CODE[] = {
            "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41",
            "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71",
            "81", "82", "91"
    };

    /** 每位加权因子 */
    public static final int POWER[] = {
            7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2
    };

    /** 第18位校检码 */
    public static final String VERIFY_CODE[] = {
            "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"
    };

    /**
     * 获取身份证号数组
     * @param idNo
     * @author tangxingfu
     * @datetime 2020/11/20 15:47
     */
    public static int[] getIdCardInt(String idNo) {
        validateIdCardBefore(idNo);
        int[] idNos = new int[CHINA_ID_MAX_LENGTH - 1];
        byte[] bytes = idNo.getBytes();
        for (int i = 0; i < CHINA_ID_MAX_LENGTH - 1; i++) {
            idNos[i] = bytes[i] & 0x7FFFFFCF;
        }
        return idNos;
    }

    /**
     * 获取身份证号数组
     * @param idNoChars
     * @author tangxingfu
     * @datetime 2020/11/20 15:48
     */
    public static int[] getIdCardInt(char[] idNoChars) {
        validateIdCardBefore(String.copyValueOf(idNoChars));
        int[] idNos = new int[CHINA_ID_MAX_LENGTH - 1];
        for (int i = 0; i < CHINA_ID_MAX_LENGTH - 1; i++) {
            idNos[i] = idNoChars[i] - 48;
        }
        return idNos;
    }

    /**
     * 获取出生年份
     * @param idNo
     * @author tangxingfu
     * @datetime 2020/11/20 17:24
     */
    public static int getYearByIdCard(String idNo) {
        validateIdCardBefore(idNo);
        return Integer.parseInt(idNo.substring(6, 10));
    }

    /**
     * 获取出生月份
     * @param idNo
     * @author tangxingfu
     * @datetime 2020/11/20 17:35
     */
    public static int getMonthByIdCard(String idNo) {
        validateIdCardBefore(idNo);
        return Integer.parseInt(idNo.substring(10, 12));
    }

    /**
     * 获取出生日
     * @param idNo
     * @author tangxingfu
     * @datetime 2020/11/20 17:36
     */
    public static int getDayByIdCard(String idNo) {
        validateIdCardBefore(idNo);
        return Integer.parseInt(idNo.substring(12, 14));
    }

    /**
     * 验证年月日
     * @param year
     * @param month
     * @param day
     * @author tangxingfu
     * @datetime 2020/11/20 17:39
     */
    public static boolean validataBorn(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month - 1, day);
        return instance.get(Calendar.YEAR) == year 
			&& instance.get(Calendar.MONTH) == month - 1
			&& instance.get(Calendar.DATE) == day;
    }

    /**
     * 身份证初步校验
     * @param idNo
     * @author tangxingfu
     * @datetime 2020/11/20 17:25
     */
    public static boolean validateIdCardBefore(String idNo) {
        if (idNo == null || idNo.length() == 0) {
            throw new RuntimeException("身份证号码不能为空");
        }
        // 18位身份证初步校验
        if (idNo.length() == CHINA_ID_MAX_LENGTH) {
            if (!idNo.matches(ID_18_REGEX)) {
                throw new RuntimeException("身份证格式错误");
            }
			int year = Integer.parseInt(idNo.substring(6, 10));
			Calendar instance = Calendar.getInstance();
			int currentYear = instance.get(Calendar.YEAR);
			if (year < MIN_ID_YEAR || year > currentYear) {
				throw new RuntimeException("身份证年份错误");
			}

			int month = Integer.parseInt(idNo.substring(10, 12));
			if (month <= Calendar.JANUARY || month > Calendar.UNDECIMBER) {
				throw new RuntimeException("身份证月份错误");
			}

			int day = Integer.parseInt(idNo.substring(12, 14));
			if (!validataBorn(year, month, day)) {
				throw new RuntimeException("身份证日期错误");
			}

			return true;
        }
        return false;
    }

    public static boolean validateIdCard18(String idNo) {
        boolean legalIdNo = false;
        int[] idCardInts = getIdCardInt(idNo);

        return legalIdNo;
    }

	public static void main (String[] args) {
		String idNo = "342422199812045273";
		System.out.println(getDayByIdCard(idNo));
	}
}