/**
 * Leetcode - Algorithm - MaximumProductOfThreeNumbers
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MaximumProductOfThreeNumbers implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MaximumProductOfThreeNumbers() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int maximumProduct(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int maximumProduct(int[] nums) {
            Arrays.sort(nums);
            return Math.max(nums[0]*nums[1]*nums[nums.length-1], nums[nums.length-3]*nums[nums.length-2]*nums[nums.length-1]);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int maximumProduct(int[] nums) {
            int[] min = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
            int[] max = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
            for (int num : nums) {
                if (num < min[1]) {
                    min[1] = num;
                    if (num < min[0]) { exch(min,0,1); }
                }
                if (num > max[2]) {
                    max[2] = num;
                    if (num > max[1]) { exch(max,1,2); }
                    if (num > max[0]) { exch(max,0,1); }
                }
            }
            return Math.max(min[0]*min[1]*max[0],max[2]*max[1]*max[0]);
        }
        public void exch(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int maximumProduct(int[] nums) {
            return 3;
        }
    }
    // you can expand more solutions HERE if you want...


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
        private MaximumProductOfThreeNumbers problem = new MaximumProductOfThreeNumbers();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, int ans) {
            System.out.println("Arrays = " + Arrays.toString(nums));
            System.out.println(solution.maximumProduct(nums) + "       [answer = " + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{1,-2,3,-4,5,-6};
            int ans1 = 120;
            int[] nums2 = new int[]{3,6,-2,4,-10,22,29,-38,12};
            int ans2 = 11020;
            /** involk call() method HERE */
            call(nums1,ans1);
            call(nums2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
