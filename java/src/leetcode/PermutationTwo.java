/**
 * Leetcode - Algorithm - Leetcode - Permutation 2
 */
package com.ciaoshen.leetcode;
import java.util.*;

class PermutationTwo {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buff = new ArrayList<>();
        List<Integer> candidates = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            candidates.add(nums[i]);
        }
        backtracking(buff,candidates,result);
        return result;
    }
    public void backtracking(List<Integer> buff, List<Integer> candidates, List<List<Integer>> result) {
        if (candidates.size() == 0) { result.add(new ArrayList<Integer>(buff)); return; }
        System.out.println(result);
        for (int i = 0; i < candidates.size(); i++) {
            int temp = candidates.get(i);
            buff.add(temp);
            candidates.remove(i);
            backtracking(buff,candidates,result);
            candidates.add(i,temp);
            buff.remove(buff.size()-1);
            while (i+1 < candidates.size() && candidates.get(i+1).equals(candidates.get(i))) { i++; }
        }
    }

    private static void testPermuteUnique() {
        PermutationTwo test = new PermutationTwo();
        int[][] testCases = new int[][] {
            {},
            {0},
            {1,1},
            {1,1,2},
            //{2,2,6,6,10,12}
        };
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(Arrays.toString(testCases[i]));
            System.out.println(test.permuteUnique(testCases[i]));
        }
    }
    public static void main(String[] args) {
        testPermuteUnique();
    }
}
