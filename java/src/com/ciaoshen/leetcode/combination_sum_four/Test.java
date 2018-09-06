/**
 * Leetcode - #377 - Combination Sum Four
 */
package com.ciaoshen.leetcode.combination_sum_four;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {
  
    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        call(s, nums1, target1, answer1);
        call(s, nums2, target2, answer2);
    }

    /**========================== 【私有成员】 ============================*/

    // 测试用例 + 答案
    private int[] nums1 = new int[]{1,2,3};
    private int target1 = 4;
    private String answer1 = "7";

    private int[] nums2 = new int[]{2,1,3};
    private int target2 = 35;
    private String answer2 = "";


    /** 测试单个用例 */
    private void call(Solution s, int[] nums, int target, String answer) {
        System.out.println("Binary Tree: " + Arrays.toString(nums));
        System.out.println("Result: " + s.combinationSum4(nums, target));
        System.out.println("Answer: " + answer);
    }


    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        Solution s2 = new Solution2();
        Solution s3 = new Solution3();
        // t.test(s1);
        // t.test(s2);
        t.test(s3);
    }
}
