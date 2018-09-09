/**
 * Leetcode - Algorithm - PaintHouse
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PaintHouse implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PaintHouse() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int minCost(int[][] costs);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int minCost(int[][] costs) {
            int[] minCost = new int[]{-1};
            dfs(costs,0,-1,0,minCost);
            return minCost[0];
        }
        public void dfs(int[][] costs, int row, int col, int costSoFar, int[] minCost) {
            // base case
            if (row == costs.length) { minCost[0] = (minCost[0] < 0)? costSoFar : Math.min(minCost[0],costSoFar); return; }
            for (int i = 0; i < costs[0].length; i++) {
                if (col >= 0 && i == col) {  continue; }
                dfs(costs,row+1,i,costSoFar+costs[row][i],minCost);
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int minCost(int[][] costs) {
            if (costs.length == 0) { return 0; }
            dp(costs,0);
            return Math.min(Math.min(costs[0][0],costs[0][1]),costs[0][2]);
        }
        public void dp(int[][] costs, int row) {
            if (row == costs.length-1) { return; }
            dp(costs,row+1);
            costs[row][0] += Math.min(costs[row+1][1],costs[row+1][2]);
            costs[row][1] += Math.min(costs[row+1][0],costs[row+1][2]);
            costs[row][2] += Math.min(costs[row+1][0],costs[row+1][1]);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int minCost(int[][] costs) {
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
        private PaintHouse problem = new PaintHouse();
        private Solution solution = null;

        // call method in solution
        private void call(int[][]costs, String answer) {
            System.out.println("For houses: ");
            Matrix.print(costs);
            System.out.println("Minimum cost is: " + solution.minCost(costs) + "        >>> [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int[][] costs1 = new int[][]{{21,15,8},{14,4,18},{5,13,3},{9,1,17},{2,10,6},{16,7,20},{11,19,12}};
            // int[][] costs2 = new int[][]{};
            // int[][] costs3 = new int[][]{};

            call(costs1,"36");
            // call(costs2,"");
            // call(costs3,"");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
    }
}
