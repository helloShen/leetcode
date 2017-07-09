/**
 * Leetcode - Algorithm - Single Number
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SingleNumber {
    /**
     * 时间复杂度:O(n)
     * 空间复杂度 O(n)
     */
    public class SolutionV1 {
        public int singleNumber(int[] nums) {
            Set<Integer> memo = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (!memo.add(nums[i])) { memo.remove(nums[i]); }
            }
            Iterator<Integer> ite = memo.iterator();
            return ite.next();
        }
    }
    /**
     * 时间复杂度:O(n)
     * 空间复杂度 O(n)
     */
    public class SolutionV2 {
        public int singleNumber(int[] nums) {
            Map<Integer,Integer> memo = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (memo.get(nums[i]) == null) {
                    memo.put(nums[i],0);
                } else {
                    memo.remove(nums[i]);
                }
            }
            int res = 0;
            for (Map.Entry<Integer,Integer> entry : memo.entrySet()) {
                res = entry.getKey();
            }
            return res;
        }
    }
    /**
     * 时间复杂度:O(n^2)
     * 空间复杂度 O(0)
     */
    public class SolutionV3 {
        public int singleNumber(int[] nums) {
            if (nums.length == 1) { return nums[0]; }
            int slow = 1;
            whileLoop:
            while (slow < nums.length) {
                for (int fast = slow; fast < nums.length; fast++) { // 遍历配对
                    if (nums[fast] == nums[slow-1]) {
                        int temp = nums[slow];
                        nums[slow] = nums[fast];
                        nums[fast] = temp;
                        slow+=2;
                        continue whileLoop;
                    }
                }
                return nums[slow-1]; // 配对失败
            }
            if (slow == nums.length) { return nums[nums.length-1]; }
            return -1; // 没找到
        }
    }
    /**
     * 时间复杂度:O(n)
     * 空间复杂度 O(0)
     */
    public class Solution {
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res ^= nums[i];
            }
            return res;
        }
    }
    private static SingleNumber test = new SingleNumber();
    private static void testSingleNumber() {
        Solution solution = test.new Solution();
        int[] nums = new int[]{1,1,2,3,3,};
        System.out.println(Arrays.toString(nums) + " >>> Single Number = " + solution.singleNumber(nums));
    }
    public static void main(String[] args) {
        testSingleNumber();
    }
}
