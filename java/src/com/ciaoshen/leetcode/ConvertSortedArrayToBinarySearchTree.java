/**
 * Leetcode - Algorithm - Convert Sorted Array to Binary Search Tree
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;


class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums,0,nums.length-1);
    }
    public TreeNode recursion(int[] nums, int lo, int hi) {
        if (lo > hi) { return null; }
        int mid = lo + (hi - lo) / 2; // 下位中位数
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursion(nums,lo,mid-1);
        root.right = recursion(nums,mid+1,hi);
        return root;
    }
    private static ConvertSortedArrayToBinarySearchTree test = new ConvertSortedArrayToBinarySearchTree();
    private static void testSortedArrayToBST() {
        Random r = new Random();
        int size = r.nextInt(30)+1;
        int[] array = new int[size];
        for (int i = 1; i <= size; i++) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));
        System.out.println(test.sortedArrayToBST(array).bfs());
    }
    public static void main(String[] args) {
        testSortedArrayToBST();
    }
}
