/**
 * Leetcode - new_21_game
 */
package com.ciaoshen.leetcode.new_21_game;
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
class Solution3 implements Solution {

    public double new21Game(int N, int K, int W) {
        if (K == 0 && N == 0) return (double) 1.0;
        if (N == 0) return (double) 0.0;
        if (K == 0) return (double) 1.0;
        double[] dp = new double[N + 1];
        dp[0] = 1.0;
        double Wsum = 1.0, res = 0.0;
        for (int i = 1; i <= N; i++) {
            dp[i] = Wsum / W;
            if (i < K) {
                Wsum += dp[i];
            } else {
                res += dp[i];
            }
            if (i - W >= 0) Wsum -= dp[i - W];
        }
        if (log.isDebugEnabled()) {
            log.debug("dp = {}", Arrays.toString(dp));
        }
        return res;
    }

}
