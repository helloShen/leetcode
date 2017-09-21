/**
 * Leetcode - Algorithm - AddTwoNumbersTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class AddTwoNumbersTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private AddTwoNumbersTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public ListNode addTwoNumbers(ListNode l1, ListNode l2); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /** 用数组缓存数字。 O(N) */
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1.val == 0 && l2.val == 0) { return new ListNode(0); }
            int len1 = getLen(l1);
            int len2 = getLen(l2);
            boolean l1IsLonger = (len1 > len2)? true : false;
            int diff = l1IsLonger? (len1 - len2) : (len2 - len1);
            int[] res = l1IsLonger? new int[len1+1] : new int[len2+1];
            ListNode longer = l1IsLonger? l1 : l2;
            ListNode shorter = l1IsLonger? l2 : l1;
            int start = 1;
            while (diff > 0) {
                res[start++] = longer.val;
                longer = longer.next;
                --diff;
            }
            while (longer != null) {
                res[start] += longer.val;
                longer = longer.next;
                res[start++] += shorter.val;
                shorter = shorter.next;
            }
            for (int i = res.length-1; i > 0; i--) {
                if (res[i] > 9) {
                    res[i] = res[i] % 10;
                    res[i-1] += 1; // carry
                }
            }
            start = 0;
            while (start < res.length && res[start] == 0) { ++start; } // trim leading zeros
            ListNode r = new ListNode(0), c = r; // dummy
            for (int i = start; i < res.length; i++) {
                ListNode newNode = new ListNode(res[i]);
                c.next = newNode;
                c = c.next;
            }
            return r.next;
        }
        private int getLen(ListNode l1) {
            int count = 0;
            while (l1 != null) {
                ++count;
                l1 = l1.next;
            }
            return count;
        }
    }

    /** 用Stack，O(N) */
    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            Deque<Integer> stack1 = new LinkedList<>();
            Deque<Integer> stack2 = new LinkedList<>();
            ListNode c1 = l1, c2 = l2;
            while (c1 != null) { stack1.offerFirst(c1.val); c1 = c1.next; }
            while (c2 != null) { stack2.offerFirst(c2.val); c2 = c2.next; }
            int sum = 0, carry = 0;
            ListNode head = null;
            while (!stack1.isEmpty() || !stack2.isEmpty()) {
                sum = carry;
                if (!stack1.isEmpty()) {  sum += stack1.pollFirst(); }
                if (!stack2.isEmpty()) { sum += stack2.pollFirst(); }
                ListNode newHead = new ListNode(sum % 10);
                newHead.next = head;
                head = newHead;
                carry = sum / 10;
            }
            if (carry == 1) {
                ListNode newHead = new ListNode(1);
                newHead.next = head;
                head = newHead;
            }
            return head;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return l1;
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
        private AddTwoNumbersTwo problem = new AddTwoNumbersTwo();
        private Solution solution = null;

        // call method in solution
        private void call(ListNode l1, ListNode l2) {
            System.out.println("List 1 = " + l1);
            System.out.println("List 2 = " + l2);
            System.out.println("Sum of l1 & l2 = " + solution.addTwoNumbers(l1,l2));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            ListNode seven = new ListNode(7);
            ListNode two = new ListNode(2);
            ListNode four1 = new ListNode(4);
            ListNode three = new ListNode(3);
            ListNode five = new ListNode(5);
            ListNode six = new ListNode(6);
            ListNode four2 = new ListNode(4);


            ListNode l11 = seven;
            seven.next = two;
            two.next = four1;
            four1.next = three;

            ListNode l12 = five;
            five.next = six;
            six.next = four2;

            ListNode zero1 = new ListNode(0);
            ListNode zero2 = new ListNode(0);

            ListNode l21 = zero1;
            ListNode l22 = zero2;

            /** involk call() method HERE */
            call(l11,l12);
            call(l21,l22);
            // call(l31,l32);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
