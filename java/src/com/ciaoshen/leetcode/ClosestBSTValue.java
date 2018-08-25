/**
 * Leetcode - Algorithm - ClosestBSTValue
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ClosestBSTValue implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ClosestBSTValue() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int closestValue(TreeNode root, double target); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private Integer gt = null;
        private Integer ls = null;

        public int closestValue(TreeNode root, double target) {
            gt = null; ls = null;
            preOrder(root,target);
            if (gt == null) { return ls; }
            if (ls == null) { return gt; }
            return ((target - ls) >= (gt - target))? gt : ls;
        }
        private void preOrder(TreeNode root, double target) {
            if (root == null) { return; }
            double val = (double)root.val;
            if (val == target) {
                gt = root.val;
            } else if (val > target) {
                gt = root.val;
                preOrder(root.left, target);
            } else {
                ls = root.val;
                preOrder(root.right, target);
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int closestValue(TreeNode root, double target) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int closestValue(TreeNode root, double target) {
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
        private ClosestBSTValue problem = new ClosestBSTValue();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root, double target) {
            System.out.println(root.bfs() + ",\t Target = " + target);
            System.out.println("closest Value = " + solution.closestValue(root,target) + "\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            Random r = new Random();
            /** involk call() method HERE */
            call(TreeNode.randomBST(20),30 * r.nextDouble());
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
