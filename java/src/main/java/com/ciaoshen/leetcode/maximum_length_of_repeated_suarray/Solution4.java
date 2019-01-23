/**
 * Leetcode - maximum_length_of_repeated_suarray
 */
package com.ciaoshen.leetcode.maximum_length_of_repeated_suarray;
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
class Solution4 implements Solution {

    public int findLength(int[] A, int[] B) {
        int sizeA = A.length;
        int sizeB = B.length;
        int[][] dp = new int[sizeA + 1][sizeB + 1];
        int maxWindow = 0;
        for (int i = 1; i <= sizeA; i++) {
            for (int j = 1; j <= sizeB; j++) {
                dp[i][j] = (A[i - 1] == B[j - 1])? dp[i - 1][j - 1] + 1 : 0;
                maxWindow = Math.max(maxWindow, dp[i][j]);
            }
        }
        return maxWindow;
    }

}
