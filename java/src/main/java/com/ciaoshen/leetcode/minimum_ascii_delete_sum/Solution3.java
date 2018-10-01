/**
 * Leetcode - minimum_ascii_delete_sum
 */
package com.ciaoshen.leetcode.minimum_ascii_delete_sum;
import java.util.*;
import com.ciaoshen.leetcode.util.*;
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Solution3 implements Solution {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Smallest edit distance
     */
    public int minimumDeleteSum(String s1, String s2) {
        int[] lineA = new int[s2.length() + 1];
        int[] lineB = new int[s2.length() + 1];
        for (int i = 1; i <= s2.length(); i++) {
            lineA[i] = lineA[i - 1] + s2.charAt(i - 1);
        }
        for (int i = 1; i <= s1.length(); i++) {
            char cRow = s1.charAt(i - 1);
            if (log.isDebugEnabled()) {
                log.debug("lineA = {}", Arrays.toString(lineA));
                log.debug("lineB = {}", Arrays.toString(lineB));
            }
            lineB[0] = lineA[0] + cRow;
            for (int j = 1; j <= s2.length(); j++) {
                char cCol = s2.charAt(j - 1);
                int fromUpper = lineA[j] + cRow;
                int fromLeft = lineB[j - 1] + cCol;
                lineB[j] = Math.min(fromUpper, fromLeft);
                int fromBevel = 0;
                if (cRow == cCol) {
                    fromBevel = lineA[j - 1];
                } else {
                    fromBevel = lineA[j - 1] + cRow + cCol;
                }
                lineB[j] = Math.min(lineB[j], fromBevel);
            }
            int[] lineTemp = lineA;
            lineA = lineB;
            lineB = lineTemp;
        }
        return lineA[s2.length()];
    }

}
