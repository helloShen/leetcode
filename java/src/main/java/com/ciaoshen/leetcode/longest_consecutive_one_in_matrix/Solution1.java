/**
 * Leetcode - longest_consecutive_one_in_matrix
 */
package com.ciaoshen.leetcode.longest_consecutive_one_in_matrix;
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

    public int longestLine(int[][] M) {
        int max = 0;
        int height = M.length;
        if (height == 0) return max;
        int width = M[0].length;
        int[][][] record = new int[height][width][4];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (M[i][j] == 1) {
                    if (record[i][j][0] == 0) { // horizontal
                        int end = j + 1;
                        while (end < width && M[i][end] == 1) {
                            record[i][end][0] = 1;
                            end++;
                        }
                        max = Math.max(max, end - j);
                    }
                    if (record[i][j][1] == 0) { // vertical
                        int end = i + 1;
                        while (end < height && M[end][j] == 1) {
                            record[end][j][1] = 1;
                            end++;
                        }
                        max = Math.max(max, end - i);
                    }
                    if (record[i][j][2] == 0) { // diagonal
                        int rowEnd = i + 1, colEnd = j + 1;
                        while (colEnd < width && rowEnd < height && M[rowEnd][colEnd] == 1) {
                            record[rowEnd][colEnd][2] = 1;
                            rowEnd++; colEnd++;
                        }
                        max = Math.max(max, rowEnd - i);
                    }
                    if (record[i][j][3] == 0) { // anti-diagonal
                        int rowEnd = i + 1, colEnd = j - 1;
                        while (colEnd >= 0 && rowEnd < height && M[rowEnd][colEnd] == 1) {
                            record[rowEnd][colEnd][3] = 1;
                            rowEnd++; colEnd--;
                        }
                        max = Math.max(max, rowEnd - i);
                    }
                }
            }
        }
        return max;
    }

}
