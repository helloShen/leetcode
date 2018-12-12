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
class Solution1 implements Solution {

    private static class UnionFind {

        private int height;
        private int width;
        private int size;
        private int[] board;

        public UnionFind(int height, int width) {
            this.height = height;
            this.width = width;
            size = height * width;
            board = new int[size];
            for (int i = 0; i < size; i++) board[i] = i;
        }

        public void kill(int a) {
            board[a] = -1;
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            board[rootB] = rootA;
        }

        public int find(int n) {
            if (board[n] == n) return n;
            int root = find(board[n]);
            board[n] = root; // path compression
            return root;
        }

        public int indexOf(int x, int y) {
            return x * width + y;
        }

        public int[] indexToPos(int idx) {
            int[] pos = new int[2];
            pos[0] = idx / width;
            pos[1] = idx % width;
            return pos;
        }

        public int count() {
            Map<Integer, List<Integer>> shapeMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                if (board[i] >= 0) {
                    int root = find(i);
                    int[] pos = indexToPos(i);
                    int[] rootPos = indexToPos(root);
                    int[] offset = new int[]{pos[0] - rootPos[0], pos[1] - rootPos[1]};
                    if (!shapeMap.containsKey(root)) shapeMap.put(root, new LinkedList<Integer>());
                    List<Integer> shapeId = shapeMap.get(root);
                    shapeId.add(offset[0]);
                    shapeId.add(offset[1]);
                }
            }
            Set<List<Integer>> shapeSet = new HashSet<>(shapeMap.values());
            return shapeSet.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) sb.append(board[indexOf(i, j)] + ", ");
                if (sb.length() > 0) sb.delete(sb.length() - 2, sb.length());
                sb.append("\n");
            }
            return sb.toString();
        }

    }

    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0) return 0;
        int height = grid.length;
        int width = grid[0].length;
        UnionFind board = new UnionFind(height, width);
        if (log.isDebugEnabled()) {
            log.debug("Before: \n {}", board.toString());
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int idx = board.indexOf(i, j);
                if (log.isDebugEnabled()) {
                    log.debug("Point [{},{}] = {}, index = {}, union now!", i, j, grid[i][j], idx);
                }
                if (grid[i][j] == 1) {
                    if (i > 0 && grid[i - 1][j] == 1) board.union(idx, board.indexOf(i - 1, j));
                    if (j > 0 && grid[i][j - 1] ==1) board.union(idx, board.indexOf(i, j - 1));
                } else {
                    board.kill(idx);
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("After: \n {}", board.toString());
        }
        return board.count();
    }

}
