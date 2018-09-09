/**
 * Leetcode - #368 - Largest Divisable Subset
 */
package com.ciaoshen.leetcode.largest_divisable_subset;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution2 implements Solution {
    // assert: all numbers are positive
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) { 
            return new ArrayList<Integer>(); 
        }
        Arrays.sort(nums);
        sorted = nums;
        dpTab = new HashMap<Integer, List<Integer>>();
        List<Integer> res = null;
        for (int i = nums.length - 1, max = 0, size = 0; i >= 0; i--) {
            if ((size = dp(i)) > max) {
                max = size;
                res = dpTab.get(i);
            }
        }
        return res;
    }

    /**==================== 【私有】 ======================= */
    private int[] sorted;
    private Map<Integer, List<Integer>> dpTab;

    private int dp(int start) {
        List<Integer> longest = new ArrayList<>();
        int max = 0;
        for (int i = start + 1; i < sorted.length; i++) {
            if (sorted[i] % sorted[start] == 0) { 
                List<Integer> sub = dpTab.get(i); 
                if (sub.size() > max) {
                    longest = sub;
                    max = sub.size();
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        res.add(sorted[start]);
        res.addAll(longest);
        dpTab.put(start, res);
        return max + 1;
    }
}