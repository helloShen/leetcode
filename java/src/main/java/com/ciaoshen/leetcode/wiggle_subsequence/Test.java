/**
 * Leetcode - #376 - Wiggle Subsequence
 */
package com.ciaoshen.leetcode.wiggle_subsequence;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {

    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        call(s, nums1, answer1);
        call(s, nums2, answer2);
        call(s, nums3, answer3);
    }

    /**========================== 【私有成员】 ============================*/

    // 测试用例 + 答案
    private int[] nums1 = new int[]{};
    private String answer1 = "0";

    private int[] nums2 = new int[]{1,7,4,9,2,5};
    private String answer2 = "6";

    private int[] nums3 = new int[]{1,17,5,10,13,15,10,5,16,8};
    private String answer3 = "7";

    /** 测试单个用例 */
    private void call(Solution s, int[] nums, String answer) {
        System.out.println("Nums1: " + Arrays.toString(nums));
        System.out.println("Result: " + s.wiggleMaxLength(nums));
        System.out.println("Answer: " + answer + "\n");
    }


    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        // Solution s2 = new Solution2();
        // Solution s3 = new Solution2();
        t.test(s1);
        // t.test(s2);
        // t.test(s3);
    }
}
