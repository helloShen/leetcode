/**
 * Leetcode - is_graph_bipartite
 */
package com.ciaoshen.leetcode.is_graph_bipartite;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * BFS
 */
class Solution3 implements Solution {

    public boolean isBipartite(int[][] graph) {
        localGraph = graph;
        groups = new int[graph.length]; // [0]: no group, [1]: group a, [-1]: group b
        int group = 1; // group a is default value
        for (int i = 0; i < graph.length; i++) {
            if (groups[i] != 0) continue;
            if (!dfs(i, group)) return false;
        }
        return true;
    }

    private int[][] localGraph;
    private int[] groups;

    private boolean dfs(int node, int group) {
        if (groups[node] != 0) return groups[node] == group;
        groups[node] = group;
        for (int i = 0; i < localGraph[node].length; i++) {
            if (!dfs(localGraph[node][i], -group)) return false;
        }
        return true;
    }

}
