/**
 * Leetcode - Algorithm - Unique Binary Search Trees
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.TreeNode;


class UniqueBinarySearchTrees {
    public int numTreesV1(int n) {
        if (n <= 0) { return 0; }
        return dacV1(1,n);
    }
    public int dacV1(int start, int end) {
        if (start > end) { return 1; }
        int res = 0;
        for (int root = start; root <=end; root++) {
            res += (dacV1(start,root-1) * dacV1(root+1,end));
        }
        return res;
    }
    public int numTreesV2(int n) {
        if (n <= 0) { return 0; }
        Map<Integer,Integer> memo = new HashMap<>();
        return dacV2(1,n,memo);
    }
    public int dacV2(int start, int end, Map<Integer,Integer> memo) {
        if (start > end) { return 1; }
        Integer log = memo.get(new Integer((start << 5)-1+end));
        if (log != null) { return log; }
        int res = 0;
        for (int root = start; root <=end; root++) {
            res += (dacV2(start,root-1,memo) * dacV2(root+1,end,memo));
        }
        memo.put(new Integer((start << 5)-1+end), res);
        return res;
    }
    public int numTreesV3(int n) {
        if (n <= 0) { return 0; }
        int[][] memo = new int[n+1][n+1];
        return dacV3(1,n,memo);
    }
    public int dacV3(int start, int end, int[][] memo) {
        if (start > end) { return 1; }
        int log = memo[start][end];
        if (log > 0) { return log; }
        int res = 0;
        for (int root = start; root <=end; root++) {
            res += (dacV3(start,root-1,memo) * dacV3(root+1,end,memo));
        }
        memo[start][end] = res;
        return res;
    }
    public int numTrees(int n) {
        if (n <= 0) { return 0; }
        int[] memo = new int[n+1];
        memo[0] = 1; memo[1] = 1;
        for (int i = 2; i <=n; i++) {
            for (int j = 1; j <= i; j++) {
                memo[i] += memo[j-1] * memo[i-j];
            }
        }
        return memo[n];
    }
    private static UniqueBinarySearchTrees test = new UniqueBinarySearchTrees();
    private static void testNumTrees() {
        for (int i = 1; i < 20; i++) {
            System.out.println("n = " + i + " has " + test.numTrees(i) + " possible trees!");
        }
    }
    public static void main(String[] args) {
        testNumTrees();
    }
}
