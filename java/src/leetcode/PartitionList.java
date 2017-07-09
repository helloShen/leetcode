/**
 * Leetcode - Algorithm - Partition List
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class PartitionList {
    //Given 1->4->3->2->5->2 and x = 3,
    //return 1->2->2->4->3->5.
    public ListNode partitionV1(ListNode head, int x) {
        ListNode ls = new ListNode(0), gt = new ListNode(0), lsHead = ls, gtHead = gt;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                ls.next = cur;
                ls = ls.next;
            } else {
                gt.next = cur;
                gt = gt.next;
            }
            cur = cur.next;
        }
        ls.next = gtHead.next;
        gt.next = null;
        return lsHead.next;
    }
    public ListNode partition(ListNode head, int x) {
        ListNode sentinel = new ListNode(0), cur = sentinel;
        ListNode ls = new ListNode(0), lsHead = ls;
        sentinel.next = head;
        while (cur != null && cur.next != null) {
            if (cur.next.val < x) { // 发现小点
                ls.next = cur.next; // ls链表收编此小点
                ls = ls.next;
                cur.next = cur.next.next; // 主链表跳过此此小点
            } else { // 主链表正常推进
                cur = cur.next;
            }
        }
        ls.next = sentinel.next;
        return lsHead.next;
    }
    private static PartitionList test = new PartitionList();
    private static Random r = new Random();
    private static void testPartition(int size) {
        for (int i = 0; i < size; i++) {
            ListNode list = ListNode.random(size,size);
            int target = r.nextInt(size) + 1;
            System.out.println("Before: " + list + "    >>> target = " + target);
            System.out.println("After: " + test.partition(list,target));
        }
    }
    public static void main(String[] args) {
        testPartition(10);
    }
}
