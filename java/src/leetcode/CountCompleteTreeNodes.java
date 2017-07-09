/**
 * Leetcode - Algorithm - Count Complete Tree Nodes
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class CountCompleteTreeNodes {
    /** 递归遍历每个节点，直接统计数量 */
    public class SolutionV1 {
        public int countNodes(TreeNode root) {
            int[] count = new int[1];
            count(root,count);
            return count[0];
        }
        private void count(TreeNode root, int[] count) {
            if (root == null) { return; }
            count[0]++;
            count(root.left,count);
            count(root.right,count);
        }
    }
    public class SolutionV2 {
        public int countNodes(TreeNode root) {
            if (root == null) { return 0; }
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    public class SolutionV3 {
        public int countNodes(TreeNode root) {
            if (root == null) { return 0; }
            int maxDepth = -1;
            TreeNode cur = root;
            while (cur != null) { cur = cur.left; maxDepth++; }
            return (1 << maxDepth) - 1 + countLeaves(root,0,maxDepth);
        }
        public int countLeaves(TreeNode root, int depth, int maxDepth) {
            if (root == null) { return 0; }
            if (depth == maxDepth) { return 1; }
            return countLeaves(root.left,depth+1,maxDepth) + countLeaves(root.right,depth+1,maxDepth);
        }
    }
    public class SolutionV4 {
        public int countNodes(TreeNode root) {
            if (root == null) { return 0; }
            int height = height(root);
            int rightHeight = height(root.right);
            if (rightHeight == height-1) { // 左子树是full tree
                return (1 << height) + countNodes(root.right);
            } else { // 右子树是full tree，但少一层
                return (1 << (rightHeight+1)) + countNodes(root.left);
            }
        }
        private int height(TreeNode root) {
            int count = -1;
            TreeNode cur = root;
            while (cur != null) { count++; cur = cur.left; }
            return count;
        }
    }
    private class Test {
        private void callCountNodes(TreeNode root) {
            System.out.println("Tree : " + root.bfs());
            System.out.println("Number of nodes is: " + solution.countNodes(root));
        }
        private void test() {
            TreeNode root = TreeNode.randomStd();
            callCountNodes(root);
        }
    }

    private static CountCompleteTreeNodes instance = new CountCompleteTreeNodes();
    private static Solution solution = instance.new Solution();
    private static Test test = instance.new Test();

    public static void main(String[] args) {
        test.test();
    }
}
