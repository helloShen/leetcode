/**
 * Leetcode - Algorithm - Binary Tree Right Side View
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class BinaryTreeRightSideView {
    /**
     * 递归版遍历。
     * 先右后左的inorder遍历。
     */
    public class SolutionV1 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            recursion(root,1,new int[]{0},ret);
            return ret;
        }
        public void recursion(TreeNode root, int depth, int[] maxDepth, List<Integer> ret) {
            if (root == null) { return; }
            if (depth > maxDepth[0]) {
                ret.add(root.val);
                maxDepth[0] = depth;
            }
            recursion(root.right, depth+1,maxDepth,ret);
            recursion(root.left, depth+1,maxDepth,ret);
        }
    }
    /**
     * 迭代版遍历。
     * 先右后左的inorder遍历。
     */
    public class SolutionV2 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            Deque<TreeNode> nodeStack = new LinkedList<>();
            Deque<Integer> depthStack = new LinkedList<>();
            int depth = 0, maxDepth = 0;
            TreeNode cur = root;
            while (cur != null || !nodeStack.isEmpty()) {
                while (cur != null) {
                    nodeStack.offerFirst(cur);
                    depthStack.offerFirst(++depth);
                    if (depth > maxDepth) {
                        ret.add(cur.val);
                        maxDepth = depth;
                    }
                    cur = cur.right;
                }
                cur = nodeStack.pollFirst().left;
                depth = depthStack.pollFirst();
            }
            return ret;
        }
    }
    /**
     * BFS. 广度优先的遍历。
     */
    public class SolutionV3 {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            List<TreeNode> level = new LinkedList<>();
            level.add(root);
            while (!level.isEmpty()) {
                int size = level.size();
                boolean findFirst = false;
                for (int i = 0; i < size; i++) {
                    TreeNode node = level.remove(0);
                    if (node != null) {
                        if (!findFirst) { ret.add(node.val); findFirst = true; }
                        level.add(node.right);
                        level.add(node.left);
                    }
                }
            }
            return ret;
        }
    }
    /**
     * DFS. 先右后左的inorder遍历。递归。
     * 和V1的区别是：不维护 "maxDepth"，改为用 "ret.size()"获得当前最大深度。
     */
    public class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> ret = new ArrayList<>();
            recursion(root,1,ret);
            return ret;
        }
        public void recursion(TreeNode root, int depth, List<Integer> ret) {
            if (root == null) { return; }
            if (depth > ret.size()) { ret.add(root.val); }
            recursion(root.right, depth+1,ret);
            recursion(root.left, depth+1,ret);
        }
    }
    private static BinaryTreeRightSideView test = new BinaryTreeRightSideView();
    private static Solution solution = test.new Solution();
    private static void callRightSideView(TreeNode root) {
        System.out.println("Right side view of Tree: " + root.bfs());
        System.out.println("    >>> " + solution.rightSideView(root));
    }
    private static void test() {
        TreeNode node = TreeNode.randomBST(20);
        callRightSideView(node);
    }
    public static void main(String[] args) {
        test();
    }
}
