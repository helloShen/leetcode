/**
 * Leetcode - serialize_and_deserialize_bst
 */
package com.ciaoshen.leetcode.serialize_and_deserialize_bst;
import java.util.*;
// import com.ciaoshen.leetcode.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {

    private static final String SPLITOR = "-";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }
    public void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(String.valueOf(root.val));
        sb.append(SPLITOR);
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] nums = data.split(SPLITOR);
        TreeNode root = new TreeNode(Integer.parseInt(nums[0]));
        for (int i = 1; i < nums.length; i++) {
            Solution.insert(root, Integer.parseInt(nums[i]));
        }
        return root;
    }

}
