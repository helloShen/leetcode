/**
 * Leetcode - Algorithm - FriendCircles
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FriendCircles implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FriendCircles() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int findCircleNum(int[][] M); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private boolean[] table = new boolean[0];
        private int[][] local = new int[0][0];
        private void init(int[][] m) {
            table = new boolean[m.length];
            local = m;
        }
        public int findCircleNum(int[][] M) {
            init(M);
            int count = 0;
            for (int i = 0; i <table.length; i++) {
                if (!table[i]) {
                    count++;
                    dfs(i);
                }
            }
            return count;
        }
        private void dfs(int student) {
            for (int i = 0; i < local.length; i++) {
                if ((local[student][i] == 1) && (table[i] == false)) {
                    table[i] = true;
                    dfs(i);
                }
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int findCircleNum(int[][] M) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int findCircleNum(int[][] M) {
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
        private FriendCircles problem = new FriendCircles();
        private Solution solution = null;

        // call method in solution
        private void call(int[][] m) {
            Matrix.print(m);
            System.out.println("Circle Number = " + solution.findCircleNum(m) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[][] m1 = new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,1}
            };
            int[][] m2 = new int[][]{
                {1,1,0},
                {1,1,1},
                {0,1,1}
            };

            /** involk call() method HERE */
            call(m1);
            call(m2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
