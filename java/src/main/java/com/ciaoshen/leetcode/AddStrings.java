/**
 * Leetcode - Algorithm - AddStrings
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class AddStrings implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private AddStrings() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String addStrings(String num1, String num2); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public String addStrings(String num1, String num2) {
            if (num1.length() == 0 || num2.length() == 0) { return ""; }
            StringBuilder sb = new StringBuilder();
            int cur1 = num1.length() - 1;
            int cur2 = num2.length() - 1;
            int a = 0, b = 0, sum = 0, carry = 0;
            while (cur1 >= 0 || cur2 >= 0) {
                a = 0; b = 0;
                if (cur1 >= 0) { a = num1.charAt(cur1--) - '0'; }
                if (cur2 >= 0) { b = num2.charAt(cur2--) - '0'; }
                sum = a + b + carry;
                carry = sum / 10;
                sb.insert(0,sum % 10);
                // System.out.println(num1.charAt(cur1+1) + " + " + num2.charAt(cur2+1) + " = " + (sum % 10) + "(carry = " + carry + ")");
            }
            if (carry == 1) { sb.insert(0,'1'); }
            return sb.toString();
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public String addStrings(String num1, String num2) {
            return "Soluiton 2";
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public String addStrings(String num1, String num2) {
            return "Solution 3";
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
        private AddStrings problem = new AddStrings();
        private Solution solution = null;

        private void call(String num1, String num2, String ans) {
            System.out.println(num1 + " + " + num2 + " = " + solution.addStrings(num1,num2));
            System.out.println("Answer should be: " + ans);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String num11 = "263547";
            String num12 = "4958";
            String ans1 = "268505";

            /** involk call() method HERE */
            call(num11,num12,ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
