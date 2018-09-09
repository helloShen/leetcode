/**
 * Leetcode - #373 - Find K Pairs with Smallest Sums
 * 
 * TO DO:
 *      1. 最好用JUnit的断言
 */
package com.ciaoshen.leetcode.find_k_pairs_with_smallest_sums;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class TestTemplate {

    /**========================== 【静态单例器】 ============================*/

    /** 测试单例对外接口 */
    public static TestTemplate getTest() {
        return test;
    }
    /** 测试单例 */
    private static final TestTemplate test = new TestTemplate();


    /**========================== 【公有接口】 ============================*/

    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        for (int i = 0; i < testcases.size(); i++) {
            call(s, testcases.get(i), answers.get(i));
        }
    }
    /** 测试用例对外开放 */
    public List<String> getTestcases() {
        return new ArrayList<String>(testcases);
    }
    /** 测试答案对外开放 */
    public List<String> getAnswers() {
        return new ArrayList<String>(answers);
    }


    /**========================== 【私有成员】 ============================*/

    /** 注册测试用例以及答案 [注：测试用例和答案必须按顺序一一对应] */
    private List<String> testcases; // 串行化的测试用例
    private List<String> answers;

    /** 构造测试用例和答案 */
    private TestTemplate() {
        // 初始化测试用例

        // 初始化答案

    }
    /** 测试单个用例 */
    private void call(Solution s, String testcase, String answer) {
        System.out.println("Before: \t");
        System.out.println("After:  \t");
        System.out.println("Answer: \t");
    }


    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        TestTemplate t = Test.getTest();
        Solution s1 = new Solution1();
        t.test(s1);
    }
}
