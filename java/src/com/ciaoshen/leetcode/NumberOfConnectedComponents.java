/**
 * Leetcode - Algorithm - NumberOfConnectedComponents
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class NumberOfConnectedComponents implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private NumberOfConnectedComponents() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int countComponents(int n, int[][] edges); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int countComponents(int n, int[][] edges) {
            if (n <= 0 || edges == null) { return 0; }
            broad = new int[n];
            for (int i = 0; i < n; i++) { broad[i] = i; }
            for (int[] edge : edges) {
                union(edge[0],edge[1]);
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (broad[i] == i) { count++; }
            }
            return count;
        }
        private int[] broad;
        private void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            broad[rootB] = rootA;
        }
        private int find(int a) {
            if (broad[a] == a) {
                return a;
            }
            broad[a] = find(broad[a]);
            return broad[a];
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int countComponents(int n, int[][] edges) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int countComponents(int n, int[][] edges) {
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
        private NumberOfConnectedComponents problem = new NumberOfConnectedComponents();
        private Solution solution = null;

        // call method in solution
        private void call(int n, int[][] edges, int answer) {
            System.out.println("For n = " + n);
            Matrix.print(edges);
            System.out.println("Number of Connected Components = " + solution.countComponents(n,edges) + "\t\t[answer=" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int n1 = 0, answer1 = 0;
            int[][] edges1 = new int[0][];
            int n2 = 5, answer2 = 2;
            int[][] edges2 = new int[][]{{0,1},{1,2},{3,4}};
            int n3 = 5, answer3 = 1;
            int[][] edges3 = new int[][]{{0,1},{1,2},{2,3},{3,4}};
            int n4 = 3, answer4 = 1;
            int[][] edges4 = new int[][]{{2,0},{2,1}};
            int n5 = 4, answer5 = 1;
            int[][] edges5 = new int[][]{{0,1},{2,3},{1,2}};

            /** involk call() method HERE */
            call(n1,edges1,answer1);
            call(n2,edges2,answer2);
            call(n3,edges3,answer3);
            call(n4,edges4,answer4);
            call(n5,edges5,answer5);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
