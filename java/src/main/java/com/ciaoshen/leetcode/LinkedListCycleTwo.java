/**
 * Leetcode - Algorithm - Linked List Cycle Two
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

class LinkedListCycleTwo {
    public class SolutionV1 {
        public ListNode detectCycle(ListNode head) {
            if (head == null) { return null; }
            Set<ListNode> memo = new HashSet<>();
            ListNode cur = head;
            while (cur != null) {
                if (memo.contains(cur)) { return cur; }
                memo.add(cur);
                cur = cur.next;
            }
            return null;
        }
    }
    public class SolutionV2 {
        public ListNode detectCycle(ListNode head) {
            if (head == null) { return null; }
            // find cycle
            ListNode mileStone = null;
            ListNode walker = head, runner = head;
            while (runner.next != null && runner.next.next != null) {
                runner = runner.next.next;
                walker = walker.next;
                if (runner == walker) {
                    mileStone = runner; // find the cycle, milestone must be one of the node in cycle
                    break;
                }
            }
            if (mileStone == null) { return null; } // do not have cycle
            ListNode cur = head, cycleCur = mileStone;
            while (true) { // assert: must have cycle
                do {
                    if (cur == cycleCur) { return cur; }
                    cycleCur = cycleCur.next;
                } while (cycleCur != mileStone);
                cur = cur.next;
            }
        }
    }
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            if (head == null) { return null; }
            // find cycle
            ListNode mileStone = null;
            ListNode walker = head, runner = head;
            int step = 0;
            while (runner.next != null && runner.next.next != null) {
                runner = runner.next.next;
                walker = walker.next;
                if (runner == walker) {
                    mileStone = runner; // find the cycle, milestone must be one of the node in cycle
                    break;
                }
                step++;
            }
            if (mileStone == null) { return null; } // do not have cycle
            ListNode cur = head, cycleCur = mileStone;
            while (cur != cycleCur) {
                cur = cur.next;
                cycleCur = cycleCur.next;
            }
            return cur;
        }
    }
    private static LinkedListCycleTwo test = new LinkedListCycleTwo();
    private static void testDetectCycle() {
        Solution solution = test.new Solution();
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4);
        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        one.next = two; two.next = three; three.next = four; four.next = five;
        five.next = three;
        ListNode result = solution.detectCycle(one);
        System.out.println("First Node of Cycle = " + result.val);
    }
    public static void main(String[] args) {
        testDetectCycle();
    }
}
