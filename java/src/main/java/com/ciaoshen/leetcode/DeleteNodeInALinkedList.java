/**
 * Leetcode - Algorithm - Delete Node in a Linked List
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.ListNode;

class DeleteNodeInALinkedList {
    public class Solution {
        public void deleteNode(ListNode node) {
            while (node.next != null) {
                node.val = node.next.val;
                if (node.next.next != null) {
                    node = node.next;
                } else {
                    node.next = null;
                }
            }
        }
    }
    private class Test {
        private void callDeleteNode(ListNode node) {
            solution.deleteNode(node);
        }
        private void test() {
            int size = 10;
            ListNode list = ListNode.random(10,1000);
            System.out.println("Original List: " + list);
            ListNode cur = list;
            for (int i = 0; i < (size/2)-1; i++) {
                cur = cur.next;
            }
            System.out.println("After delete node " + cur.val + ": ");
            callDeleteNode(cur);
            System.out.println("List become: " + list);
        }
    }
    private static DeleteNodeInALinkedList problem = new DeleteNodeInALinkedList();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
