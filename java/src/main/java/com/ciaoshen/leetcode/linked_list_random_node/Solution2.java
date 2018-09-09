/**
 * Leetcode - #382 - Linked List Random Node
 */
package com.ciaoshen.leetcode.linked_list_random_node;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * ！注意： 这是标准“蓄水池算法”， 但不是有效解
 * 因为题目要求从一开始每个数字产生概率就相同。但“蓄水池算法”在遍历完所有数字之前，只保证目前遍历到的所有数字概率相等。
 */
class Solution2 implements Solution {
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public void init(ListNode head) {
        HEAD = head;
        curr = head;
        size = 0;
        reachEnd = false;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int next = 0;
        while (true) {
            if (!reachEnd) {
                ++size;
            }
            next = curr.val;
            curr = curr.next;
            if (curr == null) {
                reachEnd = true;
                curr = HEAD;
            }
            if (R.nextInt(size) + 1 == size) {
                break;      
            }
        }
        return next;
    }
    
    
    private ListNode HEAD;
    private ListNode curr;
    private int size;
    private boolean reachEnd;
    private final Random R = new Random();
}