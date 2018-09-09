/**
 * Leetcode - Algorithm - Reverse Linked List
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class ReverseLinkedList {
    public class SolutionV1 {
        public ListNode reverseList(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            while (head != null && head.next != null) {
                ListNode next = head.next;
                head.next = next.next;
                next.next = dummy.next;
                dummy.next = next;
            }
            return dummy.next;
        }
    }
    public class Solution {
        public ListNode reverseList(ListNode head) {
            return recursion(head);
        }
        public ListNode recursion(ListNode head) {
            if (head == null || head.next == null) { return head; }
            ListNode sub = recursion(head.next);
            head.next.next = head;
            head.next = null;
            return sub;
        }
    }
    private static ReverseLinkedList test = new ReverseLinkedList();
    private static Solution solution = test.new Solution();
    private static void callReverseList(ListNode head) {
        System.out.println("Original List: " + head);
        System.out.println("Reversed List: " + solution.reverseList(head));
    }
    private static void test() {
        ListNode list = ListNode.random(10,100);
        callReverseList(list);
    }
    public static void main(String[] args) {
        test();
    }
}
