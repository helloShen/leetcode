/**
 * Leetcode - Algorithm - Spiral Matrix
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) { return res; }
        int high = 0, low = matrix.length-1;
        int left = 0, right = matrix[0].length-1;
        while (low - high >= 0 && right - left >= 0) {
            parseCircle(matrix,high++,low--,left++,right--,res);
        }
        return res;
    }
    public void parseCircle(int[][] matrix, int high, int low, int left, int right, List<Integer> res) {
        for (int i = left; i < right; i++) {
            res.add(matrix[high][i]);
        }
        for (int i = high; i < low; i++) {
            res.add(matrix[i][right]);
        }
        if (low - high > 0) {
            for (int i = right; i > left; i--) {
                res.add(matrix[low][i]);
            }
        } else { // 横一字长条
            res.add(matrix[high][right]);
        }
        if (right - left > 0) {
            for (int i = low; i > high; i--) {
                res.add(matrix[i][left]);
            }
        }  else if (low - high > 0){ // 纵向一字长条，且不是单个点
            res.add(matrix[low][left]);
        }
    }
    private static SpiralMatrix test = new SpiralMatrix();
    private static int[][] matrix1 = new int[][]{ { 1 } };
    private static int[][] matrix2 = new int[][]{ { 1,2,3,4,5,6,7,8,9,10 } };
    private static int[][] matrix3 = new int[][]{
        {1},{2},{3},{4},{5},{6},{7},{8},{9},{10}
    };
    private static int[][] matrix4 = new int[][] {
        {1,2,3},
        {4,5,6},
        {7,8,9},
        {10,11,12},
        {13,14,15}
    };
    private static void testSpiralOrder() {
        System.out.println(test.spiralOrder(matrix1));
        System.out.println(test.spiralOrder(matrix2));
        System.out.println(test.spiralOrder(matrix3));
        System.out.println(test.spiralOrder(matrix4));
    }
    public static void main(String[] args) {
        testSpiralOrder();
    }
}
