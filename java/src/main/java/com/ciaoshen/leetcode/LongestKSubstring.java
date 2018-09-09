/**
 * Leetcode - Algorithm - LongestKSubstring
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LongestKSubstring implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LongestKSubstring() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int longestSubstring(String s, int k);   // 主方法接口
        protected void sometest() { return; }           // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        private int[] letters = new int[0];
        public int longestSubstring(String s, int k) {
            int max = 0, len = s.length();
            for (int i = 0; i <= len - k; i++) {
                letters = new int[26];
                for (int j = i; j < len; j++) {
                    letters[s.charAt(j) - 'a']++;
                    if (checkMin() >= k) {
                        max = Math.max(max,j - i + 1);
                    }
                }
            }
            return max;
        }
        private int checkMin() {
            Integer min = null;
            for (int i = 0; i < 26; i++) {
                if (letters[i] > 0) {
                    min = (min == null)? letters[i] : Math.min(min,letters[i]);
                }
            }
            return (min == null)? 0 : min;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        public int longestSubstring(String s, int k) {
            int[] letters = new int[26];
            int max = 0, len = s.length();
            for (int i = 0; i < len; i++) {
                letters[s.charAt(i) - 'a']++;
                if (checkMin(letters) >= k) {
                    max = Math.max(max,i + 1);
                }
            }
            for (int i = 0; i < len - k; i++) {
                letters[s.charAt(i) - 'a']--;
                int[] copy = Arrays.copyOf(letters,26);
                if (checkMin(copy) >= k) {
                    max = Math.max(max,len - i - 1);
                }
                for (int j = len - 1; j > i; j--) {
                    copy[s.charAt(j) - 'a']--;
                    if (checkMin(copy) >= k) {
                        max = Math.max(max,j - i - 1);
                    }
                }
            }
            return max;
        }
        private int checkMin(int[] letters) {
            Integer min = null;
            for (int i = 0; i < 26; i++) {
                if (letters[i] > 0) {
                    min = (min == null)? letters[i] : Math.min(min,letters[i]);
                }
            }
            return (min == null)? 0 : min;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        private int[] letters = new int[26];
        private char[] str = new char[0];
        private int limit = 0;
        public int longestSubstring(String s, int k) {
            str = s.toCharArray();
            limit = k;
            return recursion(0,s.length());
        }
        /** (start,end] */
        private int recursion(int start, int end) {
            if (end - start < limit) { return 0; }
            Arrays.fill(letters,0);
            for (int i = start; i < end; i++) {
                letters[str[i]-'a']++;
            }
            for (int i = start; i < end; i++) {
                int freq = letters[str[i]-'a'];
                if (freq > 0 && freq < limit) {
                    return Math.max(recursion(start,i), recursion(i+1,end));
                }
            }
            return end - start;
        }
    }
    private class Solution4 extends Solution {
        { super.id = 4; }
        private char[] str = new char[0];
        private int limit = 0;
        public int longestSubstring(String s, int k) {
            str = s.toCharArray();
            limit = k;
            return recursion(0,s.length());
        }
        /** (start,end] */
        private int recursion(int start, int end) {
            if (end - start < limit) { return 0; }
            int[] letters = new int[26];
            for (int i = start; i < end; i++) {
                letters[str[i] - 'a']++;
            }
            int max = 0;
            int lo = start, hi = start;
            while (hi < end) {
                int freq = letters[str[hi]-'a'];
                if (freq > 0 && freq < limit) {
                    max = Math.max(max,recursion(lo,hi));
                    lo = hi + 1;
                }
                ++hi;
            }
            if (lo < end && lo != start) {
                max = Math.max(max,recursion(lo,end));
            } else if (lo < end){
                max = end - start;
            }
            return max;
        }
    }

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
        private LongestKSubstring problem = new LongestKSubstring();
        private Solution solution = null;

        // call method in solution
        private void call(String s, int k) {
            System.out.println(s + "    -->     " + solution.longestSubstring(s,k));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "aaabb";
            int k1 = 3;
            String s2 = "ababbc";
            int k2 = 2;
            /** involk call() method HERE */
            call(s1,k1);
            call(s2,k2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.test(4);
    }
}
