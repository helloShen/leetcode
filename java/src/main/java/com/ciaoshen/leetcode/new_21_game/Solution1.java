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
class Solution1 implements Solution {

    public double new21Game(int N, int K, int W) {
        if (K == 0 && N == 0) return (double) 1.0;
        if (N == 0) return (double) 0.0;
        if (K == 0) return (double) 1.0;
        n = N;
        k = K;
        w = W;
        max = K - 1 + W;
        count = new double[max + 1];
        dfs(0, (double)1.0);
        if (log.isDebugEnabled()) {
            log.debug("count = {}", Arrays.toString(count));
        }
        double inRange = 0, outOfRange = 0;
        for (int i = K; i <= N; i++) inRange += count[i];
        for (int i = N + 1; i <= max; i++) outOfRange += count[i];
        double sum = inRange + outOfRange;
        if (sum == 0) return 0;
        return (double) inRange / sum;
    }

    private int n;
    private int k;
    private int w;
    private int max;
    private double[] count;

    private void dfs(int num, double prob) {
        if (num < k) {
            for (int i = 1; i <= w; i++) dfs(num + i, prob / w);
        } else {
            count[num] += prob;
        }
    }

}
