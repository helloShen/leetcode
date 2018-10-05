/**
 * Leetcode - convert_bst_to_linked_list
 */
package com.ciaoshen.leetcode.convert_bst_to_linked_list;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution2 implements Solution {

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node();
        Node pre = dummy;
        Node curr = root;
        LinkedList<Node> stack = new LinkedList<>();
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (log.isDebugEnabled()) {
                log.debug("[node:{}]", curr.val);
            }
            pre.right = curr;
            curr.left = pre;
            pre = curr;
            curr = curr.right;
        }
        if (log.isDebugEnabled()) {
            log.debug("[dummy.right:{}]", dummy.right.val);
            log.debug("[pre:{}]", pre.val);
        }
        dummy.right.left = pre;
        pre.right = dummy.right;
        return dummy.right;
    }
}
