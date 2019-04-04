/**
 * Leetcode - valid_tic_tac_toe_state
 */
package com.ciaoshen.leetcode.valid_tic_tac_toe_state;
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
class Solution1 implements Solution {

    public boolean validTicTacToe(String[] board) {
        boolean xWin = false, oWin = false;
        int[][] columnCount = new int[3][2];
        int[][] diagonalCount = new int[2][2];
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 3; i++) {
            String row = board[i];
            if (row.equals("XXX")) {
                xWin = true;
            } else if (row.equals("OOO")) {
                oWin = true;
            }
            for (int j = 0; j < 3; j++) {
                char c = row.charAt(j);
                if (c == 'X') {
                    xCount++;
                    columnCount[j][0]++;
                    if (i == j) diagonalCount[0][0]++;
                    if ((i + j) == 2) diagonalCount[1][0]++;
                } else if (c == 'O') {
                    oCount++;
                    columnCount[j][1]++;
                    if (i == j) diagonalCount[0][1]++;
                    if ((i + j) == 2) diagonalCount[1][1]++;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (columnCount[i][0] == 3) xWin = true;
            if (columnCount[i][1] == 3) oWin = true;
        }
        for (int i = 0; i < 2; i++) {
            if (diagonalCount[i][0] == 3) xWin = true;
            if (diagonalCount[i][1] == 3) oWin = true;
        }
        if (xWin && oWin) return false;
        if (xCount < oCount || (xCount - oCount) > 1) return false;
        if (xWin && xCount == oCount) return false;
        if (oWin && (xCount - oCount) == 1) return false;
        return true;
    }

}
