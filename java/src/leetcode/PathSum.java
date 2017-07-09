/**
 * Leetcode - Algorithm - Path Sum
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class PathSum {
    public boolean hasPathSumV1(TreeNode root, int sum) {
        if (root == null) { return false; }
        int remain = sum - root.val;
        if (root.left == null && root.right == null && remain == 0) { return true; }
        if (root.left != null && hasPathSum(root.left,remain)) { return true; }
        if (root.right != null && hasPathSum(root.right,remain)) { return true; }
        return false;
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) { return false; }
        if (root.left == null && root.right == null) { return sum == root.val; }
        int remain = sum - root.val;
        return hasPathSum(root.left,remain) || hasPathSum(root.right,remain);
    }
    private static PathSum test = new PathSum();
    private static void testHasPathSum() {
        for (int i = 0; i < 10; i++) {
            TreeNode tree = TreeNode.randomBST(20);
            System.out.println(tree.bfs());
            System.out.println("Has Path Sum = 50 ?" + test.hasPathSum(tree,50));
        }
    }
    public static void main(String[] args) {
        testHasPathSum();
    }
}
