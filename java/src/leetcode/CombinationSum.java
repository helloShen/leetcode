/**
 * Leetcode - Algorithm - Combination Sum
 */
package com.ciaoshen.leetcode;
import java.util.*;

class CombinationSum {
    public List<List<Integer>> combinationSumV1(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        recursionV1(new ArrayList<Integer>(), 0, candidates, target, result);
        return result;
    }
    public void recursionV1(List<Integer> register, int sum, int[] candidates, int target, List<List<Integer>> result) {
        for (int i : candidates) {
            if (sum + i > target) { break; }
            List<Integer> copy = new ArrayList<>(register);
            copy.add(i);
            if (sum + i == target) {
                Collections.sort(copy);
                if (! result.contains(copy)) {
                    result.add(copy);
                }
            } else {
                recursionV1(copy,sum+i,candidates,target,result);
            }
        }
    }
    public List<List<Integer>> combinationSumV2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        recursionV2(new ArrayList<Integer>(), 0, candidates, 0, target, result);
        return result;
    }
    public void recursionV2(List<Integer> register, int sum, int[] candidates, int start, int target, List<List<Integer>> result) {
        for (int i = start; i < candidates.length; i++) {
            int newSum = sum + candidates[i];
            if (newSum > target) { break; }
            List<Integer> copy = new ArrayList<>(register);
            copy.add(candidates[i]);
            if (newSum == target) {
                result.add(copy);
            } else {
                recursionV2(copy,newSum,candidates,i,target,result);
            }
        }
    }
    public List<List<Integer>> combinationSumV3(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        recursionV3(new Integer[0], 0, candidates, 0, target, result);
        return result;
    }
    public void recursionV3(Integer[] register, int sum, int[] candidates, int start, int target, List<List<Integer>> result) {
        for (int i = start; i < candidates.length; i++) {
            int newSum = sum + candidates[i];
            if (newSum > target) { break; }
            Integer[] copy = Arrays.copyOf(register,register.length+1);
            copy[copy.length-1] = candidates[i];
            if (newSum == target) {
                result.add(new ArrayList<Integer>(Arrays.asList(copy)));
            } else {
                recursionV3(copy,newSum,candidates,i,target,result);
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), target, candidates, 0, result);
        return result;
    }
    public void backtrack(List<Integer> temp, int remain, int[] candidates, int start, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (remain < candidates[i]) { break; }
            temp.add(candidates[i]);
            backtrack(temp,remain-candidates[i],candidates,i,result);
            temp.remove(temp.size()-1);
        }
    }
    private static void testCombinationSum() {
        CombinationSum test = new CombinationSum();
        int[][] testCases = new int[][] {
            {},
            {1},
            {2,3,6,7},
            {8,7,4,3}
        };
        int[] target = new int[]{0,5,10,11};
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Array: " + Arrays.toString(testCases[i]) + ", Target: " + target[i] + ">>>   " + test.combinationSum(testCases[i],target[i]));
        }
    }
    public static void main(String[] args) {
        testCombinationSum();
    }
}
