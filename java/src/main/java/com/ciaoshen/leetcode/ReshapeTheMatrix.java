/**
 * Leetcode - Algorithm - ReshapeTheMatirx
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ReshapeTheMatrix implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ReshapeTheMatrix() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[][] matrixReshape(int[][] nums, int r, int c); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int height = nums.length;
            if (height == 0) { return nums; }
            int width = nums[0].length;
            if (width == 0 || (height * width != r * c)) { return nums; }
            int[][] res = new int[r][c];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int offset = i * width + j;
                    int newR = offset / c;
                    int newC = offset % c;
                    res[newR][newC] = nums[i][j];
                }
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int[][] matrixReshape(int[][] nums, int r, int c) {
            return new int[1][1];
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int[][] matrixReshape(int[][] nums, int r, int c) {
            return new int[1][1];
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
        private ReshapeTheMatrix problem = new ReshapeTheMatrix();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] nums, int r, int c) {
            Matrix.print(nums);
            System.out.println("New Size [" + r + " * " + c + "]");
            Matrix.print(solution.matrixReshape(nums,r,c));
            System.out.println("\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] nums1 = new int[][]{
                {1,2},
                {3,4}
            };
            int r1 = 1;
            int c1 = 4;
            int r2 = 2;
            int c2 = 4;

            /** involk call() method HERE */
            call(nums1,r1,c1);
            call(nums1,r2,c2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
