/**
 * Leetcode - Algorithm - Binary Tree Level Order Traversal
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrderV1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) { return res; }
        List<TreeNode> thisLevel = new ArrayList<>();
        thisLevel.add(root);
        while (!thisLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            List<Integer> levNums = new ArrayList<>();
            for (TreeNode node : thisLevel) {
                if (node != null) {
                    levNums.add(node.val);
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                }
            }
            thisLevel = nextLevel;
            if (!levNums.isEmpty()) { res.add(levNums); }
        }
        return res;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) { return res; }
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(root);
        while (!buffer.isEmpty()) {
            List<Integer> nums = new ArrayList<>();
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = buffer.remove(0);
                if (node != null) {
                    nums.add(node.val);
                    buffer.add(node.left);
                    buffer.add(node.right);
                }
            }
            if (!nums.isEmpty()) { res.add(nums); }
        }
        return res;
    }
    private static BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();
    private static void testLevelOrder() {
        for (int i = 0; i < 10; i++) {
            TreeNode tree = TreeNode.randomStd();
            System.out.println("DFS: " + tree);
            System.out.println("BFS: " + test.levelOrder(tree));
        }
    }
    public static void main(String[] args) {
        testLevelOrder();
    }
}
