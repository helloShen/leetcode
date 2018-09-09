/**
 * Leetcode - Algorithm - GameOfLife
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class GameOfLife implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private GameOfLife() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void gameOfLife(int[][] board); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public void gameOfLife(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int nbs = countNeighbors(board,i,j);
                    if (board[i][j] == 1) {
                        if (nbs < 2 || nbs > 3) {
                            board[i][j] = 2;                    // 将死亡，先标记上
                        }
                    } else if (nbs == 3) {
                        board[i][j] = 3;                        // 将复活，先标记上
                    }
                }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 2) { board[i][j] = 0; }  // 应死的终将死去
                    if (board[i][j] == 3) { board[i][j] = 1; }  // 应活的也将得生
                }
            }
        }
        // return the number of neighbors for the given point
        private int countNeighbors(int[][] board, int x, int y) {
            int count = 0;
            for (int i = x-1; i <= x+1; i++) {
                for (int j = y-1; j <= y+1; j++) {
                    if (i < 0) { continue; }
                    if (i >= board.length) { continue; }
                    if (j < 0) { continue; }
                    if (j >= board[0].length) { continue; }
                    if (i == x && j == y) { continue; }
                    if (board[i][j] == 0) { continue; }
                    if (board[i][j] == 3) { continue; }
                    ++count;
                }
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public void gameOfLife(int[][] board) {

        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public void gameOfLife(int[][] board) {

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
        private GameOfLife problem = new GameOfLife();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] board) {
            Matrix.print(board);
            newLine();
            for (int i = 0; i < 5; i++) {
                solution.gameOfLife(board);
                Matrix.print(board);
                newLine();
            }
        }
        private void newLine() { System.out.println("\n\n"); }

        // generate random board
        private Random r = new Random();
        private int random() { return r.nextInt(2); }
        private int[][] randomBoard(int m, int n) {
            int[][] board = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = random();
                }
            }
            return board;
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] board = randomBoard(10,10);
            /** involk call() method HERE */
            call(board);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
