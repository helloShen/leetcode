/**
 * Leetcode - find_pivot_index
 */
package com.ciaoshen.leetcode.find_pivot_index;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int pivotIndex(int[] nums) {
        if (nums.length == 0) return -1;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        if (nums[nums.length - 1] - nums[0] == 0) return 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == (nums[nums.length - 1] - nums[i])) return i;
        }
        return -1;
    }

}
