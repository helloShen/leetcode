/**
 * Leetcode - max_stack
 */
package com.ciaoshen.leetcode.max_stack;
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
class Solution2 implements Solution {

    private DoubleLinkedListNode dummy;
    private DoubleLinkedListNode curr;

    /** initialize your data structure here. */
    public Solution2() {
        dummy = new DoubleLinkedListNode(Integer.MIN_VALUE);
        curr = dummy;
    }

    public void push(int x) {
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(x);
        newNode.prev = curr;
        curr.next = newNode;
        curr = newNode;
        if (log.isDebugEnabled()) {
            log.debug("push({})", x);
            log.debug("Insert new node {}", newNode.val);
        }
    }

    public int pop() {
        int res = curr.val;
        curr = curr.prev;
        curr.next = null;
        if (log.isDebugEnabled()) {
            log.debug("pop()");
            log.debug("Current top is {}", curr.val);
        }
        return res;
    }

    public int top() {
        if (log.isDebugEnabled()) {
            log.debug("top()");
            log.debug("Current top is {}", curr.val);
        }
        return curr.val;
    }

    public int peekMax() {
        int max = curr.val;
        DoubleLinkedListNode ite = curr.prev;
        if (ite == null && log.isDebugEnabled()) {
                log.debug("ite = null");
            }
        while (ite != dummy) {
            if (log.isDebugEnabled()) {
                log.debug("ite = {}", ite.val);
            }
            max = Math.max(max, ite.val);
            ite = ite.prev;
        }
        if (log.isDebugEnabled()) {
            log.debug("peekMax()");
            log.debug("Max = {}", max);
            log.debug("Current top is {}", curr.val);
        }
        return max;
    }

    public int popMax() {
        DoubleLinkedListNode max = curr;
        DoubleLinkedListNode ite = curr.prev;
        while (ite != dummy) {
            if (ite.val > max.val) max = ite;
            ite = ite.prev;
        }
        max.prev.next = max.next;
        if (curr == max) {
            curr = curr.prev;
        } else {
            max.next.prev = max.prev;
        }
        if (log.isDebugEnabled()) {
            log.debug("peekMax()");
            log.debug("Max = {}", max.val);
            log.debug("Current top is {}", curr.val);
        }
        return max.val;
    }

    private class DoubleLinkedListNode {
        private int val;
        private DoubleLinkedListNode prev, next;
        private DoubleLinkedListNode(int x) {
            val = x;
        }
    }

}
