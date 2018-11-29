/**
 * Leetcode - peek_index_in_a_mountain_array
 */
package com.ciaoshen.leetcode.peek_index_in_a_mountain_array;
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

    public int peakIndexInMountainArray(int[] A) {
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) return i - 1;
        }
        return 0;
    }

}
