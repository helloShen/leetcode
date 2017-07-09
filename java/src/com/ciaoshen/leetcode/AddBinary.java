/**
 * Leetcode - Algorithm - Add Binary
 */
package com.ciaoshen.leetcode;
import java.util.*;

class AddBinary {

    public static String addBinary(String a, String b) {
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        char[] res = new char[Math.max(charA.length,charB.length)+1];
        int carry = 0;
        for (int i = charA.length-1, j = charB.length-1, cursor = res.length-1; i >=0 || j >= 0; ) {
            int numA = (i >= 0)? (charA[i--]-'0'):0;
            int numB = (j >= 0)? (charB[j--]-'0'):0;
            int sum = numA + numB + carry;
            res[cursor--] = (char)((sum % 2) + '0');
            carry = sum / 2;
        }
        res[0] = (char)(carry + '0');
        int firstOne = -1;
        for (int i = 0; i < res.length; i++) {
            if (res[i] == '1') { firstOne = i; break; }
        }
        if (firstOne == -1) { firstOne = res.length-1; }
        return new String(Arrays.copyOfRange(res,firstOne,res.length));
    }
    private static AddBinary test = new AddBinary();
    private static String[] pair1 = new String[]{"0","0"}; // answer: 0
    private static String[] pair2 = new String[]{"0","1"}; // answer: 1
    private static String[] pair3 = new String[]{"1","1"}; // answer: 10
    private static String[] pair4 = new String[]{"10000","01010101"}; // answer: 1100101
    private static String[] pair5 = new String[]{"111111","11111111"}; // answer: 100111110

    private static void testAddBinary() {
        System.out.println("Result = " + test.addBinary(pair1[0],pair1[1]) + "  , Answer = 0");
        System.out.println("Result = " + test.addBinary(pair2[0],pair2[1]) + "  , Answer = 1");
        System.out.println("Result = " + test.addBinary(pair3[0],pair3[1]) + "  , Answer = 10");
        System.out.println("Result = " + test.addBinary(pair4[0],pair4[1]) + "  , Answer = 1100101");
        System.out.println("Result = " + test.addBinary(pair5[0],pair5[1]) + "  , Answer = 100111110");
    }
    public static void main(String[] args) {
        testAddBinary();
    }
}
