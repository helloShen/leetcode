/**
 * Leetcode - Algorithm - Remove Duplicates from Sorted List
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class RemoveDuplicatesFromSortedList {
    // 每次都跳到最后一个相同元素再插入
    // in space
    public ListNode deleteDuplicatesV1(ListNode head) {
        ListNode sentinel = new ListNode(0), res = sentinel;
        sentinel.next = head;
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            } // stop at last node holds the same value
            res.next = cur;
            res = res.next;
            cur = cur.next;
        }
        res.next = null;
        return sentinel.next;
    }
    // 总是和前一个数字比较
    public ListNode deleteDuplicatesV2(ListNode head) {
        ListNode sentinel = new ListNode(0), res = sentinel;
        sentinel.next = head;
        ListNode pre = null, cur = head;
        while (cur != null) {
            if (pre == null || cur.val != pre.val) {
                res.next = cur;
                res = res.next;
            }
            pre = cur; cur = cur.next;
        }
        res.next = null;
        return sentinel.next;
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0), res = sentinel;
        sentinel.next = head;
        ListNode cur = head;
        Integer register = null;
        while (cur != null) {
            if (register == null || cur.val != register) {
                res.next = cur;
                res = res.next;
                register = cur.val;
            }
            cur = cur.next;
        }
        res.next = null;
        return sentinel.next;
    }
    private static RemoveDuplicatesFromSortedList test = new RemoveDuplicatesFromSortedList();
    private static void testDeleteDuplicates() {
        for (int i = 0; i < 10; i++) {
            ListNode list = ListNode.randomSorted(10,10);
            System.out.println("Before: " + list);
            System.out.println("After: " + test.deleteDuplicates(list));
        }
    }
    public static void main(String[] args) {
        testDeleteDuplicates();
    }
}
