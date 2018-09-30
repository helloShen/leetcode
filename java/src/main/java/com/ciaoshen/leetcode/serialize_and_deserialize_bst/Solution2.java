/**
 * Leetcode - serialize_and_deserialize_bst
 */
package com.ciaoshen.leetcode.serialize_and_deserialize_bst;
import java.util.*;
// import com.ciaoshen.leetcode.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution2 implements Solution {

    private static final String SPLT = "-";
    private static final char SPLT_C = '-';
    private static final String NULL = "#";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }
    public void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL);
            sb.append(SPLT);
            return;
        }
        sb.append(String.valueOf(root.val));
        sb.append(SPLT);
        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        idx = 0;
        return deserializeHelper(data);
    }
    private int idx;
    private TreeNode deserializeHelper(String data) {
        if (idx == data.length()) return null;
        int end = data.indexOf(SPLT_C, idx);
        String val = data.substring(idx, end);
        idx = end + 1;
        if (val.equals(NULL)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(data);
        node.right = deserializeHelper(data);
        return node;
    }
}
