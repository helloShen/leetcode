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
class Solution5 implements Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] flightsTable = new int[n][n];
        for (int[] row : flightsTable) Arrays.fill(row, Integer.MAX_VALUE);
        for (int[] flight : flights) {
            flightsTable[flight[0]][flight[1]] = flight[2];
        }
        List<Integer> flightList = new ArrayList<>();
        List<Integer> priceList = new ArrayList<>();
        for (int to = 0; to < n; to++) {
            if (flightsTable[src][to] != Integer.MAX_VALUE) {
                flightList.add(to);
                priceList.add(flightsTable[src][to]);
            }
        }
        K--;
        while (K-- >= 0 && !flightList.isEmpty()) {
            int size = flightList.size();
            for (int i = 0; i < size; i++) {
                int from = flightList.remove(0);
                int price = priceList.remove(0);
                for (int to = 0; to < n; to++) {
                    if (flightsTable[from][to] != Integer.MAX_VALUE && price + flightsTable[from][to] < flightsTable[src][to]) {
                        flightsTable[src][to] = price + flightsTable[from][to];
                        flightList.add(to);
                        priceList.add(flightsTable[src][to]);
                    }
                }
            }
        }
        return (flightsTable[src][dst] == Integer.MAX_VALUE)? -1 : flightsTable[src][dst];
    }

}
