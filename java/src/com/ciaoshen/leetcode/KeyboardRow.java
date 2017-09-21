/**
 * Leetcode - Algorithm - KeyboardRow
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class KeyboardRow implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private KeyboardRow() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String[] findWords(String[] words); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        private final int[] LETTER = new int[]{2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};
        public String[] findWords(String[] words) {
            int j = 0;
            for (int i = 0; i < words.length; i++) {
                if (isOneRow(words[i])) { exch(words,i,j++); }
            }
            return Arrays.copyOfRange(words,0,j);
        }
        private boolean isOneRow(String s) {
            int len = s.length();
            if (len == 0) { return true; }
            int line = LETTER[getOffset(s.charAt(0))];
            for (int i = 1; i < len; i++) {
                if (LETTER[getOffset(s.charAt(i))] != line) { return false; }
            }
            return true;
        }
        private void exch(String[] words, int x, int y) {
            String temp = words[x];
            words[x] = words[y];
            words[y] = temp;
        }
        private int getOffset(char c) {
            if (c >= 'a' && c <= 'z') {
                return c - 'a';
            } else if (c >= 'A' && c <= 'Z') {
                return c - 'A';
            } else {
                return -1;
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public String[] findWords(String[] words) {
            return null;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public String[] findWords(String[] words) {
            return null;
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
        private KeyboardRow problem = new KeyboardRow();
        private Solution solution = null;

        // call method in solution
        private void call(String[] words) {
            System.out.println("Original Words: " + Arrays.toString(words));
            System.out.println("After: " + Arrays.toString(solution.findWords(words)));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String[] words1 = new String[]{"Hello", "Alaska", "Dad", "Peace"};
            String[] words2 = new String[]{"Hello", "Peace"};
            /** involk call() method HERE */
            call(words1);
            call(words2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
