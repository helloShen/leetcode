/**
 * Leetcode - minimum_ascii_delete_sum
 */
package com.ciaoshen.leetcode.minimum_ascii_delete_sum;
import java.util.*;
import com.ciaoshen.leetcode.util.*;
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Solution2 implements Solution {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Smallest edit distance
     */
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] + (int) s1.charAt(i - 1);
        }
        for (int j = 1; j <= s2.length(); j++) {
            dp[0][j] = dp[0][j - 1] + (int) s2.charAt(j - 1);
        }
        for (int i = 1; i <= s1.length(); i++) {
            char cRow = s1.charAt(i - 1);
            for (int j = 1; j <= s2.length(); j++) {
                char cCol = s2.charAt(j - 1);
                int fromUpper = dp[i - 1][j] + cRow;
                int fromLeft = dp[i][j - 1] + cCol;
                dp[i][j] = Math.min(fromUpper, fromLeft);
                int fromBevel = 0;
                if (cRow == cCol) {
                    fromBevel = dp[i - 1][j - 1];
                } else {
                    fromBevel = dp[i - 1][j - 1] + cRow + cCol;
                }
                dp[i][j] = Math.min(dp[i][j], fromBevel);
            }
        }
        if (log.isDebugEnabled()) {
            for (int i = 0; i < dp.length; i++) {
                log.debug("dp[{}] = {}", i, Arrays.toString(dp[i]));
            }
        }
        return dp[s1.length()][s2.length()];
    }

}
