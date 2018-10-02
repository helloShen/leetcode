/**
 * Leetcode - find_pivot_index
 */
package com.ciaoshen.leetcode.find_pivot_index;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution2 implements Solution {

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (left == (sum - left - nums[i])) return i;
            left += nums[i];
        }
        return -1;
    }

}
