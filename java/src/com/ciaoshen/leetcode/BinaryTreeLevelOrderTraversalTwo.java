/**
 * Leetcode - Algorithm - Binary Tree Level Order Traversal Two
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class BinaryTreeLevelOrderTraversalTwo {
    public List<List<Integer>> levelOrderBottomV1(TreeNode root) {
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
            if (!nums.isEmpty()) { res.add(0,nums); }
        }
        return res;
    }
    public List<List<Integer>> levelOrderBottomV2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) { return res; }
        List<TreeNode> buffer = new ArrayList<>();
        buffer.add(root);
        levelRecurV1(buffer,res);
        return res;
    }
    public void levelRecurV1(List<TreeNode> buffer, List<List<Integer>> res) {
        if (buffer.isEmpty()) { return; }
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
        if (!nums.isEmpty()) { res.add(0,nums); }
        levelRecurV1(buffer,res);
    }
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        recur(root,res,0);
        Collections.reverse(res);
        return res;
    }
    public void recur(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) { return; }
        if (level == res.size()) {
            List<Integer> newLevel = new ArrayList<>();
            newLevel.add(root.val);
            res.add(newLevel);
        } else {
            List<Integer> list = res.get(level);
            list.add(root.val);
        }
        recur(root.left,res,level+1);
        recur(root.right,res,level+1);
    }
    private static BinaryTreeLevelOrderTraversalTwo test = new BinaryTreeLevelOrderTraversalTwo();
    private static void testLevelOrderBottom() {
        TreeNode tree = TreeNode.randomBST(20);
        System.out.println("BFS: " + tree.bfs());
        System.out.println("BFS reverse: " + test.levelOrderBottom(tree));
    }
    public static void main(String[] args) {
        testLevelOrderBottom();
    }
}
