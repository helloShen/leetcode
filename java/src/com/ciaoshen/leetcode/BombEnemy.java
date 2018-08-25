/**
 * Leetcode - Algorithm - BombEnemy
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BombEnemy implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BombEnemy() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int maxKilledEnemies(char[][] grid); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }

    /** 
     * 暴力遍历二维数组 
     * 复杂度O(n^3)
    */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int maxKilledEnemies(char[][] grid) {
            if (grid == null || grid.length == 0) { return 0; }
            localGrid = grid;
            int max = 0;
            for (int i = 0; i < localGrid.length; i++) {
                for (int j = 0; j < localGrid[0].length; j++) {
                    if (localGrid[i][j] == '0') {
                        max = Math.max(max,count(i,j));
                    } 
                }
            }
            return max;
        }

        /** ================= 【以下为私有成员】 ================== */
        private char[][] localGrid;
        private int up(int row, int col) {
            int count = 0;
            for (int i = row - 1; i >= 0; i--) {
                switch (localGrid[i][col]) {
                    case 'W': 
                        return count;
                    case 'E':
                        count++; break;
                }
            } 
            return count;
        }
        private int down(int row, int col) {
            int count = 0;
            for (int i = row + 1; i < localGrid.length; i++) {
                switch (localGrid[i][col]) {
                    case 'W': 
                        return count;
                    case 'E':
                        count++; break;
                }
            } 
            return count;
        }
        private int left(int row, int col) {
            int count = 0;
            for (int i = col - 1; i >= 0; i--) {
                switch (localGrid[row][i]) {
                    case 'W': 
                        return count;
                    case 'E':
                        count++; break;
                }
            } 
            return count;
        }
        private int right(int row, int col) {
            int count = 0;
            for (int i = col + 1; i < localGrid[0].length; i++) {
                switch (localGrid[row][i]) {
                    case 'W': 
                        return count;
                    case 'E':
                        count++; break;
                }
            } 
            return count;
        }
        private int count(int row, int col) {
            return up(row, col) + down(row, col) + left(row, col) + right(row, col);
        }
    }

    /** 
     * 开始构建动态规划表
     * 但没有反向传播，所以复杂度还是O(n^3)
     */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int maxKilledEnemies(char[][] grid) {
            if (grid == null || grid.length == 0) { return 0; }
            localGrid = grid;
            dp(grid); 
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '0') {
                        max = Math.max(max, enemyKilledHorizon(i, j) + enemyKilledVertical(i, j));
                    }
                }
            }
            return max;
        }

        /** =========================== 【以下私有】 ============================== */
        private char[][] localGrid;
        private int[][][] dpTab;
        private void dp(char[][] grid) {
            dpTab = new int[grid.length+1][grid[0].length+1][2];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    switch (grid[i][j]) {
                        case 'W':
                            dpTab[i+1][j+1][0] = 0;
                            dpTab[i+1][j+1][1] = 0;
                            break;
                        case 'E':
                            dpTab[i+1][j+1][0] = dpTab[i+1][j][0] + 1;
                            dpTab[i+1][j+1][1] = dpTab[i][j+1][1] + 1;
                            break;
                        case '0':
                            dpTab[i+1][j+1][0] = dpTab[i+1][j][0];
                            dpTab[i+1][j+1][1] = dpTab[i][j+1][1];
                            break;
                        default: return;
                    }
                }
            }
        }
        private int enemyKilledHorizon(int row, int col) {
            int maxHorizon = 0;
            for (int i = col; i < localGrid[0].length; i++) {
                if (localGrid[row][i] == 'W') {
                    return maxHorizon;
                } else {
                    maxHorizon = Math.max(maxHorizon,dpTab[row+1][i+1][0]);
                }
            }
            return maxHorizon;
        }
        private int enemyKilledVertical(int row, int col) {
            int maxVertical = 0;
            for (int i = row; i < localGrid.length; i++) {
                if (localGrid[i][col] == 'W') {
                    return maxVertical;
                } else {
                    maxVertical = Math.max(maxVertical,dpTab[i+1][col+1][1]);
                }
            }
            return maxVertical;
        }
    }

    /**
     * 动态规划 + 反向传播
     * O(n^2)
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public int maxKilledEnemies(char[][] grid) {
            if (grid == null || grid.length == 0) { return 0; }
            dp(grid); 
            backPropagation(grid);
            return collect(grid);
        }
            

        /** =========================== 【以下私有】 ============================== */
        private int[][][] dpTab;

        //从右往左，从上往下构建动态规划表
        //局部最大值靠右，靠下
        private void dp(char[][] grid) {
            dpTab = new int[grid.length+1][grid[0].length+1][2];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    switch (grid[i][j]) {
                        case 'W':
                            dpTab[i+1][j+1][0] = 0;
                            dpTab[i+1][j+1][1] = 0;
                            break;
                        case 'E':
                            dpTab[i+1][j+1][0] = dpTab[i+1][j][0] + 1;
                            dpTab[i+1][j+1][1] = dpTab[i][j+1][1] + 1;
                            break;
                        case '0':
                            dpTab[i+1][j+1][0] = dpTab[i+1][j][0];
                            dpTab[i+1][j+1][1] = dpTab[i][j+1][1];
                            break;
                        default: return;
                    }
                }
            }
        }
        //反向传播
        //从右往左，从下往上将局部最大值更新到整张表
        private void backPropagation(char[][] grid) {
            //horizontal
            for (int i = 0; i < grid.length; i++) {
                for (int j = grid[0].length - 2; j >= 0; ) {
                    //skip walls
                    while (j >= 0 && (grid[i][j] == 'W' || grid[i][j+1] == 'W')) { j--; }
                    //back propagation
                    while (j >= 0 && (grid[i][j] != 'W' && grid[i][j+1] != 'W')) {
                        dpTab[i+1][j+1][0] = dpTab[i+1][j+2][0];
                        j--;
                    }
                }
            }
            //vertical
            for (int i = 0; i < grid[0].length; i++) {
                for (int j = grid.length - 2; j >= 0; ) {
                    //skip walls
                    while (j >= 0 && (grid[j][i] == 'W' || grid[j+1][i] == 'W')) { j--; }
                    //back propagation
                    while (j >= 0 && (grid[j][i] != 'W' && grid[j+1][i] != 'W')) {
                        dpTab[j+1][i+1][1] = dpTab[j+2][i+1][1];
                        j--;
                    }
                }
            }
        }
        //对计算好的动态规划表做全局统计
        private int collect(char[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '0') {
                        max = Math.max(max,dpTab[i+1][j+1][0] + dpTab[i+1][j+1][1]);
                    }
                }
            }
            return max;
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }
        
        public int maxKilledEnemies(char[][] grid) {
            if (grid == null || grid.length == 0) { return 0; }
            int max = 0;
            int killRow = 0;
            int[] killCol = new int[grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 'W') { continue; } //skip wall
                    if (j == 0 || grid[i][j-1] == 'W') {
                        killRow = left(i,j,grid);
                    }
                    if (i == 0 || grid[i-1][j] == 'W') {
                        killCol[j] = down(i,j,grid);
                    }
                    if (grid[i][j] == '0') { 
                        max = Math.max(max,killRow + killCol[j]);
                    }
                }
            }
            return max;
        }
        private int left(int row, int col, char[][] grid) {
            int count = 0;
            for (int i = col; i < grid[0].length && grid[row][i] != 'W'; i++) {
                if (grid[row][i] == 'E') {
                    count++;
                }
            }
            return count;
        }
        private int down(int row, int col, char[][] grid) {
            int count = 0;
            for (int i = row; i < grid.length && grid[i][col] != 'W'; i++) {
                if (grid[i][col] == 'E') {
                    count++;
                }
            }
            return count;
        }

    }


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
        private BombEnemy problem = new BombEnemy();
        private Solution solution = null;

        // call method in solution
        private void call(char[][] grid, int answer) {
            System.out.println("Map: ");
            Matrix.print(grid);
            System.out.println("Max Killed Enemy = " + solution.maxKilledEnemies(grid) + "\t[answer=" + answer + "]\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            char[][] grid0 = new char[0][];
            char[][] grid1 = new char[][]{{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}};
            char[][] grid2 = new char[][]{{'E'}};

            /** involk call() method HERE */
            call(grid0,0);
            call(grid1,3);
            call(grid2,0);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1); 
        // test.test(2); 
        // test.test(3); 
        test.test(4); 
    }
}
