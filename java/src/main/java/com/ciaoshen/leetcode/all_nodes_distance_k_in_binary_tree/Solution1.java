/**
 * Leetcode - all_nodes_distance_k_in_binary_tree
 */
package com.ciaoshen.leetcode.all_nodes_distance_k_in_binary_tree;
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

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        if (!find(stack, target)) return res;
        TreeNode curr = stack.pop();
        sub(res, curr, null, K);
        while (!stack.isEmpty() && --K >= 0) {
            TreeNode not = curr;
            curr = stack.pop();
            sub(res, curr, not, K);
        }
        return res;
    }

    private boolean find(Deque<TreeNode> stack, TreeNode target) {
        TreeNode root = stack.peek();
        if (root == null) return false;
        if (root == target) return true;
        stack.push(root.left);
        if (find(stack, target)) return true;
        stack.pop();
        stack.push(root.right);
        if (find(stack, target)) return true;
        stack.pop();
        return false;
    }

    private void sub(List<Integer> res, TreeNode root, TreeNode not, int k) {
        if (root == null) return;
        if (k == 0) {
            res.add(root.val);
            return;
        }
        if (root.left != not) sub(res, root.left, null, k - 1);
        if (root.right != not) sub(res, root.right, null, k - 1);
    }


}
