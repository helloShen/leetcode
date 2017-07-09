/**
 * Leetcode - Algorithm - Compare Version Numbers
 */
package com.ciaoshen.leetcode;
import java.util.*;

class CompareVersionNumbers {
    public class SolutionV1 {
        public int compareVersion(String version1, String version2) {
            String[] strs1 = version1.split("\\.");
            String[] strs2 = version2.split("\\.");
            int cur = 0;
            while (cur < strs1.length && cur < strs2.length) {
                int val1 = Integer.parseInt(strs1[cur]);
                int val2 = Integer.parseInt(strs2[cur]);
                if (val1 < val2) {
                    return -1;
                } else if (val1 > val2) {
                    return 1;
                } else { // val1 == val2
                    cur++;
                }
            }
            int cur1 = cur;
            if (cur1 < strs1.length) {
                while (cur1 < strs1.length) {
                    int val1 = Integer.parseInt(strs1[cur1]);
                    if (val1 > 0) { return 1; } // 1.1.1 VS 1.1
                    cur1++;
                }
                return 0; // 1.1.0.0 VS 1.1
            }
            int cur2 = cur;
            if (cur2 < strs2.length) {
                while (cur2 < strs2.length) {
                    int val2 = Integer.parseInt(strs2[cur2]);
                    if (val2 > 0) { return -1; } // 1.1 VS 1.1.1
                    cur2++;
                }
                return 0; // 1.1 VS 1.1.0.0
            }
            return 0; // 1.1 VS 1.1
        }
    }
    public class Solution {
        public int compareVersion(String version1, String version2) {
            String[] strs1 = version1.split("\\.");
            String[] strs2 = version2.split("\\.");
            int len = Math.max(strs1.length,strs2.length);
            for (int i = 0; i < len; i++) {
                int val1 = (i < strs1.length)? Integer.parseInt(strs1[i]) : 0;
                int val2 = (i < strs2.length)? Integer.parseInt(strs2[i]) : 0;
                if (val1 < val2) {
                    return -1;
                } else if (val1 > val2) {
                    return 1;
                }
            }
            return 0;
        }
    }
    private static CompareVersionNumbers test = new CompareVersionNumbers();
    private static Solution solution = test.new Solution();
    private static void print(String version1, String version2, String answer) {
        System.out.println(version1 + " VS " + version2 + ": " + solution.compareVersion(version1,version2) + " (should be " + answer + ")");
    }
    private static void testCompareVersion() {
        String oneOne = "1.1";
        String oneOneZeroZero = "1.1.0.0";
        String oneTwo = "1.2";
        String oneHundred = "1.100";
        print(oneOne,oneTwo,"-1");
        print(oneOne,oneOneZeroZero,"0");
        print(oneOneZeroZero,oneOne,"0");
        print(oneOne,oneHundred,"-1");
        print(oneOne,oneOne,"0");
        print(oneHundred,oneTwo,"1");
    }
    public static void main(String[] args) {
        testCompareVersion();
    }
}
