/**
 * Leetcode - is_graph_bipartite
 */
package com.ciaoshen.leetcode.is_graph_bipartite;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * BFS
 */
class Solution1 implements Solution {

    public boolean isBipartite(int[][] graph) {
        Set<Integer> thisGroup = new HashSet<>();
        Set<Integer> anotherGroup = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int k = 0; k < graph.length; k++) { // graph can be composed of different isolated sub-graphs
            if (thisGroup.contains(k) || anotherGroup.contains(k)) continue;
            list.add(k);
            while (!list.isEmpty()) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    int num = list.remove(0);
                    thisGroup.add(num);
                    for (int j = 0; j < graph[num].length; j++) {
                        int neighbor = graph[num][j];
                        if (thisGroup.contains(neighbor)) return false;
                        if (anotherGroup.add(neighbor)) list.add(neighbor);
                    }
                }
                Set<Integer> tempGroup = thisGroup;
                thisGroup = anotherGroup;
                anotherGroup = tempGroup;
            }
        }
        return true;
    }

}
