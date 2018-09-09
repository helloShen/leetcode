/**
 * Reverse Integer
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.lang.Math.*;

public class ReverseInteger {
    // 利用库解码，再编码
    // int -> String -> StringBuilder -> reverse StringBuilder -> long -> int
    public static int reverse(int x) {
        String num = Integer.toString(x); // decode
        StringBuilder sb = new StringBuilder(num).reverse(); // reverse
        if (sb.charAt(sb.length()-1) == '-') { // negative
            sb.deleteCharAt(sb.length()-1);
            sb.insert(0,'-');
        }
        long longX = Long.parseLong(sb.toString()); // encode
        return (longX < (long)Integer.MIN_VALUE || longX > (long)Integer.MAX_VALUE)? 0:(int)longX; // overflow
    }

    // 利用数学
    public static int reverseMath(int x) {
        long result = 0l;
        while(x != 0) {
            result = result * 10 + (x % 10);
            x = x/10;
        }
        return (result < (long)Integer.MIN_VALUE || result > (long)Integer.MAX_VALUE)? 0:(int)result; // overflow
    }

    private static void testByteValue(Integer num) {
        System.out.println(num.byteValue());
    }
    private static void testToString(int num) {
        System.out.println(Integer.toString(num));
    }
    private static void testPercent(int negativeNum) {
        System.out.println(negativeNum%10);
    }
    /** Doesn't work!
    private static void testListOfCharacters(String s) {
        List<Character> list = Arrays.asList(s.toCharArray());
        System.out.println(list);
    }
    */

    public static void main(String[] args) {
        //testByteValue(1234567);
        //testToString(1234567);
        //testToString(-1234567);
        //testListOfCharacters("1234567");
        //testPercent(-1234567);

        // main process
        /*
        System.out.println(1234567 + " <==> " + reverse(1234567));
        System.out.println(-1234567 + " <==> " + reverse(-1234567));
        System.out.println(2147483647 + " <==> " + reverse(2147483647));
        System.out.println(-2147483648 + " <==> " + reverse(-2147483648));
        System.out.println(1234567 + " <==> " + reverseMath(1234567));
        System.out.println(-1234567 + " <==> " + reverseMath(-1234567));
        System.out.println(2147483647 + " <==> " + reverseMath(2147483647));
        System.out.println(-2147483648 + " <==> " + reverseMath(-2147483648));
        */
    }
}
