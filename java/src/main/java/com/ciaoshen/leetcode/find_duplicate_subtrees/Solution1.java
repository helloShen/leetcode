/**
 * Leetcode - find_duplicate_subtrees
 */
package com.ciaoshen.leetcode.find_duplicate_subtrees;
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

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        Map<String, Integer> memo = new HashMap<>();
        preorder(root, memo, res);
        return res;
    }

    private String preorder(TreeNode root, Map<String, Integer> memo, List<TreeNode> res) {
        if (root == null) return "#";
        String serial = root.val + ","
                        + preorder(root.left, memo, res)
                        + preorder(root.right, memo, res);
        Integer history = memo.getOrDefault(serial, 0);
        if (history == 1) res.add(root);
        memo.put(serial, history + 1);
        return serial;
    }

}
