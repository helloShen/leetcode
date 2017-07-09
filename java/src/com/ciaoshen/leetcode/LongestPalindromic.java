/**
 * Longest Palindromic Substring
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class LongestPalindromic {
    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1) { return s; }

        String subStr = "";
        int[] max = new int[]{1};
        String[] result = new String[]{s.substring(0,1)};

        int lowerCursor = 0;
        int upperCursor = 0;
        //取中位数
        if (length%2 == 0) {
            lowerCursor = (length - 2)/2;
            upperCursor = length/2;
        } else {
            lowerCursor = (length - 1)/2;
            upperCursor = (length - 1)/2;
        }
        while (lowerCursor > 0 && lowerCursor*2 > max[0]) {
            subStr = checkPalindromic(s,lowerCursor,lowerCursor);
            result = updateResult(subStr,max,result);
            subStr = checkPalindromic(s,lowerCursor,lowerCursor-1);
            result = updateResult(subStr,max,result);
            lowerCursor--;
            subStr = checkPalindromic(s,upperCursor,upperCursor);
            result = updateResult(subStr,max,result);
            subStr = checkPalindromic(s,upperCursor,upperCursor+1);
            result = updateResult(subStr,max,result);
            upperCursor++;
        }
        return result[0];
    }

    /**
     * 检查字符串s，从lower,upper位置扩散开，最长的回文。
     * 每个字母都与自己对称
     * s不为空
     */
    public static String checkPalindromic(String s, int lower, int upper) {
        int length = s.length();
        String result = s.substring(lower,lower+1);
        expansionLoop:
        while ((lower >= 0) && (upper < s.length())) {
            char lowerChar = s.charAt(lower);
            char upperChar = s.charAt(upper);
            if (Character.compare(lowerChar,upperChar) == 0) {
                result = s.substring(lower,upper+1);
            } else {
                break expansionLoop;
            }
            lower--;
            upper++;
        }
        return result;
    }

    /**
     * 为了传参数，所以用数组
     */
    public static String[] updateResult(String subStr, int[] max, String[] result) {
        int subLen = subStr.length();
        if (subLen > max[0]) {
            max[0] = subLen;
            result[0] = subStr;
        }
        return result;
    }

    public static String longestPalindromeShrink(String s) {
        for (int size = s.length(); size > 0; size--) {
            for (int low = 0, high = low+size-1; high < s.length(); low++, high++) {
                if (shrinkCheckPalindrome(s,low,high)) {
                    return s.substring(low,high+1);
                }
            }
        }
        return s.substring(0,1);
    }
    public static boolean shrinkCheckPalindrome(String s, int low, int high) {
        while (low <= high) {
            if (s.charAt(low) == s.charAt(high)) {
                low++; high--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static String longestPalindromeShrinkMerged(String s) {
        outerFor:
        for (int size = s.length(); size > 0; size--) {
            innerFor:
            for (int low = 0, high = low+size-1; high < s.length(); low++, high++) {
                int lo = low;
                int hi = high;
                innerWhile:
                while (lo <= high) {
                    if (Character.compare(s.charAt(lo),s.charAt(hi)) == 0) {
                        lo++; hi--;
                    } else {
                        continue innerFor;
                    }
                }
                return s.substring(low,high+1);
            }
        }
        return s.substring(0,1);
    }

    private static int max = 0;
    private static String res = "";
    public static String longestPalindromeExpand(String s) {
        if (s.length() == 1) { return s; }
        for (int i = 0; i < s.length()-1; i++) {
            checkPalindromeExpand(s,i,i);
            checkPalindromeExpand(s,i,i+1);
        }
        return res;
    }
    public static void checkPalindromeExpand(String s, int low, int high) {
        while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            if (high - low + 1 > max) {
                max = high - low + 1;
                res = s.substring(low,high+1);
            }
            low--; high++;
        }
    }


    private static final char[] VOWELS = "aeiou".toCharArray();
    private static final Random R = new Random();
    private static String randomString(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(VOWELS[R.nextInt(VOWELS.length)]);
        }
        String result = sb.toString();
        System.out.println(result);
        return result;
    }
    private static void justGiveMeAString() {
        int size = 50;
        randomString(size);
    }
    private static void testLongestPalindromic() {
        int times = 10;
        int max = 50;
        for (int i = 0; i < times; i++) {
            String str = randomString(R.nextInt(max));
            System.out.println("Longest Palintromic: " + longestPalindrome(str));
        }
    }
    private static void testLongestPalindromicShrink() {
        int times = 10;
        int max = 50;
        for (int i = 0; i < times; i++) {
            String str = randomString(R.nextInt(max));
            System.out.println("Longest Palintromic: " + longestPalindromeShrink(str));
        }
    }
    private static void testLongestPalindromicShrinkMerged() {
        int times = 10;
        int max = 50;
        for (int i = 0; i < times; i++) {
            String str = randomString(R.nextInt(max));
            System.out.println("Longest Palintromic: " + longestPalindromeShrink(str));
            System.out.println("Longest Palintromic: " + longestPalindromeShrinkMerged(str));
        }
    }
    private static void testLongestPalindromicExpend() {
        int max = 50;
        String str = randomString(R.nextInt(max));
        System.out.println("Longest Palintromic: " + longestPalindromeShrink(str));
        System.out.println("Longest Palintromic: " + longestPalindromeExpand(str));
    }

    public static void main(String[] args) {
        //justGiveMeAString();
        //testLongestPalindromic();
        //testLongestPalindromicShrink();
        //testLongestPalindromicShrinkMerged();
        //testLongestPalindromicExpend();
    }
}
