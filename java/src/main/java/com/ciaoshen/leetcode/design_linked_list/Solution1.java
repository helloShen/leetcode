/**
 * Leetcode - design_linked_list
 */
package com.ciaoshen.leetcode.design_linked_list;
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

    private int size;
    private Node head;
    private Node tail;

    public void init() {
        size = 0;
        head = null;
        tail = null;
        if (log.isDebugEnabled()) {
            log.debug("Initialize MyLinkedList!");
        }
    }

    public String toString() {
        return (head == null)? "" : head.toString();
    }

    public int get(int index) {
        if (log.isDebugEnabled()) {
            log.debug("call get({})", index);
        }
        if (size == 0 || index >= size) return -1;
        // asset: size > 0 && index >= 0 && exists valid result
        Node target = head;
        if (log.isDebugEnabled()) {
            log.debug("target move to= {}", target.val);
        }
        for (int i = 0; i < index; i++) {
            target = target.next;
            if (log.isDebugEnabled()) {
                log.debug("target move to= {}", target.val);
            }
        }
        return target.val;
    }

    public void addAtHead(int val) {
        if (log.isDebugEnabled()) {
            log.debug("call addAtHead({})", val);
        }
        Node newNode = new Node(val);
        if (size == 0) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void addAtTail(int val) {
        if (log.isDebugEnabled()) {
            log.debug("call addAtTail({})", val);
        }
        Node newNode = new Node(val);
        if (size == 0) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (log.isDebugEnabled()) {
            log.debug("call addAtIndex({}, {})", index, val);
        }
        if (index > size) return;
        if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            Node newNode = new Node(val);
            Node pre = head;
            for (int i = 1; i < index; i++) pre = pre.next;
            newNode.next = pre.next;
            pre.next = newNode;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (log.isDebugEnabled()) {
            log.debug("call deleteAtIndex({})", index);
        }
        if (size == 0 || index >= size) return;
        if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0){
            head = head.next;
        } else {
            Node pre = head;
            for (int i = 1; i < index; i++) pre = pre.next;
            if (pre.next == tail) tail = pre;
            pre.next = pre.next.next;
        }
        size--;
    }

}
