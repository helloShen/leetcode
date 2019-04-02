/**
 * Leetcode - search_in_a_binary_search_tree
 */
package com.ciaoshen.leetcode.search_in_a_binary_search_tree;
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

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        return (root.val < val)? searchBST(root.right, val) : searchBST(root.left, val);
    }

}
