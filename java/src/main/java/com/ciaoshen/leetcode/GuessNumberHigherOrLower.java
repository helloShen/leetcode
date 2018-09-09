/**
 * Leetcode - Algorithm - GuessNumberHigherOrLower
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class GuessNumberHigherOrLower implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private GuessNumberHigherOrLower() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        protected int answer = 0;
        protected Random r = new Random();
        abstract public int guessNumber(int n); // 主方法接口
        protected void randomNumber(int max) {
            answer = r.nextInt(max) + 1;
        }
        protected int guess(int n) {
            if (n < answer) {
                System.out.println(n + " too big!");
                return 1;
            } else if (n > answer) {
                System.out.println(n + " too small!");
                return -1;
            } else {
                System.out.println(n + " Bingo!");
                return 0;
            }
        }
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        public int guessNumber(int n) {
            return helper(1,n);
        }
        private int helper(int lo, int hi) {
            if (lo >= hi) { return lo; }
            int mid = lo + (hi - lo) / 2;
            int answer = guess(mid);
            switch (answer) {
                case 1: return helper(mid+1,hi);      // mid too small
                case -1: return helper(lo,mid-1);     // mid too big
                case 0: return mid;                   // BINGO
                default: return 0;
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        public int guessNumber(int n) {
            return 0;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        public int guessNumber(int n) {
            return 0;
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
        private GuessNumberHigherOrLower problem = new GuessNumberHigherOrLower();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            solution.randomNumber(n);
            System.out.println("From 1 to " + n + ", number is: " + solution.guessNumber(n) + "   [answer=" + solution.answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            for (int i = 1; i < 10000; i *= 10) {
                call(i);
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
