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
class Solution3 implements Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prev = new int[n];
        int[] curr = new int[n];
        Arrays.fill(prev, Integer.MAX_VALUE);
        Arrays.fill(curr, Integer.MAX_VALUE);
        Map<Integer, List<int[]>> flightsMap = new HashMap<>();
        for (int[] flight : flights) {
            if (!flightsMap.containsKey(flight[0])) {
                flightsMap.put(flight[0], new ArrayList<int[]>());
            }
            flightsMap.get(flight[0]).add(new int[]{flight[1], flight[2]});
            if (flight[1] == dst) prev[flight[0]] = flight[2];
        }
        for (int stop = 1; stop <= K; stop++) {
            for (int from = 0; from < n; from++) {
                if (from == dst) continue;
                curr[from] = prev[from];
                if (flightsMap.containsKey(from)) {
                    for (int[] flight : flightsMap.get(from)) {
                        if (prev[flight[0]] != Integer.MAX_VALUE) {
                            if (log.isDebugEnabled()) {
                                log.debug("{} to {} = {} + {}", from, dst, flight[1] , prev[flight[0]]);
                            }
                            curr[from] = Math.min(curr[from], flight[1] + prev[flight[0]]);
                        }
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
