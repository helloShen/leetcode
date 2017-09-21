/**
 * Leetcode - Algorithm - BattleshipsInABoard
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BattleshipsInABoard implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BattleshipsInABoard() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int countBattleships(char[][] board); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        private final char CROSS = 'X';
        public int countBattleships(char[][] board) {
            if (board.length == 0) { return 0; }
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == CROSS) {
                        if (!(i > 0 && board[i-1][j] == CROSS) && !(j > 0 && board[i][j-1] == CROSS)) { ++count; }
                    }
                }
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        private final char POINT = '.';
        private final char CROSS = 'X';
        public int countBattleships(char[][] board) {
            if (board.length == 0) { return 0; }
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == POINT) { continue; }
                    if (i > 0 && board[i-1][j] == CROSS) { continue; }
                    if (j > 0 && board[i][j-1] == CROSS) { continue; }
                    ++count;
                }
            }
            return count;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int countBattleships(char[][] board) {
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
        private BattleshipsInABoard problem = new BattleshipsInABoard();
        private Solution solution = null;

        // call method in solution
        private void call(char[][] board) {
            Matrix.print(board);
            System.out.println("Have " + solution.countBattleships(board) + " battleships.\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            char p = '.';
            char c = 'X';
            char[][] board1 = new char[][]{{p}};  // 0
            char[][] board2 = new char[][]{{c}};  // 1
            char[][] board3 = new char[][]{     // 2
                {c,p,p,c},
                {p,p,p,c},
                {p,p,p,c},
                {p,p,p,c}
            };
            /** involk call() method HERE */
            call(board1);
            call(board2);
            call(board3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
