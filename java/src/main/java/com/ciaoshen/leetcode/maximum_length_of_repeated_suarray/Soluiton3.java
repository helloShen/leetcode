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
class Solution3 implements Solution {

    public int findLength(int[] A, int[] B) {
        int maxWindow = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    int window = 1;
                    for (int k = i + 1, l= j + 1; k < A.length && l < B.length && A[k] == B[l]; k++, l++) window++;
                    maxWindow = Math.max(maxWindow, window);
                }
            }
        }
        return maxWindow;
    }

}
