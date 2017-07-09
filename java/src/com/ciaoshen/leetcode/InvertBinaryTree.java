/**
 * Leetcode - Algorithm - Invert Binary Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class InvertBinaryTree {
    public class SolutionV1 {
        public TreeNode invertTree(TreeNode root) {
            recur(root);
            return root;
        }
        private void recur(TreeNode root) {
            if (root == null) { return; }
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            recur(root.left);
            recur(root.right);
        }
    }
    public class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) { return null; }
            TreeNode temp = root.left;
            root.left = invertTree(root.right);
            root.right = invertTree(temp);
            return root;
        }
    }
    private class Test {
        private void callInvertTree(TreeNode root) {
            System.out.println("Original Tree: " + root.bfs());
            System.out.println("Inverted Tree: " + solution.invertTree(root).bfs());
        }
        private void test() {
            TreeNode tree = TreeNode.randomBST(20);
            callInvertTree(tree);
        }
    }
    private static InvertBinaryTree problem = new InvertBinaryTree();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
