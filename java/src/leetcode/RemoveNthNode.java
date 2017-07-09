/**
 * Leetcode - Remove Nth Node From End of List
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
public class RemoveNthNode {
    // 如果只是删除倒数第2个节点，可以这样写。只遍历一遍。
    public ListNode remove2ndFromEnd(ListNode head) {
        if (head == null || head.next == null) { return head; } // length < 2
        if (head.next.next == null) { return head.next; } // length = 2
        ListNode cursor = head;
        while (cursor.next.next.next != null) {
            cursor = cursor.next;
        }
        cursor.next = cursor.next.next;
        return head;
    }
    public ListNode removeNthFromEndV1(ListNode head, int n) {
        List<ListNode> ref = parseListNode(head);
        int size = ref.size();
        if (size-1 < n) { return head; } // length < n
        int prevIndex = size - n - 1;
        ListNode prevNode = ref.get(prevIndex);
        prevNode.next = prevNode.next.next;
        return ref.get(0).next; // the 1st node after the sentinel
    }
    public List<ListNode> parseListNode(ListNode head) {
        List<ListNode> result = new ArrayList<>();
        ListNode sentinel = new ListNode(0); // sentinel
        sentinel.next = head;
        result.add(sentinel);
        ListNode cursor = head;
        while (cursor != null) {
            result.add(cursor);
            cursor = cursor.next;
        }
        return result;
    }
    // 不使用额外空间
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode slow = null, fast = sentinel;
        int cursor = 0; // index of fast
        while (fast != null) {
            fast = fast.next;
            if (slow != null) { slow = slow.next; }
            if (cursor - n == 0) { slow = sentinel; } // slow和fast之间间隔n格距离
            cursor++;
        }
        if (slow != null) {
            slow.next = slow.next.next;
        }
        return sentinel.next;
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
    private static void testRemoveNthFromEnd() {
        RemoveNthNode test = new RemoveNthNode();
        ListNode head = null;
        for (int i = 10; i > 0; i--) {
            ListNode newNode = new ListNode(i);
            newNode.next = head;
            head = newNode;
        }
        System.out.println(head);
        for (int i = 12; i > 0; i--) {
            System.out.println(test.removeNthFromEnd(head,i));
        }
    }
    public static void main(String[] args) {
        testRemoveNthFromEnd();
    }
}
