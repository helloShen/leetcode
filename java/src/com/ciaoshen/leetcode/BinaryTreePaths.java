/**
 * Leetcode - Algorithm - Binary Tree Paths
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

class BinaryTreePaths {
    /** bfs */
    public class SolutionV1 {
        public List<String> binaryTreePaths(TreeNode root) {
            return recursion(root,"");
        }
        private List<String> recursion(TreeNode root, String path) {
            List<String> result = new ArrayList<>();
            // base case
            if (root == null) { return result; }
            path += (root.val + "->");
            if (root.left == null && root.right == null) {
                if (!path.isEmpty()) { result.add(path.substring(0,path.length()-2)); }
                return result;
            }
            // recursion
            result.addAll(recursion(root.left,path));
            result.addAll(recursion(root.right,path));
            return result;
        }
    }
    /** dfs */
    public class SolutionV2 {
        private List<String> result = new ArrayList<>();
        public List<String> binaryTreePaths(TreeNode root) {
            TreeNode dummy = new TreeNode(0);
            dummy.right = root;
            backtracking(dummy,"");
            return result;
        }
        private void backtracking(TreeNode root, String path) {
            if (root.left == null && root.right == null) {
                if (!path.isEmpty()) { result.add(path.substring(2)); } return;
            }
            if (root.left != null) { backtracking(root.left,path + "->" + root.left.val); }
            if (root.right != null) { backtracking(root.right,path + "->" + root.right.val); }
        }
    }
    private class Test {
        private void callBinaryTreePaths(TreeNode root) {
            System.out.println("Tree is: " + root.bfs());
            System.out.println("Paths are: " + solution.binaryTreePaths(root));
        }
        private void test() {
            TreeNode tree1 = TreeNode.randomBST(20);
            // TreeNode tree2 = TreeNode.randomBST(20);
            // TreeNode tree3 = TreeNode.randomBST(20);
            callBinaryTreePaths(tree1);
            // callBinaryTreePaths(tree2);
            // callBinaryTreePaths(tree3);
        }
    }
    private static BinaryTreePaths problem = new BinaryTreePaths();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
