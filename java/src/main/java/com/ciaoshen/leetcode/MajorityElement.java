/**
 * Leetcode - Algorithm - Majority Element
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MajorityElement {
    /**
     * 普通使用HashMap记录频率方法
     */
    public class SolutionV1 {
        public int majorityElement(int[] nums) {
            Map<Integer,Integer> freq = new HashMap<>();
            for (int num : nums) {
                Integer times = freq.get(num);
                if (times == null) {
                    freq.put(num,1);
                } else {
                    freq.put(num,times+1);
                }
            }
            for (Map.Entry<Integer,Integer> entry : freq.entrySet()) {
                if (entry.getValue() > (nums.length / 2)) {
                    return entry.getKey();
                }
            }
            return 0;
        }
    }
    /**
     * Boyer Moore Majority Vote Algorithm
     */
    public class SolutionV2 {
        public int majorityElement(int[] nums) {
            int major = nums[0], count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (count == 0) { // suggest new major
                    major = nums[i];
                    count++;
                } else { // vote for the current major
                    count = (major == nums[i])? count+1 : count-1;
                }
            }
            return major;
        }
    }
    /**
     * Use the sort() method
     */
    public class SolutionV3 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[(nums.length-1)/2];
        }
    }
    /**
     * Bit manipulation
     */
    public class SolutionV4 {
        public int majorityElement(int[] nums) {
            int[] bits = new int[32];
            for (int num : nums) {
                for (int i = 31; i >= 0; i--) {
                    if ((num & 1) == 1) { bits[i]++; }
                    num = num >> 1;
                }
            }
            int ret = 0;
            int half = (nums.length - 1)/ 2;
            for (int i = 0; i < 32; i++) {
                if (bits[31-i] > half) { ret += (1 << i); }
            }
            return ret;
        }
    }
    /**
     * Bit manipulation - Version 2
     */
    public class Solution {
        public int majorityElement(int[] nums) {
            int[] bits = new int[32];
            for (int num : nums) {
                for (int i = 0; i < 32; i++) {
                    bits[i] += (num & 1);
                    num = num >> 1;
                }
            }
            int ret = 0;
            int half = (nums.length - 1)/ 2;
            for (int i = 0; i < 32; i++) {
                if (bits[i] > half) { ret += (1 << i); }
            }
            return ret;
        }
    }
    private static MajorityElement test = new MajorityElement();
    private static Solution solution = test.new Solution();
    private static void callMajorityElement(int[] nums, int answer) {
        System.out.println("The majority element in array " + Arrays.toString(nums) + " is: " + solution.majorityElement(nums) + "  answer=[" + answer + "]");
    }
    private static void test() {
        int[] nums1 = new int[]{1,2,1,3,1,4,1};
        int[] nums2 = new int[]{1,2,2,2,2,4,5};
        int[] nums3 = new int[]{1,2};
        callMajorityElement(nums1,1);
        callMajorityElement(nums2,0);
        callMajorityElement(nums3,0);
    }
    public static void main(String[] args) {
        test();
    }
}
