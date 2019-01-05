/**
 * Leetcode - stone_remove_same_row_column
 */
package com.ciaoshen.leetcode.stone_remove_same_row_column;
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

    public int removeStones(int[][] stones) {
        init(stones.length);
        Map<Integer, Integer> rowMap = new HashMap<>();
        Map<Integer, Integer> colMap = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            int[] stone = stones[i];
            if (!rowMap.containsKey(stone[0])) {
                rowMap.put(stone[0], i);
            } else {
                union(rowMap.get(stone[0]), i);
            }
            if (!colMap.containsKey(stone[1])) {
                colMap.put(stone[1], i);
            } else {
                union(colMap.get(stone[1]), i);
            }
        }
        // if (log.isDebugEnabled()) {
        //     log.debug("Finally, rowMap = {}", rowMap);
        //     log.debug("Finally, colMap = {}", colMap);
        //     log.debug("Finally, board = {}", Arrays.toString(board));
        // }
        return stones.length - countGroup();
    }

    private int[] board;

    private void init(int size) {
        board = new int[size];
        for (int i = 0; i < size; i++) board[i] = i;
        // if (log.isDebugEnabled()) {
        //     log.debug("initial board = {}", Arrays.toString(board));
        // }
    }

    // merge group B to group A
    private void union(int a, int b) {
        // if (log.isDebugEnabled()) {
        //     log.debug("union stone {} and {}", a, b);
        // }
        int rootA = find(a);
        int rootB = find(b);
        board[rootB] = rootA;
    }

    private int find(int a) {
        if (board[a] == a) return a;
        int root = find(board[a]);
        board[a] = root; // path compress
        return root;
    }

    private int countGroup() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == i) count++;
        }
        return count;
    }
}
