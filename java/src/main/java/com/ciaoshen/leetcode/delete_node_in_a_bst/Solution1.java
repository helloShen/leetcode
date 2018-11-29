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
class Solution1 implements Solution {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode ite = root, parent = null;
        while (ite != null) {
            if (ite.val == key) {
                if (ite.right != null) {
                    ite.val = deleteMin(ite);
                } else { // max element, so no successor
                    if (log.isDebugEnabled()) {
                        log.debug("{} is max element, has no successor!", ite.val);
                    }
                    if (ite == root) {
                        root = root.left;
                    } else {
                        if (log.isDebugEnabled()) {
                            log.debug("parent is {}, set it's right child to null!", parent.val);
                        }
                        if (ite == parent.left) {
                            parent.left = ite.left;
                        } else {
                            parent.right = ite.left;
                        }
                    }
                }
                break;
            }
            parent = ite;
            if (ite.val > key) {
                if (log.isDebugEnabled()) {
                    log.debug("[{}] go left", ite.val);
                }
                ite = ite.left;
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("[{}] go right", ite.val);
                }
                ite = ite.right;
            }
        }
        return root;
    }

    /**
     * Delete the min element in node's right subtree
     * assertion: node != null &&  node.right != null
     * @return deleted min value
     */
    private int deleteMin(TreeNode node) {
        TreeNode root = node.right, parent = node;
        if (root.left == null) {
            if (log.isDebugEnabled()) {
                log.debug("Delete the only right successor [{}]", root.val);
            }
            parent.right = root.right;
            return root.val;
        }
        while (root.left != null) {
            parent = root;
            root = root.left;
        }
        if (log.isDebugEnabled()) {
            log.debug("Delete the min val [{}]", root.val);
        }
        parent.left = root.right;
        return root.val;
    }
}
