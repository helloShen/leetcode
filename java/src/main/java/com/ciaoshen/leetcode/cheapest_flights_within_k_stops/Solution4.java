/**
 * Leetcode - cheapest_flights_within_k_stops
 */
package com.ciaoshen.leetcode.cheapest_flights_within_k_stops;
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

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prev = new int[n];
        int[] curr = new int[n];
        Arrays.fill(prev, Integer.MAX_VALUE);
        Arrays.fill(curr, Integer.MAX_VALUE);
        int[][] flightsMap = new int[n][n];
        for (int[] flight : flights) {
            flightsMap[flight[0]][flight[1]] = flight[2];
            if (flight[1] == dst) prev[flight[0]] = flight[2];
        }
        for (int stop = 1; stop <= K; stop++) {
            for (int from = 0; from < n; from++) {
                if (from == dst) continue;
                curr[from] = prev[from];
                for (int to = 0; to < n; to++) {
                    if (prev[to] != Integer.MAX_VALUE && flightsMap[from][to] != 0) {
                        curr[from] = Math.min(curr[from], flightsMap[from][to] + prev[to]);
                    }
                }
            }
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return (prev[src] == Integer.MAX_VALUE)? -1 : prev[src];
    }

}
