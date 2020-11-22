package com.util.verify;

import java.util.*;

/**
 * 随机数工具类
 * @author 唐小甫
 * @createTime 2020-11-22 18:17:15
 */
public class RandomUtil {

    /** 数字 */
    public static final int NUMBERS                     = 0;
    /** 字母 */
    public static final int LETTERS                     = 1;
    /** 小写字母 */
    public static final int LOWERCASE_LETTERS           = 2;
    /** 大写字母 */
    public static final int UPPERCASE_LETTERS           = 3;
    /** 数字+字母 */
    public static final int NUMBERS_LETTERS             = 4;
    /** 数字+小写字母 */
    public static final int NUMBERS_LOWERCASE_LETTERS   = 5;
    /** 数字+大写字母 */
    public static final int NUMBERS_UPPERCASE_NUMBERS   = 6;

    /** 数字数组 */
    public static final char[] numbers      = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    /** 小写字母数组 */
    public static final char[] lowLetters   = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                                                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                                                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    /** 大写字母数组 */
    public static final char[] uppLetters   = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                                                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                                                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};


    /**
     * 由指定随机类型生成指定位数的随机数
     * @param randomType 随机类型
     * @param digit      位数
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/22 19:24
     */
    public static final String random (int randomType, int digit) {
        if (randomType < NUMBERS || randomType > NUMBERS_UPPERCASE_NUMBERS) {
            throw new RuntimeException("randomType超出范围");
        }
        if (digit <= 0) {
            throw new RuntimeException("digit必须大于0");
        }
        char[] stock;
        switch (randomType) {
            case NUMBERS: return random(numbers, digit);
            case LETTERS:
                stock = new char[lowLetters.length + uppLetters.length];
                System.arraycopy(lowLetters, 0, stock, 0, lowLetters.length);
                System.arraycopy(uppLetters, 0, stock, lowLetters.length, uppLetters.length);
                return random(stock, digit);
            case LOWERCASE_LETTERS: return random(lowLetters, digit);
            case UPPERCASE_LETTERS: return random(uppLetters, digit);
            case NUMBERS_LETTERS:
                stock = new char[numbers.length + lowLetters.length + uppLetters.length];
                System.arraycopy(numbers, 0, stock, 0, numbers.length);
                System.arraycopy(lowLetters, 0, stock, numbers.length, lowLetters.length);
                System.arraycopy(uppLetters, 0, stock, numbers.length + lowLetters.length, uppLetters.length);
                return random(stock, digit);
            case NUMBERS_LOWERCASE_LETTERS:
                stock = new char[numbers.length + lowLetters.length];
                System.arraycopy(numbers, 0, stock, 0, numbers.length);
                System.arraycopy(lowLetters, 0, stock, numbers.length, lowLetters.length);
                return random(stock, digit);
            case NUMBERS_UPPERCASE_NUMBERS:
                stock = new char[numbers.length + uppLetters.length];
                System.arraycopy(numbers, 0, stock, 0, numbers.length);
                System.arraycopy(uppLetters, 0, stock, numbers.length, uppLetters.length);
                return random(stock, digit);
        }
        return null;
    }


    /**
     * 由字符库生成指定长度的随机字符串
     * @param chars 字符库
     * @param digit 位数
     * @return java.lang.String
     * @author 唐小甫
     * @datetime 2020/11/22 18:54
     */
    public static final String random (char[] chars, int digit) {
        if (chars == null || chars.length == 0) {
            throw new RuntimeException("chars不能为空");
        }
        if (digit <= 0) {
            throw new RuntimeException("digit必须大于0");
        }
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < digit; i++) {
            stringBuilder.append(chars[random.nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }
}