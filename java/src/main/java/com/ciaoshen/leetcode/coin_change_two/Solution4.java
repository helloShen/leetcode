/**
 * Leetcode - coin_change_two
 */
package com.ciaoshen.leetcode.coin_change_two;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution4 implements Solution {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i < dp.length; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        return dp[amount];
    }

}
