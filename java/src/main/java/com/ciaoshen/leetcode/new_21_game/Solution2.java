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
class Solution2 implements Solution {

    public double new21Game(int N, int K, int W) {
        if (K == 0 && N == 0) return (double) 1.0;
        if (N == 0) return (double) 0.0;
        if (K == 0) return (double) 1.0;
        int max = K - 1 + W;
        double[] dp = new double[max + 1];
        dp[0] = 1.0;
        for (int i = 1; i <= max; i++) {
            for (int j = i - W; j < i; j++) {
                if (j >= 0 && j < K) dp[i] += dp[j];
            }
            dp[i] /= W;
        }
        if (log.isDebugEnabled()) {
            log.debug("dp = {}", Arrays.toString(dp));
        }
        double sumProb = 0.0;
        for (int i = K; i <= N; i++) sumProb += dp[i];
        return sumProb;
    }

}
