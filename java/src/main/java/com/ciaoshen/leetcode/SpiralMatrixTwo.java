/**
 * Leetcode - Algorithm - Spiral Matrix Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SpiralMatrixTwo {
    /*
    [
         [ 1, 2, 3 ],
         [ 8, 9, 4 ],
         [ 7, 6, 5 ]
    ]
    */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0, right = n-1, high = 0, low = n-1;
        int next = 0;
        for (; n > 0; n-=2, left++, right--, high++, low--) {
            if (right == left && low == high) { res[left][high] = ++next; }
            for (int i = left; i < right; i++) {
                res[high][i] = ++next;
            }
            for (int i = high; i < low; i++) {
                res[i][right] = ++next;
            }
            for (int i = right; i > left; i--) {
                res[low][i] = ++next;
            }
            for (int i = low; i > high; i--) {
                res[i][left] = ++next;
            }
        }
        return res;
    }
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("\n\n");
        }
    }
    private static SpiralMatrixTwo test = new SpiralMatrixTwo();
    private static void testGenerateMatrix() {
        for (int i = 0; i < 5; i++) {
            printMatrix(test.generateMatrix(i));
        }
    }
    public static void main(String[] args) {
        testGenerateMatrix();
    }
}
