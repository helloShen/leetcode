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
class Solution1 implements Solution {

    public int removeStones(int[][] stones) {
        maxMoves = 0;
        statistic(stones);
        backtracking(0);
        return maxMoves;
    }

    private void backtracking(int moves) {
        maxMoves = Math.max(moves, maxMoves);
        if (log.isDebugEnabled()) {
            log.debug("current moves = {}", moves);
        }
        for (int i = 0; i < list.size(); i++) {
            int[] stone = list.get(i);
            List<Integer> stonesOnThisRow = rowMap.get(stone[0]);
            boolean shareOnRow = (stonesOnThisRow != null) && (stonesOnThisRow.size() > 1);
            List<Integer> stonesOnThisCol = colMap.get(stone[1]);
            boolean shareOnCol = (stonesOnThisCol != null) && (stonesOnThisCol.size() > 1);
            if (shareOnRow || shareOnCol) {
                list.remove(i);
                stonesOnThisRow.remove(new Integer(stone[1]));
                stonesOnThisCol.remove(new Integer(stone[0]));
                backtracking(moves + 1);
                list.add(i, stone);
                stonesOnThisRow.add(stone[1]);
                stonesOnThisCol.add(stone[0]);
            }
        }
    }

    private int maxMoves;
    private List<int[]> list = new LinkedList<>();
    private Map<Integer, List<Integer>> rowMap = new HashMap<>();
    private Map<Integer, List<Integer>> colMap = new HashMap<>();

    private void statistic(int[][] stones) {
        list.clear();
        rowMap.clear();
        colMap.clear();
        for (int[] stone : stones) {
            list.add(stone);
            if (!rowMap.containsKey(stone[0])) rowMap.put(stone[0], new LinkedList<Integer>());
            rowMap.get(stone[0]).add(stone[1]);
            if (!colMap.containsKey(stone[1])) colMap.put(stone[1], new LinkedList<Integer>());
            colMap.get(stone[1]).add(stone[0]);
        }
    }

}
