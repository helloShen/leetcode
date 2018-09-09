/**
 * Leetcode - #373 - Find K Pairs with Smallest Sums
 * 
 * TO DO:
 *      1. 最好用JUnit的断言
 */
package com.ciaoshen.leetcode.find_k_pairs_with_smallest_sums;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Test {

    /** 测试用例对外服务接口 */
    public void test(Solution s) {
        call(s, nums11, nums12, k1, answer1);
        call(s, nums21, nums22, k2, answer2);
        call(s, nums31, nums32, k3, answer3);
    }

    /**========================== 【私有成员】 ============================*/

    // 测试用例 + 答案
    private int[] nums11 = new int[]{1,1,2};
    private int[] nums12 = new int[]{1,2,3};
    private int k1 = 10;
    private String answer1 = "[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]";

    private int[] nums21 = new int[]{-10,-4,0,0,6};
    private int[] nums22 = new int[]{3,5,6,7,8,100};
    private int k2 = 10;
    private String answer2 = "[[-10,3],[-10,5],[-10,6],[-10,7],[-10,8],[-4,3],[-4,5],[-4,6],[0,3],[0,3]]";

    private int[] nums31 = new int[]{1,2,4,5,6};
    private int[] nums32 = new int[]{3,5,7,9};
    private int k3 = 3;
    private String answer3 = "[[1,3],[2,3],[1,5]]";

    /** 测试单个用例 */
    private void call(Solution s, int[] nums1, int[] nums2, int k, String answer) {
        System.out.println("Nums1: " + Arrays.toString(nums1));
        System.out.println("Nums2: " + Arrays.toString(nums2));
        System.out.println("Result: ");
        for (int[] pair : s.kSmallestPairs(nums1, nums2, k)) {
            System.out.print("[" + pair[0] + "," + pair[1] + "], ");
        }
        System.out.println("\nAnswer: " + answer);
    }


    /**========================== 【主入口】 ============================*/

    public static void main(String[] args) {
        Test t = new Test();
        Solution s1 = new Solution1();
        Solution s2 = new Solution2();
        Solution s3 = new Solution2();
        // t.test(s1);
        t.test(s2);
        t.test(s3);
    }
}
