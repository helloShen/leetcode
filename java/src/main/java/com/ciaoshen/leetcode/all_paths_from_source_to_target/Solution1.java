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
class Solution1 implements Solution {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> bfsQueue = new LinkedList<>(), outPut = new LinkedList<>();
        Map<Integer, List<Integer>> edgesTable = statistic(graph);
        // if (log.isDebugEnabled()) {
        //     log.debug("edgesTable = ");
        //     for (Map.Entry<Integer, List<Integer>> entry : edgesTable.entrySet()) {
        //         log.debug(" >>> [{}, {}]", entry.getKey(), entry.getValue());
        //     }
        // }
        bfsQueue.add(new LinkedList<Integer>(Arrays.asList(new Integer[]{graph.length - 1})));
        // if (log.isDebugEnabled()) {
        //     log.debug("bfsQueue = {}", bfsQueue);
        // }
        while (!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();
            for (int i = 0; i < size; i++) {
                List<Integer> path = bfsQueue.remove(0);
                // if (log.isDebugEnabled()) {
                //     log.debug("path = {}", path);
                // }
                int head = path.get(0);
                List<Integer> pres = edgesTable.get(head);
                for (int pre : pres) {
                    // if (log.isDebugEnabled()) {
                    //     log.debug("{} is a pre of head {}", pre, head);
                    // }
                    List<Integer> newPath = new LinkedList<>(path);
                    newPath.add(0, pre);
                    if (pre == 0) {
                        outPut.add(newPath);
                    } else {
                        bfsQueue.add(newPath);
                    }
                }
            }
        }
        return outPut;
    }

    private Map<Integer, List<Integer>> statistic(int[][] graph) {
        Map<Integer, List<Integer>> edgesTable = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            int[] arr = graph[i];
            // if (log.isDebugEnabled()) {
            //     log.debug("graph[{}] = {}", i, Arrays.toString(arr));
            // }
            for (int j = 0; j < arr.length; j++) {
                int to = arr[j];
                if (!edgesTable.containsKey(to)) edgesTable.put(to, new LinkedList<Integer>());
                edgesTable.get(to).add(i);
            }
            // if (log.isDebugEnabled()) {
            //     log.debug("edgesTable = ");
            //     for (Map.Entry<Integer, List<Integer>> entry : edgesTable.entrySet()) {
            //         log.debug(" >>> [{}, {}]", entry.getKey(), entry.getValue());
            //     }
            // }
        }
        return edgesTable;
    }

}
