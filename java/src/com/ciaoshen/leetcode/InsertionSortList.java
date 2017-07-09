/**
 * Leetcode - Algorithm - Insertion Sort List
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


class InsertionSortList {
    /**
     * 原始版遍历插入
     */
    public class SolutionV1 {
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) { return head; }
            ListNode dummy = new ListNode(0); dummy.next = head;
            ListNode pre = head, cur = head.next;
            while (cur != null) {
                ListNode insertionPre = dummy;
                ListNode insertionCur = dummy.next;
                while (insertionCur != cur) {
                    if (insertionCur.val >= cur.val) {
                        System.out.println(cur.val + " inserted before " + insertionCur.val);
                        pre.next = cur.next;
                        ListNode temp = cur;
                        cur = cur.next;
                        temp.next = insertionCur;
                        insertionPre.next = temp;
                        break;
                    } else {
                        insertionPre = insertionCur;
                        insertionCur = insertionCur.next;
                    }
                }
                if (insertionCur == cur) {
                    System.out.println(cur.val + " insert after " + pre.val);
                    pre = cur;
                    cur = cur.next;
                }
                System.out.println("Now List = " + dummy.next);
            }
            return dummy.next;
        }
    }
    /**
     * 简洁版遍历插入
     */
    public class SolutionV2 {
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) { return head; }
            ListNode dummy = new ListNode(0); dummy.next = head;
            ListNode pre = head, cur = head.next;
            outWhile:
            while (cur != null) {
                ListNode insertionPre = dummy;
                ListNode insertionCur = dummy.next;
                inWhile:
                while (insertionCur != cur) {
                    if (insertionCur.val >= cur.val) {
                        pre.next = cur.next;
                        ListNode temp = cur;
                        cur = cur.next;
                        temp.next = insertionCur;
                        insertionPre.next = temp;
                        continue outWhile;
                    }
                    insertionPre = insertionCur;
                    insertionCur = insertionCur.next;
                }
                pre = cur;
                cur = cur.next;
            }
            return dummy.next;
        }
    }
    /**
     * 最简洁版遍历插入
     */
    public class Solution {
        public ListNode insertionSortList(ListNode head) {
            if (head == null || head.next == null) { return head; }
            ListNode dummy = new ListNode(0); dummy.next = head; // 哨兵
            ListNode pre = head, cur = head.next; // pre为顺序部分的尾巴。cur是乱序部分的开头，也是当前需要插入的节点
            outWhile:
            while (cur != null) {
                ListNode insertAfterIt = dummy; // 插入在它后面
                while (insertAfterIt.next != cur) {
                    if (insertAfterIt.next.val >= cur.val) {
                        ListNode temp = cur;
                        pre.next = cur.next;
                        cur = cur.next;
                        temp.next = insertAfterIt.next;
                        insertAfterIt.next = temp;
                        continue outWhile;
                    }
                    insertAfterIt = insertAfterIt.next;
                }
                pre = cur;
                cur = cur.next;
            }
            return dummy.next;
        }
    }
    private static InsertionSortList test = new InsertionSortList();
    private static void testInsertionSortList() {
        Solution solution = test.new Solution();
        ListNode list = ListNode.random(10,100);
        System.out.println("Original List: " + list);
        System.out.println("Sorted List: " + solution.insertionSortList(list));
    }
    public static void main(String[] args) {
        testInsertionSortList();
    }
}
