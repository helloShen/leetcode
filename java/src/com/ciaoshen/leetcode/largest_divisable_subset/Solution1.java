/**
 * Leetcode - #368 - Largest Divisable Subset
 */
package com.ciaoshen.leetcode.largest_divisable_subset;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {
    // assert: all numbers are positive
    public List<Integer> largestDivisibleSubset(int[] nums) {
        longest = new ArrayList<>();
        if (nums == null) {
            return longest;
        }
        Arrays.sort(nums);
        sorted = nums;
        System.out.println("Sorted = " + Arrays.toString(sorted));
        int[] beDividor = new int[nums.length]; // 统计一个数被整除的次数
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = i + 1; j < sorted.length; j++) {
                if (sorted[j] % sorted[i] == 0) {
                    beDividor[j]++;
                    if (!edges.containsKey(i)) {
                        edges.put(i, new ArrayList<Integer>());
                    }
                    edges.get(i).add(j);
                }
            }
        }
        System.out.println("Map =\t" + edges);
        max = 0;
        List<Integer> chain = new ArrayList<>();
        for (int i = 0; i < beDividor.length; i++) {
            if (beDividor[i] == 0) { // 无法整除任何其他元素的数
                chain.add(sorted[i]);
                if (max < 1) {
                    max = 1;
                    longest = new ArrayList<Integer>(chain);
                } 
                backtracking(i, 1, chain, edges);
                chain.remove(chain.size() - 1);
            }
        }
        return longest;
    }

    /**===================== 【私有】 ======================== */
    private int max;
    private List<Integer> longest;
    private int[] sorted;

    // recursively find the chain
    private void backtracking(int index, int len, List<Integer> chain, Map<Integer, List<Integer>> edges) {
        if (!edges.containsKey(index)) {
            return;
        }
        for (int next : edges.get(index)) {
            chain.add(sorted[next]);
            if (len + 1 > max) {
                max = len + 1;
                longest = new ArrayList<Integer>(chain);
            } 
            backtracking(next, len + 1, chain, edges);
            chain.remove(chain.size() - 1);
        }
    }
}
