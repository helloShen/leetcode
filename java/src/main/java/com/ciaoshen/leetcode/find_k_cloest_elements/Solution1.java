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
class Solution1 implements Solution {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new LinkedList<>();
        int firstGreater = firstGreater(arr, x);
        int left = firstGreater - 1, right = firstGreater;
        int leftDiff = (left >= 0)? x - arr[left] : 20001;
        int rightDiff = (right < arr.length)? arr[right] - x : 20001;
        while (k > 0) {
            if (leftDiff <= rightDiff) {
                if (log.isDebugEnabled()) {
                    log.debug("list add {}, left move to {}", arr[left], left - 1);
                }
                res.add(0, arr[left--]);
                leftDiff = (left >= 0)? x - arr[left] : 20001;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("list add {}, right move to {}", arr[right], right + 1);
                }
                res.add(arr[right++]);
                rightDiff = (right < arr.length)? arr[right] - x : 20001;
            }
            k--;
        }
        return res;
    }

    /** find the position of first element greater than given x */
    private int firstGreater(int[] arr, int x) {
        int size = arr.length;
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= x) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

}
