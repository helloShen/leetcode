/**
 * Leetcode - Algorithm - SuperPow
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class SuperPow implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private SuperPow() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int superPow(int a, int[] b); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int superPow(int a, int[] b) {
            int res = 1;
            int base = a % 1337;
            for (int i = b.length - 1; i >= 0; i--) {
                int powerTen = 1;
                if (i == (b.length - 1)) {
                    powerTen = base;
                } else {
                    for (int j = 0; j < 10; j++) {
                        powerTen = (powerTen * base) % 1337;
                    }
                }
                int local = 1;
                for (int j = b[i]; j > 0; j--) {
                    local = (local * powerTen) % 1337;
                }
                res = (res * local) % 1337;
                base = powerTen;
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int superPow(int a, int[] b) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int superPow(int a, int[] b) {
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
        private SuperPow problem = new SuperPow();
        private Solution solution = null;

        // call method in solution
        private void call(int a, int[] b, int ans) {
            System.out.println("a = " + a);
            System.out.println("b = " + Arrays.toString(b));
            System.out.println("Result = " + solution.superPow(a,b) + "\t [answer:" + ans + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int a1 = 2;
            int[] b1 = new int[]{3};
            int ans1 = 8;

            int a2 = 2;
            int[] b2 = new int[]{1,0};
            int ans2 = 1024;

            /** involk call() method HERE */
            call(a1,b1,ans1);
            call(a2,b2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
