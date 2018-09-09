/**
 * Leetcode - Algorithm - EliminationGame
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class EliminationGame implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private EliminationGame() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int lastRemaining(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int lastRemaining(int n) {
            List<Integer> list = new LinkedList<>();
            for (int i = 1; i <= n; i++) { list.add(i); }
            while (list.size() > 1) {
                System.out.println("Before: " + list);
                for (int i = 0; list.size() > 1 && i < list.size(); i++) {
                    list.remove(i);
                }
                System.out.println("After go right: " + list);
                for (int i = list.size()-1; list.size() > 1 && i >= 0; i -= 2) {
                    list.remove(i);
                }
                System.out.println("After go left: " + list);
            }
            return list.get(0);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int lastRemaining(int n) {
            int head = 1, step = 1, remain = n;
            boolean fromLeft = true;
            while (remain > 1) {
                if (fromLeft || (remain % 2 == 1)) { head += step; }
                step *= 2;
                remain /= 2;
                fromLeft = !fromLeft;
            }
            return head;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int lastRemaining(int n) {
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
        private EliminationGame problem = new EliminationGame();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            // System.out.println("--------------- >>> " + n + " <<< --------------------");
            System.out.println(n + " -> " + solution.lastRemaining(n) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            for (int i = 1; i < 50; i++) {
                call(i);
            }

            // call(15);
            // call(16);
            // call(17);
            // call(18);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
