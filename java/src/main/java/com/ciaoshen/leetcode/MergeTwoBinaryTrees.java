/**
 * Leetcode - Algorithm - MergeTwoBinaryTrees
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MergeTwoBinaryTrees implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MergeTwoBinaryTrees() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public TreeNode mergeTrees(TreeNode t1, TreeNode t2); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) { return null; }
            TreeNode t1Left = null, t1Right = null;
            TreeNode t2Left = null, t2Right = null;
            int sum = 0;
            if (t1 != null) {
                sum += t1.val;
                t1Left = t1.left; t1Right = t1.right;
            }
            if (t2 != null) {
                sum += t2.val;
                t2Left = t2.left; t2Right = t2.right;
            }
            TreeNode newNode = new TreeNode(sum);
            newNode.left = mergeTrees(t1Left,t2Left);
            newNode.right = mergeTrees(t1Right,t2Right);
            return newNode;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            return null;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            return null;
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
        private MergeTwoBinaryTrees problem = new MergeTwoBinaryTrees();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode t1, TreeNode t2) {
            System.out.println("Tree 1: " + t1.bfs() + "\n\n");
            System.out.println("Tree 2: " + t2.bfs() + "\n\n");
            System.out.println("Merged Tree: " + solution.mergeTrees(t1,t2).bfs() + "\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            TreeNode t11 = TreeNode.randomBST(20);
            TreeNode t12 = TreeNode.randomBST(20);
            TreeNode t21 = TreeNode.randomBST(20);
            TreeNode t22 = TreeNode.randomBST(20);
            TreeNode t31 = TreeNode.randomBST(20);
            TreeNode t32 = TreeNode.randomBST(20);

            /** involk call() method HERE */
            call(t11,t12);
            call(t21,t22);
            call(t31,t32);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
