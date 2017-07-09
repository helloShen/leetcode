/**
 * Leetcode - Algorithm - Plus One
 */
package com.ciaoshen.leetcode;
import java.util.*;

class PlusOne {
    public int[] plusOneV1(int[] digits) {
        int carry = 1;
        for (int i = digits.length-1; i >= 0 && carry == 1; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry == 1) {
            digits = new int[digits.length+1];
            digits[0] = 1;
        }
        return digits;
    }
    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >=0; i--) {
            if (digits[i] < 9) {
                digits[i]++; break;
            } else {
                digits[i] = 0;
            }
        }
        if (digits[0] == 0) {
            digits = new int[digits.length+1];
            digits[0] = 1;
        }
        return digits;
    }
    private static PlusOne test = new PlusOne();
    private static int[] testCase1 = new int[]{ 1 };
    private static int[] testCase2 = new int[]{ 9 };
    private static int[] testCase3 = new int[]{ 1,0 };
    private static int[] testCase4 = new int[]{ 9,9,9,9,9,9,9 };
    private static int[] testCase5 = new int[]{ 1,2,3,4,5,6 };

    private static void testPlusOne() {
        System.out.println(Arrays.toString(testCase1) + " + 1 = " + Arrays.toString(test.plusOne(testCase1)));
        System.out.println(Arrays.toString(testCase2) + " + 1 = " + Arrays.toString(test.plusOne(testCase2)));
        System.out.println(Arrays.toString(testCase3) + " + 1 = " + Arrays.toString(test.plusOne(testCase3)));
        System.out.println(Arrays.toString(testCase4) + " + 1 = " + Arrays.toString(test.plusOne(testCase4)));
        System.out.println(Arrays.toString(testCase5) + " + 1 = " + Arrays.toString(test.plusOne(testCase5)));
    }
    public static void main(String[] args) {
        testPlusOne();
    }
}
