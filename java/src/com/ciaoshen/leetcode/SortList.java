/**
 * Leetcode - Algorithm - Sort List
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

class SortList {
    /**
     * 分治法，递归版
     */
    public class Solution {
        public ListNode sortList(ListNode head) {
            // base case: 长度为[0-1]
            if (head == null || head.next == null) { return head; }
            // slow停在下位中位点，是第二部分的开头
            // pre是slow的前一个节点，是第一部分的结尾
            ListNode slow = head, fast = head, pre = null;
            while (fast != null && fast.next != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            pre.next = null;
            ListNode left = sortList(head);
            ListNode right = sortList(slow);
            return merge(left,right);
        }
        public ListNode merge(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(0), cur = dummy, cur1 = list1, cur2 = list2;
            while (cur1 != null && cur2 != null) {
                if (cur1.val <= cur2.val) {
                    cur.next = cur1;
                    cur1 = cur1.next;
                } else {
                    cur.next = cur2;
                    cur2 = cur2.next;
                }
                cur = cur.next;
            }
            if (cur1 != null) { cur.next = cur1; }
            if (cur2 != null) { cur.next = cur2; }
            return dummy.next;
        }
    }
    private static SortList test = new SortList();
    private static void testMerge() {
        Solution solution = test.new Solution();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);
        one.next = three; three.next = seven; // 1->3->7
        two.next = four; four.next = eight; // 2->4->8
        System.out.println("First Part = " + one);
        System.out.println("Second Part = " + two);
        System.out.println("After Merge = " + solution.merge(one,two));
    }
    private static void testSortList() {
        Solution solution = test.new Solution();
        ListNode list = ListNode.random(10,100);
        System.out.println("Original List: " + list);
        System.out.println("After Sort: " + solution.sortList(list));
    }
    public static void main(String[] args) {
        //testMerge();
        testSortList();
    }
}
