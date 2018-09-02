/**
 * Leetcode - #369 - Plus One Linked List
 */
package com.ciaoshen.leetcode.plus_one_linked_list;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {
    public ListNode plusOne(ListNode head) {
        int carry = doubleLinked(head);     
        if (carry == 1) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }
    
    private int doubleLinked(ListNode node) {
        if (node == null) {
            return 1;
        }
        node.val += doubleLinked(node.next);
        int carry = node.val / 10;
        node.val %= 10;
        return carry;
    }
}