/**
 * Leetcode - Algorithm - Same Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;

class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
        return false;
    }
    private static SameTree test = new SameTree();
    private static void testIsSameTree() {
        TreeNode bst1 = TreeNode.randomBST(20);
        TreeNode bst2 = TreeNode.randomBST(20);
        System.out.println(test.isSameTree(bst1,bst1));
        System.out.println(test.isSameTree(bst1,bst2));
    }
    public static void main(String[] args) {
        testIsSameTree();
    }
}
