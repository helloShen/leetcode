/**
 * Leetcode - Algorithm - JudgeRouteCircle
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class JudgeRouteCircle implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private JudgeRouteCircle() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean judgeCircle(String moves); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public boolean judgeCircle(String moves) {
            int vtc = 0, orz = 0;
            for (int i = 0; i < moves.length(); i++) {
                char c = moves.charAt(i);
                switch (c) {
                    case 'U': ++vtc; break;
                    case 'D': --vtc; break;
                    case 'L': --orz; break;
                    case 'R': ++orz; break;
                    default: return false;
                }
            }
            return vtc == 0 && orz == 0;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public boolean judgeCircle(String moves) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean judgeCircle(String moves) {
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
        private JudgeRouteCircle problem = new JudgeRouteCircle();
        private Solution solution = null;

        // call method in solution
        private void call(String s) {
            System.out.println("\"" + s + "\"\t -> \t" + solution.judgeCircle(s));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "";
            String s2 = "UD";
            String s3 = "LL";

            /** involk call() method HERE */
            call(s1);
            call(s2);
            call(s3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
