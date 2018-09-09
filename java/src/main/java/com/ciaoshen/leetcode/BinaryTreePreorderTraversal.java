/**
 * Leetcode - Algorithm - Binary Tree Preorder Traversal
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


class BinaryTreePreorderTraversal {
    /**
     * 递归版
     */
    public class SolutionV1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) { return res; }
            res.add(root.val);
            res.addAll(preorderTraversal(root.left));
            res.addAll(preorderTraversal(root.right));
            return res;
        }
    }
    /**
     * 迭代版
     */
    public class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.add(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pollFirst();
                if (node != null) {
                    res.add(node.val);
                    stack.offerFirst(node.right);
                    stack.offerFirst(node.left);
                }
            }
            return res;
        }
    }
    private static BinaryTreePreorderTraversal test = new BinaryTreePreorderTraversal();
    private static void testPreorderTraversal() {
        Solution solution = test.new Solution();
        TreeNode list = TreeNode.randomBST(100);
        System.out.println(list.bfs());
        System.out.println(solution.preorderTraversal(list));
    }
    public static void main(String[] args) {
        testPreorderTraversal();
    }
}
