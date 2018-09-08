/**
 * Leetcode - #771 - Jewels and Stones
 */
package com.ciaoshen.leetcode.jewels_and_stones;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        for (int i = 0; i < jArray.length; i++) {
            call(s, jArray[i], sArray[i], answers[i]);
        }
    }

    /**========================== 【私有成员】 ============================*/

    // 测试用例 + 答案
    private String j1  = "aA";
    private String s1  = "aAAbbbb";
    private String answer1 = "3";

    private String j2  = "z";
    private String s2  = "ZZ";
    private String answer2 = "0";

    private String[] jArray = new String[]{j1,j2};
    private String[] sArray = new String[]{s1,s2};
    private String[] answers = new String[]{answer1, answer2};

    /** 测试单个用例 */
    private void call(Solution s, String jewels, String stones, String answer) {
        System.out.println("Jewls: " + jewels);
        System.out.println("Stones: " + stones);
        System.out.println("Result: " + s.numJewelsInStones(jewels, stones));
        System.out.println("Answer: " + answer + "\n");
    }


    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        // t.test(s1);
        Solution s2 = new Solution2();
        t.test(s2);
    }
}
