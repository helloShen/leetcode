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
class Solution2 implements Solution {

    public int findLength(int[] A, int[] B) {
        int[][] idxMatrix = new int[1000][101];
        for (int i = 0; i < B.length; i++) {
            int num = B[i];
            idxMatrix[num][idxMatrix[num][1000]] = i;
            idxMatrix[num][1000]++;
        }
        int maxWindow = 0;
        for (int i = 0; i < A.length; i++) {
            int num = A[i];
            for (int p = 0; p < idxMatrix[num][1000]; p++) {
                int window = 1;
                for (int j = i + 1, k = idxMatrix[num][p] + 1; j < A.length && k < B.length && A[j] == B[k]; j++, k++) window++;
                maxWindow = Math.max(maxWindow, window);
            }
        }
        return maxWindow;
    }

}
