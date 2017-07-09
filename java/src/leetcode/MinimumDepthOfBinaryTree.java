/**
 * Leetcode - Algorithm - Minimum Depth of Binary Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) { return 0; }
        if (root.left == null && root.right == null) { return 1; }
        if (root.left == null) { return minDepth(root.right) + 1; }
        if (root.right == null) { return minDepth(root.left) + 1; }
        return Math.min(minDepth(root.left),minDepth(root.right)) + 1;
    }
    private static MinimumDepthOfBinaryTree test = new MinimumDepthOfBinaryTree();
    private static void testMinDepth() {
        TreeNode tree = TreeNode.randomBST(50);
        System.out.println("Tree: " + tree.bfs());
        System.out.println("Minimum Depth = " + test.minDepth(tree));
        TreeNode empty = null;
        TreeNode one = new TreeNode(1);
        TreeNode oneTwo = new TreeNode(1);
        oneTwo.right = new TreeNode(2);
        System.out.println("Tree: " + one.bfs());
        System.out.println("Minimum Depth = " + test.minDepth(one));
        System.out.println("Tree: " + oneTwo.bfs());
        System.out.println("Minimum Depth = " + test.minDepth(oneTwo));
    }
    public static void main(String[] args) {
        testMinDepth();
    }
}
