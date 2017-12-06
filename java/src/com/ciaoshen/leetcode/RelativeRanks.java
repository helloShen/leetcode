/**
 * Leetcode - Algorithm - RelativeRanks
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class RelativeRanks implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private RelativeRanks() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String[] findRelativeRanks(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public String[] findRelativeRanks(int[] nums) {
            int len = nums.length;
            int[] copy = Arrays.copyOf(nums,len);
            Arrays.sort(copy);
            String[] res = new String[len];
            for (int i = 0; i < len; i++) {
                int index = Arrays.binarySearch(copy,nums[i]);
                if (index == (len - 1)) {
                    res[i] = "Gold Medal";
                } else if (index == (len - 2)) {
                    res[i] = "Silver Medal";
                } else if (index == (len - 3)) {
                    res[i] = "Bronze Medal";
                } else {
                    res[i] = String.valueOf(len - index);
                }
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public String[] findRelativeRanks(int[] nums) {
            return new String[0];
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public String[] findRelativeRanks(int[] nums) {
            return new String[0];
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
        private RelativeRanks problem = new RelativeRanks();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, String[] ans) {
            System.out.println("Nums = " + Arrays.toString(nums));
            System.out.println("Ranks = " + Arrays.toString(solution.findRelativeRanks(nums)));
            System.out.println("Answer = " + Arrays.toString(ans) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{5,4,3,2,1};
            String[] ans1 = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"};

            /** involk call() method HERE */
            call(nums1,ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
