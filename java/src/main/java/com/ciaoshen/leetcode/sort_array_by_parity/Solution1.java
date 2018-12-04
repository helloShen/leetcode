/**
 * Leetcode - sort_array_by_parity
 */
package com.ciaoshen.leetcode.sort_array_by_parity;
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

    public int[] sortArrayByParity(int[] A) {
        for (int lastEven = -1, curr = 0; curr < A.length; curr++) {
            if (A[curr] % 2 == 0) swap(A, ++lastEven, curr);
        }
        return A;
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}
