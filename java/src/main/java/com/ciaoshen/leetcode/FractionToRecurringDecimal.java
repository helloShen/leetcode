/**
 * Leetcode - Algorithm - Fraction to Recurring Decimal
 */
package com.ciaoshen.leetcode;
import java.util.*;

class FractionToRecurringDecimal {
    public class SolutionV1 {
        private static final String LQ = "(";
        private static final String RQ = ")";
        private static final String DOT = ".";
        private static final String NEG = "-";
        public String fractionToDecimal(int numerator, int denominator) {
            if (denominator == 0) { return null; }
            if (numerator == 0) { return "0"; }
            // treat sign here. use long, because can't get abs of -2147483648
            int signum = Integer.signum(numerator) * Integer.signum(denominator);
            long numeratorLong = Math.abs((long)numerator);
            long denominatorLong = Math.abs((long)denominator);
            char[] num = String.valueOf(numeratorLong).toCharArray();
            // division loop
            int cur = 0;
            long remain = 0;
            boolean dot = false;
            StringBuilder sb = new StringBuilder();
            Map<Long,Integer> memo = new HashMap<>();
            while (cur < num.length || remain != 0) {
                // create new sub-numerator
                long subNumerator = remain * 10;
                if (cur < num.length) {
                    subNumerator += (num[cur]-'0');
                } else if (!dot) {
                    sb.append(DOT);
                    cur++;
                    dot = true;
                }
                cur++;
                // record each sub-numerator. (only after the dot .)
                if (dot) {
                    Integer pos = memo.get(subNumerator);
                    if (pos != null) { // find repeat
                        sb = sb.insert(pos,LQ);
                        sb = sb.append(RQ);
                        break;
                    } else {
                        memo.put(subNumerator,cur-1);
                    }
                }
                // calculate
                char quotient = (char)((int)(subNumerator / denominatorLong) + '0');
                sb.append(quotient);
                remain = subNumerator % denominatorLong;
                //System.out.println(subNumerator + " / " + denominator + " = " + quotient + "(remain: " + remain + ")");
            }
            String res = trimZero(sb.toString());
            return (signum < 0)? NEG + res : res; // give back the sign
        }
        public String trimZero(String s) {
            int cur = 0;
            while (s.charAt(cur) == '0') {
                cur++;
            }
            s = s.substring(cur);
            if (s.charAt(0) == '.') { s = "0" + s; }
            return s;
        }
    }
    public class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            if (denominator == 0) { return null; }
            StringBuilder sb = new StringBuilder();
            if ( (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0) ) {
                sb.append("-");
            }
            long numeratorLong = Math.abs((long)numerator);
            long denominatorLong = Math.abs((long)denominator);
            // integral part
            long integral = numeratorLong / denominatorLong;
            long remainder = numeratorLong % denominatorLong;
            sb.append(String.valueOf(integral));
            if (remainder == 0) { return sb.toString(); }
            // fractional part
            sb.append(".");
            Map<Long,Integer> memo = new HashMap<>();
            int fractionStart = sb.length();
            memo.put(remainder,fractionStart);
            while (remainder != 0) {
                remainder = remainder * 10;
                int quotient = (int)(remainder / denominatorLong);
                remainder = remainder % denominatorLong;
                Integer pos = memo.get(remainder);
                if (pos != null) { // 出现循环
                    sb.insert(pos,"(");
                    sb.append(String.valueOf(quotient) + ")");
                    return sb.toString(); // 出口1：找到循环
                }
                sb.append(String.valueOf(quotient));
                memo.put(remainder,fractionStart+memo.size());
            }
            return sb.toString(); // 出口2：不循环除尽
        }
    }
    private static FractionToRecurringDecimal test = new FractionToRecurringDecimal();
    private static Solution solution = test.new Solution();
    private static void callFractionToDecimal(int numerator, int denominator, String answer) {
        System.out.println(numerator + " / " + denominator + " = " + solution.fractionToDecimal(numerator,denominator) + " (answer = " + answer + ") ");
    }
    private static void test() {
        int numerator1 = 2524, denominator1 = 3;
        int numerator2 = 1, denominator2 = 17;
        int numerator3 = -1, denominator3 = 70;
        int numerator4 = 10, denominator4 = 3;
        int numerator5 = -1, denominator5 = Integer.MIN_VALUE;
        callFractionToDecimal(numerator1,denominator1,"841.(3)");
        callFractionToDecimal(numerator2,denominator2,"0.(0588235294117647)");
        callFractionToDecimal(numerator3,denominator3,"-0.0(142857)");
        callFractionToDecimal(numerator4,denominator4,"3.(3)");
        callFractionToDecimal(numerator5,denominator5,"0.0000000004656612873077392578125");
    }
    public static void main(String[] args) {
        test();
    }
}
