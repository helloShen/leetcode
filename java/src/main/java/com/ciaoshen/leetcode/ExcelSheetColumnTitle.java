/**
 * Leetcode - Algorithm - Excel Sheet Column Title
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ExcelSheetColumnTitle {
    public class SolutionV1 {
        public String convertToTitle(int n) {
            StringBuilder res = new StringBuilder();
            if (n < 1) { return res.toString(); }
            // find the highest level
            long nl = (long)n;
            int lev = 0;
            long thredshold = 0;
            while (true) {
                long preThreshold = thredshold;
                thredshold += (long)Math.pow(26,lev);
                if (nl > thredshold) {
                    lev++;
                } else if (nl < thredshold) {
                    lev--; thredshold = preThreshold; break;
                } else { // nl == thredshold
                    break;
                }
            }
            // fill each level
            int remainder = n - (int)thredshold; // here thredshold must < Integer.MAX_VALUE;
            for (int i = lev; i >=0; i--) {
                int denominator = (int)Math.pow(26,i);
                char letter = (char)(remainder / denominator + 1 + 64); // 'A'=65
                remainder = remainder % denominator;
                res.append(letter);
            }
            return res.toString();
        }
    }
    public class SolutionV2 {
        public String convertToTitle(int n) {
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                char letter = (char)((n - 1) % 26 + 'A');
                sb.insert(0,letter);
                n = (n - 1) / 26;
            }
            return sb.toString();
        }
    }
    public class Solution {
        public String convertToTitle(int n) {
            return (n == 0)? "" : convertToTitle((n-1)/26) + (char)((n-1)%26 + 'A');
        }
    }
    private static ExcelSheetColumnTitle test = new ExcelSheetColumnTitle();
    private static Solution solution = test.new Solution();
    private static void callConvertToTitle(int n) {
        System.out.println(n + "    >>>     " + solution.convertToTitle(n));
    }
    private static void test() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            callConvertToTitle(r.nextInt(100)+1);
        }
    }
    public static void main(String[] args) {
        test();
    }
}
