/**
 * Leetcode - Merge K sorted lists
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode sentinel = new ListNode(0), cursor = sentinel;
        while (true) {
            int flag = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                ListNode current = lists[i];
                if (current != null && current.val < min) {
                    min = current.val;
                    flag = i;
                }
            }
            if (flag == -1) { break; } // the only exit point
            ListNode selected = lists[flag];
            cursor.next = lists[flag];
            lists[flag] = selected.next;
            cursor = cursor.next;
        }
        return sentinel.next;
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
    private static void testMergeKLists() {
        MergeKSortedLists test = new MergeKSortedLists();
        ListNode l1 = newNodeList(10,1);
        ListNode l2 = newNodeList(20,5);
        ListNode l3 = newNodeList(30,2);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        ListNode[] lists = new ListNode[]{l1,l2,l3};
        System.out.println("After Merge: " + test.mergeKLists(lists));
    }
    public static void main(String[] args) {
        testMergeKLists();
    }
}
