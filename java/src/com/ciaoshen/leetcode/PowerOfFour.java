/**
 * Leetcode - Algorithm - PowerOfFour
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PowerOfFour implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PowerOfFour() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isPowerOfFour(int num); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean isPowerOfFour(int num) {
            if (num <= 0) { return false; }
            int mask = 1;
            do {
                if (mask == num) {
                    System.out.println(num);
                    return true;
                } else {
                    mask <<= 2;
                }
            } while (mask > 0 && mask <= num);
            return false;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        private int[] powerOfFour = new int[]{
            1,
            4,
            16,
            64,
            256,
            1024,
            4096,
            16384,
            65536,
            262144,
            1048576,
            4194304,
            16777216,
            67108864,
            268435456,
            1073741824
        };
        public boolean isPowerOfFour(int num) {
            return Arrays.binarySearch(powerOfFour,num) >= 0;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean isPowerOfFour(int num) {
            return (num > 0 && ((num & (num - 1)) == 0) && (num & 0x55555555) != 0);
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
        private PowerOfFour problem = new PowerOfFour();
        private Solution solution = null;

        // call method in solution
        private void call(int num) {
            System.out.println(num + (solution.isPowerOfFour(num)? " IS " : " IS-NOT") + " power of four!");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */

            /** involk call() method HERE */
            for (int i = 1; i <= Integer.MAX_VALUE; i++) {
                solution.isPowerOfFour(i);
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
