/**
 * Leetcode - Algorithm - SingleElementInSortedArray
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SingleElementInSortedArray implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SingleElementInSortedArray() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int singleNonDuplicate(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* XOR异或黑魔法 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int singleNonDuplicate(int[] nums) {
            int res = 0;
            for (int n : nums) {
                res ^= n;
            }
            return res;
        }
    }

    /* 标准用Set */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int singleNonDuplicate(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int n : nums) {
                if (!set.add(n)) { set.remove(n); }
            }
            if (set.size() != 1) { return 0; }
            Iterator<Integer> ite = set.iterator();
            return ite.next();
        }
    }
    /* 利用Sort过这个条件，只和前一个元素比较 */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public int singleNonDuplicate(int[] nums) {
            if (nums.length == 0) { return 0; }
            int cur = 1;
            while (cur < nums.length) {
                if (nums[cur-1] != nums[cur]) { return nums[cur-1]; }
                cur += 2;
            }
            return nums[cur-1];
        }
    }
    // you can expand more solutions HERE if you want...

    private class Solution4 extends Solution {
        { super.id = 4; }

        public int singleNonDuplicate(int[] nums) {
            int lo = 0, hi = nums.length-1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                int even = (mid % 2 == 0)? nums[mid] : nums[mid-1];
                int followingOdd = (mid % 2 == 0)? nums[mid+1] : nums[mid];
                if (even == followingOdd) {
                    lo = mid + 1;
                } else {
                    hi = (mid / 2) * 2;
                }
            }
            return nums[lo];
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
        private SingleElementInSortedArray problem = new SingleElementInSortedArray();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println("Single element in array: " + Arrays.toString(nums) + " is: " + solution.singleNonDuplicate(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1,1,2,3,3,4,4,8,8};
            int[] nums2 = new int[]{3,3,7,7,10,11,11};

            /** involk call() method HERE */
            call(nums1);
            call(nums2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.test(4);
    }
}
