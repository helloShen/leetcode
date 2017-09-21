/**
 * Leetcode - Algorithm - FindDuplicate
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FindDuplicate implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FindDuplicate() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int findDuplicate(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 先排序 O(nlogn) */
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int findDuplicate(int[] nums) {
            if (nums.length == 0) { return 0; }
            Arrays.sort(nums);
            int last = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == last) { return last; }
                last = nums[i];
            }
            return 0; // never reached
        }
    }

    /* 用容器 O(n) */
    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int findDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int i : nums) {
                if (!set.add(i)) { return i; }
            }
            return 0;
        }
    }

    /* 朴素遍历 */
    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int findDuplicate(int[] nums) {
            for (int i = 0; i < nums.length-1; i++) {
                int a = nums[i];
                for (int j = i+1; j < nums.length; j++) {
                    if (nums[j] == a) { return a; }
                }
            }
            return 0; // never reached
        }
    }
    // you can expand more solutions HERE if you want...

    /* 分治法 O(n^2) */
    private class Solution4 extends Solution {
        { super.id = 4; }
        // implement your solution's method HERE...
        public int findDuplicate(int[] nums) {
            if (nums.length == 0) { return 0; }
            return helper(nums,0,nums.length-1);
        }
        private int helper(int[] nums, int lo, int hi) {
            if (lo == hi) { return 0; }
            int mid = lo + (hi - lo) / 2;
            int left = helper(nums,lo,mid);
            if (left > 0) { return left; }
            int right = helper(nums,mid+1,hi);
            if (right > 0) { return right; }
            return merge(nums,lo,mid,mid+1,hi);
        }
        private int merge(int[] nums, int l1, int h1, int l2, int h2) {
            for (int i = l1; i <= h1; i++) {
                for (int j = l2; j <= h2; j++) {
                    if (nums[i] == nums[j]) { return nums[i]; }
                }
            }
            return 0;
        }
    }

    private class Solution5 extends Solution {
        { super.id = 5; }
        // implement your solution's method HERE...
        public int findDuplicate(int[] nums) {
            int n = nums.length-1;
            int lo = 1, hi = n;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int count = 0;
                for (int num : nums) {
                    if (num <= mid) { ++count; }
                }
                if (count > mid) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return lo;
        }
    }
    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private FindDuplicate problem = new FindDuplicate();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println(Arrays.toString(nums));
            System.out.println("Duplicate number is: " + solution.findDuplicate(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{};
            int[] nums2 = new int[]{3,1,2,4,5,8,6,7,9,4,15,12};
            int[] nums3 = new int[]{1,1};
            int[] nums4 = new int[]{1,2,1};
            int[] nums5 = new int[]{5,4,3,3,1,2};
            /** involk call() method HERE */
            call(nums1);
            call(nums2);
            call(nums3);
            call(nums4);
            call(nums5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        test.test(5);
    }
}
