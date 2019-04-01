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
class Solution1 implements Solution {

    public int[] prisonAfterNDays(int[] cells, int N) {
        for (int i = 1; i <= N; i++) {
            int[] next = change(cells);
            cells = next;
            // if (log.isDebugEnabled()) {
            //     log.debug("change to {}", Arrays.toString(cells));
            // }
        }
        return cells;
    }

    private int[] change(int[] pre) {
        int[] curr = new int[pre.length];
        for (int i = 1; i < pre.length - 1; i++) {
            curr[i] = (pre[i - 1] == pre[i + 1])? 1 : 0;
        }
        return curr;
    }

}
