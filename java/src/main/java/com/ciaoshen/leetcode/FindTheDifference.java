/**
 * Leetcode - Algorithm - FindTheDifference
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FindTheDifference implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FindTheDifference() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public char findTheDifference(String s, String t); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] freq = new int[26];

        public char findTheDifference(String s, String t) {
            init();
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i)-'a']++;
            }
            for (int i = 0; i < t.length(); i++) {
                if (--freq[t.charAt(i)-'a'] < 0) { return t.charAt(i); }
            }
            return '\0';
        }
        private void init() {
            Arrays.fill(freq,0);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public char findTheDifference(String s, String t) {
            char c = '\0';
            for (int i = 0; i < s.length(); i++) {
                c ^= s.charAt(i);
                c ^= t.charAt(i);
            }
            return (char)(c ^ t.charAt(t.length()-1));
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public char findTheDifference(String s, String t) {
            return 'c';
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
        private FindTheDifference problem = new FindTheDifference();
        private Solution solution = null;

        // call method in solution
        private void call(String s, String t) {
            System.out.println("S = " + s + ",  T = " + t);
            System.out.println("Diff = \"" + solution.findTheDifference(s,t) +  "\"");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "abcd";
            String t1 = "abcde";
            /** involk call() method HERE */
            call(s1,t1);
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
