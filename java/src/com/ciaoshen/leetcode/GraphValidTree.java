/**
 * Leetcode - Algorithm - GraphValidTree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class GraphValidTree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private GraphValidTree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean validTree(int n, int[][] edges);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        /** union find */
        private int[] board = new int[0];
        public boolean validTree(int n, int[][] edges) {
            if (n < 0) { return false; }
            if (n < 2) { return true; } // n == 0 || n == 1
            init(n);
            // connect each vertices by their edge
            for (int[] edge : edges) {
                if (!connect(edge[0],edge[1])) { return false; } // circle found
            }
            // check if each vertices belong to exactly one tree.
            int id = find(0);
            for (int i = 1; i < n; i++) {
                if (find(i) != id) { return false; }
            }
            return true;
        }
        /** initialize board elements by their index. */
        private void init(int size) {
            board = new int[size];
            for (int i = 0; i < size; i++) { board[i] = i; }
        }
        /**
         * connect two vertices if they don't belongs to a single tree, and return true,
         * return false if two given vertices are already connected (circle).
         */
        private boolean connect(int a, int b) {
            int idA = find(a);
            int idB = find(b);
            if (idA == idB) {
                return false;
            } else {
                board[idB] = idA; // add tree B to tree A
                return true;
            }
        }
        /** return the root of the target value */
        private int find(int n) {
            if (board[n] == n) { return n; } // n is the root
            int idN = find(board[n]);
            board[n] = idN; // path compress
            return idN;
        }
        
        /** print the union find board */
        public String toString() {
            return Arrays.toString(board);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean validTree(int n, int[][] edges) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean validTree(int n, int[][] edges) {
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
        private GraphValidTree problem = new GraphValidTree();
        private Solution solution = null;

        // call method in solution
        private void call(int n, int[][] edges, String answer) {
            System.out.println(solution.validTree(n,edges) + "      [answer:" + answer + "]");
            System.out.println(solution);
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int n1 = 5;
            int[][] edges1 = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}};
            String answer1 = "true";
            call(n1,edges1,answer1);
            int n2 = 5;
            int[][] edges2 = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
            String answer2 = "false";
            call(n2,edges2,answer2);
            int n3 = 2;
            int[][] edges3 = new int[0][0];
            String answer3 = "false";
            call(n3,edges3,answer3);
            int n4 = 1;
            int[][] edges4 = new int[0][0];
            String answer4 = "true";
            call(n4,edges4,answer4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
