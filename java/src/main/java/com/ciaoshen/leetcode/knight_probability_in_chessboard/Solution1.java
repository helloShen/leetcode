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
class Solution1 implements Solution {

    public double knightProbability(int N, int K, int r, int c) {
        init(N);
        moveTo(c, r, K);
        return (off == 0)? 1.0 : (double) on / (on + off);
    }

    private int size;
    private int on, off;

    private void init(int size) {
        this.size = size;
        on = 0;
        off = 0;
    }

    private void moveTo(int x, int y, int remain) {
        if (onTheBoard(x, y)) {
            if (remain == 0) {
                on++;
            } else {
                remain--;
                moveTo(x + 1, y + 2, remain);
                moveTo(x + 2, y + 1, remain);
                moveTo(x + 2, y - 1, remain);
                moveTo(x + 1, y - 2, remain);
                moveTo(x - 1, y - 2, remain);
                moveTo(x - 2, y - 1, remain);
                moveTo(x - 2, y + 1, remain);
                moveTo(x - 1, y + 2, remain);
            }
        } else {
            off += (int) Math.pow(8, remain);
        }
    }

    private boolean onTheBoard(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

}
