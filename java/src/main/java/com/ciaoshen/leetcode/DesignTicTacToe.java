/**
 * Leetcode - Algorithm - DesignTicTacToe
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DesignTicTacToe implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DesignTicTacToe() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void ticTacToe(int n);                  // 主方法接口
        abstract public int move(int row, int col, int player); // 主方法接口
        abstract public void print();
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /**
     * O(n)
     * 实际用二维数组int[n][n]模拟n*n的棋盘
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        //模拟构造函数
        public void ticTacToe(int n) {
            board = new int[n][n];
            size = n;
            win = 0;
        }
        //下棋
        public int move(int row, int col, int player) {
            if (win > 0) { return win; } //有人赢了就不能再下了
            board[row][col] = player;
            if (win(row,col,player)) {
                win = player;
                return player;
            } else {
                return 0;
            }
        }

        //棋盘
        private int[][] board;
        //棋盘大小
        private int size;
        //胜利标志(只要有一方赢了，这个标志就不改了)
        private int win;

        private boolean win(int row, int col, int player) {
            System.out.println("test [" + row + "," + col + "," + player + "]");
            return rowWin(row,col,player) ||
                   colWin(row,col,player) ||
                   ((row == col) && diagonalWin(player)) ||
                   ((row + col == size-1) && antiDiagonalWin(player));
        }
        private boolean rowWin(int row, int col, int player) {
            for (int i = 0; i < size; i++) {
                if (board[row][i] != player) {
                    System.out.println("row [" + row + "] not win! \t -> [" + row + "," + col + "]");
                    return false;
                }
            }
            System.out.println("row [" + row + "] win!");
            return true;
        }
        private boolean colWin(int row, int col, int player) {
            for (int i = 0; i < size; i++) {
                if (board[i][col] != player) {
                    System.out.println("col [" + col + "] not win! \t -> [" + row + "," + col + "]");
                    return false;
                }
            }
            System.out.println("col [" + col + "] win!");
            return true;
        }
        private boolean diagonalWin(int player) {
            for (int i = 0; i < size; i++) {
                if (board[i][i] != player) {
                    System.out.println("diagonal [" + i + "," + i + "] not win!");
                    return false;
                }
            }
            System.out.println("diagonal win!");
            return true;
        }
        private boolean antiDiagonalWin(int player) {
            for (int i = 0; i < size; i++) {
                if (board[i][size-1-i] != player) {
                    System.out.println("anti diagonal [" + i + "," + (size-1-i) + "] not win!");
                    return false;
                }
            }
            System.out.println("anti diagonal win!");
            return true;
        }
        public void print() {
            Matrix.print(board);
        }
    }

    /**
     * O(1)
     * 真实棋盘被抽象统计表代替
     */
    private class Solution2 extends Solution {
        { super.id = 2; }


        //模拟构造函数
        public void ticTacToe(int n) {
            row = new int[n][2];
            col = new int[n][2];
            diagonal = new int[2][2];
            size = n;
            win = 0;
        }
        //下棋
        public int move(int row, int col, int player) {
            if (win > 0) { return win; } //有人赢了就不能再下了
            if (win(row,col,player)) {
                win = player;
                return player;
            } else {
                return 0;
            }
        }


        /**
         * 棋盘（真实棋盘被抽象统计表代替）
         * row[x][0]标记x行归属
         * row[x][1]标记x行霸主占了多少格
         * col[y][1]标记y列归属
         * col[y][1]标记y列霸主占了多少格
         * diagonal[0][0]标记正斜线"\"归属
         * diagonal[0][1]标记正斜线"\"霸主占了多少格
         * diagonal[1][0]标记反斜线"\"归属
         * diagonal[1][1]标记反斜线"\"霸主占了多少格
         *

         */
        private int[][] row, col, diagonal;
        //棋盘大小
        private int size;
        //胜利标志(只要有一方赢了，这个标志就不改了)
        private int win;
        /**
         * 常数：
         *      0: 还没有人涉足
         *      3: 1，2都有布局，谁都不是霸主，死局
         */
        private final int EMPTY = 0;
        private final int CAN_NOT_WIN = 3;

        private boolean win(int rowNum, int colNum, int player) {
            return rowColWin(row,rowNum,player) ||
                   rowColWin(col,colNum,player) ||
                   ((rowNum == colNum) && diagonalWin(diagonal[0], player)) ||
                   ((rowNum + colNum == size-1) && diagonalWin(diagonal[1], player));
        }
        //行或列获胜判定方法一致，所以合并
        private boolean rowColWin(int[][] rowOrCol, int numRowCol, int player) {
            if (rowOrCol[numRowCol][0] == EMPTY || rowOrCol[numRowCol][0] == player) {
                if (rowOrCol[numRowCol][0] == EMPTY) {
                    rowOrCol[numRowCol][0] = player;
                }
                if (++rowOrCol[numRowCol][1] == size) {
                    win = player;
                    return true;
                } else {
                    return false;
                }
            } else { //要么对手是霸主，要么已经是死局
                if (rowOrCol[numRowCol][0] != CAN_NOT_WIN) { //对手是霸主
                    rowOrCol[numRowCol][0] = CAN_NOT_WIN;
                }
                return false;
            }
        }
        private boolean diagonalWin(int[] diagonal, int player) {
            if (diagonal[0] == EMPTY || diagonal[0] == player) {
                if (diagonal[0] == EMPTY) {
                    diagonal[0] = player;
                }
                if (++diagonal[1] == size) {
                    win = player;
                    return true;
                } else {
                    return false;
                }
            } else { //要么对手是霸主，要么已经是死局
                if (diagonal[0] != CAN_NOT_WIN) { //对手是霸主
                    diagonal[0] = CAN_NOT_WIN;
                }
                return false;
            }
        }
        public void print() {
            System.out.println("Row: ");
            Matrix.print(row);
            System.out.println("Col: ");
            Matrix.print(col);
            System.out.println("Diagonal: ");
            Matrix.print(diagonal);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void ticTacToe(int n) {
            size = n;
            rows = new int[n];
            cols = new int[n];
            diagonal = 0;
            antiDiagonal = 0;
            win = 0;
        }
        public int move(int row, int col, int player) {
            //alreay finish
            if (win > 0) { return win; }
            //move
            int move = (player == 1)? 1 : -1;
            rows[row] += move;
            cols[col] += move;
            if (row == col) { diagonal += move; }
            if (row + col == size - 1) { antiDiagonal += move; }
            //win?
            if (win(row,col,player)) {
                win = player;
                return player;
            } else {
                return 0;
            }
        }

        private int size;
        private int[] rows;
        private int[] cols;
        private int diagonal;
        private int antiDiagonal;
        private int win;

        private boolean win(int row, int col, int player) {
            int target = (player == 1)? size : -size;
            return rows[row] == target ||
                   cols[col] == target ||
                   diagonal == target ||
                   antiDiagonal == target;
        }

        public void print() {
            return;
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
        private DesignTicTacToe problem = new DesignTicTacToe();
        private Solution solution = null;

        private void call(int n, int[][] moves, int[] answer) {
            solution.ticTacToe(n);
            for (int[] move : moves) {
                System.out.println("Win?\t[" + solution.move(move[0],move[1],move[2]) + "]");
                solution.print();
            }
            System.out.println("Answer: " + Arrays.toString(answer));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int n1 = 2;
            int[][] moves1 = new int[][]{
                {0,1,1},{1,1,2},{1,0,1}
            };
            int[] answer1 = new int[]{0,0,1};

            /** involk call() method HERE */
            call(n1,moves1,answer1);
            // call();
            // call();
            // call();
            // call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
