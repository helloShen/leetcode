/**
 * Leetcode - Algorithm - UglyNumber
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class UglyNumber implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private UglyNumber() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isUgly(int num);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean isUgly(int num) {
            if (num <= 0) { return false; }
            while (true) {
                if (num % 2 == 0) { num /= 2; continue; }
                if (num % 3 == 0) { num /= 3; continue; }
                if (num % 5 == 0) { num /= 5; continue; }
                break;
            }
            return num == 1;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean isUgly(int num) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean isUgly(int num) {
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
        private UglyNumber problem = new UglyNumber();
        private Solution solution = null;

        // call method in solution
        private void call(int num, String answer) {
            System.out.println(num + " is ugly? " + solution.isUgly(num) + "        [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);
            String[] answer = new String[]{"true","true","true","true","true","true","false","true","true","true","false","true","false","false","true"};
            for (int i = 0; i < 15; i++) {
                call(i+1,answer[i]);
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
