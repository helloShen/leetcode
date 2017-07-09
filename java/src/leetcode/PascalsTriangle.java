/**
 * Leetcode - Algorithm - Pascal's Triangle
 */
package com.ciaoshen.leetcode;
import java.util.*;

class PascalsTriangle {
    public List<List<Integer>> generateV1(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 0) { return res; }
        res.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{1})));
        recursion(numRows-1,res);
        return res;
    }
    public void recursion(int numRows, List<List<Integer>> res) {
        if (numRows == 0) { return; }
        List<Integer> last = res.get(res.size()-1);
        int size = last.size();
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            int one = (i == 0)? 0 : last.get(i-1);
            int two = (i == size)? 0 : last.get(i);
            newList.add(one+two);
        }
        res.add(newList);
        recursion(numRows-1,res);
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows == 0) { return res; }
        res.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{1})));
        while (--numRows > 0) {
            List<Integer> last = res.get(res.size()-1);
            int size = last.size();
            List<Integer> newList = new ArrayList<>();
            for (int i = 0; i <= size; i++) {
                int one = (i == 0)? 0 : last.get(i-1);
                int two = (i == size)? 0 : last.get(i);
                newList.add(one+two);
            }
            res.add(newList);
        }
        return res;
    }

    private static PascalsTriangle test = new PascalsTriangle();
    private static void testGenerate() {
        for (int i = 0; i < 5; i++) {
            System.out.println(test.generate(i));
        }
    }
    public static void main(String[] args) {
        testGenerate();
    }
}
