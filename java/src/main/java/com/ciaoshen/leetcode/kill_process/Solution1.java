/**
 * Leetcode - kill_process
 */
package com.ciaoshen.leetcode.kill_process;
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

    // BFS
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer>> childrensMap = statistic(pid, ppid);
        List<Integer> queue = new LinkedList<>();
        queue.add(kill);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int target = queue.remove(0);
                res.add(target);
                if (childrensMap.containsKey(target)) queue.addAll(childrensMap.get(target));
            }
        }
        return res;
    }

    // find all childrens of each process
    private Map<Integer, List<Integer>> statistic(List<Integer> pid, List<Integer> ppid) {
        Map<Integer, List<Integer>> childrensMap = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int id = pid.get(i);
            int parentId = ppid.get(i);
            if (!childrensMap.containsKey(parentId)) childrensMap.put(parentId, new ArrayList<Integer>());
            childrensMap.get(parentId).add(id);
        }
        return childrensMap;
    }

}
