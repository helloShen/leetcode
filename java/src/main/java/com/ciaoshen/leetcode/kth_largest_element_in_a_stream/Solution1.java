/**
 * Leetcode - kth_largest_element_in_a_stream
 */
package com.ciaoshen.leetcode.kth_largest_element_in_a_stream;
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

    private int k;
    private List<Integer> list;

    public void init(int k, int[] nums) {
        this.k = k - 1;
        list = new ArrayList<Integer>();
        for (int num : nums) list.add(num);
        Collections.sort(list, (Integer a, Integer b) ->  b - a);
    }

    public int add(int val) {
        list.add(indexOf(val), val);
        return list.get(k);
    }

    /**
     * @param  val find the position for that val
     * @return     the position to insert given val
     */
    private int indexOf(int val) {
        int lo = 0, hi = list.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int midNum = list.get(mid);
            if (val > midNum) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("List = {}", list);
            log.debug("index of [{}] is [{}]", val, lo);
        }
        return lo;
    }

}
