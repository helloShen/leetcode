/**
 * Leetcode - Algorithm - Multiply Strings
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MultiplyStrings {
    public String multiplyV1(String num1, String num2) {
        String res = "0";
        for (int i = num1.length()-1; i >= 0; i--) {
            int multi1 = num1.charAt(i) - '0';
            String temp = "0";
            for (int j = num2.length()-1; j >= 0; j--) {
                int multi2 = num2.charAt(j) - '0';
                int product = multi1 * multi2;
                StringBuilder sb = new StringBuilder(String.valueOf(product));
                if (product != 0) {
                    int numZero = (num1.length() - 1 - i) + (num2.length() -1 -j);
                    for (int k = 0; k < numZero; k++) {
                        sb.append("0");
                    }
                }
                temp = plus(temp, sb.toString());
            }
            res = plus(res,temp);
        }
        return res;
    }
    int[][] table = new int[][] { // 9*9乘法表
        {0,0,0,0,0,0,0,0,0,0},
        {0,1,2,3,4,5,6,7,8,9},
        {0,2,4,6,8,10,12,14,16,18},
        {0,3,6,9,12,15,18,21,24,27},
        {0,4,8,12,16,20,24,28,32,36},
        {0,5,10,15,20,25,30,35,40,45},
        {0,6,12,18,24,30,36,42,48,54},
        {0,7,14,21,28,35,42,49,56,63},
        {0,8,16,24,32,40,48,56,64,72},
        {0,9,18,27,36,45,54,63,72,81}
    };
    public String multiplyV2(String num1, String num2) {
        String res = "0";
        for (int i = num1.length()-1; i >= 0; i--) {
            int multi1 = num1.charAt(i) - '0';
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int j = num2.length()-1; j >= 0; j--) {
                int multi2 = num2.charAt(j) - '0';
                int product = multi1 * multi2 + carry;
                sb.insert(0,product % 10);
                carry = product / 10;
            }
            if (carry > 0) { sb.insert(0,carry); }
            if (sb.charAt(0) != '0') {
                for (int k = 0; k < num1.length()-1-i; k++) {
                    sb.append("0");
                }
                res = plus(res,sb.toString());
            }
        }
        return res;
    }
    public String multiplyV3(String num1, String num2) {
        String res = "0";
        for (int i = num1.length()-1; i >= 0; i--) {
            int multi1 = num1.charAt(i) - '0';
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for (int j = num2.length()-1; j >= 0; j--) {
                int multi2 = num2.charAt(j) - '0';
                int product = table[multi1][multi2]+ carry;
                sb.insert(0,product % 10);
                carry = product / 10;
            }
            if (carry > 0) { sb.insert(0,carry); }
            if (sb.charAt(0) != '0') {
                for (int k = 0; k < num1.length()-1-i; k++) {
                    sb.append("0");
                }
                res = plus(res,sb.toString());
            }
        }
        return res;
    }
    public String plus(String first, String second) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = first.length() - 1, j = second.length() - 1; i >= 0 || j >= 0; i--,j--) {
            int num1 = 0, num2 = 0;
            if (i >= 0) { num1 = first.charAt(i) - '0'; }
            if (j >= 0) { num2 = second.charAt(j) - '0'; }
            int sum = num1 + num2 + carry;
            sb.insert(0,(sum%10));
            carry = sum / 10;
        }
        if (carry == 1) { sb.insert(0,"1"); }
        System.out.println(first + " + " + second + " = " + sb.toString());
        return sb.toString();
    }


    public String multiplyV4(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        char[] res = new char[]{'0'};
        for (int i = chars2.length-1; i >= 0; i--) {
            char[] temp = multi(chars1,chars2[i],chars2.length-1-i);
            res = plus2(res,temp);
        }
        return new String(res);
    }
    public char[] multi(char[] num1, char num2, int zero) {
        if (num1[0] == '0' || num2 == '0') { return new char[]{'0'}; }
        char[] res = new char[num1.length+1+zero];
        for (int i = 0; i < zero; i++) {
            res[num1.length+1+i] = '0';
        }
        int carry = 0;
        int int2 = num2 - '0';
        for (int i = num1.length-1; i >=0; i--) {
            int product = (num1[i]-'0') * int2 + carry;
            res[i+1] = (char)((product % 10) + '0');
            carry = product / 10;
        }
        res[0] = (char)(carry + '0');
        return (carry == 0)? Arrays.copyOfRange(res,1,res.length):res;
    }
    public char[] plus2(char[] first, char[] second) {
        System.out.println("First: " + (new String(first)) + ", Second: " + (new String(second)));
        int length = Math.max(first.length, second.length);
        char[] res = new char[length+1];
        int carry = 0;
        for (int i = first.length - 1, j = second.length - 1; i >= 0 || j >= 0; i--,j--) {
            int num1 = 0, num2 = 0;
            if (i >= 0) { num1 = (first[i] - '0'); }
            if (j >= 0) { num2 = (second[j] - '0'); }
            int sum = num1 + num2 + carry;
            res[length--] = (char)((sum % 10) + '0');
            carry = sum / 10;
        }
        res[0] = (char)(carry + '0');
        return (carry == 0)? Arrays.copyOfRange(res,1,res.length):res;
    }
    public String multiply(String num1, String num2) {
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        if (c1[0] == '0' || c2[0] == '0') { return "0"; }
        int[] result = new int[c1.length+c2.length];
        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                int product = (c1[i] - '0') * (c2[j] - '0');
                result[i+j] += product / 10;
                result[i+j+1] += product % 10;
            }
        }
        int carry = 0;
        for (int i = result.length-1; i >= 0; i--) {
            int val = result[i] + carry;
            result[i] = val % 10;
            carry = val / 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = (result[0] == 0)? 1:0; i < result.length; i++) {
            sb.append(result[i]);
        }
        return sb.toString();
    }
    private static MultiplyStrings test = new MultiplyStrings();
    private static String[][] testCases = new String[][] {
            {"0","0"},
            {"100","20"},
            {"1024","512"},
            {"123","456"},
            {"9133","0"},
            {"123456789","987654321"},
            {"5","12"},
            {"9","9"}
    };
    private static void testPlus() {
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(testCases[i][0] + " + " + testCases[i][1] + " = " + test.plus(testCases[i][0],testCases[i][1]));
        }
    }
    private static void testMulti() {
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(testCases[i][0] + " * " + testCases[i][1].charAt(0) + " = " + new String(test.multi(testCases[i][0].toCharArray(),(char)(testCases[i][1].charAt(0)-'0'),0)));
        }
    }
    private static void testMultiply() {
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(testCases[i][0] + " * " + testCases[i][1] + " = " + test.multiply(testCases[i][0],testCases[i][1]));
        }
    }
    public static void main(String[] args) {
        //testPlus();
        //testMulti();
        testMultiply();
    }
}
