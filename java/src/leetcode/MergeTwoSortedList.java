/**
 * Leetcode - Merge Two Sorted List
 */
package com.ciaoshen.leetcode;
import java.util.*;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class MergeTwoSortedList {
    public ListNode mergeTwoListsV1(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(0), cursor = sentinel;
        int min = Integer.MAX_VALUE;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                min = l1.val;
                l1 = l1.next;
            } else {
                min = l2.val;
                l2 = l2.next;
            }
            cursor.next = new ListNode(min);
            cursor = cursor.next;
        }
        if (l1 != null) { cursor.next = l1; }
        if (l2 != null) { cursor.next = l2;}
        return sentinel.next;
    }
    public ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(0), cursor = sentinel;
        mergeTwoListsRecursive(cursor,l1,l2);
        return sentinel.next;
    }
    public void mergeTwoListsRecursive(ListNode cursor, ListNode l1, ListNode l2) {
        if (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cursor.next = l1;
                mergeTwoListsRecursive(cursor.next,l1.next,l2);
            } else {
                cursor.next = l2;
                mergeTwoListsRecursive(cursor.next,l1,l2.next);
            }
        }
        if (l1 == null) { cursor.next = l2; }
        if (l2 == null) { cursor.next = l1; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) { return l2; }
        if (l2 == null) { return l1; }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode cursor = this;
            while (cursor != null) {
                sb.append(cursor.val);
                if (cursor.next != null) {
                    sb.append("->");
                }
                cursor = cursor.next;
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
    private static void testMergeTwoList() {
        MergeTwoSortedList test = new MergeTwoSortedList();
        ListNode l1 = newNodeList(10,1);
        ListNode l2 = newNodeList(20,5);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println("After Merge: " + test.mergeTwoLists(l1,l2));
    }
    public static void main(String[] args) {
        testMergeTwoList();
    }
}
