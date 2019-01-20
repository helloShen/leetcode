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
class Solution1 implements Solution {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        localMaze = maze;
        visited = new HashSet<Integer>();
        return dfs(start, destination);
    }

    private int[][] localMaze;
    private Set<Integer> visited;

    private boolean dfs(int[] start, int[] destination) {
        // if (log.isDebugEnabled()) {
        //     log.debug("call dfs(start = {}, destination = {})", Arrays.toString(start), Arrays.toString(destination));
        // }
        if (start[0] == destination[0] && start[1] == destination[1]) return true;
        if (!visited.add(start[0] * localMaze[0].length + start[1])) return false;
        return dfs(rollLeft(start), destination) ||
               dfs(rollRight(start), destination) ||
               dfs(rollUp(start), destination) ||
               dfs(rollDown(start), destination);
    }

    private int[] rollLeft(int[] start) {
        for (int i = start[1] - 1; i >= 0; i--) {
            if (localMaze[start[0]][i] == 1) return new int[]{start[0], i + 1};
        }
        return new int[]{start[0], 0};
    }

    private int[] rollRight(int[] start) {
        for (int i = start[1] + 1; i < localMaze[0].length; i++) {
            if (localMaze[start[0]][i] == 1) return new int[]{start[0], i - 1};
        }
        return new int[]{start[0], localMaze[0].length - 1};
    }

    private int[] rollUp(int[] start) {
        for (int i = start[0] - 1; i >= 0; i--) {
            if (localMaze[i][start[1]] == 1) return new int[]{i + 1, start[1]};
        }
        return new int[]{0, start[1]};
    }
    private int[] rollDown(int[] start) {
        for (int i = start[0] + 1; i < localMaze.length; i++) {
            if (localMaze[i][start[1]] == 1) return new int[]{i - 1, start[1]};
        }
        return new int[]{localMaze.length - 1, start[1]};
    }
}
