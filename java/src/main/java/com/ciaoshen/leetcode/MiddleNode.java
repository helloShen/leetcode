/**
 * Leetcode - Algorithm - MiddleNode
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MiddleNode implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MiddleNode() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public ListNode middleNode(ListNode head); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public ListNode middleNode(ListNode head) {
            if (head == null) { return null; }
            ListNode lead = new ListNode(0);
            lead.next = head;
            ListNode fast = lead, slow = lead;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow.next;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public ListNode middleNode(ListNode head) {
            return null;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public ListNode middleNode(ListNode head) {
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
        private MiddleNode problem = new MiddleNode();
        private Solution solution = null;

        // call method in solution
        private void call() {
            Random r = new Random();
            int len = r.nextInt(20);
            ListNode list = ListNode.random(len,100);
            System.out.println(list + "\t [Length=" + len + "]");
            System.out.println("Middle Node = " + solution.middleNode(list).val );
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            //... ...
            //... ...
            //... ...
            //... ...
            //... ...
            //... ...

            /** involk call() method HERE */
            call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1); // call by the solution id
    }
}
