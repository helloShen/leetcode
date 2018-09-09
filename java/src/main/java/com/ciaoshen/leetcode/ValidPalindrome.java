/**
 * Leetcode - Algorithm - Valid Palindrome
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ValidPalindrome {
    public boolean isPalindromeV1(String s) {
        int lo = 0, hi = s.length()-1;
        while (true) {
            while (lo <= hi && !isAlphaNumeric(s.charAt(lo))) { lo++; }
            while (hi >= lo && !isAlphaNumeric(s.charAt(hi))) { hi--; }
            if (lo > hi) { break; }
            if (Character.toLowerCase(s.charAt(lo)) != Character.toLowerCase(s.charAt(hi))) { return false; }
            lo++; hi--;
        }
        return true;
    }
    public boolean isPalindromeV2(String s) {
        char[] chars = s.toCharArray();
        int lo = 0, hi = chars.length-1;
        while (true) {
            while (lo <= hi && !isAlphaNumeric(chars[lo])) { lo++; }
            while (hi >= lo && !isAlphaNumeric(chars[hi])) { hi--; }
            if (lo > hi) { break; }
            if (Character.toLowerCase(chars[lo]) != Character.toLowerCase(chars[hi])) { return false; }
            lo++; hi--;
        }
        return true;
    }
    public boolean isAlphaNumeric(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }
    public boolean isPalindromeV3(String s) {
        int lo = 0, hi = s.length()-1;
        while (lo <= hi) {
            if (!isAlphaNumeric(s.charAt(lo))) { lo++; continue; }
            if (!isAlphaNumeric(s.charAt(hi))) { hi--; continue; }
            if (Character.toLowerCase(s.charAt(lo++)) != Character.toLowerCase(s.charAt(hi--))) { return false; }
        }
        return true;
    }
    public boolean isPalindrome(String s) {
        int lo = 0, hi = s.length()-1;
        while (lo <= hi) {
            if (!Character.isLetterOrDigit(s.charAt(lo))) { lo++; continue; }
            if (!Character.isLetterOrDigit(s.charAt(hi))) { hi--; continue; }
            if (Character.toLowerCase(s.charAt(lo++)) != Character.toLowerCase(s.charAt(hi--))) { return false; }
        }
        return true;
    }
    private static ValidPalindrome test = new ValidPalindrome();
    private static void testIsPalindrome() {
        String[] strs = new String[]{
            "A man, a plan, a canal: Panama",
            "race a car",
            "",
            " "
        };
        for (String str : strs) {
            System.out.println(str + " is Palindrome ? " + test.isPalindrome(str));
        }
    }
    public static void main(String[] args) {
        testIsPalindrome();
    }
}
