/**
 * Leetcode - Algorithm - Largest Number
 */
package com.ciaoshen.leetcode;
import java.util.*;

class LargestNumber {
    /**
     * 自己写merge sort
     */
    public class SolutionV1 {
        public String largestNumber(int[] nums) {
            int[][] memo = new int[nums.length][nums.length];
            for (int i = 0; i < memo.length; i++) {
                Arrays.fill(memo[i],-2);
            }
            sort(nums,0,nums.length-1,memo);
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                sb.append(String.valueOf(num));
            }
            String ret = sb.toString();
            int cur = 0;
            while (cur < ret.length() && ret.charAt(cur) == '0') { cur++; }
            ret = ret.substring(cur);
            return (ret.isEmpty())? "0" : ret;
        }
        public void sort(int[] nums, int lo, int hi, int[][] memo) {
            if (lo == hi) { return; }
            int mid = lo + (hi - lo) / 2;
            sort(nums,lo,mid,memo);
            sort(nums,mid+1,hi,memo);
            merge(nums,lo,mid,mid+1,hi,memo);
        }
        public void merge(int[] nums, int lo1, int hi1, int lo2, int hi2, int[][] memo) {
            int[] temp = new int[hi2-lo1+1];
            int cur = 0, cur1 = lo1, cur2 = lo2;
            while (cur1 <= hi1 && cur2 <= hi2) {
                int ret = compare(nums,cur1,cur2,memo);
                temp[cur++] = (ret >= 0)? nums[cur1++] : nums[cur2++];
            }
            while (cur1 <= hi1) { temp[cur++] = nums[cur1++]; }
            while (cur2 <= hi2) { temp[cur++] = nums[cur2++]; }
            for (int i = 0; i < temp.length; i++) {
                nums[lo1+i] = temp[i];
            }
        }
        /*
         * return 1,-1,or 0
         */
        public int compare(int[] nums, int pos1, int pos2, int[][] memo) {
            if (memo[pos1][pos2] != -2) { return memo[pos1][pos2]; }
            String s1 = String.valueOf(nums[pos1]);
            String s2 = String.valueOf(nums[pos2]);
            String sum1 = s1 + s2;
            String sum2 = s2 + s1;
            for (int i = 0; i < sum1.length(); i++) {
                char c1 = sum1.charAt(i);
                char c2 = sum2.charAt(i);
                if (c1 > c2) {
                    memo[pos1][pos2] = 1;
                    return 1;
                } else if (c1 < c2){
                    memo[pos1][pos2] = -1;
                    return -1;
                }
            }
            memo[pos1][pos2] = 0;
            return 0;
        }
    }
    /**
     * 稍微改进Version 1.
     */
    public class SolutionV2 {
        public String largestNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }
            int[][] memo = new int[nums.length][nums.length];
            sort(strs,0,strs.length-1,memo);
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str);
            }
            return trimLeadingZero(sb.toString());
        }

        public void sort(String[] nums, int lo, int hi, int[][] memo) {
            if (lo == hi) { return; }
            int mid = lo + (hi - lo) / 2;
            sort(nums,lo,mid,memo);
            sort(nums,mid+1,hi,memo);
            merge(nums,lo,mid,mid+1,hi,memo);
        }
        public void merge(String[] nums, int lo1, int hi1, int lo2, int hi2, int[][] memo) {
            String[] temp = new String[hi2-lo1+1];
            int cur = 0, cur1 = lo1, cur2 = lo2;
            while (cur1 <= hi1 && cur2 <= hi2) {
                int ret = compare(nums,cur1,cur2,memo);
                temp[cur++] = (ret >= 2)? nums[cur1++] : nums[cur2++];
            }
            while (cur1 <= hi1) { temp[cur++] = nums[cur1++]; }
            while (cur2 <= hi2) { temp[cur++] = nums[cur2++]; }
            for (int i = 0; i < temp.length; i++) {
                nums[lo1+i] = temp[i];
            }
        }
        /*
         * return:
         *  1: num1 < num2
         *  2: num1 == num2
         *  3: num1 > num2
         */
        public int compare(String[] nums, int pos1, int pos2, int[][] memo) {
            if (memo[pos1][pos2] > 0) { return memo[pos1][pos2]; }
            String sum1 = nums[pos1] + nums[pos2];
            String sum2 = nums[pos2] + nums[pos1];
            for (int i = 0; i < sum1.length(); i++) {
                char c1 = sum1.charAt(i);
                char c2 = sum2.charAt(i);
                if (c1 > c2) {
                    memo[pos1][pos2] = 3;
                    return 3;
                } else if (c1 < c2){
                    memo[pos1][pos2] = 1;
                    return 1;
                }
            }
            memo[pos1][pos2] = 2;
            return 2;
        }
        public String trimLeadingZero(String s) {
            int cur = 0;
            while (cur < s.length() && s.charAt(cur) == '0') { cur++; }
            s = s.substring(cur);
            return (s.isEmpty())? "0" : s;
        }
    }
    /**
     * 用标准库自带的Arrays.sort()
     */
    public class Solution {
        public String largestNumber(int[] nums) {
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }
            int[][] memo = new int[nums.length][nums.length];
            Arrays.sort(strs, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    String sum1 = s1 + s2;
                    String sum2 = s2 + s1;
                    return sum2.compareTo(sum1);
                }
            });
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str);
            }
            return trimLeadingZero(sb.toString());
        }
        public String trimLeadingZero(String s) {
            int cur = 0;
            while (cur < s.length() && s.charAt(cur) == '0') { cur++; }
            s = s.substring(cur);
            return (s.isEmpty())? "0" : s;
        }
    }
    private static LargestNumber test = new LargestNumber();
    private static Solution solution = test.new Solution();
    private static void callLargestNumber(int[] nums, String ans) {
        System.out.println("For array " + Arrays.toString(nums) + ", the largest number is: " + solution.largestNumber(nums) + "    (answer = " + ans + ")");
    }
    private static void test() {
        int[] nums1 = new int[]{3,30,2,23,34,5,9};
        int[] nums2 = new int[]{3,34,343,344,322,3224};
        int[] nums3 = new int[]{32,3232};
        callLargestNumber(nums1,"9534330232");
        callLargestNumber(nums2,"3443434333224322");
        callLargestNumber(nums3,"323232");
    }
    public static void main(String[] args) {
        test();
    }
}
