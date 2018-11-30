/**
 * Leetcode - poor_pigs
 */
package com.ciaoshen.leetcode.poor_pigs;
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

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) return 0;
        int len = (minutesToTest / minutesToDie + 1);
        for (int pigs = 1, product = 1; pigs <= buckets; pigs++) {
            product *= len;
            if (product >= buckets) return pigs;
        }
        return 0;
    }

}
