/**
 * Leetcode - Algorithm - Combinations
 */
package com.ciaoshen.leetcode;
import java.util.*;

class Combinations {
    public List<List<Integer>> combineV1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtracking(res,temp,k,1,n+1);
        return res;
    }
    // [from,to):  from=inclusive,  to=exclusive
    public void backtracking(List<List<Integer>> res, List<Integer> temp, int remain, int from, int to) {
        if (remain == 0) { res.add(new ArrayList<Integer>(temp)); return; }
        for (int i = from; i < to; i++) {
            temp.add(i);
            backtracking(res,temp,remain-1,i+1,to);
            temp.remove(temp.size()-1);
        }
    }
    public List<List<Integer>> combineV2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == n) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) { list.add(i); }
            res.add(list);
            return res;
        }
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                res.add(temp);
            }
            //System.out.println("n = " + n + ", k = " + k + ".   " + res);
            return res;
        }
        res = combine(n-1,k-1);
        for (List<Integer> list : res) { list.add(n); }
        res.addAll(combine(n-1,k));
        return res;
    }
    public List<List<Integer>> combineV3(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k > n) { return res; }
        if (k == 0) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        res = combine(n-1,k-1);
        for (List<Integer> list : res) { list.add(n); }
        res.addAll(combine(n-1,k));
        return res;
    }
    public List<List<Integer>> combine(int n, int k) {
        Map<String,List<List<Integer>>> memo = new HashMap<>();
        return dp(n,k,memo);
    }
    public List<List<Integer>> dp(int n, int k, Map<String,List<List<Integer>>> memo) {
        String key = "" + n + k;
        if (memo.containsKey(key)) { return memo.get(key); }
        List<List<Integer>> res = new ArrayList<>();
        if (k > n) {
            memo.put(key,res);
            return res;
        }
        if (k == 0) {
            res.add(new ArrayList<Integer>());
            memo.put(key,res);
            return res;
        }
        res = combine(n-1,k-1);
        for (List<Integer> list : res) { list.add(n); }
        res.addAll(combine(n-1,k));
        memo.put(key,res);
        return res;
    }
    private static Combinations test = new Combinations();
    private static int[][] testCases = new int[][]{
        {1,1},
        {2,1},
        {3,2},
        {4,2},
        {3,3},
        {5,2}
        //{10,5}
    };
    private static void testCombine() {
        for (int[] pair : testCases) {
            System.out.println("n = " + pair[0] + ", k= " + pair[1]);
            System.out.println(test.combine(pair[0],pair[1]));
        }
    }
    public static void main(String[] args) {
        testCombine();
    }
}
