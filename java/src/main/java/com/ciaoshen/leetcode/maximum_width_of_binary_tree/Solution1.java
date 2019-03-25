/**
 * Leetcode - maximum_width_of_binary_tree
 */
package com.ciaoshen.leetcode.maximum_width_of_binary_tree;
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

    public int widthOfBinaryTree(TreeNode root) {
        Map<Integer, int[]> map = new HashMap<>();
        parseTree(root, 0, 0, map);
        // if (log.isDebugEnabled()) {
        //     log.debug("map = {}", map);
        // }
        int maxScope = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] scope = entry.getValue();
            maxScope = Math.max(maxScope, scope[1] - scope[0] + 1);
        }
        return maxScope;
    }

    private void parseTree(TreeNode node, int depth, int offset, Map<Integer, int[]> map) {
        if (node != null) {
            // if (log.isDebugEnabled()) {
            //     log.debug("parse node {}", node);
            //     log.debug("depth = {}, offset = {}", depth, offset);
            // }
            if (!map.containsKey(depth)) map.put(depth, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
            int[] scope = map.get(depth);
            scope[0] = Math.min(scope[0], offset);
            scope[1] = Math.max(scope[1], offset);
            // if (log.isDebugEnabled()) {
            //     log.debug("scope for depth {} update to {}", depth, Arrays.toString(scope));
            // }
            parseTree(node.left, depth + 1, offset * 2, map);
            parseTree(node.right, depth + 1, offset * 2 + 1, map);
        }
    }

}
