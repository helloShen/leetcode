/**
 * Leetcode - minimum_absolute_difference_in_bst
 */
package com.ciaoshen.leetcode.minimum_absolute_difference_in_bst;
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

    public int getMinimumDifference(TreeNode root) {
        minDiff = Integer.MAX_VALUE;
        prev = -1;
        inorder(root);
        return minDiff;
    }
    int minDiff, prev;
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev >= 0) minDiff = Math.min(minDiff, Math.abs(root.val - prev));
        prev = root.val;
        inorder(root.right);
    }

}
