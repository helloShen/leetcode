/**
 * Leetcode - Algorithm - CountBinarySubstrings
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class CountBinarySubstrings implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private CountBinarySubstrings() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int countBinarySubstrings(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int countBinarySubstrings(String s) {
            int cur = 0, count = 0;
            char pre = 'a', curr = 'a';
            while (cur < s.length()) {
                curr = s.charAt(cur);
                if (pre != 'a' && pre != curr) {
                    int pl = cur-1, pr = cur;
                    char left = s.charAt(pl), right = s.charAt(pr);
                    while (pl >= 0 && pr < s.length() && (s.charAt(pl) == left) && (s.charAt(pr) == right)) {
                        count++;
                        pl--; pr++;
                    }
                    cur = pr;
                } else {
                    cur++;
                }
                pre = curr;
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int countBinarySubstrings(String s) {
            if (s == null || s.length() < 2) { return 0; }
            int len = s.length();
            int count = 0;
            int pre = (int)s.charAt(0), curr = 0;
            for (int i = 1; i < len;) {
                curr = (int)s.charAt(i);
                if (curr != pre) {
                    int pl = i-1, pr = i;
                    int left = (int)s.charAt(pl), right = (int)s.charAt(pr);
                    while ((pl >= 0) && (pr < len) && ((int)s.charAt(pl) == left) && ((int)s.charAt(pr) == right)) {
                        count++;
                        pl--; pr++;
                    }
                    i = pr;
                } else {
                    i++;
                }
                pre = curr;
            }
            return count;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int countBinarySubstrings(String s) {
            return 3;
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
        private CountBinarySubstrings problem = new CountBinarySubstrings();
        private Solution solution = null;

        // call method in solution
        private void call(String s, int ans) {
            System.out.println("Code: " + s);
            System.out.println(solution.countBinarySubstrings(s) + " -> \t[answer: " + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "00110011";
            int ans1 = 6;
            String s2 = "10101";
            int ans2 = 4;

            /** involk call() method HERE */
            call(s1,ans1);
            call(s2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
