/**
 * Leetcode - Algorithm - Binary Tree Inorder Traversal
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversalV1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) { return res; }
        res.addAll(inorderTraversal(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal(root.right));
        return res;
    }
    public List<Integer> inorderTraversalV2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> parentStack = new LinkedList<TreeNode>();
        Deque<Boolean> fromLeft = new LinkedList<Boolean>();
        boolean leftVisted = false, rightVisited = false;
        boolean shouldAdd = true;
        TreeNode cur = root;
        while (cur != null) {
            System.out.println("I am at node: [" + cur.val + "]");
            if (cur.left != null) {
                parentStack.addFirst(cur);
                fromLeft.addFirst(true);
                cur = cur.left;
                continue;
            }
            if (shouldAdd) {
                res.add(cur.val);
                System.out.println("Node: [" + cur.val + "] added!");
            } else {
                shouldAdd = true;
            }
            if (cur.right != null) {
                parentStack.offerFirst(cur);
                fromLeft.addFirst(false);
                cur = cur.right;
                continue;
            }
            cur = parentStack.pollFirst();
            if (cur != null) {
                Boolean isFromLeft = fromLeft.pollFirst();
                if (isFromLeft) {
                    cur.left = null;
                } else {
                    cur.right = null;
                    shouldAdd = false; // 从右节点回溯回来，不需要再重复添加父节点
                }
            }
        }
        return res;
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    private static BinaryTreeInorderTraversal test = new BinaryTreeInorderTraversal();
    private static void testInorderTraversal() {
        TreeNode tree = TreeNode.randomStd();
        System.out.println("DFS: " + tree);
        System.out.println("MFS: " + tree.mfsToString());
        System.out.println("BFS: " + tree.bfsToString());
        System.out.println("My traversal: " + test.inorderTraversal(tree));
    }
    public static void main(String[] args) {
        testInorderTraversal();
    }
}
