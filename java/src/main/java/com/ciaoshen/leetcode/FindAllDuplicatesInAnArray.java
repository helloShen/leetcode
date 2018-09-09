/**
 * Leetcode - Algorithm - FindAllDuplicatesInAnArray
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FindAllDuplicatesInAnArray implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FindAllDuplicatesInAnArray() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public List<Integer> findDuplicates(int[] nums); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public List<Integer> findDuplicates(int[] nums) {
            Set<Integer> set = new HashSet<>();
            List<Integer> res = new ArrayList<>();
            for (int num : nums) {
                if (!set.add(num)) { res.add(num); }
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> res = new ArrayList<>();
            for (int num : nums) {
                int offset = Math.abs(num) - 1;
                if (nums[offset] < 0) { res.add(offset+1); }
                nums[offset] = -nums[offset];
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public List<Integer> findDuplicates(int[] nums) {
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
        private FindAllDuplicatesInAnArray problem = new FindAllDuplicatesInAnArray();
        private Solution solution = null;

        // call method in solution
        private void call(int[] nums) {
            System.out.println("Original numbers: " + Arrays.toString(nums));
            System.out.println("Duplicates: " + solution.findDuplicates(nums));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] nums1 = new int[]{4,3,2,7,8,2,3,1};

            /** involk call() method HERE */
            call(nums1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
