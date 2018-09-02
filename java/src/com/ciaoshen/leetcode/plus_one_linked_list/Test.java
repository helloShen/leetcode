/**
 * Leetcode - #369 - Plus One Linked List
 */
package com.ciaoshen.leetcode.plus_one_linked_list;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
    
    /** 测试用例对外开放 */
    public List<ListNode> getTestcases() {
        return testcases;
    }
    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        for (int i = 0; i < testcases.size(); i++) {
            call(s, testcases.get(i), answers.get(i));
        }
    }


    /**========================== 【私有成员】 ============================*/

    /** 注册测试用例以及答案 [注：测试用例和答案必须按顺序一一对应] */
    private List<ListNode> testcases;
    private List<String> answers;

    /** 构造测试用例 [测试用例是测试类的核心资源] */
    private Test() { 
        // 测试用例
        ListNode case1 = null;
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        ListNode case2 = one;
        testcases = new ArrayList<ListNode>();
        testcases.add(case1);
        testcases.add(case2);

        // 答案
        String answer1 = "null";
        String answer2 = "[1,2,4]";
        answers = new ArrayList<String>();
        answers.add(answer1);
        answers.add(answer2);
    }
    /** 测试单个用例 */
    private void call(Solution s, ListNode list, String answer) {
        System.out.println("Before: \t" + list);
        System.out.println("After:  \t" + s.plusOne(list));
        System.out.println("Answer: \t" + answer + "\n");
    }

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        t.test(s1);
    }
}