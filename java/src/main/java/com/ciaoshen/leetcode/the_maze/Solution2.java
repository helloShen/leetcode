/**
 * Leetcode - the_maze
 */
package com.ciaoshen.leetcode.the_maze;
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

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        height = maze.length;
        width = maze[0].length;
        localMaze = maze;
        visited = new int[height][width];
        return dfs(start, destination);
    }

    private int height, width;
    private int[][] localMaze;
    private int[][] visited;

    private boolean dfs(int[] start, int[] destination) {
        if (Arrays.equals(start, destination)) return true;
        if (visited[start[0]][start[1]] == 1) return false;
        visited[start[0]][start[1]] = 1;
        return dfs(rollLeft(start), destination) ||
               dfs(rollRight(start), destination) ||
               dfs(rollUp(start), destination) ||
               dfs(rollDown(start), destination);
    }

    private int[] rollLeft(int[] start) {
        int row = start[0], col = start[1];
        for (int i = col - 1; i >= 0; i--) {
            if (localMaze[row][i] == 1) return new int[]{row, i + 1};
        }
        return new int[]{row, 0};
    }

    private int[] rollRight(int[] start) {
        int row = start[0], col = start[1];
        for (int i = col + 1; i < width; i++) {
            if (localMaze[row][i] == 1) return new int[]{row, i - 1};
        }
        return new int[]{row, width - 1};
    }

    private int[] rollUp(int[] start) {
        int row = start[0], col = start[1];
        for (int i = row - 1; i >= 0; i--) {
            if (localMaze[i][col] == 1) return new int[]{i + 1, col};
        }
        return new int[]{0, col};
    }
    private int[] rollDown(int[] start) {
        int row = start[0], col = start[1];
        for (int i = row + 1; i < height; i++) {
            if (localMaze[i][col] == 1) return new int[]{i - 1, col};
        }
        return new int[]{height - 1, col};
    }
}
