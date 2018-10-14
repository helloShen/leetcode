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
class Solution2 implements Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] toDstTable = new int[n][K + 1];
        for (int[] row : toDstTable) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            if (log.isDebugEnabled()) {
                log.debug("{}", Arrays.toString(toDstTable[i]));
            }
        }
        Map<Integer, List<int[]>> flightsMap = new HashMap<>();
        for (int[] flight : flights) {
            if (!flightsMap.containsKey(flight[0])) {
                flightsMap.put(flight[0], new ArrayList<int[]>());
            }
            flightsMap.get(flight[0]).add(new int[]{flight[1], flight[2]});
            if (flight[1] == dst) toDstTable[flight[0]][0] = flight[2];
        }
        for (int i = 0; i < n; i++) {
            if (log.isDebugEnabled()) {
                log.debug("{}", Arrays.toString(toDstTable[i]));
            }
        }
        for (int stop = 1; stop <= K; stop++) {
            for (int from = 0; from < n; from++) {
                if (from == dst) continue;
                toDstTable[from][stop] = toDstTable[from][stop - 1];
                if (flightsMap.containsKey(from)) {
                    for (int[] flight : flightsMap.get(from)) {
                        if (toDstTable[flight[0]][stop - 1] != Integer.MAX_VALUE) {
                            if (log.isDebugEnabled()) {
                                log.debug("{} to {} = {} + {}", from, dst, flight[1] , toDstTable[flight[0]][stop - 1]);
                            }
                            toDstTable[from][stop] = Math.min(toDstTable[from][stop], flight[1] + toDstTable[flight[0]][stop - 1]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (log.isDebugEnabled()) {
                log.debug("{}", Arrays.toString(toDstTable[i]));
            }
        }
        return (toDstTable[src][K] == Integer.MAX_VALUE)? -1 : toDstTable[src][K];
    }

}
