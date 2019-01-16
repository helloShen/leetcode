/**
 * Leetcode - k_diff_pairs_in_an_array
 */
package com.ciaoshen.leetcode.k_diff_pairs_in_an_array;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution1 implements Solution {

    public int findPairs(int[] nums, int k) {
        if (k < 0) return 0;
        if (k == 0) return findPairsSpecialZero(nums);
        Set<Integer> numSet = new HashSet<>();
        for (int n : nums) numSet.add(n);
        Set<Integer> asSmaller = new HashSet<>();
        Set<Integer> asGreater = new HashSet<>();
        int count = 0;
        for (int n : nums) {
            if (!asSmaller.contains(n) && numSet.contains(n + k)) {
                count++;
                asSmaller.add(n);
                asGreater.add(n + k);
            }
            if (!asGreater.contains(n) && numSet.contains(n - k)) {
                count++;
                asGreater.add(n);
                asSmaller.add(n - k);
            }
        }
        return count;
    }

    private int findPairsSpecialZero(int[] nums) {
        int count = 0;
        Map<Integer, Integer> numFreq = new HashMap<>();
        for (int n : nums) {
            if (!numFreq.containsKey(n)) {
                numFreq.put(n, 1);
            } else {
                int freq = numFreq.get(n);
                if (freq == 1) {
                    count++;
                    numFreq.put(n, numFreq.get(n) + 1);
                }
            }
        }
        return count;
    }

}
