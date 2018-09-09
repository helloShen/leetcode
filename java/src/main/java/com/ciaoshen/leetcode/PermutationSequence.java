/**
 * Leetcode - Algorithm - Permutation Sequence
 */
package com.ciaoshen.leetcode;
import java.util.*;

class PermutationSequence {
    //"123"
    //"132"
    //"213"
    //"231"
    //"312"
    //"321"
    public String getPermutationV1(int n, int k) {
        char[] array = new char[n];
        for (int i = 0; i < n; i++) {
            array[i] = (char)('0'+i+1);
        }
        for (int i = 1; i < k; i++) {
            rotate(array);
        }
        return new String(array);
    }
    public void rotate(char[] array) {
        //System.out.println("Before: " + Arrays.toString(array));
        //search target bit
        char c = Character.MIN_VALUE;
        int target = -1;
        for (int i = array.length-1; i >= 0; i--) {
            if (array[i] < c) {
                target = i;
                //System.out.println("Find target bit: " + target);
                break;
            } else {
                //System.out.println(array[i] + " > " + c);
                c = array[i];
            }
        }
        // swap target bit and the smallest number that is larger than target bit
        if (target != -1) {
            for (int i = array.length-1; i > target; i--) {
                if (array[i] > array[target]) {
                    char temp = array[target];
                    array[target] = array[i];
                    array[i] = temp;
                    break;
                }
            }
            //System.out.println("After switch two target bits: " + Arrays.toString(array));
        }
        // sort ascendant
        for (int start = target+1, end = array.length-1; start < end; start++,end--) {
            //System.out.println("Element start = " + array[start] + ", Element end = " + array[end]);
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
        }
        //System.out.println("After sort the tail: " + Arrays.toString(array));
    }
    public String getPermutation(int n, int k) {
        List<Character> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add((char)(i+'0'));
        }
        char[] res = new char[n];
        int period;
        k--;
        for (int index = 0; index < n; index++) {
            period = 1; // 循环周期，5位数的话，首位数的循环周期就是[5-0-1]!，就是4的阶乘，等于24
            for (int i = n - index - 1; i > 0; i--) {
                period *= i;
            }
            res[index] = nums.remove(k/period);
            k = k % period;
        }
        return new String(res);
    }
    private static PermutationSequence test = new PermutationSequence();
    private static char[] one = new char[]{ '1' };
    private static char[] two = new char[]{ '1', '2' };
    private static char[] three = new char[]{ '1', '2', '3' };
    private static char[] four = new char[]{ '1', '2', '3', '4' };
    private static char[] five = new char[]{ '1', '2', '3', '4', '5' };

    private static void testRotate(int times) {
        for (int i = 0; i < times; i++) {
            test.rotate(five);
        }
    }
    private static void testGetPermutation(int times) {
        for (int i = 1; i <= times; i++) {
            System.out.println(test.getPermutation(5,i));
        }
    }
    public static void main(String[] args) {
        //testRotate(10);
        testGetPermutation(10);
    }
}
