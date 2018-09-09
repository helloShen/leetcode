/**
 * Leetcode - Algorithm - Balanced Binary Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) { return true; }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (Math.abs(leftDepth-rightDepth) > 1) { return false; }
        return isBalanced(root.left) && isBalanced(root.right);
    }
    public int depth(TreeNode root) {
        if (root == null) { return 0; }
        return Math.max(depth(root.left),depth(root.right)) + 1;
    }

    public boolean isBalancedV2(TreeNode root) {
        int depth = depthV2(root);
        return (depth != -1);
    }
    // return -1, if find some node is not balanced
    public int depthV2(TreeNode root) {
        if (root == null) { return 0; }
        int leftDepth = depthV2(root.left);
        int rightDepth = depthV2(root.right);
        if (leftDepth == -1 || rightDepth == -1) {
            return -1;
        } else if (Math.abs(leftDepth-rightDepth) > 1) {
            return -1;
        } else {
            return Math.max(leftDepth,rightDepth) + 1;
        }
    }
    private static BalancedBinaryTree test = new BalancedBinaryTree();
    private static void testIsBalanced() {
        for (int i = 0; i < 10; i++) {
            TreeNode tree = TreeNode.randomBST(50);
            System.out.println(tree.bfs());
            System.out.println("Is Balanced? " + test.isBalanced(tree));
        }
    }
    public static void main(String[] args) {
        testIsBalanced();
    }
}
