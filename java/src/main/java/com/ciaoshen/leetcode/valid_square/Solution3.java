/**
 * Leetcode - valid_square
 */
package com.ciaoshen.leetcode.valid_square;
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
class Solution3 implements Solution {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Integer[] disArr = new Integer[]{dis(p1, p2), dis(p1, p3), dis(p1, p4), dis(p2, p3), dis(p2, p4), dis(p3, p4)};
        Set<Integer> set = new HashSet<>(Arrays.asList(disArr));
        List<Integer> list = new ArrayList<>(set);
        return list.contains(0) || list.size() != 2;
    }

    private Integer dis(int[] a, int[] b) {
        return (int) (Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

}
