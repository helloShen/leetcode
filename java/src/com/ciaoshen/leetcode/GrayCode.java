/**
 * Leetcode - Algorithm - Gray Code
 */
package com.ciaoshen.leetcode;
import java.util.*;

class GrayCode {
    public List<Integer> grayCodeV1(int n) {
        if (n == 0) { return new ArrayList<Integer>(Arrays.asList(new Integer[]{0})); }
        List<Integer> res = new ArrayList<>();
        recursive(res,n,0);
        return res;
    }
    public boolean recursive(List<Integer> res, int n, int num) {
        if (res.size() == (int)Math.pow(2.0,(double)n)) { return true; }
        if (res.contains(num)) { return false; }
        res.add(num);
        int mask = 1;
        for (int i = 0; i < n; i++) {
            int variant = num ^ mask; // 逐位取补码
            if (recursive(res,n,variant)) { return true; }
            mask = mask << 1;
        }
        res.remove(res.size()-1);
        return false;
    }
    public List<Integer> grayCode(int n) {
        if (n == 0) { return new ArrayList<Integer>(Arrays.asList(new Integer[]{0})); }
        List<Integer> res = grayCode(n-1);
        Deque<Integer> mirror = new LinkedList<>();
        int mask = 1 << n-1;
        for (int i : res) {
            mirror.offerFirst(i ^ mask);
        }
        res.addAll(mirror);
        return res;
    }
    private static GrayCode test = new GrayCode();
    private static void testGrayCode() {
        for (int i = 0; i < 6; i++) {
            System.out.println(test.grayCode(i));
        }
    }
    public static void main(String[] args) {
        testGrayCode();
    }
}
