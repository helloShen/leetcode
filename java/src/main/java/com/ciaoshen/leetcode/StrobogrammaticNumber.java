/**
 * Leetcode - Algorithm - TrobogrammaticNumber
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class StrobogrammaticNumber implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private StrobogrammaticNumber() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isStrobogrammatic(String num);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public boolean isStrobogrammatic(String num) {
            int len = num.length();
            if (len == 0) { return false; }
            if (len == 1) {
                char c = num.charAt(0);
                return (c == '0') || (c == '1') || (c == '8');
            }
            int half = (len - 1) / 2;
            for (int i = 0, j = len-1; i <= half; i++,j--) {
                char l = num.charAt(i);
                char r = num.charAt(j);
                if (
                    !(l == '0' && r == '0') &&
                    !(l == '1' && r == '1') &&
                    !(l == '8' && r == '8') &&
                    !(l == '6' && r == '9') &&
                    !(l == '9' && r == '6')
                ) { return false; }
            }
            return true;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public boolean isStrobogrammatic(String num) {
            int len = num.length();
            if (len == 0) { return false; }
            int lo = 0, hi = num.length()-1;
            while (lo <= hi) {
                char l = num.charAt(lo++);
                char r = num.charAt(hi--);
                if (
                    !(l == '0' && r == '0') &&
                    !(l == '1' && r == '1') &&
                    !(l == '8' && r == '8') &&
                    !(l == '6' && r == '9') &&
                    !(l == '9' && r == '6')
                ) { return false; }
            }
            return true;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean isStrobogrammatic(String num) {
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
        private StrobogrammaticNumber problem = new StrobogrammaticNumber();
        private Solution solution = null;

        // call method in solution
        private void call(Solution s, String num , String answer) {
            System.out.println(num + " is Strobogrammtic Number? " + s.isStrobogrammatic(num) + "       [answer: " + answer + "]");
        }
        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            // initialize your testcases HERE...
            String num1 = "123";
            String answer1 = "false";
            String num2 = "101";
            String answer2 = "true";
            String num3 = "111";
            String answer3 = "true";
            String num4 = "1";
            String answer4 = "true";
            String num5 = "6";
            String answer5 = "false";
            String num6 = "10";
            String answer6 = "false";

            /** involk call() method HERE */
            call(solution, num1, answer1);
            call(solution, num2, answer2);
            call(solution, num3, answer3);
            call(solution, num4, answer4);
            call(solution, num5, answer5);
            call(solution, num6, answer6);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        // test.test(3);
    }
}
