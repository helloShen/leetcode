/**
 * Leetcode - Algorithm - Pow(x,n)
 */
package com.ciaoshen.leetcode;
import java.util.*;

class Pow {

    public double myPowV1(double x, int n) {
        if (n == 0) { return 1.0; } //If the second argument is positive or negative zero, then the result is 1.0.
        if (Double.isNaN(x)) { return Double.NaN; } //If the first argument is NaN and the second argument is nonzero, then the result is NaN.
        if (x == 0.0 && n < 0) { return Double.POSITIVE_INFINITY; }  //If the first argument is positive zero and the second argument is less than zero, then the result is positive infinity.
        if (x == Double.POSITIVE_INFINITY && n > 0) { return Double.POSITIVE_INFINITY; }//If the first argument is positive infinity and the second argument is greater than zero, then the result is positive infinity.
        if (x == -0.0) {
            if (n > 0 && n % 2 == 1) { return -0.0; } //If the first argument is negative zero and the second argument is a positive finite odd integer, then the result is negative zero.
            if (n < 0 && n % 2 == 0) { return 0.0; } //If the first argument is negative zero and the second argument is less than zero but not a finite odd integer, then the result is positive zero.
            if (n < 0 && n % 2 == 1) { return Double.NEGATIVE_INFINITY; } //If the first argument is negative zero and the second argument is a negative finite odd integer, then the result is negative infinity.
        }
        if (x == Double.NEGATIVE_INFINITY) {
            if (n < 0 && n % 2 == 1) { return -0.0; } //If the first argument is negative infinity and the second argument is a negative finite odd integer, then the result is negative zero.
            if (n > 0 && n % 2 == 0) { return 0.0; } //If the first argument is negative infinity and the second argument is greater than zero but not a finite odd integer, then the result is positive infinity.
            if (n > 0 && n % 2 == 1) { return Double.NEGATIVE_INFINITY; }//If the first argument is negative infinity and the second argument is a positive finite odd integer
        }
        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        for (int i = 0; i > n; i--) {
            result /= x;
        }
        return result;
    }
    public double myPowV2(double x, int n) {
        return Math.pow(x,(double)n);
    }
    public double myPowV3(double x, int n) {
        System.out.println("x = " + x + ", n= " + n);
        if (n == 0) { return 1.0; }
        if (n == 1) { return x; }
        if (n == -1) { return 1/x; }
        if (n > 0) {
            return (n % 2 == 0)? myPow(x*x,n/2) : x * myPow(x*x,n/2);
        } else { // n<0
            return (n % 2 == 0)? myPow(x*x,n/2) : (1/x) * myPow(x*x,n/2);
        }
    }
    public double myPow(double x, int n) {
        System.out.println("x = " + x + ", n= " + n);
        if (n == 0) { return 1.0; }
        double prefix = (n > 0)? x : 1/x;
        return (n % 2 == 0)? myPow(x*x,n/2) : prefix * myPow(x*x,n/2);
    }
    private static void testMyPow() {
        Pow test = new Pow();
        Map<Double, Integer> testPairs = new HashMap<>();
        testPairs.put(34.00515,-3);
        testPairs.put(0.44528,0);
        for (Map.Entry<Double,Integer> entry : testPairs.entrySet()) {
            double x = entry.getKey();
            int n = entry.getValue();
            System.out.println(x + " ^ " + n + " = " + test.myPow(x,n));
        }
    }
    public static void main(String[] args) {
        testMyPow();
    }
}
