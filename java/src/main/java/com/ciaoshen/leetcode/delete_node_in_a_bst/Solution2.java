/**
 * Leetcode - delete_node_in_a_bst
 */
package com.ciaoshen.leetcode.delete_node_in_a_bst;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution2 implements Solution {

    public TreeNode deleteNode(TreeNode root, int key) {
        return deleteHelper(root, root, null, key);
    }

    private TreeNode deleteHelper(TreeNode root, TreeNode ite, TreeNode parent, int key) {
        if (ite == null) return root;
        if (ite.val == key) { // swap with successor
            if (ite.right != null) {
                ite.val = deleteMin(ite);
            } else { // connect with left sub-tree
                if (ite == root) {
                    root = root.left;
                } else {
                    if (ite == parent.left) {
                        parent.left = ite.left;
                    } else {
                        parent.right = ite.left;
                    }
                }
            }
            return root;
        } else if (ite.val > key) {
            return deleteHelper(root, ite.left, ite, key);
        } else {
            return deleteHelper(root, ite.right, ite, key);
        }
    }

    /**
     * Delete the min element in node's right subtree
     * assertion: node != null &&  node.right != null
     * @return deleted min value
     */
    private int deleteMin(TreeNode node) {
        TreeNode root = node.right, parent = node;
        if (root.left == null) {
            parent.right = root.right;
            return root.val;
        }
        while (root.left != null) {
            parent = root;
            root = root.left;
        }
        parent.left = root.right;
        return root.val;
    }

}
