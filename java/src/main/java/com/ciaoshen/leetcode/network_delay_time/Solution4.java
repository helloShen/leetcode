/**
 * Leetcode - network_delay_time
 */
package com.ciaoshen.leetcode.network_delay_time;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * Floyd-Warshall Algorithm
 */
class Solution4 implements Solution {

    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] matrix = new int[N + 1][N + 1];
        // initialize maxtrix
        for (int[] row : matrix) Arrays.fill(row, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) matrix[i][i] = 0;
        for (int[] flight : times) matrix[flight[0]][flight[1]] = flight[2];
        // dp
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int includeK = matrix[i][k] + matrix[k][j];
                    if (matrix[i][k] != Integer.MAX_VALUE && matrix[k][j] != Integer.MAX_VALUE && matrix[i][j] > includeK) matrix[i][j] = includeK;
                }
            }
        }
        int maxPrice = 0;
        for (int i = 1; i <= N; i++) {
            if (matrix[K][i] == Integer.MAX_VALUE) return -1;
            maxPrice = Math.max(maxPrice, matrix[K][i]);
        }
        return maxPrice;
    }
}
