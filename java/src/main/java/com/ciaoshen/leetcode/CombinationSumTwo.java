/**
 * Leetcode - Algorithm - Combination Sum 2
 */
package com.ciaoshen.leetcode;
import java.util.*;

class CombinationSumTwo {
    // [1,1,2,5,6,7,10]
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtracking(new ArrayList<Integer>(), target, candidates, 0, target, result);
        return result;
    }
    public void backtracking(List<Integer> temp, int remain, int[] candidates, int start, int target, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i != start && candidates[i] == candidates[i-1]) { continue; } // eliminate duplicates
            int preRemain = remain - candidates[i];
            if (preRemain < 0) { break; } // 剪枝
            temp.add(candidates[i]);
            backtracking(temp, preRemain, candidates, i+1, target, result);
            temp.remove(temp.size()-1);
        }
    }
    private static void testCombinationSum2() {
        CombinationSumTwo test = new CombinationSumTwo();
        int[][] testCases = new int[][] {
            {},
            {1},
            {2,3,6,7},
            {8,7,4,3},
            {1,1,2,5,6,7,10}
        };
        int[] target = new int[]{0,5,10,11,8};
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Array: " + Arrays.toString(testCases[i]) + ", Target: " + target[i] + ">>>   " + test.combinationSum2(testCases[i],target[i]));
        }
    }
    public static void main(String[] args) {
        testCombinationSum2();
    }
}
