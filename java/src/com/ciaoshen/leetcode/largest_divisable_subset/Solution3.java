/**
 * Leetcode - #368 - Largest Divisable Subset
 */
package com.ciaoshen.leetcode.largest_divisable_subset;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution3 implements Solution {
    // assert: all numbers are positive
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int[] len = new int[nums.length];
        int[] pre = new int[nums.length];
        int globalMaxIndex = nums.length - 1; // 标记最长链的下标
        for (int i = nums.length - 1; i >= 0; i--) {
            int maxLen = 0;
            pre[i] = -1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0) {
                    if (len[j] > maxLen) {
                        maxLen = len[j];
                        pre[i] = j;
                    }
                }
            }
            len[i] = maxLen + 1;
            if (len[i] > len[globalMaxIndex]) {
                globalMaxIndex = i;
            }
        }
        while (globalMaxIndex != -1) {
            res.add(nums[globalMaxIndex]);
            globalMaxIndex = pre[globalMaxIndex];
        }
        return res;
    }
}