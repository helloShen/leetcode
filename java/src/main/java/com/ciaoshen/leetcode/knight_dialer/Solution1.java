/**
 * Leetcode - knight_dialer
 */
package com.ciaoshen.leetcode.knight_dialer;
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

    private int max = 1000000007;
    private int[][] hopsTable = new int[][]{
        {4, 6},
        {6, 8},
        {7, 9},
        {4, 8},
        {3, 9, 0},
        {},
        {1, 7, 0},
        {2, 6},
        {1, 3},
        {2, 4}
    };

    public int knightDialer(int N) {
        int [] curr = new int[10];
        Arrays.fill(curr, 1);
        if (log.isDebugEnabled()) {
            log.debug("curr = {}", Arrays.toString(curr));
        }
        for (int i = 1; i < N; i++) {
            long[] next = new long[10];
            for (int n = 0; n < hopsTable.length; n++) {
                for (int p = 0; p < hopsTable[n].length; p++) {
                    if (hopsTable[n][p] >= 0) {
                        next[n] += curr[hopsTable[n][p]];
                    }
                }
            }
            curr = mod(next);
            if (log.isDebugEnabled()) {
                log.debug("curr = {}", Arrays.toString(curr));
            }
        }
        return sum(curr);
    }

    private int[] mod(long[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = (int) (arr[i] % max);
        }
        return res;
    }
    private int sum(int[] arr) {
        long sum = 0;
        for (int n : arr) sum += n;
        return (int) (sum % max);
    }


}
