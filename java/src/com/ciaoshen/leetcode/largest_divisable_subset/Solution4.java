/**
 * Leetcode - #368 - Largest Divisable Subset
 */
package com.ciaoshen.leetcode.largest_divisable_subset;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution4 implements Solution {
    // assert: all numbers are positive
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        sorted = nums;
        System.out.println("Sorted:\t" + Arrays.toString(sorted));
        maxNum = nums[nums.length-1];       // 全局最大元素
        len = new int[nums.length];         // 以每个节点为头节点的链长度
        next = new int[nums.length];        // 指向全局最优子问题
        longestChainHead = -1;              // 标记当前最长链头元素下标
        maxLen = 0;                         // 当前最长链的长度
        int limit = maxNum;                 // 当前还可能最长链头节点的最大可能值
        for (int i = 0; i < nums.length && nums[i] <= limit; i++) {
            dp(i, 0);
            limit = maxNum >> maxLen;
        }
        while (longestChainHead != -1) {
            res.add(nums[longestChainHead]);
            longestChainHead = next[longestChainHead];
        }
        System.out.println("len[] = " + Arrays.toString(len));
        System.out.println("pre[] = " + Arrays.toString(next));
        return res;
    }
    
    private int[] sorted;
    private int[] len;
    private int[] next;
    private int longestChainHead;
    private int maxLen;
    private int maxNum;

    private void dp(int start, int preLen) {
        if (len[start] == 0) { 
            len[start] = 1;
            next[start] = -1;
        }
        if (len[start] > maxLen) {
            maxLen = len[start];
            longestChainHead = start;
        }
        int limit = maxNum >> Math.max((maxLen - preLen - 1), 0);
        System.out.println("start = " + start + ", maxLen = " + maxLen + ", preLen = " + preLen + ", limit = " + limit);
        int max = 0;
        for (int i = start + 1; i < sorted.length && sorted[i] <= limit; i++) {
            if (sorted[i] % sorted[start] == 0) {
                if (len[i] == 0) {
                    dp(i, preLen + 1);
                } 
                if (len[i] > max) {
                    max = len[i];
                    len[start] = len[i] + 1;
                    next[start] = i;
                    System.out.println("next[" + start + "] = " + next[start]);
                    if (len[start] > maxLen) {
                        maxLen = len[start];
                        longestChainHead = start;
                        limit = maxNum >> Math.max((maxLen - preLen - 1), 0);
                    }           
                }
            }
        }
    }
}