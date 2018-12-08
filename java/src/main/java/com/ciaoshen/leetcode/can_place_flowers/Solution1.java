/**
 * Leetcode - can_place_flowers
 */
package com.ciaoshen.leetcode.can_place_flowers;
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

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int size = flowerbed.length;
        if (size == 1) return flowerbed[0] == 0 || n == 0;
        int lo = 0, hi = 0;
        while (lo < size && n > 0) {
            while (lo < size && flowerbed[lo] == 1) lo++;
            hi = lo;
            while (hi < size && flowerbed[hi] == 0) hi++;
            if (lo < size) n -= plantForWindow(lo, hi , size);
            lo = hi;
        }
        return n <= 0;
    }

    /** window range = [lo, hi) */
    private int plantForWindow(int lo, int hi, int size) {
        if (hi - lo == 1) return 0;
        if (log.isDebugEnabled()) {
            log.debug("In empty window: [{}, {}]", lo, hi);
        }
        int half = (hi - lo - 1) / 2;
        if (log.isDebugEnabled()) {
            log.debug(" can plant {}", (lo == 0 || hi == size)? half + 1 : half);
        }
        return (lo == 0 || hi == size)? half + 1 : half;
    }

}
