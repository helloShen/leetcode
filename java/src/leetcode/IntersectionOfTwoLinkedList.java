/**
 * Leetcode - Algorithm - Intersection of Two LinkedList
 * Definition for singly-linked list.
 * public class ListNode {
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

class IntersectionOfTwoLinkedList {
    /**
     * 用一个Set记录所有listA的元素。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public class SolutionV1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) { return null; }
            Set<ListNode> memo = new HashSet<>();
            ListNode cur = headA;
            while (cur != null) {
                memo.add(cur);
                cur = cur.next;
            }
            cur = headB;
            while (cur != null) {
                if (memo.contains(cur)) { return cur; }
                cur = cur.next;
            }
            return null;
        }
    }
    /**
     * 一个Runner和一个Walker的追逐算法。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public class SolutionV2 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) { return null; }
            ListNode end = headA;
            while (end != null && end.next != null) { // cur stop at the last node
                end = end.next;
            }
            //System.out.println("Find End: " + end);
            end.next = headA; // make a circle
            ListNode walker = headB, runner = headB;
            while (runner != null && runner.next != null) {
                walker = walker.next;
                runner = runner.next.next;
                if (runner == walker) { // find circle
                    //System.out.println("Runner meets Walker @" + runner.val);
                    ListNode anotherWalker = headB;
                    while (anotherWalker != walker) {
                        //System.out.println("Walker @" + walker.val);
                        //System.out.println("Another Walker @" + anotherWalker.val);
                        anotherWalker = anotherWalker.next;
                        walker = walker.next;
                    }
                    end.next = null;
                    return walker;
                }
            }
            end.next = null;
            return null;
        }
    }
    /**
     * 计算listA和listB的长度
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public class SolutionV3 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) { return null; }
            int sizeA = size(headA);
            int sizeB = size(headB);
            ListNode curA = headA, curB = headB;
            // curA,curB归同一起跑线
            while (sizeA > sizeB) {
                curA = curA.next;
                sizeA--;
            }
            while (sizeB > sizeA) {
                curB = curB.next;
                sizeB--;
            }
            while (curA != curB && curA != null) {
                curA = curA.next;
                curB = curB.next;
            }
            return (curA == null)? null : curA;
        }
        public int size(ListNode list) {
            ListNode cur = list;
            int count = 0;
            while (cur != null) {
                count++;
                cur = cur.next;
            }
            return count;
        }
    }
    /**
     * 不用计算listA和listB的长度。都跑lengthA + lengthB
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) { return null; }
            ListNode curA = headA, curB = headB;
            while (curA != curB) { // will stop after seconde iteration if no intersection exists
                curA = (curA.next == null)? headB : curA.next;
                curB = (curB.next == null)? headA : curB.next;
            }
            return curA;
        }
    }
    private static IntersectionOfTwoLinkedList test = new IntersectionOfTwoLinkedList();
    private static Solution solution = test.new Solution();
    private static ListNode[] randomIntersectionList(int size, int max) {
        ListNode list1 = ListNode.random(size+1,max); ListNode cur1 = list1;
        ListNode list2 = ListNode.random(size,max); ListNode cur2 = list2;
        ListNode list3 = ListNode.random(size,max);
        for (int i = 0; i < size-1; i++) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        cur1.next.next = list3;
        cur2.next = list3;
        System.out.println("List1: " + list1);
        System.out.println("List2: " + list2);
        System.out.println("List3: " + list3);
        return new ListNode[]{list1,list2};
    }
    private static void testGetIntersectionNode() {
        ListNode[] intersection = randomIntersectionList(5,100);
        System.out.println("Begin of intersection = " + solution.getIntersectionNode(intersection[0],intersection[1]));
    }
    public static void main(String[] args) {
        //ListNode[] intersection = randomIntersectionList(5,100);
        testGetIntersectionNode();
    }
}
