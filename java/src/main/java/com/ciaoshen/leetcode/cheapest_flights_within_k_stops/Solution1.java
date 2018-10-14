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
class Solution1 implements Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        flightsMap = new HashMap<Integer, List<int[]>>();
        for (int[] flight : flights) {
            if (!flightsMap.containsKey(flight[0])) {
                flightsMap.put(flight[0], new ArrayList<int[]>());
            }
            flightsMap.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        minPrice = Integer.MAX_VALUE;
        dfs(0, K, src, dst, new HashSet<Integer>());
        return (minPrice == Integer.MAX_VALUE)? -1 : minPrice;
    }

    private Map<Integer, List<int[]>> flightsMap;
    private int minPrice;

    private void dfs(int price, int stop, int curr, int dst, Set<Integer> visited) {
        if (stop < -1) return;
        if (curr == dst) {
            minPrice = Math.min(minPrice, price);
            return;
        }
        List<int[]> flightsFromCurr = flightsMap.get(curr);
        if (flightsMap.containsKey(curr)) {
            for (int[] to : flightsMap.get(curr)) {
                if (visited.add(to[0])) {
                    dfs(price + to[1], stop - 1, to[0], dst, visited);
                    visited.remove(to[0]);
                }
            }
        }
    }
}
