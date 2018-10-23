/**
 * Leetcode - network_delay_time
 */
package com.ciaoshen.leetcode.network_delay_time;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * Bellman-Ford Algorithm
 */
class Solution3 implements Solution {

    public int networkDelayTime(int[][] times, int N, int K) {
        int[] disTo = new int[N + 1];
        Arrays.fill(disTo, Integer.MAX_VALUE);
        disTo[K] = 0;
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) {
                if (disTo[edge[0]] == Integer.MAX_VALUE) continue;
                disTo[edge[1]] = Math.min(disTo[edge[1]], disTo[edge[0]] + edge[2]);
            }
        }
        int maxPrice = 0;
        for (int i = 1; i <= N; i++) {
            if (disTo[i] == Integer.MAX_VALUE) return -1;
            maxPrice = Math.max(maxPrice, disTo[i]);
        }
        return maxPrice;
    }
}
