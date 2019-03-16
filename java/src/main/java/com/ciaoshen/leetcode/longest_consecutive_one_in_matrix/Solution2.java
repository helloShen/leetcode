/**
 * Leetcode - longest_consecutive_one_in_matrix
 */
package com.ciaoshen.leetcode.longest_consecutive_one_in_matrix;
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

    public int longestLine(int[][] M) {
        int max = 0;
        int height = M.length;
        if (height == 0) return max;
        int width = M[0].length;
        int[][][] dp = new int[height][width][4];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = (j > 0)? dp[i][j - 1][0] + 1 : 1;    // horizontal
                    max = Math.max(max, dp[i][j][0]);
                    dp[i][j][1] = (i > 0)? dp[i - 1][j][1] + 1 : 1;    // vertical
                    max = Math.max(max, dp[i][j][1]);
                    dp[i][j][2] = (i > 0 && j > 0)? dp[i - 1][j - 1][2] + 1 : 1;    // diagonal
                    max = Math.max(max, dp[i][j][2]);
                    dp[i][j][3] = (i > 0 && j + 1 < width)? dp[i - 1][j + 1][3] + 1 : 1;    // anti-diagonal
                    max = Math.max(max, dp[i][j][3]);
                } else {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                    dp[i][j][2] = 0;
                    dp[i][j][3] = 0;
                }
            }
        }
        return max;
    }

}
