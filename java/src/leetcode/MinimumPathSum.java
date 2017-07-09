/**
 * Leetcode - Algorithm - Minimum Path Sum
 */
package com.ciaoshen.leetcode;

class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int lineSize = grid.length;
        if (lineSize == 0) { return 0; }
        int columnSize = grid[0].length;
        if (columnSize == 0) { return 0; }
        int[][] memo = new int[lineSize+1][columnSize+1]; // 最后多加一行一列哨兵。
        for (int i = 0; i < lineSize+1; i++) {
            memo[i][columnSize] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < columnSize+1; i++) {
            memo[lineSize][i] = Integer.MAX_VALUE;
        }
        memo[lineSize-1][columnSize] = 0; // 为了不影响点[i][j]
        memo[lineSize][columnSize-1] = 0; // 为了不影响点[i][j]
        for (int i = lineSize-1; i >= 0; i--) {
            for (int j = columnSize-1; j >= 0; j--) {
                memo[i][j] = grid[i][j] + Math.min(memo[i+1][j],memo[i][j+1]);
            }
        }
        return memo[0][0];
    }
    private static MinimumPathSum test = new MinimumPathSum();
    private static int[][] testCase1 = new int[][]{
        {1,2,3}
    };
    private static int[][] testCase2 = new int[][]{
        {1},
        {2},
        {3}
    };
    private static int[][] testCase3 = new int[][]{
        {7,22,9,13,3,16},
        {21,19,1,10,5,6},
        {18,2,24,15,14,17},
        {8,23,4,11,12,20}
    };
    private static void testMinPathSum() {
        System.out.println("Test Case 1: " + test.minPathSum(testCase1));
        System.out.println("Test Case 2: " + test.minPathSum(testCase2));
        System.out.println("Test Case 3: " + test.minPathSum(testCase3));
    }
    public static void main(String[] args) {
        testMinPathSum();
    }
}
