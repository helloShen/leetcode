/**
 * Leetcode - Algorithm - ReverseWordsInAString
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ReverseWordsInAString implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ReverseWordsInAString() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String reverseWords(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        private final String SPACE = " ";
        public String reverseWords(String s) {
            String[] words = s.split(SPACE);
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                char[] ca = word.toCharArray();
                int lo = 0, hi = ca.length-1;
                while (lo < hi) { exch(ca,lo++,hi--); }
                sb = sb.append(ca).append(SPACE);
            }
            return sb.substring(0,sb.length()-1);
        }
        private void exch(char[] ca, int lo, int hi) {
            char temp = ca[lo];
            ca[lo] = ca[hi];
            ca[hi] = temp;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        private final String SPACE = " ";
        public String reverseWords(String s) {
            String[] words = s.split(SPACE);
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(new StringBuilder(word).reverse().append(SPACE));
            }
            return sb.substring(0,sb.length()-1);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public String reverseWords(String s) {
            return s;
        }
    }


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
        private ReverseWordsInAString problem = new ReverseWordsInAString();
        private Solution solution = null;

        // call method in solution
        private void call(String s) {
            System.out.println("Original Words: " + s);
            System.out.println("Reversed Words: " + solution.reverseWords(s) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s0 = "";
            String s1 = "Let's take leetcode contest.";

            /** involk call() method HERE */
            call(s0);
            call(s1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
