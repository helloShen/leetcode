/**
 * Leetcode - Algorithm - Convert Sorted List to Binary Search Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;
import com.ciaoshen.leetcode.myUtils.ListNode;

class ConvertSortedListToBinarySearchTree {
    public TreeNode sortedListToBSTV1(ListNode head) {
        int[] array = listToArray(head);
        return sortedArrayToBST(array);
    }
    public int[] listToArray(ListNode head) {
        ListNode cur = head;
        int size = 0;
        while (cur != null) { size++; cur = cur.next; }
        int[] array = new int[size];
        cur = head;
        for (int i = 0; i < size; i++) {
            if (cur != null) {
                array[i] = cur.val;
                cur = cur.next;
            } else {
                break;
            }
        }
        return array;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return recursionV1(nums,0,nums.length-1);
    }
    public TreeNode recursionV1(int[] nums, int lo, int hi) {
        if (lo > hi) { return null; }
        int mid = lo + (hi - lo) / 2; // 下位中位数
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursionV1(nums,lo,mid-1);
        root.right = recursionV1(nums,mid+1,hi);
        return root;
    }
    public TreeNode sortedListToBST(ListNode head) {
        int size = size(head);
        return recursion(head,size);
    }
    public TreeNode recursion(ListNode head, int size) {
        if (size == 0) { return null; }
        int half = (size - 1) / 2;
        ListNode cur = head;
        for (int i = 0; i < half; i++) { cur = cur.next; }
        TreeNode root = new TreeNode(cur.val);
        root.left = recursion(head,half);
        root.right = recursion(cur.next,size-half-1);
        return root;
    }
    public int size(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            count++; cur = cur.next;
        }
        return count;
    }
    private static ConvertSortedListToBinarySearchTree test = new ConvertSortedListToBinarySearchTree();
    private static void testSortedListToBST() {
        Random r = new Random();
        int size = r.nextInt(30)+1;
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int i = 1; i <= size; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        System.out.println(dummy.next);
        System.out.println(test.sortedListToBST(dummy.next).bfs());
    }
    public static void main(String[] args) {
        testSortedListToBST();
    }
}
