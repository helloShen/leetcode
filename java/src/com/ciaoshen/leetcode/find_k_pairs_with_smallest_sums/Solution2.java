/**
 * Leetcode - #373 - Find K Pairs with Smallest Sums
 */
package com.ciaoshen.leetcode.find_k_pairs_with_smallest_sums;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/** 
 * O(klog(min(m,n)))
 * nums1数组中的每一个数，都维持一个指向nums2数组的一条向量。
 */
class Solution2 implements Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return res;
        }
        // 以较小的数组作为生成向量的主数组
        int[] numsA = (nums1.length <= nums2.length)? nums1 : nums2;
        int[] numsB = (numsA == nums1)? nums2 : nums1;
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return numsA[a[0]] + numsB[a[1]] - numsA[b[0]] - numsB[b[1]];
            }
        });
        int vectorP = 0;
        heap.offer(new int[]{vectorP, 0});
        while (k-- > 0 && !heap.isEmpty()) {
            int[] next = heap.poll();
            if (numsA == nums1) {
                res.add(new int[]{numsA[next[0]], numsB[next[1]]});
            } else {
                res.add(new int[]{numsB[next[1]], numsA[next[0]]});
            }
            if (next[0] == vectorP && vectorP < numsA.length - 1 && next[1] == 0) { // 添加新向量
                heap.offer(new int[]{++vectorP, 0});
            }
            if (next[1] < numsB.length - 1) {
                heap.offer(new int[]{next[0], next[1] + 1});
            } 
        }
        return res;
    }
}
