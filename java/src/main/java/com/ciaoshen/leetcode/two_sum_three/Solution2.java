/**
 * Leetcode - two_sum_three
 */
package com.ciaoshen.leetcode.two_sum_three;
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

    private int min, max;
    private List<Integer> nums;
    private Map<Integer, Integer> table;

    public void init() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        nums = new ArrayList<Integer>();
        table = new HashMap<Integer, Integer>();
    }

    public void add(int number) {
        min = Math.min(min, number);
        max = Math.max(max, number);
        nums.add(number);
        Integer prevTimes = table.get(number);
        table.put(number, (prevTimes == null)? 1 : prevTimes + 1);
    }

    public boolean find(int value) {
        if (value < min * 2 || value > max * 2) return false;
        for (int n : nums) {
            int target = value - n;
            if (target == n) {
                if (table.containsKey(target) && table.get(target) > 1) return true;
            } else {
                if (table.containsKey(target)) return true;
            }
        }
        return false;
    }

}
