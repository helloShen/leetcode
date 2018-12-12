/**
 * Leetcode - number_of_distinct_islands
 */
package com.ciaoshen.leetcode.number_of_distinct_islands;
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
class Solution2 implements Solution {

    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0) return 0;
        height = grid.length;
        width = grid[0].length;
        localGrid = grid;
        Set<String> islands = new HashSet<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (localGrid[i][j] == 1) {
                    islands.add(dfs(i, j, 0, 0).toString());
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("islands = {}", islands);
        }
        return islands.size();
    }

    private static final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int height;
    private int width;
    private int[][] localGrid;

    private StringBuilder dfs(int x, int y, int offsetX, int offsetY) {
        StringBuilder sb = new StringBuilder();
        if (x >= 0 && x < height && y >= 0 && y < width && localGrid[x][y] == 1) {
            sb.append(offsetX).append(offsetY);
            localGrid[x][y] = 0;
            for (int[] dir : dirs) sb.append(dfs(x + dir[0], y + dir[1], offsetX + dir[0], offsetY + dir[1]));
        }
        return sb;
    }

}
