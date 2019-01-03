/**
 * Leetcode - rectangle_overlap
 */
package com.ciaoshen.leetcode.rectangle_overlap;
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

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (isZeroArea(rec1) || isZeroArea(rec2)) return false;
        return oneDOverlap(rec1[0], rec1[2], rec2[0], rec2[2]) && oneDOverlap(rec1[1], rec1[3], rec2[1], rec2[3]);
    }

    private boolean isZeroArea(int[] rec) {
        return rec[0] == rec[2] || rec[1] == rec[3];
    }

    private boolean oneDOverlap(int aLo, int aHi, int bLo, int bHi) {
        return aLo < bHi && aHi > bLo;
    }

}
