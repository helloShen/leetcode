/**
 * Leetcode - Algorithm - Excel Sheet Column Number
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ExcelSheetColumnNumber {
    public class SolutionV1 {
        public int titleToNumber(String s) {
            int num = 0, length = s.length();
            for (int i = 0; i < length; i++) {
                num += (s.charAt(i)-64) * (int)Math.pow(26,length-1-i);
            }
            return num;
        }
    }
    public class Solution {
        public int titleToNumber(String s) {
            return recursion(s,0);
        }
        public int recursion(String s, int pos) {
            if (pos == s.length()) { return 0; }
            return (s.charAt(pos)-64) * (int)Math.pow(26,s.length()-1-pos) + recursion(s,pos+1);
        }
    }
    private static ExcelSheetColumnNumber test = new ExcelSheetColumnNumber();
    private static Solution solution = test.new Solution();
    private static void callTitleToNumber(String s, String ans) {
        System.out.println("The corresponding number of title " + s + " is " + solution.titleToNumber(s) + "    [answer should be " + ans + "]");
    }
    private static void test() {
        String s1 = "A";
        String s2 = "AA";
        String s3 = "AAA";
        String s4 = "ZZ";
        callTitleToNumber(s1,"1");
        callTitleToNumber(s2,"27");
        callTitleToNumber(s3,"703");
        callTitleToNumber(s4,"702");
    }
    public static void main(String[] args) {
        test();
    }
}
