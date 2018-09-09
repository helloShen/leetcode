/**
 * Leetcode - Algorithm - Subsets Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class SubsetsTwo {
    public List<List<Integer>> subsetsWithDupV1(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> res = new HashSet<>();
        res.add(new ArrayList<Integer>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> mirror = new ArrayList<>(res);
            System.out.println("Result for n-1=" + res);
            for (List<Integer> list : mirror) {
                list.add(nums[i]);
                res.add(new ArrayList<Integer>(list));
                list.remove(list.size()-1);
            }
        }
        return new ArrayList<List<Integer>>(res);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        for (int i = 0, start = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i-1]) { start = 0; } // append nums[i] to each old member
            int size = res.size();
            for (int j = start; j < size; j++) {
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
            start = size; // next iteration begin after the last member
        }
        return res;
    }
    private static void testListCopy() {
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4,5}));
        List<List<Integer>> listlist = new ArrayList<>();
        listlist.add(list);
        List<List<Integer>> listlistcopy = new ArrayList<>(listlist);
        list.add(999);
        System.out.println("List = " + list); // list肯定多了999元素
        System.out.println("ListList = " + listlist); // listlist也受影响
        System.out.println("ListListCopy = " + listlistcopy); // listlistcopy里的list就是那个前面那个list，所以也受影响
    }
    private static SubsetsTwo test = new SubsetsTwo();
    private static int[][] testCases = new int[][]{
        {1},
        {2,2},
        {1,2,2}
    };
    private static void testSubsetsWithDup() {
        for (int[] nums : testCases) {
            System.out.println("For array: " + Arrays.toString(nums));
            System.out.println("Subsets: " + test.subsetsWithDup(nums));
        }
    }
    public static void main(String[] args) {
        testSubsetsWithDup();
        //testListCopy();
    }
}
