/**
 * Leetcode - Algorithm - Reorder List
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

class ReorderList {
    public class SolutionV1 {
        public void reorderList(ListNode head) {
            // 1st iteration: get size
            ListNode cur = head;
            int size = 0;
            while (cur != null) {
                size++;
                cur = cur.next;
            }
            if (size < 3) { return; }
            System.out.println("Size = " + size);
            // 2nd iteration: 1-2-3-4-5-6  >>>   [1-2-3], [4-5-6]
            int mid = (size - 1) / 2; // 上位中位数
            cur = head;
            for (int i = 0; i < mid; i++) {
                cur = cur.next;
            }
            ListNode headTwo = cur.next;
            cur.next = null;
            System.out.println("First Half: " + head);
            System.out.println("Second Half: " + headTwo);
            // 2nd iteration: [1-2-3], [4-5-6]  >>>   [1-2-3], [6-5-4]
            ListNode curTwo = headTwo;
            while (curTwo.next != null) {
                ListNode temp = curTwo.next;
                curTwo.next = temp.next;
                temp.next = headTwo;
                headTwo = temp;
            }
            System.out.println("First Half: " + head);
            System.out.println("Second Half: " + headTwo);
            // 3rd iteration: [1-2-3],[6-5-4]  >>>  1-6-2-5-3-4
            cur = head;
            curTwo = headTwo;
            while (cur != null && curTwo != null) {
                ListNode one = cur;
                ListNode two = curTwo;
                cur = cur.next;
                curTwo = curTwo.next;
                two.next = one.next;
                one.next = two;
            }
        }
    }
    public class Solution {
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) { return; }
            // 1st iteration: 1-2-3-4-5-6  >>>   [1-2-3], [4-5-6]
            ListNode walker = head, runner = head, pre = head;
            while (runner != null && runner.next != null) {
                pre = walker;
                walker = walker.next;
                runner = runner.next.next;
            }
            ListNode headTwo = walker;
            pre.next = null;
            System.out.println("First Half: " + head);
            System.out.println("Second Half: " + headTwo);
            // 1st iteration: [1-2-3], [4-5-6]  >>>   [1-2-3], [6-5-4]
            headTwo = reverse(headTwo);
            System.out.println("First Half: " + head);
            System.out.println("Second Half: " + headTwo);
            // 2nd iteration: [1-2-3],[6-5-4]  >>>  1-6-2-5-3-4
            head = merge(head,headTwo);
            System.out.println("After Merge: " + head);
        }
        public ListNode reverse(ListNode head) {
            ListNode cur = head;
            while (cur.next != null) {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = head;
                head = next;
            }
            return head;
        }
        public ListNode merge(ListNode firstHalf, ListNode secondHalf) {
            ListNode cur = firstHalf, curTwo = secondHalf, temp = null;
            while (cur != null && curTwo != null) {
                temp = curTwo;
                curTwo = curTwo.next;
                temp.next = cur.next;
                cur.next = temp;
                cur = temp.next;
            }
            if (curTwo != null) { temp.next = curTwo; }
            return firstHalf;
        }
    }


    private static ReorderList test = new ReorderList();
    private static void testReorderList(ListNode list) {
        Solution solution = test.new Solution();
        System.out.println("Original List: " + list);
        solution.reorderList(list);
        System.out.println("Reordered List: " + list);
    }
    public static void main(String[] args) {
        ListNode list1 = ListNode.randomSorted(10,100);
        ListNode list2 = ListNode.randomSorted(11,100);
        testReorderList(list1);
        testReorderList(list2);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        one.next = two;
        testReorderList(one);
        ListNode three = new ListNode(3);
        two.next = three;
        testReorderList(one);
    }
}
