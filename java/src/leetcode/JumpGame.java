/**
 * Leetcode - Algorithm - Jump Game
 */
package com.ciaoshen.leetcode;
import java.util.*;

class JumpGame {

    public boolean canJumpV1(int[] nums) {
        return dfs(nums,0);
    }
    public boolean dfs(int[] nums, int cursor) {
        if (cursor == nums.length-1) { return true; }
        if (cursor >= nums.length || nums[cursor] == 0 ) { return false; }
        for (int i = 1; i <= nums[cursor]; i++) {
            if (dfs(nums,cursor+i)) { return true; }
        }
        return false;
    }
    public boolean canJump(int[] nums) {
        Map<Integer,Boolean> memo = new HashMap<>();
        return dp(nums,0,memo);
    }
    public boolean dp(int[] nums, int cursor, Map<Integer,Boolean> memo) {
        Boolean res = memo.get(cursor);
        if (res != null) { return res; }
        // dfs
        if (cursor == nums.length-1) { memo.put(cursor,true); return true; }
        if (cursor >= nums.length || nums[cursor] == 0 ) { memo.put(cursor,false); return false; }
        res = false;
        for (int i = 1; i <= nums[cursor]; i++) {
            if (dp(nums,cursor+i,memo)) { res = true; break; }
        }
        memo.put(cursor,res);
        return res;
    }
    public boolean canJumpV3(int[] nums) {
        int cursor = 0;
        for (int scope = 0; cursor < nums.length && cursor <= scope; cursor++) {
            scope = Math.max(scope, cursor + nums[cursor]);
        }
        return cursor == nums.length;
    }
    private static JumpGame test = new JumpGame();
    private static int[][] testCases = new int[][] {
        {0},
        {1},
        {1,2},
        {2,3,1,1,4},
        {3,2,1,0,4},
        {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6}
    };
    private static void testCanJump() {
        for (int[] nums : testCases) {
            System.out.println(Arrays.toString(nums) + ": " + test.canJump(nums));
        }
    }
    public static void main(String[] args) {
        testCanJump();
    }
}
