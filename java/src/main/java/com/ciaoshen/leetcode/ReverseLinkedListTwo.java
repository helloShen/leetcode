/**
 * Leetcode - Algorithm - Reverse Linked List Two
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class ReverseLinkedListTwo {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) { return head; }
        ListNode sentinel = new ListNode(0), wall = sentinel; // m点左边的第一个点。m为head时，
        sentinel.next = head;
        for (int i = 1; i < m; i++) {
            wall = wall.next;
        }
        ListNode left = wall.next, right = left.next;
        for (int i = m + 1; i <= n; i++) {
            // System.out.println("Wall = " + wall.val + ", Left = " + left.val + ", Right = " + right.val);
            left.next = right.next;
            right.next = wall.next;
            wall.next = right;
            right = left.next;
            // System.out.println("i = " + i + " >>>    " + sentinel.next);
        }
        return sentinel.next;
    }
    private static ReverseLinkedListTwo test = new ReverseLinkedListTwo();
    private static void testReverseBetween(int size) {
        for (int i = 0; i < size; i++) {
            ListNode list = ListNode.random(size,size);
            int start = 1 + (i % (size/2-1));
            int end = start + size/2-1;
            System.out.println("Before: " + list + ", From: " + start + ", To: " + end);
            System.out.println("After: " + test.reverseBetween(list,start,end));
        }
    }
    private static void testReverseBetweenCornerCases() {
        ListNode one = new ListNode(1); // 1
        ListNode oneTwo = new ListNode(1); // 1->2
        oneTwo.next = new ListNode(2);
        ListNode oneTwoThree = new ListNode(1); // 1->2->3
        oneTwoThree.next = new ListNode(2);
        oneTwoThree.next.next = new ListNode(3);
        List<ListNode> lists = new ArrayList<>(Arrays.asList(new ListNode[]{one,oneTwo,oneTwoThree}));
        for (int start = 1, end = 1; end < 4; end++) {
            ListNode list = lists.get(end-1);
            System.out.println("Before: " + list + ", From: " + start + ", To: " + end);
            System.out.println("After: " + test.reverseBetween(list,start,end));
        }
    }
    public static void main(String[] args) {
        testReverseBetween(10);
        testReverseBetweenCornerCases();
    }
}
