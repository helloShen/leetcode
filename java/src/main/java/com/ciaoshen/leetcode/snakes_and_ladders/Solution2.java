/**
 * Leetcode - snakes_and_ladders
 * BFS
 */
package com.ciaoshen.leetcode.snakes_and_ladders;
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

    public int snakesAndLadders(int[][] board) {
        int size = board.length;
        int target = size * size;
        Map<Integer, Integer> skipMap = skipMap(board);
        List<Integer> list = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        list.add(1);
        visited.add(1);
        int step = 0;
        while (!list.isEmpty()) {
            ++step;
            int len = list.size();
            for (int i = 0; i < len; i++) {
                int start = list.remove(0);
                if (log.isDebugEnabled()) {
                    log.debug("step[{}] start @{}", step, start);
                }
                for (int dice = 1; dice <= 6; dice++) {
                    int to = start + dice;
                    if (log.isDebugEnabled()) {
                        log.debug("[{}] -> [{}], dice = {}", start, to, dice);
                    }
                    if (to > target) break;
                    if (to == target) return step;
                    if (skipMap.containsKey(to)) {
                        int skipTarget = skipMap.get(to);
                        if (skipTarget == target) return step;
                        if (visited.add(skipTarget)) list.add(skipTarget);
                    } else if (visited.add(to)) {
                        list.add(to);
                    }
                }
            }
        }
        return -1;
    }

    private Map<Integer, Integer> skipMap(int[][] board) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int row = board.length - 1, col = 0, dir = 1, count = 1; row >= 0; row--) {
            while (col >= 0 && col < board.length) {
                if (board[row][col] != -1) table.put(count, board[row][col]);
                col += dir;
                count++;
            }
            dir = -dir;
            col += dir;
        }
        if (log.isDebugEnabled()) {
            log.debug("Skip Map = {}", table);
        }
        return table;
    }

}
