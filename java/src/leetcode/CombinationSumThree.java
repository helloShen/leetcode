/**
 * Leetcode - Algorithm - Combination Sum Three
 */
package com.ciaoshen.leetcode;
import java.util.*;

class CombinationSumThree {
    /**
     * Backtracking Version 1
     */
    public class SolutionV1 {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> nums = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));
            List<Integer> buffer = new ArrayList<>();
            backtracking(k,n,nums,buffer,result);
            return result;
        }

        private void backtracking(int k, int n, List<Integer> nums, List<Integer> buffer, List<List<Integer>> memo) {
            if (k == 0) {
                if (n == 0) { memo.add(new ArrayList<Integer>(buffer)); }
                return;
            }
            if (n <= 0) { return; }
            List<Integer> copyNums = new ArrayList<>(nums);
            int size = copyNums.size();
            for (int i = 0; i < size; i++) {
                Integer num = copyNums.remove(0);
                buffer.add(num);
                backtracking(k-1,n-num,copyNums,buffer,memo);
                buffer.remove(num);
            }
        }
    }
    public class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> buffer = new ArrayList<>();
            backtracking(k,n,buffer,1,result);
            return result;
        }
        /**
         * 用 int start 模拟 pointer
         */
        private void backtracking(int k, int n, List<Integer> buffer, int start, List<List<Integer>> memo) {
            // System.out.println("k=" + k + ", n=" + n + ", start=" + start);
            if (k == 0) {
                if (n == 0) { memo.add(new ArrayList<Integer>(buffer)); }
                return;
            }
            if (n <= 0) { return; } // 提前剪枝
            for (int i = start; i < 10; i++) {
                buffer.add(i);
                backtracking(k-1,n-i,buffer,i+1,memo);
                buffer.remove(buffer.size()-1);
            }
        }
    }
    private static CombinationSumThree test = new CombinationSumThree();
    private static Solution solution = test.new Solution();
    private static void callCombinationSum3(int k, int n) {
        System.out.println(k + " numbers add up to " + n + ": " + solution.combinationSum3(k,n));
    }
    private static void getAllCombinations() {
        for (int j = 1; j < 46; j++) {
            System.out.println(">>>>>> Sum to " + j + " <<<<<<");
            for (int i = 1; i < 10; i++) {
                List<List<Integer>> result = solution.combinationSum3(i,j);
                if (!result.isEmpty()) {
                    System.out.println(result);
                }
            }
            System.out.println("\n\n");
        }
    }
    private static void test() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 46; j++) {
                callCombinationSum3(i,j);
            }
        }
    }
    public static void main(String[] args) {
        // test();
        getAllCombinations();
    }
}
