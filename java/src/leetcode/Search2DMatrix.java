/**
 * Leetcode - Algorithm - Search 2D Matrix
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.Matrix;

class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) { return false; }
        int rowSize = matrix.length, colSize = matrix[0].length;
        int lo = 0, hi = (rowSize * colSize) - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int num = matrix[mid/colSize][mid%colSize];
            if (target < num) {
                hi = mid - 1;
            } else if (target > num) {
                lo = mid + 1;
            } else { // target == num
                return true;
            }
        }
        return false;
    }
    private static Search2DMatrix test = new Search2DMatrix();
    private static int[][][] matrixs = new int[][][]{
        { {1} },
        { {0,1} },
        { {0},{1} },
        {
            {1,3,5,7},
            {10,11,16,20},
            {23,30,34,50}
        },
        {
            {1,3,5,7},
            {10,11,16,20},
            {23,30,34,50}
        }
    };
    private static int[] targets = new int[]{ 1,1,1,11,15 };
    private static void testSearchMatrix() {
        for (int i = 0; i < matrixs.length; i++) {
            System.out.println("Search " + targets[i] + " in Matrix: ");
            Matrix.print(matrixs[i]);
            System.out.println(test.searchMatrix(matrixs[i],targets[i]));
        }
    }
    public static void main(String[] args) {
        testSearchMatrix();
    }
}
