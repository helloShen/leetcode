/**
 * Leetcode - Algorithm - Isomorphic Strings
 */
package com.ciaoshen.leetcode;
import java.util.*;

class IsomorphicStrings {
    /**
     * 用Map记录映射表
     */
    public class SolutionV1 {
        public boolean isIsomorphic(String s, String t) {
            Map<Character,Character> ts = new HashMap<>(); // key = t, value = s
            Map<Character,Character> st = new HashMap<>(); // key = s, value = t
            for (int i = 0; i < s.length(); i++) {
                Character cs = s.charAt(i);
                Character ct = t.charAt(i);
                Character csShouldBe = ts.get(ct);
                Character ctShouldBe = st.get(cs);
                if (csShouldBe == null && ctShouldBe == null) {
                    ts.put(ct,cs);
                    st.put(cs,ct);
                    // System.out.println("Note: " + cs + " <=> " + ct);
                    // System.out.println("TS = " + ts);
                    // System.out.println("ST = " + st);
                } else if (csShouldBe == null || ctShouldBe == null || csShouldBe != cs || ctShouldBe != ct) {
                    // System.out.println("Error: " + "ct = " + ct + ", cs = " + cs + ", ct should be " + ctShouldBe + ", cs should be " + csShouldBe);
                    return false;
                } else {
                    // System.out.println(cs + " <=> " + ct);
                }
            }
            return true;
        }
    }
    /**
     * 用数组记录映射表
     * String中的所有char都属于ASCII字符集，对应[0,255]
     */
    public class Solution {
        public boolean isIsomorphic(String s, String t) {
            char[] ts = new char[256];
            char[] st = new char[256];
            for (int i = 0; i < s.length(); i++) {
                char cs = s.charAt(i);
                char ct = t.charAt(i);
                char csShouldBe = ts[ct];
                char ctShouldBe = st[cs];
                if (csShouldBe == '\u0000' && ctShouldBe == '\u0000') {
                    ts[ct] = cs;
                    st[cs] = ct;
                } else if (csShouldBe == '\u0000' || ctShouldBe == '\u0000' || csShouldBe != cs || ctShouldBe != ct) {
                    return false;
                }
            }
            return true;
        }
    }
    private static IsomorphicStrings test = new IsomorphicStrings();
    private static Solution solution = test.new Solution();
    private static void callIsIsomorphic(String s, String t) {
        if (solution.isIsomorphic(s,t)) {
            System.out.println("[" + s + "]" + " and [" + t + "] are isomorphic strings.");
        } else {
            System.out.println("[" + s + "]" + " and [" + t + "] are not isomorphic strings.");
        }
    }
    private static void test() {
        String s1 = "title";
        String t1 = "paper";
        String s2 = "foo";
        String t2 = "bar";
        callIsIsomorphic(s1,t1);
        callIsIsomorphic(s2,t2);
    }
    public static void main(String[] args) {
        test();
    }
}
