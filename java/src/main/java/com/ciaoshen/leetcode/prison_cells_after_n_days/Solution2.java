/**
 * Leetcode - prison_cells_after_n_days
 */
package com.ciaoshen.leetcode.prison_cells_after_n_days;
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
class Solution2 implements Solution {

    public int[] prisonAfterNDays(int[] cells, int N) {
        int num = 0;
        for (int i = 0; i < cells.length; i++) {
            num <<= 1;
            num |= cells[i];
        }
        // if (log.isDebugEnabled()) {
        //     log.debug("num = {}", num);
        // }
        for (int i = 1; i <= N; i++) {
            num = change(num);
            // if (log.isDebugEnabled()) {
            //     log.debug("change to {}", num);
            // }
        }
        int[] res = new int[cells.length];
        for (int i = cells.length - 1; i >= 0; i--) {
            res[i] = num & 1;
            num >>= 1;
        }
        return res;
    }

    private int change(int pre) {
        int mask = 126; // 126 = 0111 1110
        int left = pre << 1;
        int right = pre >> 1;
        int res = (~ (left ^ right)) & mask;
        // if (log.isDebugEnabled()) {
        //     log.debug("left = {}", left);
        //     log.debug("right = {}", right);
        //     log.debug("change to {}", res);
        // }
        return res;
    }

}
