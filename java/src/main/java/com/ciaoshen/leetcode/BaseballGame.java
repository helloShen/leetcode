/**
 * Leetcode - Algorithm - BaseballGame
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BaseballGame implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BaseballGame() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int calPoints(String[] ops); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int calPoints(String[] ops) {
            int[] points = new int[ops.length];
            int cur = 0;
            for (String s : ops) {
                switch(s) {
                    case "+": points[cur] = points[cur-1] + points[cur-2]; cur++; break;
                    case "D": points[cur] = points[cur-1] * 2; cur++; break;
                    case "C": cur--; break;
                    default: points[cur++] = Integer.parseInt(s); break;
                }
            }
            int sum = 0;
            for (int i = 0; i < cur; i++) { sum += points[i]; }
            return sum;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int calPoints(String[] ops) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int calPoints(String[] ops) {
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
        private BaseballGame problem = new BaseballGame();
        private Solution solution = null;

        private void call(String[] ops, int ans) {
            System.out.println("Operations: " + Arrays.toString(ops));
            System.out.println("Sum = " + solution.calPoints(ops) + ",     [Answer: " + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String[] ops1 = new String[]{"5","2","C","D","+"};
            int ans1 = 30;
            String[] ops2 = new String[]{"5","-2","4","C","D","9","+","+"};
            int ans2 = 27;

            /** involk call() method HERE */
            call(ops1,ans1);
            call(ops2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
