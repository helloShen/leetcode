/**
 * Leetcode - partition_equal_subset_sum
 */
package com.ciaoshen.leetcode.partition_equal_subset_sum;
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

    public boolean canPartition(int[] nums){
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        Set<Integer> sumSet = new HashSet<>();
        sumSet.add(0);
        for (int n : nums) {
            int[] newSumSet = new int[sumSet.size()];
            int p = 0;
            for (int s : sumSet) {
                int newSum = n + s;
                if (newSum == target) return true;
                newSumSet[p++] = newSum;
            }
            for (int newSum : newSumSet) sumSet.add(newSum);
        }
        return false;
    }

}
