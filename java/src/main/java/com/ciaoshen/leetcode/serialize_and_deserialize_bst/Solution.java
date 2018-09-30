/**
 * Leetcode - serialize_and_deserialize_bst
 */
package com.ciaoshen.leetcode.serialize_and_deserialize_bst;
import java.util.*;
// import com.ciaoshen.leetcode.util.*;
import com.ciaoshen.leetcode.myUtils.*;

interface Solution {

    // Insert new node into given binary search tree
    public static void insert(TreeNode root, int val) {
        if (root == null) return;
        if (root.val >= val) {
            if (root.left != null) {
                insert(root.left, val);
            } else {
                TreeNode newNode = new TreeNode(val);
                root.left = newNode;
            }
        } else {
            if (root.right != null) {
                insert(root.right, val);
            } else {
                TreeNode newNode =new TreeNode(val);
                root.right = newNode;
            }
        }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root);
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data);


}
