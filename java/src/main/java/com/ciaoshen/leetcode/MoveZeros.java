/**
 * Leetcode - Algorithm - MoveZeros
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MoveZeros implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MoveZeros() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void moveZeroes(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public void moveZeroes(int[] nums) {
            int high = nums.length - 1;
            while ( high >= 0 && nums[high] == 0) { --high; } // high points to the last non zero number
            int low = high - 1;
            while (low >= 0) {
                if (nums[low] == 0) {
                    int cur = low;
                    while (cur < high) {
                        int temp = nums[cur];
                        nums[cur] = nums[cur+1];
                        nums[cur+1] = temp;
                        ++cur;
                    }
                    --high;
                }
                --low;
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public void moveZeroes(int[] nums) {
            int firstZero = 0;
            while (firstZero < nums.length && nums[firstZero] != 0) { ++firstZero; } // firstZero points to the first zero in array
            int cur = firstZero + 1;
            while (cur < nums.length) {
                if (nums[cur] != 0) { nums[firstZero++] = nums[cur]; } // don't need to update nums[cur]
                ++cur;
            }
            while (firstZero < nums.length) { nums[firstZero++] = 0; } // the rest are all zero
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void moveZeroes(int[] nums) {
            return;
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
        private MoveZeros problem = new MoveZeros();
        private Solution solution = null;

        private void call(int[] nums) {
            System.out.println("Before: " + Arrays.toString(nums));
            solution.moveZeroes(nums);
            System.out.println("After: " + Arrays.toString(nums) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[] nums0 = new int[0];
            int[] nums1 = new int[]{0};
            int[] nums2 = new int[]{1};
            int[] nums3 = new int[]{0,1,0,3,12};

            call(nums0);
            call(nums1);
            call(nums2);
            call(nums3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        // test.test(3);
    }
}
