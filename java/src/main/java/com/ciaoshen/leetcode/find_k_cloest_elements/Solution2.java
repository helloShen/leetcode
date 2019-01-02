/**
 * Leetcode - find_k_cloest_elements
 */
package com.ciaoshen.leetcode.find_k_cloest_elements;
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

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int size = arr.length;
        int lo = 0, hi = size - 1 - k;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (x - arr[mid] <= arr[mid + k] - x) {
                if (log.isDebugEnabled()) {
                    log.debug("{} too large, move to left.", mid);
                }
                hi = mid - 1;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("{} too small, move to right.", mid);
                }
                lo = mid + 1;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("start at {}", lo);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = lo; i < lo + k; i++) res.add(arr[i]);
        return res;
    }

}
