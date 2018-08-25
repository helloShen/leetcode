/**
 * Leetcode - Algorithm - LongestPalindromicSubsequence
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LongestPalindromicSubsequence implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LongestPalindromicSubsequence() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int longestPalindromeSubseq(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /**
     * 递归思路
     * 没用带备忘录的动态规划
     */
    private class Solution1 extends Solution {
        { super.id = 1; }

        private String local = null;

        public int longestPalindromeSubseq(String s) {
            local = s;
            return dp(0, local.length()-1);
        }
        private int dp(int begin, int end) {
            int len = end - begin;
            if (len <= 0) { return len+1; }
            if (local.charAt(begin) == local.charAt(end)) {
                return dp(begin+1, end-1) + 2;
            } else {
                return Math.max(dp(begin+1, end), dp(begin, end-1));
            }
        }
    }

    /**
     * 带备忘录的动态规划
     */
    private class Solution2 extends Solution {
        { super.id = 2; }

        private String local = null;
        private int[][] memo = null;

        public int longestPalindromeSubseq(String s) {
            local = s;
            int len = s.length();
            memo = new int[len][len];
            return dp(0, local.length()-1);
        }
        private int dp(int begin, int end) {
            int len = end - begin;
            if (len <= 0) { return len+1; }
            if (memo[begin][end] > 0) { return memo[begin][end]; }
            if (local.charAt(begin) == local.charAt(end)) {
                return dp(begin+1, end-1) + 2;
            } else {
                return Math.max(dp(begin+1, end), dp(begin, end-1));
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int longestPalindromeSubseq(String s) {
            int len = s.length();
            int[][] memo = new int[len][len];
            for (int i = 0; i < len; i++) {
                memo[i][i] = 1;
                char charRight = s.charAt(i);
                for (int j = i-1; j >= 0; j--) {
                    if (charRight == s.charAt(j)) {
                        memo[j][i] = memo[j+1][i-1] + 2;
                    } else {
                        memo[j][i] = Math.max(memo[j+1][i], memo[j][i-1]);
                    }
                }
            }
            return memo[0][len-1];
        }
    }
    // you can expand more solutions HERE if you want...


    /**
     * register a solution in the solution registry
     * return false if this type of solution already exist in the registry.
     */
    private boolean register(Solution s) {
        return (solutions.put(s.id,s) == null)? true : false;
    }
    /**
     * chose one of the solution to test
     * return null if solution id does not exist
     */
    private Solution solution(int id) {
        return solutions.get(id);
    }

    private static class Test {
        private LongestPalindromicSubsequence problem = new LongestPalindromicSubsequence();
        private Solution solution = null;

        // call method in solution
        private void call(String s, int ans) {
            System.out.println("Original String: " + s);
            System.out.println("Longest Palindromic Subsequence = " + solution.longestPalindromeSubseq(s));
            System.out.println("Answer should be: [" + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "bbbab";
            int ans1 = 4;
            String s2 = "cbbd";
            int ans2 = 2;

            /** involk call() method HERE */
            call(s1,ans1);
            call(s2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
