/**
 * Leetcode - Algorithm - Maximal Square
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.Matrix;

class MaximalSquare {
    public class SolutionV1 {
        public int maximalSquare(char[][] matrix) {
            int result = 0;
            if (matrix.length == 0) { return result; }
            int height = matrix.length, width = matrix[0].length;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int maxPossibleHeight = height - i;
                    if (maxPossibleHeight <= result) { return result * result; } // 剪枝
                    int maxPossibleWidth = width - j;
                    if (maxPossibleWidth <= result) { break; } // 剪枝
                    int range = Math.min(maxPossibleHeight,maxPossibleWidth);
                    // System.out.println("Check point [" + i + "," + j + "], max size within range[" + range + "] = " + checkSquare(matrix,i,j,range));
                    result = Math.max(result,checkSquare(matrix,i,j,range));
                }
            }
            return result * result;
        }
        public int checkSquare(char[][] matrix, int x, int y, int range) {
            int result = 0;
            for (int i = 0; i < range; i++) {
                // System.out.println("Check range " + i);
                for (int j = x + i, k = y; k <= y + i; k++) {
                    // System.out.println("point [" + j + "," + k + "] = " + matrix[j][k]);
                    if (matrix[j][k] == '0') { return result; }
                } // check line
                for (int j = y + i, k = x; k <= x + i; k++) {
                    // System.out.println("point [" + k + "," + j + "] = " + matrix[k][j]);
                    if (matrix[k][j] == '0') { return result; }
                } // check column
                // System.out.println("Pass the test for range " + i);
                result++;
            }
            return result;
        }
    }
    public class SolutionV2 {
        public int maximalSquare(char[][] matrix) {
            int size = 0;
            if (matrix.length == 0 || matrix[0].length == 0) { return size * size; }
            int height = matrix.length, width = matrix[0].length;
            int[][] dpTable = new int[height+1][width+1];
            for (int i = 1; i < dpTable.length; i++) {
                for (int j = 1; j < dpTable[0].length; j++) {
                    if (matrix[i-1][j-1] == '1') {
                        int dpVal = Math.min(Math.min(dpTable[i-1][j],dpTable[i][j-1]),dpTable[i-1][j-1]) + 1;
                        dpTable[i][j] = dpVal;
                        size = Math.max(size,dpVal);
                    }
                }
            }
            return size * size;
        }
    }
    public class Solution {
        public int maximalSquare(char[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) { return 0; }
            int max = 0, height = matrix.length, width = matrix[0].length;
            int[] dpHelper = new int[width+1];
            for (int i = 0; i < height; i++) {
                int[] localHelper = new int[width+1];
                for (int j = 0; j < width; j++) {
                    if (matrix[i][j] == '1') {
                        int dpVal = Math.min(localHelper[j],Math.min(dpHelper[j],dpHelper[j+1])) + 1;
                        localHelper[j+1] = dpVal;
                        max = Math.max(max,dpVal);
                    }
                }
                dpHelper = localHelper;
            }
            return max * max;
        }
    }
    private static MaximalSquare test = new MaximalSquare();
    private static Solution solution = test.new Solution();
    private static void callMaximalSquare(char[][] matrix, String answer) {
        Matrix.print(matrix);
        System.out.println("Maximal Square is " + solution.maximalSquare(matrix) + "    [answer = " + answer + "]");
    }
    private static void test() {
        char[][] matrix1 = new char[][]{
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        char[][] matrix2 = new char[][] {
            {'0'}
        };
        char[][] matrix3 = new char[][] {
            {'0','0','0'},
            {'0','0','0'},
            {'0','0','0'},
            {'0','0','0'}
        };
        char[][] matrix4 = new char[][] {
            {'0','0','0','1'},
            {'1','1','0','1'},
            {'1','1','1','1'},
            {'0','1','1','1'},
            {'0','1','1','1'}
        };
        callMaximalSquare(matrix1,"4");
        callMaximalSquare(matrix2,"0");
        callMaximalSquare(matrix3,"0");
        callMaximalSquare(matrix4,"0");
    }
    public static void main(String[] args) {
        test();
    }
}
