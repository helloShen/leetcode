/**
 * Leetcode - Algorithm - RangeSumQuery2D
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class RangeSumQuery2D implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private RangeSumQuery2D() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void init(int[][] matrix);
        abstract public int sumRegion(int row1, int col1, int row2, int col2); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[][] sumTable = new int[0][0];

        public void init(int[][] matrix) {
            if (matrix.length == 0) { return; }
            sumTable = new int[matrix.length+1][matrix[0].length+1];
            for (int i = 1; i < sumTable.length; i++) {
                for (int j = 1; j < sumTable[0].length; j++) {
                    sumTable[i][j] = sumTable[i-1][j] + sumTable[i][j-1] - sumTable[i-1][j-1] + matrix[i-1][j-1];
                }
            }
        }
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sumTable[row2+1][col2+1] - sumTable[row1][col2+1] - sumTable[row2+1][col1] + sumTable[row1][col1];
        }

    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public void init(int[][] matrix) {

        }
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return 2;
        }

    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void init(int[][] matrix) {

        }
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return 3;
        }

    }
    // you can expand more solutions HERE if you want...


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private RangeSumQuery2D problem = new RangeSumQuery2D();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] matrix, int[] range, int ans) {
            Matrix.print(matrix);
            solution.init(matrix);
            System.out.println("Sum of range " + Arrays.toString(range) + " = " + solution.sumRegion(range[0],range[1],range[2],range[3]));
            System.out.println("Result should be: " + ans + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] matrix1 = new int[][]{
              {3, 0, 1, 4, 2},
              {5, 6, 3, 2, 1},
              {1, 2, 0, 1, 5},
              {4, 1, 0, 1, 7},
              {1, 0, 3, 0, 5}
            };
            int[] range1 = new int[]{2,1,4,3};
            int ans1 = 8;
            int[] range2 = new int[]{1,1,2,2};
            int ans2 = 11;
            int[] range3 = new int[]{1,2,2,4};
            int ans3 = 12;

            /** involk call() method HERE */
            call(matrix1,range1,ans1);
            call(matrix1,range2,ans2);
            call(matrix1,range3,ans3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
