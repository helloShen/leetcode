/**
 * Leetcode - redundant_connection
 */
package com.ciaoshen.leetcode.redundant_connection;
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

    public int[] findRedundantConnection(int[][] edges) {
        int size = 1000;
        board = new int[size + 1];
        for (int i = 1; i <= size; i++) board[i] = i;
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) return edge;
        }
        return null;
    }
    private int[] board;
    private boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        board[rootB] = rootA;
        return true;
    }
    private int find(int a) {
        if (board[a] == a) return a;
        int prev = find(board[a]);
        board[a] = prev; // path compression
        return prev;
    }

}
