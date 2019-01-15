/**
 * Leetcode - minimum_area_rectangle
 */
package com.ciaoshen.leetcode.minimum_area_rectangle;
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

    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> colMap = new HashMap<>();
        for (int[] point : points) {
            if (!colMap.containsKey(point[0])) colMap.put(point[0], new HashSet<Integer>());
            colMap.get(point[0]).add(point[1]);
        }
        List<Map.Entry<Integer, Set<Integer>>> sorted = new ArrayList<Map.Entry<Integer, Set<Integer>>>(colMap.entrySet());
        Collections.sort(sorted, (Map.Entry<Integer, Set<Integer>> a, Map.Entry<Integer, Set<Integer>> b) -> a.getKey() - b.getKey());
        int minArea = 1600000001;
        for (int i = 0; i < sorted.size(); i++) {
            Map.Entry<Integer, Set<Integer>> entry = sorted.get(i);
            int xLeft = entry.getKey();
            List<Integer> col = new ArrayList<Integer>(entry.getValue());
            Collections.sort(col, (Integer a, Integer b) -> a - b);
            if (log.isDebugEnabled()) {
                log.debug("col [{}] = {}", xLeft, col);
            }
            for (int j = i + 1; j < sorted.size(); j++) {
                int xRight = sorted.get(j).getKey();
                List<Integer> ys = new ArrayList<>();
                if (log.isDebugEnabled()) {
                    log.debug("check right col [{}]", xRight);
                }
                for (int k = 0; k < col.size(); k++) {
                    int y = col.get(k);
                    if (sorted.get(j).getValue().contains(y)) ys.add(y);
                }
                int minDiffY = 40001;
                if (ys.size() > 1) {
                    int a = ys.get(0);
                    for (int l = 1; l < ys.size(); l++) {
                        int b = a;
                        a = ys.get(l);
                        minDiffY = Math.min(minDiffY, a - b);
                    }
                }
                if (minDiffY != 40001) minArea = Math.min(minArea, (xRight - xLeft) * minDiffY);
            }
        }
        return (minArea == 1600000001)? 0 : minArea;
    }

}
