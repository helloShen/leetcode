/**
 * Leetcode - Algorithm - Linked List Cycle
 *
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class LinkedListCycle {
    public class SolutionV1 {
        public boolean hasCycle(ListNode head) {
            ListNode cur = head;
            Set<ListNode> memo = new HashSet<>();
            while (cur != null) {
                if (cur.next != null && memo.contains(cur)) { return true; }
                memo.add(cur);
                cur = cur.next;
            }
            return false;
        }
    }
    public class SolutionV2 {
        public boolean hasCycle(ListNode head) {
            if (head == null) { return false; }
            ListNode cur = head.next;
            while (cur != null) {
                if (cur == head) { return true; }
                ListNode temp = cur;
                cur = cur.next;
                temp.next = head;
            }
            return false;
        }
    }
    public class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null) { return false; }
            ListNode walker = head, runner = head;
            while (runner.next != null && runner.next.next != null) { // 能跑到头，则没有cycle
                walker = walker.next;
                runner = runner.next.next;
                if (walker == runner) { return true; }
            }
            return false;
        }
    }
    public static void main(String[] args) {

    }
}
