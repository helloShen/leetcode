/**
 * Leetcode - Algorithm - AndroidUnlockPatterns
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class AndroidUnlockPatterns implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private AndroidUnlockPatterns() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int numberOfPatterns(int m, int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int numberOfPatterns(int m, int n) {
            if (notInit()) { init(); }
            return memo[n] - memo[m-1];
        }

        private int[] memo;
        private int[][] board;
        private final int SIZE = 3;

        //预先算好所有结果
        private void init() {
            memo = new int[SIZE*SIZE+1];
            board = new int[SIZE][SIZE];
            backtracking(1);
            for (int i = 1; i < memo.length; i++) {
                memo[i] += memo[i-1];
            }
            // System.out.println(Arrays.toString(memo));
        }
        //惰性初始化
        private boolean notInit() {
            return memo == null;
        }
        //暴力回溯遍历所有可能
        private void backtracking(int num) {
            if (num > SIZE*SIZE) { return; }
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == 0 && (num == 1 || notJump(i,j))) {
                        board[i][j] = 1;
                        memo[num]++;
                        backtracking(num+1);
                        board[i][j] = 0;
                    }
                }
            }
        }
        //判定某一点是否是孤立的（因为不能飞）
        private boolean notJump(int i, int j) {
            for (int x = i-1; x <= i+1; x++) {
                for (int y = j-1; y <= j+1; y++) {
                    if (x >= 0 && x < SIZE && y >= 0 && y < SIZE &&
                       (x != i || y != j) && board[x][y] == 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private final int[] RESULT = new int[]{0, 9, 49, 273, 1513, 7569, 31649, 103889, 248369, 392849};
        public int numberOfPatterns(int m, int n) {
            return RESULT[n] - RESULT[m-1];
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int numberOfPatterns(int m, int n) {
            if (notInit()) { init(); }
            return count[n] - count[m-1];
        }

        private int[] count;
        private int[] board;
        private int[][] banned;
        private final int SIZE = 9;

        //预先算好所有结果
        private void init() {
            count = new int[SIZE+1];
            board = new int[SIZE+1];
            banned = new int[SIZE+1][SIZE+1];
            initBanned();
            backtracking(1,0);
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i-1];
            }
            // System.out.println(Arrays.toString(count));
        }
        private void initBanned() {
            //横
            banned[1][3] = 2;
            banned[3][1] = 2;
            banned[7][9] = 8;
            banned[9][7] = 8;
            //竖
            banned[1][7] = 4;
            banned[7][1] = 4;
            banned[3][9] = 6;
            banned[9][3] = 6;
            //中
            banned[2][8] = 5;
            banned[8][2] = 5;
            banned[4][6] = 5;
            banned[6][4] = 5;
            //斜
            banned[3][7] = 5;
            banned[7][3] = 5;
            banned[1][9] = 5;
            banned[9][1] = 5;
        }
        //惰性初始化
        private boolean notInit() {
            return count == null;
        }
        //暴力回溯遍历所有可能
        private void backtracking(int len, int last) {
            if (len > SIZE) { return; }
            // System.out.println("\n这个情况下：");
            // System.out.println(Arrays.toString(board));
            for (int i = 1; i <= SIZE; i++) {
                int obstacle = banned[last][i];
                if (board[i] == 0 &&
                    (obstacle == 0 || board[obstacle] == 1)) {
                    board[i] = 1;
                    count[len]++;
                    backtracking(len+1,i);
                    board[i] = 0;
                } else {
                    // System.out.println(last + " -> " + i + " banned!");
                }
            }
        }
    }
    // you can expand more solutions HERE if you want...

    private class Solution4 extends Solution {
        { super.id = 4; }

        public int numberOfPatterns(int m, int n) {
            count = new int[SIZE+1];
            board = new int[SIZE+1];
            banned = new int[SIZE+1][SIZE+1];
            initBanned();
            backtracking(1,0);
            int res = 0;
            for (int i = m ; i <=n; i++) {
                res += count[i];
            }
            return res;
        }

        private int[] count;
        private int[] board;
        private int[][] banned;
        private final int SIZE = 9;

        private void initBanned() {
            //横
            banned[1][3] = 2;
            banned[3][1] = 2;
            banned[7][9] = 8;
            banned[9][7] = 8;
            //竖
            banned[1][7] = 4;
            banned[7][1] = 4;
            banned[3][9] = 6;
            banned[9][3] = 6;
            //中
            banned[2][8] = 5;
            banned[8][2] = 5;
            banned[4][6] = 5;
            banned[6][4] = 5;
            //斜
            banned[3][7] = 5;
            banned[7][3] = 5;
            banned[1][9] = 5;
            banned[9][1] = 5;
        }
        //暴力回溯遍历所有可能
        private void backtracking(int len, int last) {
            if (len > SIZE) { return; }
            for (int i = 1; i <= SIZE; i++) {
                int obstacle = banned[last][i];
                if (board[i] == 0 &&
                    (obstacle == 0 || board[obstacle] == 1)) {
                    board[i] = 1;
                    count[len]++;
                    backtracking(len+1,i);
                    board[i] = 0;
                }
            }
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
        private AndroidUnlockPatterns problem = new AndroidUnlockPatterns();
        private Solution solution = null;

        // call method in solution
        private void call(int m, int n, int ans) {
            System.out.println("m = " + m + ", n = " + n);
            System.out.println("Result = " + solution.numberOfPatterns(m,n) + "\t[answer=" + ans + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */

            /** involk call() method HERE */
            call(1,1,9);
            call(1,2,65);
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
