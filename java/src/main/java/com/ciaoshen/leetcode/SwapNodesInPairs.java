/**
 * Leetcode - Swap Nodes in Pairs
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class SwapNodesInPairs {
    public ListNode swapPairsV1(ListNode head) {
        ListNode sentinel = new ListNode(0), cursor = sentinel;
        sentinel.next = head;
        while (cursor.next != null && cursor.next.next != null) {
            ListNode temp = cursor.next;
            cursor.next = cursor.next.next;
            temp.next = cursor.next.next;
            cursor.next.next = temp;
            cursor = cursor.next.next;
            System.out.println(sentinel.next);
        }
        return sentinel.next;
    }

    public ListNode swapPairsV2(ListNode head) {
        if (head == null || head.next == null) { return head; }
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode cursor = sentinel, nextNode = cursor, afterNext = cursor;
        while (cursor.next != null && cursor.next.next != null) {
            nextNode = cursor.next;
            afterNext = cursor.next.next;
            nextNode.next = afterNext.next;
            cursor.next = afterNext;
            cursor.next.next = nextNode;
            cursor = cursor.next.next;
        }
        return sentinel.next;
    }

    public ListNode swapPairsV3(ListNode head) {
        if (head == null || head.next == null) { return head; }
        ListNode sentinel = new ListNode(0), cursor = sentinel;
        sentinel.next = head;
        ListNode nextNode = cursor.next, afterNext = nextNode.next;
        swapPairsRecursive(cursor,nextNode,afterNext);
        return sentinel.next;
    }
    public void swapPairsRecursive(ListNode cursor, ListNode nextNode, ListNode afterNext) {
        nextNode.next = afterNext.next;
        cursor.next = afterNext;
        cursor.next.next = nextNode;
        cursor = cursor.next.next;
        if (cursor.next != null && cursor.next.next != null) {
            swapPairsRecursive(cursor,cursor.next,cursor.next.next);
        }
    }
    public ListNode swapPairsV4(ListNode head) {
        if (head == null || head.next == null) { return head; } // base case
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        public String toString() {
            ListNode cursor = this;
            StringBuilder sb = new StringBuilder();
            while (cursor != null) {
                sb.append(cursor.val);
                cursor = cursor.next;
                if (cursor != null) {
                    sb.append("->");
                }
            }
            return sb.toString();
        }
    }
    private static ListNode newNodeList(int begin, int diff) {
        ListNode core = null;
        for (int i = begin; i > 0; i-=diff) {
            ListNode newNode = new ListNode(i);
            newNode.next = core;
            core = newNode;
        }
        return core;
    }
    private static void testSwapPairs() {
        SwapNodesInPairs test = new SwapNodesInPairs();
        ListNode l1 = newNodeList(10,1);
        ListNode l2 = newNodeList(20,5);
        ListNode l3 = newNodeList(30,2);
        ListNode l4 = new ListNode(0);
        System.out.println(l1);
        System.out.println(test.swapPairs(l1));
        System.out.println(l2);
        System.out.println(test.swapPairs(l2));
        System.out.println(l3);
        System.out.println(test.swapPairs(l3));
        System.out.println(l4);
        System.out.println(test.swapPairs(l4));
    }
    public static void main(String[] args) {
        testSwapPairs();
    }
}
