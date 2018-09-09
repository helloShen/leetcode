/**
 * Leetcode - Algorithm - Majority Element Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class MajorityElementTwo {
    /**
     * 使用额外Map记录出现数字的频率
     * 计算复杂度：O(n)，空间复杂度O(n)
     */
    public class SolutionV1 {
        public List<Integer> majorityElement(int[] nums) {
            Map<Integer,Integer> map = new HashMap<>();
            for (Integer num : nums) {
                Integer freq = map.get(num);
                freq = (freq == null)? 1 : freq+1;
                map.put(num,freq);
            }
            List<Integer> result = new ArrayList<>();
            Integer target = nums.length / 3;
            for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
                Integer freq = entry.getValue();
                if (freq > target) {
                    result.add(entry.getKey());
                }
            }
            return result;
        }
    }
    /**
     * Boyer-Moore Majority Vote algorithm
     * 出现超过n/3次的数字，最多只能有2个。所以维护一个大小为2的bag。叫 "K reduced bag"
     * 按照下面这个过程消掉候选元素，再插入新候选元素，
     * 出现超过n/3次的数字，一定就在最后留下的两个数字中间。
     * 有可能，留下两个一个都不是，可能有一个是，也可能两个都是。
     */
    public class Solution {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> result = new ArrayList<>();
            if (nums.length == 0) { return result; }
            int num1 = nums[0], num2 = nums[0], count1 = 0, count2 = 0;
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (num == num1) {
                    count1++;
                } else if (num == num2) {
                    count2++;
                } else if (count1 == 0) {
                    num1 = num; count1 = 1;
                } else if (count2 == 0) {
                    num2 = num; count2 = 1;
                } else {
                    count1--; count2--;
                }
            }
            count1 = 0; count2 = 0;
            for (int num : nums) {
                if (num == num1) { count1++; continue; }
                if (num == num2) { count2++; }
            }
            int target = nums.length / 3;
            if (count1 > target) { result.add(num1); }
            if (count2 > target) { result.add(num2); }
            return result;
        }
    }
    private class Test {
        private void callMajorityElement(int[] nums, String answer) {
            System.out.println("Majority Elements in array " + Arrays.toString(nums) + " are: " + solution.majorityElement(nums) + "        [answer = " + answer + "]");
        }
        private void test() {
            int[] nums1 = new int[]{};
            int[] nums2 = new int[]{3,4,3,5,3,1,2,5,4,3,3,1,7};
            // int[] nums3 = new int[]{};
            callMajorityElement(nums1,"");
            callMajorityElement(nums2,"3");
            // callMajorityElement(nums3,"");
        }
    }
    private static MajorityElementTwo problem = new MajorityElementTwo();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
