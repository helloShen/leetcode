/**
 * Leetcode - flatten_a_multilevel_doubly_linked_list
 */
package com.ciaoshen.leetcode.flatten_a_multilevel_doubly_linked_list;
import java.util.*;
// import com.ciaoshen.leetcode.util.*;

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

    public Node flatten(Node head) {
        if (head == null) return null;
        if (log.isDebugEnabled()) {
            log.debug("For node [{}]", head.val);
        }
        Node next = flatten(head.next);
        Node child = flatten(head.child);
        if (log.isDebugEnabled()) {
            log.debug("node [{}].next = {}", head.val, head.next);
        }
        if (log.isDebugEnabled()) {
            log.debug("node [{}].child = {}", head.val, head.child);
        }
        head.child = null;
        head.next = child;
        if (child != null) child.prev = head;
        Node oldHead = head;
        while (head != null && head.next != null) head = head.next;
        head.next = next;
        if (next != null) next.prev = head;
        return oldHead;
    }

}
