/**
 * Leetcode - minimum_falling_path_sum
 */
package com.ciaoshen.leetcode.minimum_falling_path_sum;
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

    public int minFallingPathSum(int[][] A) {
        int height = A.length, width = A[0].length;
        int[] dpRow = Arrays.copyOf(A[0], width);
        for (int i = 1; i < height; i++) {
            int[] nextRow = new int[width];
            for (int j = 0; j < width; j++) {
                nextRow[j] = dpRow[j] + A[i][j];
                if (j > 0) nextRow[j] = Math.min(nextRow[j], dpRow[j - 1] + A[i][j]);
                if (j < width - 1) nextRow[j] = Math.min(nextRow[j], dpRow[j + 1] + A[i][j]);
            }
            dpRow = nextRow;
        }
        int res = 10001;
        for (int n : dpRow) res = Math.min(res, n);
        return res;
    }

}
