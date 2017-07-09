/**
 * Leetcode - Algorithm - Pascal's Triangle Two
 */
package com.ciaoshen.leetcode;
import java.util.*;

class PascalsTriangleTwo {
    public List<Integer> getRowV1(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 0; i < rowIndex; i++) {
            int last = 0;
            for (int j = 1; j < res.size(); j++) {
                int temp = res.get(j-1)+res.get(j);
                if (last > 0) { res.set(j-1,last); }
                last = temp;
            }
            if (last > 0) { res.set(res.size()-1,last); }
            res.add(1);
        }
        return res;
    }
    public List<Integer> getRow(int rowIndex) {
        int[] p = new int[rowIndex+1];
        for (int i = 0; i <= rowIndex; i++) {
            int memo = 0;
            for (int j = 1; j < i; j++) {
                int temp = p[j-1] + p[j];
                if (memo > 0) { p[j-1] = memo; }
                memo = temp;
            }
            if (memo > 0) { p[i-1] = memo; }
            p[i] = 1;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {
            res.add(p[i]);
        }
        return res;
    }
    private static PascalsTriangleTwo test = new PascalsTriangleTwo();
    private static void testGetRow() {
        for (int i = 0; i < 10; i++) {
            System.out.println(test.getRow(i));
        }
    }
    public static void main(String[] args) {
        testGetRow();
    }
}
