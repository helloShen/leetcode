/**
 * Leetcode - delete_and_earn
 */
package com.ciaoshen.leetcode.delete_and_earn;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution1 implements Solution {

    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) { return 0; }
        Map<Integer, Integer> table = new HashMap<>();
        for (int n : nums) {
            if (!table.containsKey(n)) {
                table.put(n, n);
            } else {
                table.put(n, table.get(n) + n);
            }
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(table.entrySet());
        Collections.sort(list, (Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) -> a.getKey() - b.getKey());
        dpTable = new int[list.size() + 1];
        Arrays.fill(dpTable, -1);
        dpTable[list.size()] = 0;
        dpTable[list.size() - 1] = list.get(list.size() - 1).getValue();
        return dp(list, 0);
    }

    private int[] dpTable;

    private int dp(List<Map.Entry<Integer, Integer>> list, int index) {
        if (dpTable[index] != -1) {
            return dpTable[index];
        }
        int value = list.get(index).getValue();
        int curr = list.get(index).getKey();
        int next = list.get(index + 1).getKey();
        if (next - curr > 1) {
            dpTable[index] = value + dp(list, index + 1);
        } else {
            int getThisNum = value + dp(list, index + 2);
            int notGetThisNum = dp(list, index + 1);
            dpTable[index] = Math.max(getThisNum, notGetThisNum);
        }
        return dpTable[index];
    }

}
