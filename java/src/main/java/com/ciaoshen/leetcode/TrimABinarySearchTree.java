/**
 * Leetcode - Algorithm - TrimABinarySearchTree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class TrimABinarySearchTree implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private TrimABinarySearchTree() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public TreeNode trimBST(TreeNode root, int L, int R); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public TreeNode trimBST(TreeNode root, int L, int R) {
            TreeNode dummy = new TreeNode(0);
            dummy.left = root;
            TreeNode cur = root, pre = dummy;
            while (cur != null) {
                if (cur.val < L) {
                    pre.left = cur.right;
                    cur = cur.right;
                } else {
                    pre = cur;
                    cur = cur.left;
                }
            }
            dummy.right = dummy.left;
            dummy.left = null;
            cur = dummy.right; pre = dummy;
            while (cur != null) {
                if (cur.val > R) {
                    pre.right = cur.left;
                    cur = cur.left;
                } else {
                    pre = cur;
                    cur = cur.right;
                }
            }
            return dummy.right;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public TreeNode trimBST(TreeNode root, int L, int R) {
            TreeNode dummy = new TreeNode(0);
            dummy.left = root;
            trimSmaller(dummy,L);
            dummy.right = dummy.left;
            trimGreater(dummy,R);
            return dummy.right;
        }
        private void trimSmaller(TreeNode root, int L) {
            if (root.left == null) { return; }
            if (root.left.val < L) {
                root.left = root.left.right;
                trimSmaller(root,L);
            } else {
                trimSmaller(root.left,L);
            }
        }
        private void trimGreater(TreeNode root, int R) {
            if (root.right == null) { return; }
            if (root.right.val > R) {
                root.right = root.right.left;
                trimGreater(root,R);
            } else {
                trimGreater(root.right,R);
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public TreeNode trimBST(TreeNode root, int L, int R) {
            if (root == null) { return null; }

            if (root.val < L) { return trimBST(root.right,L,R); }
            if (root.val > R) { return trimBST(root.left,L,R); }

            root.left = trimBST(root.left,L,R);
            root.right = trimBST(root.right,L,R);

            return root;
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
        private TrimABinarySearchTree problem = new TrimABinarySearchTree();
        private Solution solution = null;

        // call method in solution
        private void call(TreeNode root, int l, int r) {
            System.out.println("Before trim: \n" + root.bfs() + "\n");
            root = solution.trimBST(root,l,r);
            System.out.println("After trim: \n" + root.bfs() + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            TreeNode root1 = TreeNode.randomBST(20);

            /** involk call() method HERE */
            call(root1,5,10);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
