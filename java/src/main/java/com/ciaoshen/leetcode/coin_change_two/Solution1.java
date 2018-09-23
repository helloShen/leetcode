/**
 * Leetcode - coin_change_two
 */
package com.ciaoshen.leetcode.coin_change_two;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int change(int amount, int[] coins) {
        localCoins = coins;
        dpTable = new HashMap<Integer, Set<List<Integer>>>();
        dp(amount);
        return dpTable.get(amount).size();
    }

    private int[] localCoins;
    private Map<Integer, Set<List<Integer>>> dpTable;

    /** from bottom to top */
    private void dp(int amount) {
        List<Integer> emptySolution = new ArrayList<>();
        Set<List<Integer>> zeroSet = new HashSet<>();
        zeroSet.add(emptySolution);
        dpTable.put(0, zeroSet);
        for (int i = 1; i <= amount; i++) {
            Set<List<Integer>> set = new HashSet<>();
            dpTable.put(i, set);
            for (int coin : localCoins) {
                int subcase = i - coin;
                if (dpTable.containsKey(subcase)) {
                    Set<List<Integer>> subSet = dpTable.get(subcase);
                    for (List<Integer> subSolution : subSet) {
                        List<Integer> newSolution = new ArrayList<>(subSolution);
                        newSolution.add(firstEqualGreater(coin, newSolution), coin);
                        set.add(newSolution);
                    }
                }
            }
        }
    }
    /** return the index of first element equal to or greater than target value */
    private int firstEqualGreater(int target, List<Integer> list) {
        int lo = 0, hi = list.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

}
