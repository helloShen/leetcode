/**
 * Leetcode - coin_change_two
 */
package com.ciaoshen.leetcode.coin_change_two;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution3 implements Solution {

    public int change(int amount, int[] coins) {
        // dp[i]: number of combination to make up amount of "i" with current bag of coins
        int[] dp = new int[amount + 1];
        // basecase: always 1 combination to make up amount of "0"
        dp[0] = 1;
        // dp probagation
        for (int coinIdx = 0; coinIdx < coins.length; coinIdx++) {
            for (int i = 1; i < dp.length; i++) {
                int doNotUseCoin = dp[i];
                int coin = coins[coinIdx];
                int useCoin = (i - coin >= 0)? dp[i - coin] : 0;
                dp[i] = doNotUseCoin + useCoin;
            }
        }
        return dp[amount];
    }

}
