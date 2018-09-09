/**
 * Leetcode - Algorithm - PalindromicSubstrings
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PalindromicSubstrings implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PalindromicSubstrings() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int countSubstrings(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int countSubstrings(String s) {
            return dp(s,0);
        }
        private int dp(String s, int start) {
            // System.out.println("Start at " + start);
            if (start == (s.length()-1)) { return 1; }
            int sub = dp(s,start+1);
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s,start,i)) {
                    sub++;
                }
            }
            return sub;
        }
        private boolean isPalindrome(String s, int lo, int hi) {
            while (lo < hi) {
                if (s.charAt(lo++) != s.charAt(hi--)) { return false; }
            }
            return true;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private char[] str = new char[0];

        public int countSubstrings(String s) {
            init(s);
            return dp(0);
        }
        private void init(String s) {
            str = s.toCharArray();
        }
        private int dp(int start) {
            if (start == str.length - 1) { return 1; }
            int sub = dp(start+1);
            for (int i = start; i < str.length; i++) {
                if (isPalindrome(start,i)) {
                    sub++;
                }
            }
            return sub;
        }
        private boolean isPalindrome(int lo, int hi) {
            while (lo < hi) {
                if (str[lo++] != str[hi--]) {
                    return false;
                }
            }
            return true;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        private char[] str = new char[0];
        private int count;

        public int countSubstrings(String s) {
            init(s);
            for (int i = 0; i < str.length; i++) {
                extendPalindrome(i,i);      // odd
                extendPalindrome(i,i+1);    // even
            }
            return count;
        }
        private void init(String s) {
            str = s.toCharArray();
            count = 0;
        }
        private void extendPalindrome(int lo, int hi) {
            while (lo >= 0 && hi < str.length && (str[lo--] == str[hi++])) {
                count++;
            }
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
        private PalindromicSubstrings problem = new PalindromicSubstrings();
        private Solution solution = null;

        // call method in solution
        private void call(String s, int ans) {
            System.out.println(s);
            System.out.println(solution.countSubstrings(s) + "\t [answer=" + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "abc";
            int ans1 = 3;
            String s2 = "aaa";
            int ans2 = 6;
            String s3 = "longtimenosee";
            int ans3 = 14;

            /** involk call() method HERE */
            call(s1,ans1);
            call(s2,ans2);
            call(s3,ans3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
