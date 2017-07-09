/**
 * Leetcode - Algorithm - Decode Ways
 */
package com.ciaoshen.leetcode;
import java.util.*;

class DecodeWays {
    public int numDecodingsV1(String s) {
        if (s.length() == 0) { return 0; }
        char[] c = s.toCharArray();
        int[] count = new int[]{ 0 };
        recursive(c,0,count);
        return count[0];
    }
    public void recursive(char[] c, int cur, int[] count) {
        if (cur >= c.length) { count[0]++; return; }
        if (c[cur] == '0') { return; }
        recursive(c,cur+1,count);
        if (cur+1 < c.length && (c[cur] == '1' || (c[cur] == '2' && c[cur+1] <= '6'))) {
            recursive(c,cur+2,count);
        }
    }
    public int numDecodingsV2(String s) {
        if (s.length() == 0) { return 0; }
        char[] c = s.toCharArray();
        Map<Integer,Integer> memo = new HashMap<>();
        memo.put(s.length(),1);
        return dpV1(c,0,memo);
    }
    public int dpV1(char[] c, int cur, Map<Integer,Integer> memo) {
        // look up in memo
        Integer res = memo.get(cur);
        if (res != null) { return res; }
        // calculate for the first time
        if (c[cur] == '0') {
            memo.put(cur,0);
            return 0;
        }
        res = dpV1(c,cur+1,memo);
        if (cur+1 < c.length && (c[cur] == '1' || (c[cur] == '2' && c[cur+1] <= '6'))) {
            res = res + dpV1(c,cur+2,memo);
        }
        memo.put(cur,res); // memorise the result
        return res;
    }
    public int numDecodingsV3(String s) {
        if (s.length() == 0) { return 0; }
        char[] c = s.toCharArray();
        int[] memo = new int[c.length+1];
        Arrays.fill(memo,-1);
        memo[c.length] = 1;
        return dp(c,0,memo);
    }
    public int dp(char[] c, int cur, int[] memo) {
        // look up in memo
        Integer res = memo[cur];
        if (res != -1) { return res; }
        // calculate for the first time
        if (c[cur] == '0') {
            memo[cur] = 0;
            return 0;
        }
        res = dp(c,cur+1,memo);
        if (cur+1 < c.length && (c[cur] == '1' || (c[cur] == '2' && c[cur+1] <= '6'))) {
            res = res + dp(c,cur+2,memo);
        }
        memo[cur] = res; // memorise the result
        return res;
    }
    public int numDecodings(String s) {
        if (s.length() == 0) { return 0; }
        char[] c = s.toCharArray();
        int[] memo = new int[c.length+1];
        memo[c.length] = 1;
        for (int i = c.length-1; i >= 0; i--) {
            if (c[i] == '0') { memo[i] = 0; continue; }
            if (i+1 < c.length && (c[i] == '1' || (c[i] == '2' && c[i+1] <= '6'))) {
                memo[i] = memo[i+1] + memo[i+2];
            } else {
                memo[i] = memo[i+1];
            }
        }
        return memo[0];
    }
    private static DecodeWays test = new DecodeWays();
    private static void testNumDecodings() {
        String[] testCases = new String[]{
            "74568923457",
            "28314618267",
            "124124234",
            "0",
            "101010",
            "01010101",
            "17",
            "7",
            "1",
            "2"
        };
        for (String str : testCases) {
            System.out.println(str + " has " + test.numDecodings(str) + " ways to decode!");
        }
    }
    public static void main(String[] args) {
        testNumDecodings();
    }
}
