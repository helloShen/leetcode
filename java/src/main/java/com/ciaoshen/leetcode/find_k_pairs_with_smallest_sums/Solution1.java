/**
 * Leetcode - #373 - Find K Pairs with Smallest Sums
 */
package com.ciaoshen.leetcode.find_k_pairs_with_smallest_sums;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * x = min(m*n, k)
 * O(xlogx)
 * 老老实实计算出每一对和，放进一个Heap里输出。
 */
class Solution1 implements Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1 == null || nums2 == null || k < 0) {
            return res;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[0] + a[1] - b[0] - b[1];
            }
        });
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                heap.offer(new int[]{nums1[i], nums2[j]});
            }
        }
        for (int i = 0; i < k && !heap.isEmpty(); i++) {
            res.add(heap.poll());
        }
        return res;
    }
}
