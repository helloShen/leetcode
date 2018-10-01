/**
 * Leetcode - is_graph_bipartite
 */
package com.ciaoshen.leetcode.is_graph_bipartite;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * BFS
 */
class Solution2 implements Solution {

    public boolean isBipartite(int[][] graph) {
        int[] groups = new int[graph.length]; // [0]: no group,  [1]: group a,  [-1]: group b
        List<Integer> list = new ArrayList<>();
        int group = 1; // [1]: group a,  [-1]: group b
        for (int k = 0; k < graph.length; k++) { // graph can be composed of different isolated sub-graphs
            if (groups[k] != 0) continue;
            list.add(k);
            while (!list.isEmpty()) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    int num = list.remove(0);
                    groups[num] = group;
                    for (int j = 0; j < graph[num].length; j++) {
                        int neighbor = graph[num][j];
                        if (groups[neighbor] == group) return false;
                        if (groups[neighbor] == 0) {
                            groups[neighbor] = -group;
                            list.add(neighbor);
                        }
                    }
                }
                group = -group;
            }
        }
        return true;
    }

}
