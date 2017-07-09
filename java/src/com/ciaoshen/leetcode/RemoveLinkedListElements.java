/**
 * Leetcode - Algorithm - Remove Linked List Elements
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class RemoveLinkedListElements {
    public class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = dummy;
            while (pre != null && pre.next != null) {
                if (pre.next.val == val) {
                    pre.next = pre.next.next;
                } else {
                    pre = pre.next;
                }
            }
            return dummy.next;
        }
    }
    private static RemoveLinkedListElements test = new RemoveLinkedListElements();
    private static Solution solution = test.new Solution();
    private static void callRemoveElements(ListNode head, int val) {
        System.out.println("Original List: " + head + ",    remove " + val);
        System.out.println("After: " + solution.removeElements(head,val));
    }
    private static void test() {
        Random r = new Random();
        int size = 10, max = 10;
        for (int i = 0; i < 10; i++) {
            ListNode list = ListNode.random(size,max);
            int val = r.nextInt(max)+1;
            callRemoveElements(list,val);
        }
    }
    public static void main(String[] args) {
        test();
    }
}
