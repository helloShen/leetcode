/**
 * Leetcode - Algorithm - Subsets
 */
package com.ciaoshen.leetcode;
import java.util.*;

class Subsets {
    public List<List<Integer>> subsetsV1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) { return res; }
        //backtracking(res,new ArrayList<Integer>(),nums,0);
        backtrackingReverse(res,new ArrayList<Integer>(),nums,0);
        return res;
    }
    public void backtracking(List<List<Integer>> res, List<Integer> temp, int[] nums, int cursor) {
        if (cursor == nums.length) { res.add(new ArrayList<Integer>(temp)); return; }
        temp.add(nums[cursor]);
        backtracking(res,temp,nums,cursor+1);
        temp.remove(temp.size()-1);
        //System.out.println("In " + Arrays.toString(nums) + ": nums[" + cursor + "]" + nums[cursor] + " added!");
        backtracking(res,temp,nums,cursor+1);
    }
    public void backtrackingReverse(List<List<Integer>> res, List<Integer> temp, int[] nums, int cursor) {
        if (cursor == nums.length) { res.add(new ArrayList<Integer>(temp)); return; }
        backtracking(res,temp,nums,cursor+1);
        //System.out.println("In " + Arrays.toString(nums) + ": nums[" + cursor + "]" + nums[cursor] + " added!");
        temp.add(nums[cursor]);
        backtracking(res,temp,nums,cursor+1);
    }
    public List<List<Integer>> subsetsV2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) { return res; }
        int numLen = nums.length;
        int maxMask = (int)Math.pow((double)2,(double)numLen); // 小心int溢出
        List<Integer> temp = new ArrayList<>();
        for (int bitmask = 0; bitmask < maxMask; bitmask++) { // 0000 ~ 1111
            temp.clear();
            //System.out.println("cut= " + cut);
            for (int pos = numLen-1, cut = 1; pos >= 0; cut = cut << 1, pos--) {
                //System.out.println("<<" + cut + ", ");
                if ((bitmask & cut) == cut) { // 取每一位上的信息
                    temp.add(nums[pos]);
                }
            }
            res.add(new ArrayList<Integer>(temp));
        }
        return res;
    }
    /**
     * 起始subset集为：[]
     * 添加S0后为：[], [S0]
     * 添加S1后为：[], [S0], [S1], [S0, S1]
     * 添加S2后为：[], [S0], [S1], [S0, S1], [S2], [S0, S2], [S1, S2], [S0, S1, S2]
     */
    public List<List<Integer>> subsetsV3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) { return res; }
        res.add(new ArrayList<Integer>());
        for (int num : nums) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<Integer>(res.get(i));
                temp.add(num);
                res.add(temp);
            }
        }
        return res;
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) { return res; }
        res.add(new ArrayList<Integer>());
        recursive(res,nums,0);
        return res;
    }
    public void recursive(List<List<Integer>> res, int[] nums, int cursor) {
        if (cursor == nums.length) { return; }
        List<List<Integer>> newComponent = new ArrayList<>();
        for (List<Integer> ele: res) {
            List<Integer> temp = new ArrayList<>(ele);
            temp.add(nums[cursor]);
            newComponent.add(temp);
        }
        res.addAll(newComponent);
        recursive(res,nums,++cursor);
    }
    private static void testSubSets() {
        Subsets test = new Subsets();
        int[][] testCases = new int[][]{
            {1},
            {1,2},
            {1,2,3},
            {1,2,3,4}
        };
        for (int[] nums : testCases) {
            System.out.println("Combinations for " + Arrays.toString(nums) + " is: ");
            System.out.println(test.subsets(nums));
        }
    }
    public static void main(String[] args) {
        testSubSets();
    }
}
