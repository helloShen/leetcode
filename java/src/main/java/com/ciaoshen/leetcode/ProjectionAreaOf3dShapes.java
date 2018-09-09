/**
 * Leetcode - Algorithm - ProjectionAreaOf3dShapes
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ProjectionAreaOf3dShapes implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ProjectionAreaOf3dShapes() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int projectionArea(int[][] grid); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int projectionArea(int[][] grid) {
            int x = 0, y = 0, z = 0;
            int[] maxY = new int[grid[0].length];   //记录每一列的最大值
            for (int i = 0; i < grid.length; i++) {
                int maxX = 0;                       //记录每一行的最大值
                for (int j = 0; j < grid[0].length; j++) {
                   if (grid[i][j] != 0) {
                        maxX = Math.max(grid[i][j],maxX);
                        maxY[j] = Math.max(grid[i][j],maxY[j]);
                        z++;
                   }
                }
                x += maxX;
            }
            for (int i = 0; i < grid[0].length; i++) {
                y += maxY[i];
            }
            return x + y + z;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int projectionArea(int[][] grid) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int projectionArea(int[][] grid) {
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
        private ProjectionAreaOf3dShapes problem = new ProjectionAreaOf3dShapes();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] grid) {
            Matrix.print(grid);
            System.out.println("Area = " + solution.projectionArea(grid) + "\n\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] grid1 = new int[][]{ {1,1,1,},{1,0,1},{1,1,1} };
            int[][] grid2 = new int[][]{ {1,2},{3,4} };

            /** involk call() method HERE */
            call(grid1);
            call(grid2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
