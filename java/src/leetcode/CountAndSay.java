/**
 * Leetcode - Algorithm - Count and Say
 */
package com.ciaoshen.leetcode;
import java.util.*;

class CountAndSay {
    public String countAndSayV1(int n) {
        if (n < 1) { return ""; }
        if (n == 1) { return "1"; }
        return read(countAndSay(n-1));
    }
    public String read(String s) {
        int cursor = 0, count = 1;
        char reg = s.charAt(cursor);
        StringBuilder sb = new StringBuilder();
        while (++cursor < s.length()) {
            char c = s.charAt(cursor);
            if (c != reg) {
                sb.append(Integer.toString(count)).append(reg);
                reg = c;
                count = 1;
            } else {
                count++;
            }
        }
        sb.append(Integer.toString(count)).append(reg);
        return sb.toString();
    }
    public String countAndSay(int n) {
        if (n < 1) { return ""; }
        String bootstrap = "1";
        while (--n > 0) {
            bootstrap = read(bootstrap);
        }
        return bootstrap;
    }
    private static void testCountAndSay() {
        CountAndSay test = new CountAndSay();
        for (int i = 0; i < 10; i++) {
            System.out.println(test.countAndSay(i));
        }
    }
    public static void main(String[] args) {
        testCountAndSay();
    }
}
