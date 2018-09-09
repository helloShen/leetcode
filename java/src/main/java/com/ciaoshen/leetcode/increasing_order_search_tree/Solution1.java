/**
 * Leetcode - #897 - Increasing Order Search Tree
 */
package com.ciaoshen.leetcode.increasing_order_search_tree;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {
    public TreeNode increasingBST(TreeNode root) {
        dummy = new TreeNode(0);
        TreeNode head = dummy;
        inOrder(root);
        return head.right;   
    }
    private TreeNode dummy;
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        dummy.right = new TreeNode(root.val);
        dummy = dummy.right;
        inOrder(root.right);
    }
}