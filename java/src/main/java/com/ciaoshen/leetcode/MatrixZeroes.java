/**
 * Leetcode - Algorithm - Matrix Zeroes
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MatrixZeroes {
    public void setZeroesV1(int[][] matrix) {
        int lineSize = matrix.length;
        if (lineSize == 0) { return; }
        int columnSize = matrix[0].length;
        for (int i = 0; i < lineSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < lineSize; k++) {
                        if (matrix[k][j] != 0) { matrix[k][j] = Integer.MIN_VALUE; }
                    }
                    for (int k = 0; k < columnSize; k++) {
                        if (matrix[i][k] !=0) { matrix[i][k] = Integer.MIN_VALUE; }
                    }
                }
            }
        }
        for (int i = 0; i < lineSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == Integer.MIN_VALUE) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    public void setZeroesV2(int[][] matrix) {
        int lineSize = matrix.length;
        if (lineSize == 0) { return; }
        int columnSize = matrix[0].length;
        List<Integer> zeroLine = new ArrayList<>();
        List<Integer> zeroColumn = new ArrayList<>();
        for (int i = 0; i < lineSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == 0) {
                    zeroLine.add(i);
                    zeroColumn.add(j);
                }
            }
        }
        for (int num : zeroLine) {
            for (int i = 0; i < columnSize; i++) {
                matrix[num][i] = 0;
            }
        }
        for (int num : zeroColumn) {
            for (int i = 0; i < lineSize; i++) {
                matrix[i][num] = 0;
            }
        }
    }
    public void setZeroesV3(int[][] matrix) {
        int lineSize = matrix.length;
        if (lineSize == 0) { return; }
        int columnSize = matrix[0].length;
        boolean firstLineShouldFillZero = false;
        for (int i = 0; i < lineSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == 0) { // 标记，此行回头应该整行变0.
                    if (i == 0) {
                        firstLineShouldFillZero = true; // 第一行比较特殊，用firstLineShouldFillZero来标记
                    } else {
                        matrix[i][0] = 0;
                    }
                    matrix[0][j] = 0; // 标记，当全部遍历完，回过头把整个这一列变0.
                }
            }
        }
        for (int i = 1; i < lineSize; i++) { // 根据第一列，把需要变0的行全变0。跳过第一行
            if (matrix[i][0] == 0) {
                for (int j = 0; j < columnSize; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < columnSize; i++) { // 根据第一行，把需要变0的列，全变0.
            if (matrix[0][i] == 0) {
                for (int j = 0; j < lineSize; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (firstLineShouldFillZero) { // 根据firstLineShouldFillZero决定第一行要不要全变0
            for (int i = 0; i < columnSize; i++) {
                matrix[0][i] = 0;
            }
        }
    }
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0) { return; }
        int lineSize = matrix.length, columnSize = matrix[0].length;
        boolean firstLineShouldFillZero = false, firstColumnShouldFillZero = false;
        for (int i = 0; i < lineSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) { firstLineShouldFillZero = true; }
                    if (j == 0) { firstColumnShouldFillZero = true; }
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < lineSize; i++) { // 根据第一行，第一列，把需要变0的行全变0。跳过第一行，第一列
            for (int j = 1; j < columnSize; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstLineShouldFillZero) { // 根据firstLineShouldFillZero决定第一行要不要全变0
            for (int i = 0; i < columnSize; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstColumnShouldFillZero) { // 根据firstColumnShouldFillZero决定第一列要不要全变0
            for (int i = 0; i < lineSize; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    private static MatrixZeroes test = new MatrixZeroes();
    private static int[][] testCase1 = new int[][]{
        { 1 }
    };
    private static int[][] testCase2 = new int[][]{
        { 1,0 }
    };
    private static int[][] testCase3 = new int[][]{
        { 1 },
        { 0 }
    };
    private static int[][] testCase4 = new int[][]{
        {1,0,3,4,5},
        {1,2,3,4,5},
        {6,7,8,9,0},
        {1,3,5,7,9}
    };
    private static void printMatrix(int[][] matrix) {
        int height = matrix.length, width = matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.print("\n\n");
        }
    }
    private static void testSetZeroes() {
        System.out.println(">>>>>>>>>>> Before >>>>>>>>>>>>>>>>");
        printMatrix(testCase1);
        System.out.println("<<<<<<<<<<< After <<<<<<<<<<<<<<<<<");
        test.setZeroes(testCase1);
        printMatrix(testCase1);
        System.out.println(">>>>>>>>>>> Before >>>>>>>>>>>>>>>>");
        printMatrix(testCase2);
        System.out.println("<<<<<<<<<<< After <<<<<<<<<<<<<<<<<");
        test.setZeroes(testCase2);
        printMatrix(testCase2);
        System.out.println(">>>>>>>>>>> Before >>>>>>>>>>>>>>>>");
        printMatrix(testCase3);
        System.out.println("<<<<<<<<<<< After <<<<<<<<<<<<<<<<<");
        test.setZeroes(testCase3);
        printMatrix(testCase3);
        System.out.println(">>>>>>>>>>> Before >>>>>>>>>>>>>>>>");
        printMatrix(testCase4);
        System.out.println("<<<<<<<<<<< After <<<<<<<<<<<<<<<<<");
        test.setZeroes(testCase4);
        printMatrix(testCase4);
    }
    public static void main(String[] args) {
        testSetZeroes();
    }
}
