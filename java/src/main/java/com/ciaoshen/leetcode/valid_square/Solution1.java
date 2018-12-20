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
class Solution1 implements Solution {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] matrix = new int[][]{p1, p2, p3, p4};
        if (log.isDebugEnabled()) {
            log.debug("Matrix: ");
            for (int i = 0; i < 4; i++) {
                log.debug(">>>  {}", Arrays.toString(matrix[i]));
            }
        }
        int globalEdgeSquare = -1, globalCrossSquare = -1;
        for (int i = 0; i < 4; i++) {
            int[] point = matrix[i];
            int edgeSquare = -1, crossSquare = -1;
            for (int j = 0; j < 4; j++) {
                if (j == i) continue;
                int len = (int) (Math.pow(point[0] - matrix[j][0], 2) + Math.pow(point[1] - matrix[j][1], 2));
                if (len == 0) return false;
                if (edgeSquare < 0) {
                    edgeSquare = len;
                } else if (edgeSquare < len) {
                    crossSquare = len;
                } else if (edgeSquare > len) {
                    crossSquare = edgeSquare;
                    edgeSquare = len;
                }
            }
            if (log.isDebugEnabled()) {
                log.debug("For point[{},{}]", point[0], point[1]);
                log.debug("edgeSquare = {}, crossSquare = {}", edgeSquare, crossSquare);
            }
            if (crossSquare != 2 * edgeSquare) return false;
            if (globalEdgeSquare < 0) {
                globalEdgeSquare = edgeSquare;
                globalCrossSquare = crossSquare;
            } else {
                if (edgeSquare != globalEdgeSquare || crossSquare != globalCrossSquare) return false;
            }
        }
        return true;
    }

}
