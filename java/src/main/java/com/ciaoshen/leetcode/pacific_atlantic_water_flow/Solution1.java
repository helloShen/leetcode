/**
 * Leetcode - pacific_atlantic_water_flow
 */
package com.ciaoshen.leetcode.pacific_atlantic_water_flow;
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

    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new LinkedList<int[]>();
        init(matrix);
        parsePacific();
        parseAtlantic();
        return check();
    }

    private int[][] localMatrix;
    private boolean[][][] memo;
    private int height, width;

    private void init(int[][] matrix) {
        localMatrix = matrix;
        height = matrix.length;
        width = matrix[0].length;
        memo = new boolean[height][width][2];
    }

    private void parsePacific() {
        // if (log.isDebugEnabled()) {
        //     log.debug("In parsePacific() method!");
        // }
        for (int i = 0; i < width; i++) canFlow(0, i, 0);
        for (int i = 0; i < height; i++) canFlow(i, 0, 0);
    }

    private void parseAtlantic() {
        // if (log.isDebugEnabled()) {
        //     log.debug("In parsePacific() method!");
        // }
        for (int i = 0; i < width; i++) canFlow(height - 1, i, 1);
        for (int i = 0; i < height; i++) canFlow(i, width - 1, 1);
    }

    /**
     * @param  ocean: 0 = pacific, 1 = atlantic
     */
    private void canFlow(int row, int col, int ocean) {
        // if (log.isDebugEnabled()) {
        //     String o = (ocean == 0)? "Pacific" : "Atlantic";
        //     log.debug("[{},{}] can flow to {}", row, col, o);
        // }
        memo[row][col][ocean] = true;
        if (row < height - 1 && !memo[row + 1][col][ocean] && localMatrix[row + 1][col] >= localMatrix[row][col]) canFlow(row + 1, col, ocean);
        if (row > 0 && !memo[row - 1][col][ocean] && localMatrix[row - 1][col] >= localMatrix[row][col]) canFlow(row - 1, col, ocean);
        if (col < width - 1 && !memo[row][col + 1][ocean] && localMatrix[row][col + 1] >= localMatrix[row][col]) canFlow(row, col + 1, ocean);
        if (col > 0 && !memo[row][col - 1][ocean] && localMatrix[row][col - 1] >= localMatrix[row][col]) canFlow(row, col - 1, ocean);
    }

    private List<int[]> check() {
        List<int[]> res = new LinkedList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (memo[i][j][0] && memo[i][j][1]) res.add(new int[]{i, j});
            }
        }
        return res;
    }

}
