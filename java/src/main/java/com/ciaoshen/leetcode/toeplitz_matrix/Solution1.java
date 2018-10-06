/**
 * Leetcode - toeplitz_matrix
 */
package com.ciaoshen.leetcode.toeplitz_matrix;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        for (int i = height - 1; i >= 0; i--) {
            int num = matrix[i][0];
            int x = i, y = 0;
            while (x + 1 < height && y + 1 < width) {
                if (matrix[x + 1][y + 1] != num) return false;
                x++; y++;
            }
        }
        for (int i = 0; i < width; i++) {
            int num = matrix[0][i];
            int x = 0, y = i;
            while (y + 1 < width && x + 1 < height) {
                if (matrix[x + 1][y + 1] != num) return false;
                y++; x++;
            }
        }
        return true;
    }

}
