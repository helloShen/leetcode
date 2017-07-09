/**
 * Leetcode - Algorithm - Binary Tree Zigzag Level Order Traversal
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrderV1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) { return res; }
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(root);
        boolean leftToRight = true;
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
            if (!nums.isEmpty()) {
                if (!leftToRight) { Collections.reverse(nums); }
                res.add(nums);
            }
            leftToRight = !leftToRight;
        }
        return res;
    }
    public List<List<Integer>> zigzagLevelOrderV2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) { return res; }
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(root);
        boolean leftToRight = true;
        while (!buffer.isEmpty()) {
            List<Integer> nums = new ArrayList<>();
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = buffer.remove(0);
                if (node != null) {
                    if (leftToRight) {
                        nums.add(node.val);
                    } else {
                        nums.add(0,node.val);
                    }
                    buffer.add(node.left);
                    buffer.add(node.right);
                }
            }
            if (!nums.isEmpty()) { res.add(nums); }
            leftToRight = !leftToRight;
        }
        return res;
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) { return res; }
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(root);
        zigzagLevelOrderRecur(res,buffer,new Boolean(true));
        return res;
    }
    public void zigzagLevelOrderRecur(List<List<Integer>> res, List<TreeNode> buffer, boolean fromLeftToRight) {
        if (buffer.isEmpty()) { return; }
        List<Integer> nums = new ArrayList<>();
        int size = buffer.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = buffer.remove(0);
            if (node != null) {
                if (fromLeftToRight) {
                    nums.add(node.val);
                } else {
                    nums.add(0,node.val);
                }
                buffer.add(node.left);
                buffer.add(node.right);
            }
        }
        if (!nums.isEmpty()) { res.add(nums); }
        zigzagLevelOrderRecur(res,buffer,!fromLeftToRight);
    }
    private static BinaryTreeZigzagLevelOrderTraversal test = new BinaryTreeZigzagLevelOrderTraversal();
    private static void testZigzagLevelOrder() {
        for (int i = 0; i < 10; i++) {
            TreeNode tree = TreeNode.randomStd();
            System.out.println("DFS: " + tree);
            System.out.println("ZigZag BFS: " + test.zigzagLevelOrder(tree));
        }
    }
    public static void main(String[] args) {
        testZigzagLevelOrder();
    }
}
