/**
 * Leetcode - Algorithm - Binary Tree Postorder Traversal
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


class BinaryTreePostorderTraversal {
    public class SolutionV1 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) { return res; }
            res.addAll(postorderTraversal(root.left));
            res.addAll(postorderTraversal(root.right));
            res.add(root.val);
            return res;
        }
    }
    public class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            Deque<TreeNode> buffer = new LinkedList<TreeNode>();
            buffer.offerFirst(root);
            while (!buffer.isEmpty()) {
                TreeNode node = buffer.pollFirst();
                if (node != null) {
                    buffer.offerFirst(node.left);
                    buffer.offerFirst(node.right);
                    res.add(0,node.val);
                }
            }
            return res;
        }
    }
    private static BinaryTreePostorderTraversal test = new BinaryTreePostorderTraversal();
    private static void testPostorderTraversal() {
        Solution solution = test.new Solution();
        TreeNode list = TreeNode.randomBST(100);
        System.out.println("Level Order: " + list.bfs());
        System.out.println("Post order: " + solution.postorderTraversal(list));
    }
    public static void main(String[] args) {
        testPostorderTraversal();
    }
}
