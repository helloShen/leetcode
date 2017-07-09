/**
 * Zig-Zag Pattern
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class ZigZagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1) { return s; }
        char[] chars = s.toCharArray();
        char[][] matrix = new char[numRows][chars.length];
        int[] cursors = new int[numRows];
        boolean goesUp = false; // 控制掉头，0=down, 1=up
        for (int i = 0, row = 0; i < chars.length; i++){
            matrix[row][cursors[row]++] = chars[i];
            // 掉头
            if (row == 0) { goesUp = false; }
            if (row == numRows-1) { goesUp = true; }
            // 跑
            row = (goesUp)? row-1 : row+1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            sb.append(matrix[i],0,cursors[i]);
        }
        return sb.toString();
    }
    private static void testConvert() {
        //String s = "PAYPALISHIRING";
        String s = "AB";
        System.out.println("Original String: " + s);
        System.out.println("Zig-Zag Pattern: " + convert(s,3));
    }
    public static void main(String[] args) {
        testConvert();
    }
}
