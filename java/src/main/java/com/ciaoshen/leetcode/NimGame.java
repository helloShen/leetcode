/**
 * Leetcode - Algorithm - NimGame
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class NimGame implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private NimGame() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean canWinNim(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean canWinNim(int n) {
            if (n <= 0) { return false; }
            return n % 4 > 0;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean canWinNim(int n) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean canWinNim(int n) {
            return false;
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
        private NimGame problem = new NimGame();
        private Solution solution = null;

        // call method in solution
        private void call(int n, String answer) {
            System.out.println("Given " + n + " stones, can win? " + solution.canWinNim(n) + "      [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            // call method
            for (int i = 1; i < 30; i++) {
                call(i,"?");
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
