/**
 * Leetcode - knight_probability_in_chessboard
 */
package com.ciaoshen.leetcode.knight_probability_in_chessboard;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution2 implements Solution {

    private int size;
    private double[][] oldBoard, newBoard;

    private void init(int size) {
        this.size = size;
        oldBoard = new double[size][size];
        newBoard = new double[size][size];
    }
    public double knightProbability(int N, int K, int r, int c) {
        if (K == 0) return 1.0;
        init(N);
        for (int i = 0; i < N; i++) Arrays.fill(oldBoard[i], 1.0);
        for (int i = 0; i < K - 1; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    newBoard[j][k] = sumProb(j, k, oldBoard);
                }
            }
            double[][] temp = oldBoard;
            oldBoard = newBoard;
            newBoard = temp;
            if (log.isDebugEnabled()) {
                for (int row = 0; row < size; row++) {
                    log.debug("[{}]", Arrays.toString(oldBoard[row]));
                }
            }
        }
        return sumProb(r, c, oldBoard);
    }

    private double sumProb(int r, int c, double[][] board) {
        double sum = 0;
        sum  += (onTheBoard(r + 1, c + 2))? board[r + 1][c + 2] : 0;
        sum  += (onTheBoard(r + 2, c + 1))? board[r + 2][c + 1] : 0;
        sum  += (onTheBoard(r + 2, c - 1))? board[r + 2][c - 1] : 0;
        sum  += (onTheBoard(r + 1, c - 2))? board[r + 1][c - 2] : 0;
        sum  += (onTheBoard(r - 1, c - 2))? board[r - 1][c - 2] : 0;
        sum  += (onTheBoard(r - 2, c - 1))? board[r - 2][c - 1] : 0;
        sum  += (onTheBoard(r - 2, c + 1))? board[r - 2][c + 1] : 0;
        sum  += (onTheBoard(r - 1, c + 2))? board[r - 1][c + 2] : 0;
        return sum / 8;
    }

    private boolean onTheBoard(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

}
