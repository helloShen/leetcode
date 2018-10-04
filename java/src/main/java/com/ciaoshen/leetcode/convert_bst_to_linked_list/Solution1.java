/**
 * Leetcode - convert_bst_to_linked_list
 */
package com.ciaoshen.leetcode.convert_bst_to_linked_list;

import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;


class Solution1 implements Solution {

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node[] headTail = recursion(root);
        headTail[0].left = headTail[1];
        headTail[1].right = headTail[0];
        return headTail[0];
    }
    private Node[] recursion(Node root) {
        if (root == null) return null;
        if (log.isDebugEnabled()) {
            log.debug("[node:{}] ", root.val);
        }
        Node[] res = new Node[]{root, root};
        if (root.left != null) {
            Node[] left = recursion(root.left);
            res[0] = left[0];
            root.left = left[1];
            left[1].right = root;
        }
        if (root.right != null) {
            Node[] right = recursion(root.right);
            res[1] = right[1];
            root.right = right[0];
            right[0].left = root;
        }
        if (log.isDebugEnabled()) {
            log.debug("sub-chain:[left:{}, right:{}] ", res[0].val, res[1].val);
        }
        return res;
    }

}
