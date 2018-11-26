/**
 * Leetcode - card_flipping_game
 */
package com.ciaoshen.leetcode.card_flipping_game;
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

    public int flipgame(int[] fronts, int[] backs) {
        int res = 2001;
        Set<Integer> banned = new HashSet<>();
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i]) banned.add(fronts[i]);
        }
        for (int i = 0; i < fronts.length; i++) {
            if (!banned.contains(fronts[i])) res = Math.min(res, fronts[i]);
            if (!banned.contains(backs[i])) res = Math.min(res, backs[i]);
        }
        return (res == 2001)? 0 : res;
    }

}
