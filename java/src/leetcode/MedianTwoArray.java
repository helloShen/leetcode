/**
 * Leetcode Median of two sorted arrays
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class MedianTwoArray {
    // 朴素归并法 O(n)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        if (totalLength == 0) {
            return 0.0d;
        }
        if (totalLength == 1) {
            if (nums1.length == 1) {
                return (double)nums1[0];
            } else {
                return (double)nums2[0];
            }
        }
        // 下面开始两个数组长度总和 >= 2
        int index1 = 0;
        int index2 = 0;
        int num1 = 0;
        int num2 = 0;
        int cursor = 0; // 当前窗口
        int[] candidate = new int[2]; // 不管长度是奇数还是偶数，先把两个候选数取出来
        for (int i = 0; i <= totalLength/2; i++) {
            if (index1 < nums1.length) {
                num1 = nums1[index1];
            } else {
                num1 = Integer.MAX_VALUE;
            }
            if (index2 < nums2.length) {
                num2 = nums2[index2];
            } else {
                num2 = Integer.MAX_VALUE;
            }
            if (num1 < num2) {
                cursor = num1;
                index1++;
            } else {
                cursor = num2;
                index2++;
            }
            if (i == (totalLength/2 -1)) {
                candidate[0] = cursor;
            }
            if (i == (totalLength/2)) {
                candidate[1] = cursor;
            }
        }
        // 根据总长度是奇数还是偶数，用候选数计算出结果。
        if (totalLength%2 == 0) { // 长度是偶数：取两个候选数的平均数
            return ((double)candidate[0] + (double)candidate[1]) / 2;
        } else { // 长度是奇数：取两个候选数中的后者
            return (double)candidate[1];
        }
    }

    // 递归二分法版本， 复杂度 O(log(n))
    public static double findMedianSortedArraysBinary(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArraysBinary(nums2,nums1);
        }
        int mid1 = 0;
        int mid2 = 0;
        if ((nums1.length + nums2.length)%2 == 0) {
            mid1 = ((nums1.length + nums2.length)-2)/2;
            mid2 = (nums1.length + nums2.length)/2;
        } else {
            mid1 = ((nums1.length + nums2.length)-1)/2;
            mid2 = ((nums1.length + nums2.length)-1)/2;
        }
        if ((nums1.length + nums2.length) <= 4) {
            return simpleFindTarget(nums1, nums2, 0, nums1.length-1, 0, nums2.length-1, mid1, mid2);
        } else {
            return findTarget(nums1, nums2, 0, nums1.length-1, 0, nums2.length-1, mid1, mid2);
        }
    }

    // 假设num1比num2短，把num1插入num2
    public static double findTarget(int[] nums1, int[] nums2, int low1, int high1, int low2, int high2, int mid1, int mid2) {
        // base case
        if (high1 - low1 < 2) {
            System.out.println(low1 + ", " + high1 + ", " + low2 + ", " + high2 + ", " + mid1 + ", " + mid2);
            return simpleFindTarget(nums1, nums2, low1, high1, low2, high2, mid1, mid2);
        }
        // recursion
        int median = ((high1 - low1 + 1) - 1) / 2; //相对地址
        System.out.println(nums1[low1 + median] + ", " + "nums2, " + low2 + ", " + high2);
        int index = indexOf(nums1[low1 + median],nums2,low2,high2); //相对地址
        int printIndex = low2+index;
        System.out.println("index = " + low2 + " + " + index + " = " + printIndex);
        if (median + index < mid1) {
            int a = low1+median+1;
            int b = high1;
            int c = low2+index;
            int d = high2;
            int e = mid1-median-index-1;
            int f = mid2-median-index-1;
            System.out.println(a+ ", " + b + ", " + c + ", " + d + ", " + mid1 + ", " + mid2);
            return findTarget(nums1, nums2, low1 + median + 1, high1, low2 + index, high2 , mid1 - median - index - 1, mid2 - median - index - 1);
        } else if (median + index > mid2) {
            int a = low1;
            int b = low1+median-1;
            int c = low2;
            int d = low2+index-1;
            System.out.println(a+ ", " + b + ", " + c + ", " + d + ", " + mid1 + ", " + mid2);
            return findTarget(nums1, nums2, low1, low1 + median - 1, low2, low2 + index - 1, mid1, mid2);
        } else if (median + index == mid1) {
            int a = low1+median;
            int b = high1;
            int c = low2+index;
            int d = high2;
            int e = mid1-median-index;
            int f = mid2-median-index;
            System.out.println(a+ ", " + b + ", " + c + ", " + d + ", " + mid1 + ", " + mid2);
            return findTarget(nums1, nums2, low1 + median, high1, low2 + index, high2, mid1 - median - index, mid2 - median - index);
        } else if (median + index == mid2) {
            int a = low1;
            int b = low1+median;
            int c = low2;
            int d = low2+index-1;
            System.out.println(a+ ", " + b + ", " + c + ", " + d + ", " + mid1 + ", " + mid2);
            return findTarget(nums1, nums2, low1, low1 + median, low2, low2 + index - 1, mid1, mid2);
        }
        // never reach
        return 0;
    }

    /**
     * 简单的归并法。用两个指针分别指向两个数组。依次比较。直到获得第mid1和mid2个数。然后取平均值。
     * mid1和mid2是没有算上low基的。
     * @param   [nums1: 数组1]
     * @param   [nums2: 数组2]
     * @param   [low1: 数组1下界]
     * @param   [high1: 数组1上界]
     * @param   [low2: 数组2下界]
     * @param   [high2: 数组2上界]
     * @param   [mid1: 目标数1]
     * @param   [mid2: 目标数2]
     * @return  [两个数组的中位数]
     */
    public static double simpleFindTarget(int[] nums1, int[] nums2, int low1, int high1, int low2, int high2, int mid1, int mid2) {
        int value1 = 0;
        int value2 = 0;
        int index = 0;
        int cursor = 0;
        while (low1 <= high1 || low2 <= high2) {
            System.out.println("Low1 = " + low1 + ", Low2 = " + low2);
            int num1 = (low1 > high1)? Integer.MAX_VALUE : nums1[low1];
            int num2 = (low2 > high2)? Integer.MAX_VALUE : nums2[low2];
            if (num1 <= num2) {
                cursor = num1;
                low1++;
            } else {
                cursor = num2;
                low2++;
            }
            if (index == mid1) {
                value1 = cursor;
            }
            if (index == mid2) {
                value2 = cursor;
                return ((double)value1 + (double)value2) / 2;
            }
            index++;
        }
        return 0;
    }

    // 返回num如果插入array的low到high位子串的话，应该插入的位置。简洁版。
    // 返回相对地址
    public static int indexOf(int num, int[] array, int low, int high) {
        if (high < low) {
            return 0;
        }
        if (high == low) {
            return (num <= array[low])? 0:1;
        }
        int median = low + ((high - low + 1) - 1)/2; // 下位中位数数学定义
        if (num <= array[median]) {
            return indexOf(num,array,low,median-1);
        } else {
            return (median - low + 1) + indexOf(num,array,median+1,high);
        }
    }

    // 有序数列的递归二分查找的标准解法
    public static int binarySearch(int num, int[] array, int min, int max) {
        // base case
        if (max < min) { return -1; } // return -1 if not found

        // 严格的下位中位数数学定义。不论数组长度为奇数还是偶数。
        int length = max - min + 1;
        int median = min + ( (length - 1) / 2 );

        // 尾递归
        if (array[median] > num) {
            return binarySearch(num, array, min, median-1); // num小于中位数，丢弃中位数及所有大于中位数的数
        } else if (array[median] < num) {
            return binarySearch(num, array, median+1, max); // num大于中位数，丢弃中位数及所有小于中位数的数
        } else { // array[median] == num  //找到了
            return median;
        }
    }

    // 递归二分法找出num在numArray正确的插入位置
    // 假设numArray中元素互异，且有序
    // O(log(n))复杂度
    // 递归一直在同一个array上操作，只用两个指针min和max来划出子数组的范围
    public static int indexOfV2(int num, int[] array, int min, int max) {
        //测试工具：System.out.println("Min=" + min + ", Max=" + max);
        // check arguments
        if (min < 0 || max >= array.length) {
            throw new IllegalArgumentException();
        }
        if (min > max) {
            throw new IllegalArgumentException();
        }
        // base case
        if (max - min == 0) { // subarray has 1 number
            return (num > array[min])? min + 1 : min;
        }
        if (max - min == 1) { // subarray has 2 numbers
            if (num < array[min]) {
                return min;
            } else if (num > array[max]) {
                return max + 1;
            } else {
                return max;
            }
        }
        int halfIndex = min + (max - min) / 2;
        int half = array[halfIndex];
        //测试工具：System.out.println("HalfIndex=" + halfIndex + ", HalfNumber=" + half);
        if (num > half) {
            return indexOf(num, array, halfIndex, max);
        } else if (num < half) {
            return indexOf(num, array, 0, halfIndex-1);
        } else { // num = half
            return half; // insert ahead
        }
    }
    private static class UnitTest {
        private static final int MAX = 1000;
        private static final Random R = new Random();
        private static int[] randomArray(int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = R.nextInt(MAX);
            }
            System.out.println(Arrays.toString(array));
            return array;
        }
        private static int[] nums1 = new int[0];
        private static int[] nums2 = new int[] {R.nextInt(MAX)};
        private static int[] nums3 = new int[] {R.nextInt(MAX)};
        private static int[] nums4 = new int[] {R.nextInt(MAX),R.nextInt(MAX)};
        private static int[] nums5 = new int[] {R.nextInt(MAX),R.nextInt(MAX)};
        private static int[] nums6 = new int[] {R.nextInt(MAX),R.nextInt(MAX),R.nextInt(MAX)};
        private static int[] nums7 = new int[] {R.nextInt(MAX),R.nextInt(MAX),R.nextInt(MAX)};
        private static int[] nums8 = new int[] {R.nextInt(MAX),R.nextInt(MAX),R.nextInt(MAX)};
        private static int[] nums9 = new int[] {R.nextInt(MAX),R.nextInt(MAX),R.nextInt(MAX),R.nextInt(MAX),R.nextInt(MAX)};
        private static int[] nums10 = new int[] {R.nextInt(MAX)};
        static {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            Arrays.sort(nums3);
            Arrays.sort(nums4);
            Arrays.sort(nums5);
            Arrays.sort(nums6);
            Arrays.sort(nums7);
            Arrays.sort(nums8);
            Arrays.sort(nums9);
            Arrays.sort(nums10);
        }
        private static void testVersion1() {
            System.out.println(Arrays.toString(nums1) + "  " + Arrays.toString(nums2) + "  : Median = " + findMedianSortedArrays(nums1,nums2));
            System.out.println(Arrays.toString(nums3) + "  " + Arrays.toString(nums4) + "  : Median = " + findMedianSortedArrays(nums3,nums4));
            System.out.println(Arrays.toString(nums5) + "  " + Arrays.toString(nums6) + "  : Median = " + findMedianSortedArrays(nums5,nums6));
            System.out.println(Arrays.toString(nums7) + "  " + Arrays.toString(nums8) + "  : Median = " + findMedianSortedArrays(nums7,nums8));
            System.out.println(Arrays.toString(nums9) + "  " + Arrays.toString(nums10) + "  : Median = " + findMedianSortedArrays(nums9,nums10));
        }
        private static void testVersion2() {
            System.out.println(Arrays.toString(nums1) + "  " + Arrays.toString(nums2) + "  : Median = " + findMedianSortedArraysBinary(nums1,nums2));
            System.out.println(Arrays.toString(nums3) + "  " + Arrays.toString(nums4) + "  : Median = " + findMedianSortedArraysBinary(nums3,nums4));
            System.out.println(Arrays.toString(nums5) + "  " + Arrays.toString(nums6) + "  : Median = " + findMedianSortedArraysBinary(nums5,nums6));
            System.out.println(Arrays.toString(nums7) + "  " + Arrays.toString(nums8) + "  : Median = " + findMedianSortedArraysBinary(nums7,nums8));
            System.out.println(Arrays.toString(nums9) + "  " + Arrays.toString(nums10) + "  : Median = " + findMedianSortedArraysBinary(nums9,nums10));
        }
        private static void testSimpleFindTarget() {
            System.out.println(Arrays.toString(nums1) + "  " + Arrays.toString(nums2) + "  : Median = " + simpleFindTarget(nums1,nums2,0,nums1.length-1,0,nums2.length-1,0,0));
            System.out.println(Arrays.toString(nums3) + "  " + Arrays.toString(nums4) + "  : Median = " + simpleFindTarget(nums3,nums4,0,nums3.length-1,0,nums4.length-1,1,1));
            System.out.println(Arrays.toString(nums5) + "  " + Arrays.toString(nums6) + "  : Median = " + simpleFindTarget(nums5,nums6,0,nums5.length-1,0,nums6.length-1,2,2));
            System.out.println(Arrays.toString(nums7) + "  " + Arrays.toString(nums8) + "  : Median = " + simpleFindTarget(nums7,nums8,0,nums7.length-1,0,nums8.length-1,2,3));
            System.out.println(Arrays.toString(nums9) + "  " + Arrays.toString(nums10) + "  : Median = " + simpleFindTarget(nums9,nums10,0,nums9.length-1,0,nums10.length-1,2,3));
        }
        private static void testBinarySearch() {
            int size = 10;
            int[] array = new int[size];
            for (int i = 0; i< size; i++) {
                array[i] = R.nextInt(MAX);
            }
            int num = R.nextInt(MAX);
            Arrays.sort(array);
            System.out.println("Index of " + num + " in " + Arrays.toString(array) + " is: " + binarySearch(num, array, 0, array.length-1));
        }
        private static void testIndexOf() {
            int size = 15;
            int[] array = new int[size];
            for (int i = 0; i< size; i++) {
                array[i] = R.nextInt(MAX);
            }
            int num = R.nextInt(MAX);
            Arrays.sort(array);
            System.out.println("Index of " + num + " in " + Arrays.toString(array) + " is: " + indexOf(num,array,8,14));
        }
        private static void testFindTarget() {
            int[] nums1 = randomArray(10);
            int[] nums2 = randomArray(20);
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            System.out.println("Nums1 = " + Arrays.toString(nums1));
            System.out.println("Nums2 = " + Arrays.toString(nums2));
            System.out.println("Median = " + findTarget(nums1,nums2,0,9,0,19,14,15));
        }
        private static void testFindMedianBinary() {
            int[] nums1 = randomArray(10);
            int[] nums2 = randomArray(20);
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            System.out.println("Nums1 = " + Arrays.toString(nums1));
            System.out.println("Nums2 = " + Arrays.toString(nums2));
            System.out.println("Median = " + findMedianSortedArraysBinary(nums1,nums2));
        }
    }
    public static void main(String[] args) {
        /*
        for (int i = 0; i < 10; i++) {
            UnitTest.testIndexOf();
        }
        */
        //UnitTest.testVersion1();
        //UnitTest.testVersion2();
        //UnitTest.testSimpleFindTarget();
        //UnitTest.testFindTarget();
        UnitTest.testFindMedianBinary();
    }
}
