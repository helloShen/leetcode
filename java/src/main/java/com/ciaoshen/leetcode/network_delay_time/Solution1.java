/**
 * Leetcode - network_delay_time
 */
package com.ciaoshen.leetcode.network_delay_time;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * BFS + DP
 */
class Solution1 implements Solution {

    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] timeMatrix = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(timeMatrix[i], Integer.MAX_VALUE);
        for (int[] time : times) timeMatrix[time[0] - 1][time[1] - 1] = time[2];
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (timeMatrix[K - 1][i] != Integer.MAX_VALUE) level.add(i);
        }
        while (!level.isEmpty()) {
            int size = level.size();
            for (int i = 0; i < size; i++) {
                int from = level.remove(0);
                for (int to = 0; to < N; to++) {
                    if (to == K - 1) continue;
                    if (timeMatrix[from][to] != Integer.MAX_VALUE) {
                        if (timeMatrix[K - 1][from] + timeMatrix[from][to] < timeMatrix[K - 1][to]) {
                            timeMatrix[K - 1][to] = timeMatrix[K - 1][from] + timeMatrix[from][to];
                            level.add(to);
                        }
                    }
                }
            }
        }
        int maxTime = 0;
        for (int i = 0; i < N; i++) {
            if (i == K - 1) continue;
            if (timeMatrix[K - 1][i] == Integer.MAX_VALUE) return -1;
            maxTime = Math.max(maxTime, timeMatrix[K - 1][i]);
        }
        return maxTime;
    }

}
