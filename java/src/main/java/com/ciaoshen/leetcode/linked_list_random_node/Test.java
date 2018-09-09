/**
 * Leetcode - #382 - Linked List Random Node
 */
package com.ciaoshen.leetcode.linked_list_random_node;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    private static final int SIZE = 1;
    private static final int MAX_LEN = 20;
    private static final int MAX_VAL = 1000;
    private static final Random R = new Random();
    private ListNode[] testcases;

    public Test() {
        testcases = new ListNode[SIZE];
        for (int i = 0; i < SIZE; i++) {
            testcases[i] = ListNode.random(R.nextInt(MAX_LEN) + 1, MAX_VAL);
        }
    }

    public void test(Solution s) {
        for (ListNode testcase : testcases) {
            call(s, testcase);
        }
    }
    
    private void call(Solution s, ListNode head) {
        System.out.println(head);
        int times = 100;
        s.init(head);
        for (int i = 0; i < times; i++) {
            System.out.print(s.getRandom() + ", ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        Solution s2 = new Solution2();
        Solution s3 = new Solution3();
        Solution s4 = new Solution4();
        // t.test(s1);
        // t.test(s2);
        // t.test(s3);
        t.test(s4);
    }
}
