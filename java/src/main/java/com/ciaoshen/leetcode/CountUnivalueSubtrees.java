/**
 * Leetcode - Algorithm - CountUnivalueSubtrees
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class CountUnivalueSubtrees implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private CountUnivalueSubtrees() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int countUnivalSubtrees(TreeNode root);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int countUnivalSubtrees(TreeNode root) {
            count = 0;
            if (root == null) { return count; }
            recursion(root);
            return count;
        }
        private int count;
        public boolean recursion(TreeNode root) {
            // base case
            if (root.left == null && root.right == null) { count++; return true; }
            // recursion
            boolean l = false, r = false;
            if (root.left != null) { l = recursion(root.left) && root.val == root.left.val; }
            if (root.right != null) { r = recursion(root.right) && root.val == root.right.val; }
            if (root.left == null) { if (r) { count++; } return r; }
            if (root.right == null) { if (l) { count++; } return l; }
            if (l & r) { count++; }
            return (l & r);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int countUnivalSubtrees(TreeNode root) {
            count = 0;
            recursion(root);
            return count;
        }
        private int count = 0;
        private boolean recursion(TreeNode root) {
            // base case
            if (root == null) { return true; }
            // recursion
            boolean l = recursion(root.left);
            boolean r = recursion(root.right);
            if (l && r) {
                if (root.left != null && root.val != root.left.val) { return false; }
                if (root.right != null && root.val != root.right.val) { return false; }
                count++; return true;
            } else {
                return false;
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int countUnivalSubtrees(TreeNode root) {
            return 0;
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
        private CountUnivalueSubtrees problem = new CountUnivalueSubtrees();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root, String answer) {
            System.out.println("In Tree " + root);
            System.out.println("Has " + solution.countUnivalSubtrees(root) + "      [answer: " + answer + "]");
        }

        /**
         *       5
         *      / \
         *     1   5
         *    / \   \
         *   5   5   5
         */
        private TreeNode tree1() {
            TreeNode five1 = new TreeNode(5);
            TreeNode five2 = new TreeNode(5);
            TreeNode five3 = new TreeNode(5);
            TreeNode five4 = new TreeNode(5);
            TreeNode five5 = new TreeNode(5);
            TreeNode one = new TreeNode(1);
            five1.left = one;
            five1.right = five2;
            one.left = five3;
            one.right = five4;
            five2.right = five5;
            return five1;
        }
        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            call(tree1(),"4");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
    }
}
