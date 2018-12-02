/**
 * Leetcode - subtree_of_another_tree
 */
package com.ciaoshen.leetcode.subtree_of_another_tree;
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

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        int val = t.val;
        Iterator ite = iterator(s);
        while (ite.hasNext()) {
            TreeNode node = ite.next();
            if (node.val == val && compare(node, t)) return true;
        }
        return false;
    }

    private Iterator iterator(TreeNode root) {
        return new Iterator(root);
    }

    private static class Iterator {
        private List<TreeNode> level;
        public Iterator (TreeNode root) {
            level = new LinkedList<TreeNode>();
            level.add(root);
        }
        public boolean hasNext() {
            while (!level.isEmpty() && level.get(0) == null) level.remove(0); // trim leading null nodes
            return !level.isEmpty();
        }
        public TreeNode next() {
            while (!level.isEmpty() && level.get(0) == null) level.remove(0); // trim leading null nodes
            if (!level.isEmpty()) {
                TreeNode next = level.remove(0);
                level.add(next.left);
                level.add(next.right);
                return next;
            }
            return null;
        }
    }

    private List<TreeNode> getNodes(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        List<TreeNode> level = new LinkedList<>();
        level.add(root);
        while (!level.isEmpty()) {
            int size = level.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = level.remove(0);
                if (node != null) {
                    list.add(node);
                    level.add(node.left);
                    level.add(node.right);
                }
            }
        }
        return list;
    }

    private boolean compare(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        if (!compare(s.left, t.left) || !compare(s.right, t.right)) return false;
        return true;
    }

}
