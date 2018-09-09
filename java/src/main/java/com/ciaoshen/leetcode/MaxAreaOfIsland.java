/**
 * Leetcode - Algorithm - MaxAreaOfIsland
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MaxAreaOfIsland implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MaxAreaOfIsland() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int maxAreaOfIsland(int[][] grid); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] count = new int[0];
        private int[] board = new int[0];

        private void init(int[][] grid) {
            int height = grid.length;
            int width = grid[0].length;
            count = new int[height * width + 1];
            board = new int[height * width + 1];
        }
        public int maxAreaOfIsland(int[][] grid) {
            int height = grid.length;
            if (height == 0) { return 0; }
            int width = grid[0].length;
            init(grid);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (grid[i][j] == 1) {
                        int posCurr = j+1 + width * i, posLeft = -1, posUpper = -1;
                        create(posCurr);
                        if (j > 0 && grid[i][j-1] == 1) {
                            posLeft = j + width * i;
                            merge(posCurr,posLeft); // 当前树嫁接到左树
                        }
                        if (i > 0 && grid[i-1][j] == 1) {
                            posUpper = j+1 + width * (i-1);
                            merge(posCurr,posUpper); // 当前树嫁接到上树
                        }
                    }
                }
            }
            int max = 0;
            for (int i = 0; i < count.length; i++) {
                max = Math.max(max,count[i]);
            }
            // System.out.println(Arrays.toString(count));
            // System.out.println(Arrays.toString(board));
            return max;
        }
        private void create(int pos) {
            board[pos] = pos;
            count[pos] = 1;
        }
        private int find(int pos) {
            if (board[pos] == pos) {
                return pos;
            } else {
                int root = find(board[pos]);
                board[pos] = root; // path compression
                return root;
            }
        }
        // root1嫁接到root2上
        private void merge(int pos1, int pos2) {
            int root1 = find(pos1);
            int root2 = find(pos2);
            if (root1 != root2) {
                board[root1] = board[root2];
                // System.out.println("Merge Group " + root1 + " to Group " + root2);
                // System.out.println(count[root1] + " members from Group " + root1 + " merged to Group " + root2 + " with " + count[root2] + " members.");
                count[root2] += count[root1];
                count[root1] = 0;
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int maxAreaOfIsland(int[][] grid) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int maxAreaOfIsland(int[][] grid) {
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
        private MaxAreaOfIsland problem = new MaxAreaOfIsland();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] grid, int ans) {
            Matrix.print(grid);
            System.out.println("Max Area = " + solution.maxAreaOfIsland(grid) + "\t [Answer: " + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] grid1 = new int[][]{
                 {0,0,1,0,0,0,0,1,0,0,0,0,0},
                 {0,0,0,0,0,0,0,1,1,1,0,0,0},
                 {0,1,1,0,1,0,0,0,0,0,0,0,0},
                 {0,1,0,0,1,1,0,0,1,0,1,0,0},
                 {0,1,0,0,1,1,0,0,1,1,1,0,0},
                 {0,0,0,0,0,0,0,0,0,0,1,0,0},
                 {0,0,0,0,0,0,0,1,1,1,0,0,0},
                 {0,0,0,0,0,0,0,1,1,0,0,0,0}
            };
            int ans1 = 6;

            int[][] grid2 = new int[][] {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
            };
            int ans2 = 4;

            /** involk call() method HERE */
            call(grid1,ans1);
            call(grid2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
