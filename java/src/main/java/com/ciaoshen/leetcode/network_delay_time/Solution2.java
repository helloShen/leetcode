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
    }
    public int networkDelayTime(int[][] times, int N, int K) {
        int[][] flightTable = new int[N + 1][N + 1];
        for (int[] row : flightTable) Arrays.fill(row, -1);
        for (int i = 1; i <= N; i++) flightTable[i][i] = 0;
        for (int[] time : times) flightTable[time[0]][time[1]] = time[2];
        PriorityQueue<Flight> heap = new PriorityQueue<>((Flight a, Flight b) -> (a.cost - b.cost));
        heap.add(new Flight(K, flightTable[K][K]));
        boolean[] visited = new boolean[N + 1];
        int[] minCosts = new int[N + 1];
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        int remain = N;
        int maxPrice = 0;
        while (!heap.isEmpty()) {
            Flight cheapest = heap.poll();
            int from = cheapest.dest;
            if (visited[from]) continue;
            visited[from] = true;
            maxPrice = Math.max(maxPrice, cheapest.cost);
            remain--;
            for (int to = 1; to <= N; to++) {
                if (!visited[to] && flightTable[from][to] >= 0) {
                    int newPrice = cheapest.cost + flightTable[from][to];
                    if (newPrice < minCosts[to]) {
                        minCosts[to] = newPrice;
                        heap.add(new Flight(to, newPrice));
                    }
                }
            }
        }
        return (remain == 0)? maxPrice : -1;
    }
    
}
