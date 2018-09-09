/**
 * Leetcode - Algorithm -
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.RandomListNode;

class CopyListWithRandomPointer {
    public class SolutionV1 {
        public RandomListNode copyRandomList(RandomListNode head) {
            RandomListNode dummy = new RandomListNode(0);
            Map<Integer,RandomListNode> memo = new HashMap<>();
            RandomListNode cur = head;
            RandomListNode copyCur = dummy;
            while (cur != null) { // copy next chain
                RandomListNode copyNode = new RandomListNode(cur.label);
                memo.put(cur.label,copyNode);
                copyCur.next = copyNode;
                cur = cur.next;
                copyCur = copyCur.next;
            }
            cur = head;
            copyCur = dummy.next;
            while (cur != null) { // copy random chain
                if (cur.random != null) {
                    copyCur.random = memo.get(cur.random.label);
                }
                cur = cur.next;
                copyCur = copyCur.next;
            }
            return dummy.next;
        }
    }
    /**
     * 不使用额外Map
     */
    public class Solution {
        public RandomListNode copyRandomList(RandomListNode head) {
            RandomListNode cur = head, dummy = new RandomListNode(0), copyCur = dummy;
            while (cur != null) { // copy next chain, and insert new node right after the original node
                RandomListNode newNode = new RandomListNode(cur.label);
                //System.out.println("New Node " + cur.label + " created!");
                newNode.next = cur.next;
                cur.next = newNode;
                copyCur.next = newNode;
                copyCur = newNode.next;
                cur = newNode.next;
            }
            cur = head;
            while (cur != null) { // 拷贝random
                copyCur = cur.next;
                if (cur.random != null) {
                    copyCur.random = cur.random.next; // 拷贝random指针
                }
                cur = copyCur.next;
            }
            cur = head;
            while (cur != null) { // 从original list上剥离copy list
                copyCur = cur.next;
                cur.next = copyCur.next;
                if (cur.next != null) {
                    copyCur.next = cur.next.next;
                } else {
                    copyCur.next = null;
                }
                cur = cur.next;
            }
            //System.out.println(head.next == dummy.next.next);
            return dummy.next;
        }
    }
    private static CopyListWithRandomPointer test = new CopyListWithRandomPointer();
    private static RandomListNode testCaseOne() {
        RandomListNode minusOne = new RandomListNode(-1);
        RandomListNode one = new RandomListNode(1);
        minusOne.next = one;
        return minusOne;
    }
    private static void testCopyRandomList(RandomListNode list) {
        Solution solution = test.new Solution();
        System.out.println(">>> Origin List <<<");
        System.out.println(list);
        System.out.println(list.showRandomChain());
        System.out.println(">>> Copy List <<<");
        RandomListNode copy = solution.copyRandomList(list);
        System.out.println(copy);
        System.out.println(copy.showRandomChain());
    }
    public static void main(String[] args) {
        RandomListNode list = RandomListNode.random(10,100);
        //RandomListNode list = testCaseOne();
        testCopyRandomList(list);
    }
}
