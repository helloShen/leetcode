/**
 * Kth largest element in the array
 */
package com.ciaoshen.leetcode;
import java.util.*;

public class KthLargest {
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    public static int findKthLargestV2(int[] nums, int k) {
        return findKthSmallest(nums,0,nums.length-1,nums.length-k);
    }
    public static int findKthSmallest(int[] nums, int low, int high, int k) {
        int pivot = partition(nums,low,high);
        if (pivot == k) {
            //System.out.println(pivot + " = " + k);
            return nums[pivot];
        } else if (pivot < k) {
            //System.out.println(pivot + " < " + k);
            return findKthSmallest(nums,pivot+1,high,k);
        } else {
            //System.out.println(pivot + " > " + k);
            return findKthSmallest(nums,low,pivot-1,k);
        }
    }
    public static int partition(int[] nums, int low, int high) {
        if (low == high) { return low; }
        int bound = low - 1;
        int pivot = nums[high];
        for (int i = low; i < high; i++) {
            if (nums[i] <= pivot) {
                bound++;
                exchange(nums,bound,i);
            }
        }
        bound++;
        exchange(nums,bound,high);
        return bound;
    }
    public static void exchange(int[] nums, int i, int j) {
        //System.out.println("Change " + i + " and " + j);
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static int[] randomArray(int size, int max) {
        Random r = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(max);
        }
        return array;
    }
    public static void main(String[] args) {
        int size = 10;
        int max = 1000;
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            int k = r.nextInt(size-1)+1;
            int[] array = randomArray(size,max);
            System.out.println(k + " th element in array " + Arrays.toString(array) + " is: ");
            System.out.println(">>> " + findKthLargestV2(array,k));
        }
    }
}
