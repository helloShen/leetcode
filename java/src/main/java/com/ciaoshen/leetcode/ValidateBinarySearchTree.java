/**
 * Leetcode - Algorithm - Validate Binary Search Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class ValidateBinarySearchTree {
    public boolean isValidBSTV1(TreeNode root) {
        return isValidBSTRecurV1(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public boolean isValidBSTRecurV1(TreeNode root, int min, int max) { // range inclusive
        if (root == null) { return true; }
        int value = root.val;
        if (root.left != null) {
            int left = root.left.val;
            if (left < min || left >= value) { return false; }
        }
        if (root.right != null) {
            int right = root.right.val;
            if (right > max || right <= value) { return false; }
        }
        return isValidBSTRecurV1(root.left,min,value-1) && isValidBSTRecurV1(root.right,value+1,max);
    }
    public boolean isValidBSTV2(TreeNode root) {
        return isValideBSTRecurV2(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
    public boolean isValideBSTRecurV2(TreeNode root, long min, long max) { // range inclusive
        if (root == null) { return true; }
        //System.out.println("Node Value = " + root.val + ", Min = " + min + ", Max = " + max);
        if (root.val < min || root.val > max) { return false; }
        return isValideBSTRecurV2(root.left,min,(long)root.val-1) && isValideBSTRecurV2(root.right,(long)root.val+1,max);
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null) { return true; }
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode cur = root;
        long pre = Long.MIN_VALUE;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            if (cur.val <= pre) { return false; }
            pre = cur.val;
            cur = cur.right;
        }
        return true;
    }
    private static ValidateBinarySearchTree test = new ValidateBinarySearchTree();
    private static void testIsValidBST() {
        for (int i = 0; i < 10; i++) {
            System.out.println(test.isValidBST(TreeNode.randomStd()));
            System.out.println(test.isValidBST(TreeNode.randomBST(20)));
        }
        TreeNode wrong = new TreeNode(-2147483648);
        wrong.left = new TreeNode(-2147483648);
        System.out.println(test.isValidBST(wrong));
    }
    public static void main(String[] args) {
        testIsValidBST();
    }
}
