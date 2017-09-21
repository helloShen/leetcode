/**
 * Leetcode - Algorithm - OddEvenLinkedList
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class OddEvenLinkedList implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private OddEvenLinkedList() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public ListNode oddEvenList(ListNode head); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public ListNode oddEvenList(ListNode head) {
            ListNode odd = new ListNode(0); // dummy odd
            ListNode even = new ListNode(0); // dummy even
            odd.next = even;
            even.next = head;
            ListNode cur = head;
            while (cur != null) {
                // find next odd
                ListNode nextOdd = cur.next;
                if (nextOdd != null) { nextOdd = nextOdd.next; }
                // insert curr odd
                even.next = cur.next;
                even = cur.next;
                cur.next = odd.next;
                odd.next = cur;
                odd = cur;
                // pass to next odd
                cur = nextOdd;
            }
            odd.next = odd.next.next; // skip dummy even
            return head;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public ListNode oddEvenList(ListNode head) {
            if (head == null) { return null; }
            ListNode odd = head, even = head.next, firstEven = even;
            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = firstEven;
            return head;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public ListNode oddEvenList(ListNode head) {
            return null;
        }
    }
    // you can expand more solutions HERE if you want...


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private OddEvenLinkedList problem = new OddEvenLinkedList();
        private Solution solution = null;

        private void call(ListNode head) {
            System.out.println("Before: " + head);
            solution.oddEvenList(head);
            System.out.println("After: " + head);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            // Test cases
            ListNode head1 = stdListNode(1);
            ListNode head2 = stdListNode(2);
            ListNode head3 = stdListNode(10);
            call(head1);
            call(head2);
            call(head3);
        }
        // create 1->2->3->4->5 standard ListNode according to the size
        private ListNode stdListNode(int size) {
            if (size < 0) { return null; }
            ListNode[] nodes = new ListNode[size];
            for (int i = 1; i <= size; i++) {
                nodes[i-1] = new ListNode(i);
            }
            for (int i = 0; i < size-1; i++) {
                nodes[i].next = nodes[i+1];
            }
            return nodes[0];
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
