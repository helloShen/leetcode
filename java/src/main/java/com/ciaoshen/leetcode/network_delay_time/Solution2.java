/**
 * Leetcode - network_delay_time
 */
package com.ciaoshen.leetcode.network_delay_time;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * Dijkstra Algorithm
 */
class Solution2 implements Solution {

    private class Flight {
        int dest, cost;
        private Flight(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
        public boolean equals(Object o) {
            if (! (o instanceof Flight)) return false;
            return (((Flight)o).dest == this.dest);
        }
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] flightTable = new int[N + 1][N + 1];
        for (int[] row : flightTable) Arrays.fill(row, -1);
        for (int i = 1; i <= N; i++) flightTable[i][i] = 0;
        for (int[] time : times) flightTable[time[0]][time[1]] = time[2];
        PriorityQueue<Flight> heap = new PriorityQueue<>((Flight a, Flight b) -> (a.cost - b.cost));
        heap.add(new Flight(K, flightTable[K][K]));
        int[] costs = new int[N + 1];
        Arrays.fill(costs, -1);
        while (!heap.isEmpty()) {
            Flight cheapest = heap.poll();
            int from = cheapest.dest;
            costs[from] = cheapest.cost;
            for (int to = 1; to <= N; to++) {
                if (costs[to] >= 0) continue;
                if (flightTable[from][to] >= 0) {
                    int newCost = cheapest.cost + flightTable[from][to];
                    boolean update = true;
                    Iterator<Flight> ite = heap.iterator();
                    while (ite.hasNext()) {
                        Flight line = ite.next();
                        if (line.dest == to) {
                            if (line.cost > newCost) {
                                ite.remove();
                            } else {
                                update = false;
                            }
                            break;
                        }
                    }
                    if (update) heap.add(new Flight(to, newCost));
                }
            }
        }
        int maxPrice = 0;
        for (int i = 1; i <= N; i++) {
            if (costs[i] == -1) return -1;
            maxPrice = Math.max(maxPrice, costs[i]);
        }
        return maxPrice;
    }
}
