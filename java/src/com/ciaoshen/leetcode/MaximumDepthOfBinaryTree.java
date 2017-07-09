/**
 * Leetcode - Algorithm - Maximum Depth of Binary Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class MaximumDepthOfBinaryTree {
    public int maxDepthV1(TreeNode root) {
        int[] max = new int[]{ 0 };
        dfs(root,1,max);
        return max[0];
    }
    public void dfs(TreeNode root, int depth, int[] max) {
        if (root == null) { return; }
        max[0] = Math.max(depth,max[0]);
        if (root.left != null) { dfs(root.left,depth+1,max); }
        if (root.right != null) { dfs(root.right,depth+1,max); }
    }
    public int maxDepthV2(TreeNode root) {
        int maxDepth = 0, depth = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> depthMemo = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                depth++;
                maxDepth = Math.max(depth,maxDepth);
                stack.offerFirst(cur);
                depthMemo.offerFirst(depth);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            depth = depthMemo.pollFirst();
            cur = cur.right;
        }
        return maxDepth;
    }
    public int maxDepthV3(TreeNode root) {
        int depth = 0;
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(root);
        while (!buffer.isEmpty()) {
            int size = buffer.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = buffer.remove(0);
                if (node != null) {
                    buffer.add(node.left);
                    buffer.add(node.right);
                }
            }
            if (!buffer.isEmpty()) { depth++; } // 这时候才能确定上一层至少有一个非null的节点
        }
        return depth;
    }
    public int maxDepthV4(TreeNode root) {
        if (root == null) { return 0; }
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
    public int maxDepth(TreeNode root) {
        return (root == null)? 0 : Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
    private static MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();
    private static void testMaxDepth() {
        for (int i = 0; i < 10; i++) {
            TreeNode tree = TreeNode.randomBST(20);
            System.out.println("Depth of : " + tree.bfs() + " = " + test.maxDepth(tree));
        }
    }
    public static void main(String[] args) {
        testMaxDepth();
    }
}
