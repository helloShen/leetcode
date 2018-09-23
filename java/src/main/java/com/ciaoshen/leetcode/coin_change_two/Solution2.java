/**
 * Leetcode - coin_change_two
 */
package com.ciaoshen.leetcode.coin_change_two;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution2 implements Solution {

    public int change(int amount, int[] coins) {
        // dpMatrix[i][j]: number of combination to make up amount of "j" with the first "i" types of coin
        int[][] dpMatrix = new int[coins.length + 1][amount + 1];
        // basecase: always 1 combination to make up amount of "0"
        for (int i = 0; i < dpMatrix.length; i++) {
            dpMatrix[i][0] = 1;
        }
        // dp probagation
        for (int i = 1; i < dpMatrix.length; i++) {
            for (int j = 1; j < dpMatrix[0].length; j++) {
                int doNotUseCoinI = dpMatrix[i - 1][j];
                int coin = coins[i - 1];
                int useCoinI = (j - coin >= 0)? dpMatrix[i][j - coin] : 0;
                dpMatrix[i][j] = doNotUseCoinI + useCoinI;
            }
        }
        return dpMatrix[coins.length][amount];
    }

}
