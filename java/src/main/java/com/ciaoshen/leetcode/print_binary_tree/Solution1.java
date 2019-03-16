/**
 * Leetcode - print_binary_tree
 */
package com.ciaoshen.leetcode.print_binary_tree;
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

    public List<List<String>> printTree(TreeNode root) {
        int depth = depth(root, 1);
        int width = (int)(Math.pow(2, depth) - 1);
        int aline = (width - 1) / 2;
        // if (log.isDebugEnabled()) {
        //     log.debug("depth = {}", depth);
        //     log.debug("width = {}", width);
        //     log.debug("aline = {}", aline);
        // }
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            String[] arr = new String[width];
            Arrays.fill(arr, "");
            List<String> newLine = new ArrayList<String>(Arrays.asList(arr));
            res.add(newLine);
        }
        // if (log.isDebugEnabled()) {
        //     log.debug("original res = ");
        //     for (List<String> list : res) {
        //         log.debug("{}", list);
        //     }
        // }
        helper(1, 0, aline, root, res);
        return res;
    }

    private void helper(int depth, int offset, int aline, TreeNode root, List<List<String>> res) {
        if (root == null) return;
        fill(depth, offset, aline, root.val, res);
        int newAline = (aline - 1 ) / 2;
        helper(depth + 1, offset, newAline, root.left, res);
        helper(depth + 1, offset + aline + 1, newAline, root.right, res);
    }

    private void fill(int depth, int offset, int aline, int val, List<List<String>> res) {
        List<String> list = res.get(depth - 1);
        int idx = offset + aline;
        list.remove(idx);
        // String old = list.remove(idx);
        // if (log.isDebugEnabled()) {
        //     log.debug("remove {}", old);
        //     log.debug("and fill {}", String.valueOf(val));
        // }
        list.add(idx, String.valueOf(val));
    }

    private int depth(TreeNode root, int currentDepth) {
        if (root == null) return 0;
        int nextLev = Math.max(depth(root.left, currentDepth + 1), depth(root.right, currentDepth + 1));
        return (nextLev == 0)? currentDepth : nextLev;
    }

}
