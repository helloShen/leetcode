/**
 * Leetcode - Algorithm - Rotate Array
 */
package com.ciaoshen.leetcode;
import java.util.*;

class RotateArray {
    /**
     * 解法1：直接转。用一个辅助array缓存一部分元素。
     */
    public class SolutionV1 {
        public void rotate(int[] nums, int k) {
            // defense: so that k can accept negative number
            k = ((k % nums.length) + nums.length) % nums.length;
            if (nums.length < 2 || k == 0) { return; }
            int[] copy = new int[nums.length];
            System.arraycopy(nums,0,copy,0,nums.length);
            for (int i = 0; i < nums.length; i++) {
                nums[(i+k)%nums.length] = copy[i];
            }
        }
    }
    /**
     * 第一步，切割成3部分，
     *          1. 如果k大于长度一半，分成下面3部分：
     *              [a,bl,br]，其中 length(bl+br) = k
     *          2. 如果k小于长度一半，分成下面3部分：
     *              [a,bl,br]，其中 length(a） = k
     *
     * 第二步，交换 a 和 br 两部分，变成 [br,bl,a] 三部分。
     *
     * 第三步，递归。
     *          1. 如果k大于长度一半，
     *              [br,bl]部分继续向右转 k-length(br) 位
     *          2. 如果k小于长度一半，
     *              [bl,a]部分继续向右转 k 位
     */
    public class SolutionV2 {
        public void rotate(int[] nums, int k) {
            // defense: so that k can accept negative number
            k = ((k % nums.length) + nums.length) % nums.length;
            if (nums.length < 2 || k == 0) { return; }
            // System.out.println("Length = " + nums.length + ", k = " + k);
            recursion(nums,0,nums.length,k);
        }
        /** range: [lo,hi)  inclusive lo, exclusive hi
         *  points:        lo      bar1     bar2      hi
         *  three parts:   |   a   |   bl   |   br   |
         */
        public void recursion(int[] nums, int lo, int hi, int k) {
            // System.out.println(Arrays.toString(nums) + ", lo = " + lo + ", hi = " + hi + ", k = " +k);
            int length = hi - lo;
            if ((length < 2) || (k % length == 0)) { return; } // base case
            int half = length / 2;
            int len = (k <= half)? k : (hi - lo) - k; // length of br part
            int bar1 = lo + len, bar2 = hi - len;
            // exchange [a] & [br]
            for (int i = 0; i < len; i++) {
                int temp = nums[bar2+i];
                nums[bar2+i] = nums[lo+i];
                nums[lo+i] = temp;
            }
            // exchange [bl] & [br]
            if (k <= half) {
                recursion(nums,bar1,hi,k); // continue to rotate k to right
            } else {
                recursion(nums,lo,bar2,k-len); // continue to rotate k-len to right
            }
        }
    }
    /**
     * 求逆法。
     * [a,b,c,d,e,f,g,h] 右移 3 格，分三步走：
     *      1. [a,b,c,d,e] 求逆：-> [e,d,c,b,a]
     *      2. [f,g,h] 求逆：-> [h,g,f]
     *      3. [e,d,c,b,a,h,g,f] 一起再求逆：-> [f,g,h,a,b,c,d,e]
     */
    public class SolutionV3 {
        public void rotate(int[] nums, int k) {
            // defense: so that k can accept negative number
            k = ((k % nums.length) + nums.length) % nums.length;
            if (nums.length < 2 || k == 0) { return; }
            int bar = nums.length - k;
            reverse(nums,0,bar);
            reverse(nums,bar,nums.length);
            reverse(nums,0,nums.length);
        }
        /**
         * [lo,hi)
         */
        public void reverse(int[] nums, int lo, int hi) {
            int len = hi - lo;
            if (len < 2) { return; }
            int upperMid = lo + (len - 1) / 2;
            int lowerMid = lo + len / 2;
            while (upperMid >= lo && lowerMid < hi) {
                int temp = nums[upperMid];
                nums[upperMid] = nums[lowerMid];
                nums[lowerMid] = temp;
                upperMid--; lowerMid++;
            }
            // System.out.println("After reverse " + lo + " to " + hi + ": " + Arrays.toString(nums));
        }
    }
    /**
     * 杂技法。从最高位0位开始，每位i的数nums[i]拷贝到(i+k)%len的位置，以此类推。
     * 例如：[1,2,3,4,5,6,7] 向右转动3格。
     * Original Array: [1, 2, 3, 4, 5, 6, 7]
     * Arrays becomes: [1, 2, 3, 1, 5, 6, 7]
     * Arrays becomes: [1, 2, 3, 1, 5, 6, 4]
     * Arrays becomes: [1, 2, 7, 1, 5, 6, 4]
     * Arrays becomes: [1, 2, 7, 1, 5, 3, 4]
     * Arrays becomes: [1, 6, 7, 1, 5, 3, 4]
     * Arrays becomes: [1, 6, 7, 1, 2, 3, 4]
     * Arrays becomes: [5, 6, 7, 1, 2, 3, 4]
     * Rotated Array:  [5, 6, 7, 1, 2, 3, 4]
     */
    public class SolutionV4 {
        public void rotate(int[] nums, int k) {
            // defense: so that k can accept negative number
            k = ((k % nums.length) + nums.length) % nums.length;
            if (nums.length < 2 || k == 0) { return; }
            // iteration
            int minCovenant = minimumCovenant(nums.length, k); // 求最小公约数
            System.out.println("Minimum Covenant of " + nums.length + " & " + k + " is: " + minCovenant);
            for (int i = 0; i < minCovenant; i++) { // 必定转满最小公约数圈
                int cur = i, register = nums[cur];
                do {
                    cur = (cur + k) % nums.length;
                    int temp = nums[cur];
                    nums[cur] = register;
                    register = temp;
                    System.out.println("Arrays becomes: " + Arrays.toString(nums));
                } while (cur != i);
            }
        }
        // 辗转相除法求最小公约数
        public int minimumCovenant(int numerator, int denominator) {
            // System.out.println("numerator = " + numerator + ", denominator = " + denominator);
            int remainder = numerator % denominator;
            if (remainder == 0) { return denominator; }
            return minimumCovenant(denominator,remainder);
        }
    }
    /**
     * 杂技跳跃法的简化版。不需要计算最小公约数，而是统计移动过的数字的数量。
     * 当所有数字都移动过了，就结束循环。
     */
    public class SolutionV5 {
        public void rotate(int[] nums, int k) {
            k = ((k % nums.length) + nums.length) % nums.length;
            if (nums.length < 2 || k == 0) { return; }
            int remain = nums.length;
            for (int i = 0; remain > 0; i++) {
                int cur = i, pre = nums[cur];
                do {
                    cur = (cur + k) % nums.length;
                    int temp = nums[cur];
                    nums[cur] = pre;
                    pre = temp;
                    remain--;
                } while (cur != i);
            }
        }
    }
    /**
     * 最蠢的全体右移一位，移动k次。
     */
    public class SolutionV6 {
        public void rotate(int[] nums, int k) {
            k = ((k % nums.length) + nums.length) % nums.length;
            if (nums.length < 2 || k == 0) { return; }
            for (int i = 0; i < k; i++) {
                int pre = nums[nums.length-1];
                for (int j = 0; j < nums.length; j++) {
                    int temp = nums[j];
                    nums[j] = pre;
                    pre = temp;
                }
            }
        }
    }
    private static RotateArray test = new RotateArray();
    private static Solution solution = test.new Solution();
    private static void callRotate(int[] nums, int k) {
        System.out.println("Original Array: " + Arrays.toString(nums) + "   >>> Rotate " + k + " to right: ");
        solution.rotate(nums,k);
        System.out.println("Rotated Array: " + Arrays.toString(nums));
    }
    private static void test() {
        int[] nums1 = new int[]{1,2,3,4,5,6,7};
        int[] nums2 = new int[]{1,2,3,4,5,6,7,8,9};
        int[] nums3 = new int[]{1,2};
        int[] nums4 = new int[]{1,2,3,4,5,6};
        callRotate(nums1,3);
        callRotate(nums1,4);
        callRotate(nums2,3);
        callRotate(nums2,6);
        callRotate(nums3,2);
        callRotate(nums1,-3);
        callRotate(nums4,2);
    }
    private static void testPersentage() {
        for (int i = -10; i < 11; i++) {
            System.out.println(i + " % 10 = " + (i % 10));
        }
    }
    public static void main(String[] args) {
        test();
        // testPersentage();
    }
}
