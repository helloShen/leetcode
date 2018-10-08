/**
 * Leetcode - max_increase_to_keep_city_skyline
 */
package com.ciaoshen.leetcode.max_increase_to_keep_city_skyline;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/** two pass */
class Solution1 implements Solution {

    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int height = grid.length, width = grid[0].length;
        int[] horizon = new int[width];
        int[] vertical = new int[height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                horizon[j] = Math.max(horizon[j], grid[i][j]);
                vertical[i] = Math.max(vertical[i], grid[i][j]);
            }
        }
        int diff = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                diff += (Math.min(horizon[j], vertical[i]) - grid[i][j]);
            }
        }
        return diff;
    }

}
