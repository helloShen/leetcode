/**
 * Leetcode - Algorithm - ThreeSumSmaller
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ThreeSumSmaller implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ThreeSumSmaller() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int threeSumSmaller(int[] nums, int target);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int threeSumSmaller(int[] nums, int target) {
            int count = 0;
            for (int i = 0; i < nums.length-2; i++) {
                for (int j = i+1; j < nums.length-1; j++) {
                    for (int k = j+1; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] < target) {
                            // System.out.println("[" + nums[i] + "," + nums[j] + "," + nums[k] + "]");
                            count++;
                        }
                    }
                }
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int threeSumSmaller(int[] nums, int target) {
            if (nums.length < 3) { return 0; }
            Arrays.sort(nums);
            int count = 0;
            for (int i = 0; i < nums.length-2; i++) {
                int left = i+1, right = nums.length-1;
                while (left < right) {
                    if (nums[i] + nums[left] + nums[right] < target) {
                        count += (right - left); left++;
                    } else {
                        right--;
                    }
                }
            }
            return count;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int threeSumSmaller(int[] nums, int target) {
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
        private ThreeSumSmaller problem = new ThreeSumSmaller();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums, int target, String answer) {
            System.out.println("Array: " + Arrays.toString(nums) + " has " + solution.threeSumSmaller(nums,target) + " triplets!       >>> [answer:" + answer +"]");
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[] nums1 = new int[]{-2, 0, 1, 3};
            int target1 = 2;

            call(nums1,target1,"2");
            // call(nums2,target2,"");
            // call(nums3,target3,"");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        // test.test(3);
    }
}
