/**
 * Leetcode - Algorithm - InorderSuccessorInBST
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class InorderSuccessorInBST implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private InorderSuccessorInBST() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public TreeNode inorderSuccessor(TreeNode root, TreeNode p); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            boolean found = false;
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                // System.out.println("Cur = " + cur.val);
                if (found) { return cur; }
                if (cur == p) { found = true; }
                cur = (cur.right == null)? null : cur.right;
            }
            return null;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode[] res = new TreeNode[1];
            helper(root,p,res);
            return res[0];
        }
        private boolean helper(TreeNode root, TreeNode p, TreeNode[] res) {
            if (root == null) { return false; }
            boolean foundInLeft = helper(root.left,p,res);
            if (foundInLeft) {
                if (res[0] == null) { res[0] = root; }
                return true;
            }
            if (root == p) {
                if (root.right != null) {
                    res[0] = root.right;
                    while (res[0].left != null) { res[0] = res[0].left; }
                }
                return true;
            }
            if (helper(root.right,p,res)) { return true; }
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (root == null) { return null; }
            if (root.val <= p.val) {
                return inorderSuccessor(root.right,p);
            } else {
                TreeNode left = inorderSuccessor(root.left,p);
                return (left != null)? left : root;
            }
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
        private InorderSuccessorInBST problem = new InorderSuccessorInBST();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root, TreeNode p) {
            System.out.println(root.bfs());
            TreeNode res = solution.inorderSuccessor(root,p);
            System.out.println("Inorder Successor of [" + p.val + "] is " + ((res == null)? res : res.val) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            TreeNode root1 = TreeNode.randomBST(20);
            TreeNode p1 = root1.left;

            /** involk call() method HERE */
            call(root1,p1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
