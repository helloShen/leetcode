/**
 * Leetcode - Algorithm - Remove Duplicates From Sorted List Two
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class RemoveDuplicatesFromSortedListTwo {
    /**
     * Use Set to check duplicate
     * Use extra space
     * @param  head [Target List]
     * @return      [A new list without duplicates]
     */
    public ListNode deleteDuplicatesV1(ListNode head) {
        Set<Integer> numMemo = new HashSet<>();
        Set<Integer> vals = new LinkedHashSet<>();
        ListNode headCursor = head;
        while (headCursor != null) {
            int value = headCursor.val;
            if (numMemo.add(value)) {
                vals.add(value);
            } else {
                vals.remove(value);
            }
            headCursor = headCursor.next;
        }
        ListNode sentinel = new ListNode(0), senCursor = sentinel;
        for (int num : vals) {
            senCursor.next = new ListNode(num);
            senCursor = senCursor.next;
        }
        return sentinel.next;
    }

    public ListNode deleteDuplicatesV2(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode cursor = head, last = null, lastlast = null;
        while (cursor != null) {
            if (last == null || cursor.val != last.val) { // not duplicate
                stack.offerFirst(cursor.val); // insert last
                System.out.println("First find " + cursor.val);
                System.out.println(stack);
            } else if (lastlast == null || (lastlast != null && cursor.val != lastlast.val)){ // first duplicate
                stack.pollFirst(); // remove last
                System.out.println("First duplicate " + cursor.val);
                System.out.println(stack);
            }
            lastlast = last; last = cursor; cursor = cursor.next;
        }
        ListNode sentinel = new ListNode(0), senCursor = sentinel;
        while (!stack.isEmpty()) {
            senCursor.next = new ListNode(stack.pollLast());
            senCursor = senCursor.next;
        }
        return sentinel.next;
    }
    public ListNode deleteDuplicatesV3(ListNode head) {
        // return directly if length < 3
        if (head == null || head.next == null) { return head; }
        if (head.next.next == null) { return (head.val == head.next.val)? null:head; }
        Deque<Integer> stack = new LinkedList<>();
        if (head.val != head.next.val) {
            stack.offerFirst(head.val);
            stack.offerFirst(head.next.val);
        }
        ListNode lastlast = head, last = head.next, cursor = head.next.next;
        while (cursor != null) {
            if (cursor.val != last.val) { // not duplicate
                stack.offerFirst(cursor.val); // insert last
                System.out.println("First find " + cursor.val);
                System.out.println(stack);
            } else if (cursor.val != lastlast.val) { // first duplicate
                stack.pollFirst(); // remove last
                System.out.println("First duplicate " + cursor.val);
                System.out.println(stack);
            }
            lastlast = last; last = cursor; cursor = cursor.next;
        }
        ListNode sentinel = new ListNode(0), senCursor = sentinel;
        while (!stack.isEmpty()) {
            senCursor.next = new ListNode(stack.pollLast());
            senCursor = senCursor.next;
        }
        return sentinel.next;
    }
    public ListNode deleteDuplicatesV4(ListNode head) {
        ListNode sentinel = new ListNode(0), res = sentinel;
        ListNode cursor = head, last = null, lastlast = null;
        ListNode candidate = null;
        while (cursor != null) {
            if (last == null || cursor.val != last.val) { // not duplicate
                if (candidate != null) {
                    res.next = candidate;
                    res = res.next;
                }
                candidate = cursor; // set candidate
                System.out.println("Set candidate " + candidate.val);
                System.out.println(sentinel);
            } else if (lastlast == null || (lastlast != null && cursor.val != lastlast.val)){ // first duplicate
                System.out.println("Cancel candidate " + candidate.val);
                candidate = null; // cancel candidate
                System.out.println(sentinel);
            }
            lastlast = last; last = cursor; cursor = cursor.next;
        }
        if (candidate != null) {
            res.next = candidate;
            res = res.next;
        }
        res.next = null; // cut the tail
        return sentinel.next;
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0), res = sentinel;
        sentinel.next = head;
        ListNode cur = head, pre = sentinel;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) { // until last occurrence of the same number
                cur = cur.next;
            }
            if (cur == pre.next) { // no duplicate
                res.next = cur; res = res.next;
            }
            pre = cur; cur = cur.next;
        }
        res.next = null;
        return sentinel.next;
    }
    private static RemoveDuplicatesFromSortedListTwo test = new RemoveDuplicatesFromSortedListTwo();
    private static ListNode oneTwoTwo() {
        ListNode res = new ListNode(1);
        res.next = new ListNode(2);
        res.next.next = new ListNode(2);
        return res;
    }
    private static void testDeleteDuplicates() {
        for (int i = 0; i < 10; i++) {
            ListNode list = ListNode.random(10,10);
            System.out.println("Before: " + list);
            System.out.println("After: " + test.deleteDuplicates(list));
        }
    }
    private static void testDeleteDuplicatesSorted() {
        for (int i = 0; i < 10; i++) {
            ListNode list = ListNode.randomSorted(10,10);
            System.out.println("Before: " + list);
            System.out.println("After: " + test.deleteDuplicates(list));
        }
        ListNode oneTwoTwo = oneTwoTwo();
        System.out.println("Before: " + oneTwoTwo);
        System.out.println("After: " + test.deleteDuplicates(oneTwoTwo));
    }
    public static void main(String[] args) {
        //testDeleteDuplicates();
        testDeleteDuplicatesSorted();
    }
}
