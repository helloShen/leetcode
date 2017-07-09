/**
 * Palindrome number
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class PalindromeNumber {
    public static boolean isPalindromeV1(int x) {
        if (x < 0) { return false; } // negative integer is not palindrome
        long orig = x, reverse = 0;
        while (x != 0) {
            reverse = reverse * 10 + (x % 10);
            x = x /10;
        }
        return orig == reverse;
    }
    public static boolean isPalindromeV2(int x) {
        if (x < 0 || (x!= 0 && x%10 == 0)) { return false; }
        long orig = x, rev = 0;
        while (x != 0) {
            int rmd = x % 10;
            x = x/10;
            if (x == rev) { return true; }
            rev = rev * 10 + rmd;
            if (x == rev) { return true; }
            if (rev > x) { return false; }
        }
        return true; // x = 0
    }
    public static boolean isPalindromeV3(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) { return false; }
        int rev = 0;
        while (rev < x) {
            rev = rev * 10 + x % 10;
            x = x/10;
        }
        return (x == rev || x == rev/10);
    }

    private static int zero = 0;
    private static int one = 1;
    private static int minusOne = -1;
    private static int max = Integer.MAX_VALUE;
    private static int min = Integer.MIN_VALUE;
    private static int palindrome1 = 123454321;
    private static int palindrome2 = -123454321;
    private static int palindrome3 = 123321;
    private static int palindrome4 = -123321;
    private static void testIsPalindrome() {
        System.out.println(zero + " is Palindrome? " + isPalindrome(zero));
        System.out.println(one + " is Palindrome? " + isPalindrome(one));
        System.out.println(minusOne + " is Palindrome? " + isPalindrome(minusOne));
        System.out.println(max + " is Palindrome? " + isPalindrome(max));
        System.out.println(min + " is Palindrome? " + isPalindrome(min));
        System.out.println(palindrome1 + " is Palindrome? " + isPalindrome(palindrome1));
        System.out.println(palindrome2 + " is Palindrome? " + isPalindrome(palindrome2));
        System.out.println(palindrome3 + " is Palindrome? " + isPalindrome(palindrome3));
        System.out.println(palindrome4 + " is Palindrome? " + isPalindrome(palindrome4));
    }
    public static void main(String[] args) {
        testIsPalindrome();
    }
}
