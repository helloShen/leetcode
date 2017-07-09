/**
 * Leetcode - Algorithm - Leetcode - Permutation
 */
package com.ciaoshen.leetcode;
import java.util.*;

class Permutation {

    public List<List<Integer>> permute(int[] nums) {
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
        }
    }

    private static void testPermute() {
        Permutation test = new Permutation();
        int[][] testCases = new int[][] {
            {},
            {0},
            {1,2,3},
            {3,2,1},
            {2,4,6,8,10,12}
        };
        for (int i = 0; i < testCases.length; i++) {
            System.out.println(Arrays.toString(testCases[i]));
            System.out.println(test.permute(testCases[i]));
        }
    }
    public static void main(String[] args) {
        testPermute();
    }
}
