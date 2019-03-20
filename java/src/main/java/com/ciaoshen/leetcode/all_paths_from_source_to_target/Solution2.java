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
class Solution2 implements Solution {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> bfsQueue = new LinkedList<>(), outPut = new LinkedList<>();
        int[][] edgesTable = statistic(graph);
        // if (log.isDebugEnabled()) {
        //     log.debug("edgesTable = ");
        //     for (int i = 0; i < edgesTable.length; i++) {
        //         log.debug(" >>> [{}]", Arrays.toString(edgesTable[i]));
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
                int[] pres = edgesTable[head];
                for (int pre : pres) {
                    if (pre == -1) break;
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

    private int[][] statistic(int[][] graph) {
        int[][] edgesTable = new int[graph.length][15];
        for (int[] edge : edgesTable) Arrays.fill(edge, -1);
        int[] ps = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            int[] arr = graph[i];
            // if (log.isDebugEnabled()) {
            //     log.debug("graph[{}] = {}", i, Arrays.toString(arr));
            // }
            for (int j = 0; j < arr.length; j++) {
                int to = arr[j];
                edgesTable[to][ps[to]++] = i;
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
