/**
 * Leetcode - delete_and_earn
 */
package com.ciaoshen.leetcode.delete_and_earn;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution2 implements Solution {

    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) { return 0; }
        Arrays.sort(nums);
        List<Integer> key = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        for (int start = 0, end = start; start < nums.length; start = end) {
            while (end < nums.length && nums[end] == nums[start]) end++;
            key.add(nums[start]);
            value.add(nums[start] * (end - start));
        }
        int size = value.size();
        int[] dpTable = new int[size + 1];
        dpTable[size - 1] = value.get(size - 1);
        for (int i = size - 2; i >= 0; i--) {
            if (key.get(i + 1) - key.get(i) > 1) {
                dpTable[i] = value.get(i) + dpTable[i + 1];
            } else {
                int take = value.get(i) + dpTable[i + 2];
                int skip = dpTable[i + 1];
                dpTable[i] = Math.max(take, skip);
            }
        }
        return dpTable[0];
    }

}
