/**
 * Leetcode - Algorithm - Rotate list
 */
package com.ciaoshen.leetcode;
import java.util.*;

class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) { return head; }
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        } // stop at the last element, get the size of list
        int breakPoint = size - 1 - (k % size);
        if (breakPoint != (size - 1)) { // when true, keep the same list
            ListNode newTail = head;
            for (int i = 0; i < breakPoint; i++) { // stop at the breaking point
                newTail = newTail.next;
            }
            tail.next = head;
            head = newTail.next;
            newTail.next = null;
        }
        return head;
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode cursor = this;
            while (cursor != null) {
                sb.append(cursor.val);
                cursor = cursor.next;
                if (cursor != null) { sb.append("->"); }
            }
            return sb.toString();
        }
    }
    private static Random r = new Random();
    private static int max = 100;
    private static ListNode newList(int size) {
        ListNode sentinel = new ListNode(Integer.MAX_VALUE), cursor = sentinel;
        for (int i = 0; i < size; i++) {
            cursor.next = new ListNode(r.nextInt(max));
            cursor = cursor.next;
        }
        return sentinel.next;
    }
    private static RotateList test = new RotateList();
    private static void testToString(int maxLen) {
        if (maxLen < 1) { maxLen = 1; }

        System.out.println(newList(r.nextInt(maxLen)+1));
    }
    private static void testRotateRight(int max) {
        ListNode list = newList(r.nextInt(max)+1);
        int k = r.nextInt(max*2)+1;
        System.out.println("[ " + list + " ]" + " rotate " + k + " times,");
        System.out.println("    >>> " + test.rotateRight(list,k) + "\n");
    }
    public static void main(String[] args) {
        //testToString(10);
        testRotateRight(10);
    }
}
