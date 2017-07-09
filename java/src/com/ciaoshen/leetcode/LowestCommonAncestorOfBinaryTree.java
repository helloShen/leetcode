/**
 * Leetcode - Algorithm - Lowest Common Ancestor of Binary Tree
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class LowestCommonAncestorOfBinaryTree {
    /** 比较健壮的版本，适合扩展。完整找完Path，再比较。*/
    public class SolutionV1 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || p == null || q == null) { return null; } // defense
            Deque<TreeNode> pathP = getPath(root,p);
            Deque<TreeNode> pathQ = getPath(root,q);
            System.out.println("Path of P is: " + pathP);
            System.out.println("Path of Q is: " + pathQ);
            TreeNode commonAncestor = null;
            while (!pathP.isEmpty() && !pathQ.isEmpty()) {
                TreeNode currP = pathP.pollFirst();
                TreeNode currQ = pathQ.pollFirst();
                if (currP == currQ) {
                    commonAncestor = currP;
                } else { break; }
            }
            return commonAncestor;
        }
        /**
         * root can be null, but target CANNOT
         * Return an empty Stack if nothing found.
         */
        public Deque<TreeNode> getPath(TreeNode root, TreeNode target) {
            Deque<TreeNode> path = new LinkedList<>();
            if (root == null) { return path; }
            if (root == target) { path.offerFirst(root); return path; }
            path = getPath(root.left,target);
            if (!path.isEmpty()) { path.offerFirst(root); return path; }
            path = getPath(root.right,target);
            if (!path.isEmpty()) { path.offerFirst(root); return path; }
            return path;
        }
    }

    /** 针对本题追求效率的做法
     *  假设 p 和 q 都存在于树里。
     */
    public class SolutionV2 {
        private TreeNode lowestCommonAncestor = null;
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || p == null || q == null) { return null; }
            lowestCommonAncestor = null;
            recur(root,p,q);
            return lowestCommonAncestor;
        }
        /**
         * Using BITMAP
         *  1. nothing found in tree:           00 -> 0
         *  2. p found in tree:                 01 -> 1
         *  3. q found in tree:                 10 -> 2
         *  4. both p & q are found in tree:    11 -> 3
         */
        public int recur(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) { return 0; }
            int left = recur(root.left,p,q);
            if (left == 3) { return left; } // LCA found in left sub tree
            int right = recur(root.right,p,q);
            if (right == 3) { return right; } // LCA found in right sub tree
            int sub = left | right;
            if (sub == 3) { lowestCommonAncestor = root; return sub; }
            int curr = 0;
            if (root == p) { curr |= 1; }
            if (root == q) { curr |= 2; }
            curr |= sub;
            if (curr == 3) { lowestCommonAncestor = root; }
            return curr;
        }
    }

    /**
     * 换个角度看问题：不用盯住哪些子树有p,q，而是盯住哪些子树什么也没有。
     * 只要在root树中，既没有p也没有q，就返回null。
     */
    public class SolutionV3 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) { return null; }
            if (root == p || root == q) { return root; }
            TreeNode left = lowestCommonAncestor(root.left,p,q);
            TreeNode right = lowestCommonAncestor(root.right,p,q);
            if (left == null) {
                return right;
            } else if (right == null) {
                return left;
            } else {
                return root;
            }
        }
    }


    private class Test {
        private void callLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            System.out.println("In the Tree: " + root.bfs());
            System.out.println("The lowest common ancestor of [" + p.val + "] and [" + q.val + "]" + " is: " + solution.lowestCommonAncestor(root,p,q));
        }
        private void test() {
            TreeNode[] testcase = testcaseA();
            callLowestCommonAncestor(testcase[0],testcase[1],testcase[2]);
        }
        private TreeNode[] getNodesArray(int size) {
            TreeNode[] array = new TreeNode[size];
            for (int i = 0; i < size; i++) {
                array[i] = new TreeNode(i);
            }
            return array;
        }
        private TreeNode[] testcaseA() {
            TreeNode[] nodes = getNodesArray(10);
            nodes[2].left = nodes[7];
            nodes[2].right = nodes[4];
            nodes[5].left = nodes[6];
            nodes[5].right = nodes[2];
            nodes[1].left = nodes[0];
            nodes[1].right = nodes[8];
            nodes[3].left = nodes[5];
            nodes[3].right = nodes[1];
            TreeNode[] result = new TreeNode[3];
            result[0] = nodes[3];
            result[1] = nodes[6];
            result[2] = nodes[1];
            return result;
        }
    }
    private static LowestCommonAncestorOfBinaryTree problem = new LowestCommonAncestorOfBinaryTree();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
