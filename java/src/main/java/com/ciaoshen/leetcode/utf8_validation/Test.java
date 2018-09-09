/**
 * Leetcode - #393 - UTF-8 Validation
 */
package com.ciaoshen.leetcode.utf8_validation;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        for (int i = 0; i < datas.length; i++) {
            call(s, datas[i], answers[i]);
        }
    }

    /**========================== 【私有成员】 ============================*/

    // 测试用例 + 答案
    private int[] data1  = new int[]{197, 130, 1};
    private String answer1 = "true";

    private int[] data2  = new int[]{
        194,155,231,184,185,246,176,131,161,222,174,227,162,134,241,154,168,
        185,218,178,229,187,139,246,178,187,139,204,146,225,148,179,245,139,
        172,134,193,156,233,131,154,240,166,188,190,216,150,230,145,144,240,
        167,140,163,221,190,238,168,139,241,154,159,164,199,170,224,173,140,
        244,182,143,134,206,181,227,172,141,241,146,159,170,202,134,230,142,
        163,244,172,140,191};
    private String answer2 = "true";

    private int[][] datas = new int[][]{data1, data2};
    private String[] answers = new String[]{answer1, answer2};

    /** 测试单个用例 */
    private void call(Solution s, int[] data, String answer) {
        System.out.println("Data: " + Arrays.toString(data));
        System.out.println("Result: " + s.validUtf8(data));
        System.out.println("Answer: " + answer);
    }


    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        t.test(s1);
    }
}
