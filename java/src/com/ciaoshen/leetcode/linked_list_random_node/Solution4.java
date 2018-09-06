/**
 * Leetcode - #382 - Linked List Random Node
 */
package com.ciaoshen.leetcode.linked_list_random_node;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * 适应本题的“蓄水池算法”变种
 * 从一开始就求出数字总数，并对完整的数字空间随机取数字
 */
class Solution4 implements Solution {
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public void init(ListNode head) {
            HEAD = head;
            curr = head;
            size = size(head);
        }
        
        /** Returns a random node's value. */
        public int getRandom() {
            int offset = R.nextInt(size);
            while (offset-- > 0) {
                curr = (curr.next == null)? HEAD : curr.next;
            }
            return curr.val;
        }
        
        /**======================= 【private member】 =======================*/ 
        private ListNode HEAD;
        private ListNode curr;
        private int size;
        private final Random R = new Random();
        
        private static int size(ListNode head) {
            int count = 0;
            while (head != null) {
                ++count;
                head = head.next;
            }
            return count;
        }

}