/**
 * Leetcode - #897 - Increasing Order Search Tree
 */
package com.ciaoshen.leetcode.increasing_order_search_tree;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        // call();
        // call();
        // call();
    }

    /**========================== 【私有成员】 ============================*/

    // 测试用例 + 答案
    private TreeNode root1;
    private String answer1;

    /** 测试单个用例 */
    private void call(Solution s, TreeNode root, String answer) {
        System.out.println("Binary Tree: ");
        System.out.println("Result: ");
        System.out.println("Answer: ");
    }


    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        t.test(s1);
    }
}
