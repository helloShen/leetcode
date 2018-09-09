/**
 * Leetcode - Algorithm - MovingAverage
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MovingAverage implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MovingAverage() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        protected abstract void init(int size);
        protected abstract double next(int val);
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        int size = 0;
        int[] windows = new int[0];
        double sum = 0.0;
        int next = 0;

        public void init(int size) {
            this.size = size;
            windows = new int[size];
            sum = 0;
            next = 0;
        }
        public double next(int val) {
            int offset = next % size;
            sum -= windows[offset];
            sum += val;
            windows[offset] = val;
            next++;
            return (next > size)? (sum / size) : (sum / next);
        }

    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public void init(int size) {

        }
        public double next(int val) {
            return 0.0;
        }

    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void init(int size) {

        }
        public double next(int val) {
            return 0.0;
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
        private MovingAverage problem = new MovingAverage();
        private Solution solution = null;

        // call method in solution
        private void call(int size, int[] nums, double[] ans) {
            solution.init(size);
            System.out.println("Nums = " + Arrays.toString(nums));
            System.out.print("[");
            for (int i = 0; i < nums.length; i++) {
                System.out.print(String.format("%5.1f",solution.next(nums[i])));
                if (i < (nums.length - 1)) { System.out.print(", "); }
            }
            System.out.print("]\n");
            System.out.println("Answer should be : " + Arrays.toString(ans));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int size1 = 3;
            int[] nums1 = new int[]{1,10,3,5};
            double[] ans1 = new double[]{1.0, 5.5, 4.7, 6.0};

            /** involk call() method HERE */
            call(size1, nums1, ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
