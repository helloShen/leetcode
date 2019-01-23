/**
 * Leetcode - maximum_length_of_repeated_suarray
 */
package com.ciaoshen.leetcode.maximum_length_of_repeated_suarray;
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

    public int findLength(int[] A, int[] B) {
        Map<Integer, List<Integer>> idxMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            int num = B[i];
            if (!idxMap.containsKey(num)) idxMap.put(num, new ArrayList<Integer>());
            idxMap.get(num).add(i);
        }
        int maxWindow = 0;
        for (int i = 0; i < A.length; i++) {
            int num = A[i];
            if (idxMap.containsKey(num)) {
                for (int idx : idxMap.get(num)) {
                    int window = 1;
                    for (int j = i + 1, k = idx + 1; j < A.length && k < B.length && A[j] == B[k]; j++, k++) window++;
                    maxWindow = Math.max(maxWindow, window);
                }
            }
        }
        return maxWindow;
    }

}
