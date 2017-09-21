/**
 * Leetcode - Algorithm - FindAllNumbersDisappearedInAnArray
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FindAllNumbersDisappearedInAnArray implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FindAllNumbersDisappearedInAnArray() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<Integer> findDisappearedNumbers(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        private int[] array = new int[0];
        public List<Integer> findDisappearedNumbers(int[] nums) {
            array = nums;
            for (int i = 0; i < array.length; i++) {
                int n = array[i];
                if (n > 0) { check(n-1); }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                int n = array[i];
                if (n > 0) { res.add(i+1); }
            }
            System.out.println(Arrays.toString(array));
            return res;
        }
        private void check(int i) {
            int n = array[i];
            if (n < 1) { return; }
            array[i] = 0;
            check(n-1);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public List<Integer> findDisappearedNumbers(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int offset = nums[i]-1;
                while (offset >= 0) {
                    int newOffset = nums[offset]-1;
                    nums[offset] = 0;
                    offset = newOffset;
                }
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                if (n > 0) { res.add(i+1); }
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public List<Integer> findDisappearedNumbers(int[] nums) {
            return null;
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
        private FindAllNumbersDisappearedInAnArray problem = new FindAllNumbersDisappearedInAnArray();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println("Array: " + Arrays.toString(nums));
            System.out.println("Missing numbers: " + solution.findDisappearedNumbers(nums) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums0 = new int[]{};
            int[] nums1 = new int[]{4,3,2,7,8,2,3,1};

            /** involk call() method HERE */
            call(nums0);
            call(nums1);
            // call();
            // call();
            // call();
            // call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
