/**
 * Leetcode - random_pick_index
 */
package com.ciaoshen.leetcode.random_pick_index;
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

    private int[] nums;
    private Random r;

    public void init(int[] nums) {
        this.nums = nums;
        r = new Random();
    }

    public int pick(int target) {
        int res = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (r.nextInt(++count) == count - 1) res = i;
            }
        }
        return res;
    }

}
