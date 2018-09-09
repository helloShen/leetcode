/**
 * Leetcode - #382 - Linked List Random Node
 */
package com.ciaoshen.leetcode.linked_list_random_node;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * 所有数字读入内存中的数组
 * 随机获得下标
 * 
 * 只有初始化读取数字是O(n)
 * 之后的getRandom()是O(1)
 */
class Solution1 implements Solution {
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public void init(ListNode head) {
        size = size(head);
        nums = collectNums(head, size);
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        return nums[R.nextInt(size)];
    }
    
    /**===================== 【private members】 ========================*/
    private int size;
    private int[] nums;

    private final Random R = new Random();
    private int size(ListNode head) {
        int count = 0;
        while (head != null) {
            ++count;
            head = head.next;
        }
        return count;
    }
    private static int[] collectNums(ListNode head, int size) {
        int[] numsArray = new int[size];
        int p = 0;
        while (head != null) {
            numsArray[p++] = head.val;
            head = head.next;
        }
        return numsArray;
    }   

}