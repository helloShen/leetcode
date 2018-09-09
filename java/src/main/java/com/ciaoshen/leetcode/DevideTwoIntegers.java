/**
 * Leetcode - Devide Two Integers
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class DevideTwoIntegers {
    public int divideV1(int dividend, int divisor) {
        if (divisor == 0) { return Integer.MAX_VALUE; } // 0不能当除数
        if (dividend == Integer.MIN_VALUE && divisor == -1) { return Integer.MAX_VALUE; } // int不能表示2147483648
        if (dividend == 0) { return 0; } // 0除以任何数等于0
        int sign = (Integer.signum(dividend)== Integer.signum(divisor))? 1:-1; // get the sign
        int dividendAbs = Math.abs(dividend);
        int divisorAbs = Math.abs(divisor);
        int times = 0;
        while (true) {
            dividendAbs = dividendAbs - divisorAbs;
            if (dividendAbs >= 0) {
                times++;
            } else {
                break;
            }
        }
        return (sign==1)? times : -times;
    }

    public int divideV2(int dividend, int divisor) {
        // edge case
        if (divisor == 0) { return Integer.MAX_VALUE; } // 0不能当除数
        if (dividend == Integer.MIN_VALUE && divisor == -1) { return Integer.MAX_VALUE; } // int不能表示2147483648
        if (dividend == 0) { return 0; } // 0除以任何数等于0
        // treat sign
        int sign = (Integer.signum(dividend)== Integer.signum(divisor))? 1:-1; // get the sign
        // division
        int result = 0;
        long dividendL = Math.abs((long)dividend), divisorL = Math.abs((long)divisor);
        while (dividendL >= divisorL) {
            int times = 1;
            long temp = divisorL;
            while (true) {
                temp = temp << 1; // *2
                if (dividendL >= temp) {
                    times = times << 1; // *2
                } else {
                    temp = temp >> 1;
                    dividendL -= temp;
                    break;
                }
            }
            result += times;
        }
        return (sign == 1)? result : -result;
    }

    public int divide(int dividend, int divisor) {
        // edge case
        if (divisor == 0) { return Integer.MAX_VALUE; } // 0不能当除数
        if (dividend == Integer.MIN_VALUE && divisor == -1) { return Integer.MAX_VALUE; } // int不能表示2147483648
        if (dividend == 0) { return 0; } // 0除以任何数等于0
        // treat sign
        int sign = (Integer.signum(dividend)== Integer.signum(divisor))? 1:-1; // get the sign
        // division
        int result = 0;
        long dividendL = Math.abs((long)dividend), divisorL = Math.abs((long)divisor);
        while (dividendL >= divisorL) {
            int shift = 1;
            while (dividendL >= (divisorL << shift)) { shift++; }
            result += 1 << (shift-1);
            dividendL -= (divisorL << shift-1);
        }
        return (sign == 1)? result : -result;
    }

    private static void testDevide() {
        int hundred = 100;
        int ten = 10;
        int negHundred = -100;
        int negTen = -10;
        int nineNineNine = 999;
        int hundredTen = 110;
        int one = 1;
        DevideTwoIntegers test = new DevideTwoIntegers();
        System.out.println(hundred + "/" + ten + "=" + test.divide(hundred,ten));
        System.out.println(negHundred + "/" + ten + "=" + test.divide(negHundred,ten));
        System.out.println(hundred + "/" + negTen + "=" + test.divide(hundred,negTen));
        System.out.println(negHundred + "/" + negTen + "=" + test.divide(negHundred,negTen));
        System.out.println(nineNineNine + "/" + ten + "=" + test.divide(nineNineNine,ten));
        System.out.println(nineNineNine + "/" + negTen + "=" + test.divide(nineNineNine,negTen));
        System.out.println(hundredTen + "/" + ten + "=" + test.divide(hundredTen,ten));
        System.out.println(Integer.MIN_VALUE + "/" + one + "=" + test.divide(Integer.MIN_VALUE,one));
    }
    public static void main(String[] args) {
        testDevide();
    }
}
