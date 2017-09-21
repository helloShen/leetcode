/**
 * Leetcode - Algorithm - ReverseString
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ReverseString implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ReverseString() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String reverseString(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public String reverseString(String s) {
            char[] chars = s.toCharArray();
            char register = '\0';
            int lo = 0, hi = chars.length-1;
            while (lo < hi) {
                register = chars[lo];
                chars[lo++] = chars[hi];
                chars[hi--] = register;
            }
            return new String(chars);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public String reverseString(String s) {
            return "";
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public String reverseString(String s) {
            return "";
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
        private ReverseString problem = new ReverseString();
        private Solution solution = null;

        // call method in solution
        private void call(String s) {
            System.out.println(s + " -> " + solution.reverseString(s));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            call("hello");
            call("");
            call("a");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
