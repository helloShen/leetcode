/**
 * Leetcode - Algorithm - Palindrome Linked List
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class PalindromeLinkedList {

    public class SolutionV1 {
        public boolean isPalindrome(ListNode head) {
            if (head == null) { return true; }
            Deque<Integer> stack = new LinkedList<>();
            int size = size(head);
            boolean isOdd = ((size % 2) == 0)? false : true;
            // System.out.println("Size is odd?" + isOdd + "\n");
            int mid = (size - 1) / 2;
            // System.out.println("Size = " + size + ", midian = " + mid);
            ListNode cur = head;
            for (int i = 0; i <= mid; i++) {
                stack.offerFirst(cur.val);
                cur = cur.next;
            }
            // System.out.println("Stack: " + stack);
            // System.out.println("Cur = " + cur.val);
            int rest = mid;
            if (isOdd) { stack.pollFirst(); rest = mid-1; }
            for (int i = 0; i <= rest; i++) {
                Integer history = stack.pollFirst();
                if (history == null || cur == null) { return false; }
                if (history != cur.val) { return false; }
                cur = cur.next;
            }
            // System.out.println("stack is empty? " + stack.isEmpty());
            // System.out.println("cur is null? " + (cur == null));
            if (!stack.isEmpty() || cur != null) { return false; }
            return true;
        }
        public int size(ListNode head) {
            ListNode cur = head;
            int count = 0;
            while (cur != null) {
                count++;
                cur = cur.next;
            }
            return count;
        }
    }
    public class Solution {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) { return true; }
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                // System.out.println("Slow = " + slow.val + ", Fast = " + fast.val);
                fast = fast.next.next;
                ListNode next = slow.next;
                slow.next = dummy.next;
                dummy.next = slow;
                if (head.next != null) { head.next = null; }
                slow = next;
            }
            if (fast != null) { slow = slow.next; } // size is odd
            head = dummy.next;
            // System.out.println("Dummy = " + dummy);
            // System.out.println("Head = " + head);
            // System.out.println("Slow = " + slow);
            while (head != null && slow != null) { // two linked list must have the same size
                if (head.val != slow.val) { return false; }
                head = head.next; slow = slow.next;
            }
            return true;
        }
    }
    private class Test {
        private void callIsPalindrome(ListNode head) {
            System.out.print("List: " + head);
            boolean result = solution.isPalindrome(head);
            System.out.print((result)? " is palindrome!\n" : " is not palindrome!\n");
        }
        private void test() {
            for (int i = 0; i < 10; i++) {
                ListNode list = ListNode.random(5,3);
                callIsPalindrome(list);
            }
            for (int i = 0; i < 10; i++) {
                ListNode list = ListNode.random(6,3);
                callIsPalindrome(list);
            }
        }
    }
    private static PalindromeLinkedList problem = new PalindromeLinkedList();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
