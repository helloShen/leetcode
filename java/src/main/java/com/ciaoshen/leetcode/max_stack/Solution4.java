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
class Solution4 implements Solution {

    private DoubleLinkedList list;
    private TreeMap<Integer, List<DoubleLinkedNode>> map;

    /** initialize your data structure here. */
    public Solution4() {
        list = new DoubleLinkedList();
        map = new TreeMap<Integer, List<DoubleLinkedNode>>();
    }

    public void push(int x) {
        DoubleLinkedNode newNode = new DoubleLinkedNode(x);
        list.add(newNode);
        List<DoubleLinkedNode> sameValList = map.get(x);
        if (sameValList == null) {
            sameValList = new LinkedList<DoubleLinkedNode>();
        }
        sameValList.add(newNode);
        map.put(x, sameValList);
    }

    public int pop() {
        if (list.isEmpty()) return 0;
        DoubleLinkedNode last = list.removeLast();
        List<DoubleLinkedNode> sameValList = map.get(last.val);
        sameValList.remove(sameValList.size() - 1);
        if (sameValList.isEmpty()) map.remove(last.val);
        return last.val;
    }

    public int top() {
        if (list.isEmpty()) return 0;
        return list.getLast().val;
    }

    public int peekMax() {
        if (list.isEmpty()) return 0;
        return map.lastKey();
    }

    public int popMax() {
        if (list.isEmpty()) return 0;
        int max = peekMax();
        List<DoubleLinkedNode> sameValList = map.get(max);
        DoubleLinkedNode removed = sameValList.remove(sameValList.size() - 1);
        if (sameValList.isEmpty()) map.remove(max);
        list.remove(removed);
        return max;
    }

    private class DoubleLinkedNode {
        private int val;
        private DoubleLinkedNode prev, next;
        private DoubleLinkedNode(int x) {
            val = x;
        }
    }
    private class DoubleLinkedList {
        // 2 sentinals to simplify linked list operations
        private DoubleLinkedNode head;
        private DoubleLinkedNode tail;

        private DoubleLinkedList() {
            head = new DoubleLinkedNode(0);
            tail = new DoubleLinkedNode(0);
            head.next = tail;
            tail.prev = head;
        }

        private boolean isEmpty() {
            return head.next == tail;
        }
        private void add(DoubleLinkedNode node) {
            tail.prev.next = node;
            node.next = tail;
            node.prev = tail.prev;
            tail.prev = node;
        }

        private DoubleLinkedNode getLast() {
            if (isEmpty()) return null;
            return tail.prev;
        }

        private DoubleLinkedNode removeLast() {
            if (isEmpty()) return null;
            return remove(tail.prev);
        }

        private DoubleLinkedNode remove(DoubleLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }

}
