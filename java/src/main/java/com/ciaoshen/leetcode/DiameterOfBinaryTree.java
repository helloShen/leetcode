/**
 * Leetcode - Algorithm - DiameterOfBinaryTree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DiameterOfBinaryTree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DiameterOfBinaryTree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int diameterOfBinaryTree(TreeNode root); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int diameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            diameter = 0;
            depth(root);
            return diameter;
        }

        public int depth(TreeNode root) {
            if (root == null) { return -1; }
            int left = depth(root.left) + 1;
            int right = depth(root.right) + 1;
            diameter = Math.max(diameter, (left + right));
            return Math.max(left,right);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int diameterOfBinaryTree(TreeNode root) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int diameterOfBinaryTree(TreeNode root) {
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
        private DiameterOfBinaryTree problem = new DiameterOfBinaryTree();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root) {
            System.out.println(root.bfs());
            System.out.println("Diameter = " + solution.diameterOfBinaryTree(root));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            TreeNode root1 = TreeNode.randomBST(20);

            /** involk call() method HERE */
            call(root1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
