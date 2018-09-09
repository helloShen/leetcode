/**
 * Leetcode - Algorithm - WordPattern
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class WordPattern implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private WordPattern() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean wordPattern(String pattern, String str); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public boolean wordPattern(String pattern, String str) {
            String[] memo = new String[26];
            String[] words = str.split(" ");
            if (pattern.length() != words.length) { return false; }
            for (int i = 0; i < words.length; i++) {
                int offset = pattern.charAt(i) - 'a';
                String history = memo[offset];
                if (history == null) {
                    for (int j = 0; j < 26; j++) {
                        String pre = memo[j];
                        if (pre != null && pre.equals(words[i])) { return false; }
                    }
                    memo[offset] = words[i];
                    System.out.println("Add new " + words[i]);
                } else if (!history.equals(words[i])) {
                    System.out.println("Word " + words[i] + " doesn't match! \t" + history + " in memo!");
                    return false;
                } else {
                    System.out.println("Words " + words[i] + " already exist!");
                }
            }
            return true;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public boolean wordPattern(String pattern, String str) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean wordPattern(String pattern, String str) {
            return false;
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
        private WordPattern problem = new WordPattern();
        private Solution solution = null;

        // call method in solution
        private void call(String pattern, String str) {
            System.out.println("Patter: " + pattern);
            System.out.println("String: " + str);
            System.out.println("Result = " + solution.wordPattern(pattern,str) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String pattern1 = "abba"; String str1 = "dog cat cat dog";
            String pattern2 = "abba"; String str2 = "dog cat cat fish";
            String pattern3 = "aaaa"; String str3 = "dog cat cat dog";
            String pattern4 = "abba"; String str4 = "dog dog dog dog";

            /** involk call() method HERE */
            call(pattern1,str1);
            call(pattern2,str2);
            call(pattern3,str3);
            call(pattern4,str4);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
