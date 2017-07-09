/**
 * String to Integer
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.util.regex.*;

public class StringToInteger {
    private static final Pattern P = Pattern.compile("^[\\s]*([+-]?[0-9]+).*$");
    private static final Map<Character,Integer> DICTIONARY = new HashMap<>();
    static {
        DICTIONARY.put('0',0);
        DICTIONARY.put('1',1);
        DICTIONARY.put('2',2);
        DICTIONARY.put('3',3);
        DICTIONARY.put('4',4);
        DICTIONARY.put('5',5);
        DICTIONARY.put('6',6);
        DICTIONARY.put('7',7);
        DICTIONARY.put('8',8);
        DICTIONARY.put('9',9);
    }
    public static int myAtoiV1(String str) {
        if (str == null || str.isEmpty()) { return 0; }
        Matcher m = P.matcher(str);
        if (!m.find()) { return 0; }

        char[] chars = m.group(1).toCharArray();
        int head = (chars[0] == '+' || chars[0] == '-')? 1:0;
        int signum = (chars[0] == '-')? -1:1;
        long result = 0l;
        long max = (long)Integer.MAX_VALUE;
        long min = (long)Integer.MIN_VALUE;
        for (int i = head; i < chars.length; i++) {
            result = (result * 10) + (DICTIONARY.get(chars[i]) * signum);
            if (result > 0 && result > max) { return Integer.MAX_VALUE; }
            if (result < 0 && result < min) { return Integer.MIN_VALUE; }
        }
        return (int)result;
    }

    // 不维护char-int的map. 直接查ascii表转码
    public static int myAtoiV2(String str) {
        if (str == null || str.isEmpty()) { return 0; }
        Matcher m = P.matcher(str);
        if (!m.find()) { return 0; }

        char[] chars = m.group(1).toCharArray();
        int head = (chars[0] == '+' || chars[0] == '-')? 1:0;
        int signum = (chars[0] == '-')? -1:1;
        long result = 0l;
        long max = (long)Integer.MAX_VALUE;
        long min = (long)Integer.MIN_VALUE;
        for (int i = head; i < chars.length; i++) {
            result = (result * 10) + (((int)chars[i]-'0') * signum); // ascii码中 0=48
            if (result > 0 && result > max) { return Integer.MAX_VALUE; }
            if (result < 0 && result < min) { return Integer.MIN_VALUE; }
        }
        return (int)result;
    }

    // 让 Integer.parseInt()替我们工作
    public static int myAtoiV3(String str) {
        // 过滤格式不对的情况
        if (str == null || str.isEmpty()) { return 0; }
        Matcher m = P.matcher(str);
        if (!m.find()) { return 0; }

        String num = m.group(1);
        int signum = (num.charAt(0) == '-')? -1:1;
        try {
            return Integer.parseInt(num); // 让 Integer.parseInt()替我们工作
        } catch (NumberFormatException e) { // 溢出时Integer.parseInt()会抛出异常，这里额外处理一下
            if (signum == 1) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
    }

    // 不用正则表达式抠数字。直接用迭代过滤。
    public static int myAtoi(String str) {
        if (str == null || str.isEmpty()) { return 0; }
        str = str.trim(); // discards whitespace
        char[] chars = str.toCharArray();
        int signum = 1, head = 0;
        if (chars[head] == '+' || chars[head] == '-') { // treat sign
            if (chars[head] == '-') { signum = -1; }
            head++;
        }
        // accumulate
        long result = 0l;
        long max = (long)Integer.MAX_VALUE;
        long min = (long)Integer.MIN_VALUE;
        while (head < chars.length && Character.isDigit(chars[head])) {
            result = (result * 10) + (((int)chars[head++]-'0') * signum); // ascii码中 0=48
            if (result > 0 && result > max) { result = max; break; }
            if (result < 0 && result < min) { result = min; break; }
        }
        return (int)result;
    }

    private static String normal = "1234567";
    private static String negative = "-1234567";
    private static String whiteSpace = "   1234567";
    private static String withDecimalPoint = "0.1234567";
    private static String overflow = "9876543210";
    private static String zero = "0";
    private static String negativeZero = "-0";
    private static String max = Integer.toString(Integer.MAX_VALUE);
    private static String min = Integer.toString(Integer.MIN_VALUE);
    private static String minMinusOne = "-2147483649";
    private static String maxPlusOne = "2147483648";
    private static void testIntPatterns() {
        testIntPattern(normal); // normal
        testIntPattern(negative); // negative
        testIntPattern(whiteSpace); // whitespace
        testIntPattern(withDecimalPoint); // float pointer
        testIntPattern(overflow); // overflow
    }
    private static void testIntPattern(String s) {
        Pattern p = Pattern.compile("^[+-]?[0-9]+$");
        Matcher m = p.matcher(s);
        boolean isInt = m.find();
        System.out.print("\"" + s + "\" is int? " + isInt);
        if (isInt) {
            System.out.println("    : " + m.group());
        } else {
            System.out.println("");
        }
    }
    private static void testParseInt() {
        System.out.println("1234567 -> " + Integer.parseInt(normal));
        System.out.println("-1234567 -> " + Integer.parseInt(negative));
        // System.out.println("   1234567 -> " + Integer.parseInt(withSpace)); // ERROR: NumberFormatException
        // System.out.println("0.1234567 -> " + Integer.parseInt(withDecimalPoint)); // ERROR: NumberFormatException
        // System.out.println("9876543210 -> " + Integer.parseInt(overflow)); // ERROR: NumberFormatException
        System.out.println("0 -> " + Integer.parseInt(zero));
        System.out.println("-0 -> " + Integer.parseInt(negativeZero));
        System.out.println("2147483647 -> " + Integer.parseInt(max));
        System.out.println("-2147483648 -> " + Integer.parseInt(min));
    }
    private static void testMyAtoi() {
        System.out.println("-1234567 -> " + myAtoi(negative));
        System.out.println("   1234567 -> " + myAtoi(whiteSpace));
        System.out.println("0.1234567 -> " + myAtoi(withDecimalPoint)); //ERROR: Format not accepted
        System.out.println("9876543210 -> " + myAtoi(overflow)); //ERROR: Format not accepted
        System.out.println("0 -> " + myAtoi(zero));
        System.out.println("-0 -> " + myAtoi(negativeZero));
        System.out.println("2147483647 -> " + myAtoi(max));
        System.out.println("-2147483648 -> " + myAtoi(min));
        System.out.println("2147483648 -> " + myAtoi(maxPlusOne)); // ERROR: Overflow, Too Big
        System.out.println("-2147483649 -> " + myAtoi(minMinusOne)); // ERROR: Overflow, Too Small
    }
    public static void main(String[] args) {
        //testParseInt();
        //testIntPatterns();
        testMyAtoi();
    }
}
