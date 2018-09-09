/**
 * Leetcode - Algorithm - IslandPerimeter
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class IslandPerimeter implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private IslandPerimeter() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int islandPerimeter(int[][] grid); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int islandPerimeter(int[][] grid) {
            if (grid.length == 0) { return 0; }
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        count += 4;
                        if (i > 0 && grid[i-1][j] == 1) { count -= 2; }
                        if (j > 0 && grid[i][j-1] == 1) { count -= 2; }
                    }
                }
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int islandPerimeter(int[][] grid) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int islandPerimeter(int[][] grid) {
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
        private IslandPerimeter problem = new IslandPerimeter();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] grid) {
            System.out.println("Matrix: ");
            Matrix.print(grid);
            System.out.println("Perimeter is: " + solution.islandPerimeter(grid));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[][] grid1 = new int[][]{{0}};
            int[][] grid2 = new int[][]{{1}};
            int[][] grid3 = new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
            };
            /** involk call() method HERE */
            call(grid1);
            call(grid2);
            call(grid3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
