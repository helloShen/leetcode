/**
 * Leetcode - all_paths_from_source_to_target
 */
package com.ciaoshen.leetcode.all_paths_from_source_to_target;
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

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        localGraph = graph;
        res = new LinkedList<List<Integer>>();
        dfs(new LinkedList<Integer>(Arrays.asList(new Integer[]{0})), 0, graph.length - 1);
        return res;
    }

    int[][] localGraph;
    List<List<Integer>> res;

    private void dfs(List<Integer> path, int node, int target) {
        if (node == target) {
            res.add(new ArrayList<Integer>(path));
        } else {
            for (int next : localGraph[node]) {
                path.add(next);
                dfs(path, next, target);
                path.remove(path.size() - 1);
            }
        }
    }
}
