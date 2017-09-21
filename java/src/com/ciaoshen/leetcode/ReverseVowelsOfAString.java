/**
 * Leetcode - Algorithm - ReverseVowelsOfAString
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ReverseVowelsOfAString implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ReverseVowelsOfAString() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String reverseVowels(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public String reverseVowels(String s) {
            int[] stack = new int[s.length()];
            int cursor = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (isVowel(chars[i])) {
                    stack[cursor++] = i;
                }
            }
            int lo = 0, hi = --cursor;
            while (hi > lo) {
                exch(chars,stack[lo++],stack[hi--]);
            }
            return new String(chars);
        }
        private boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c =='U';
        }
        private void exch(char[] chars, int a, int b) {
            char temp = chars[a];
            chars[a] = chars[b];
            chars[b] = temp;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        private final String vowels = "aeiouAEIOU";
        private char[] chars = new char[0];
        public String reverseVowels(String s) {
            chars = s.toCharArray();
            int[] stack = new int[chars.length];
            int cursor = 0;
            for (int i = 0; i < chars.length; i++) {
                if (vowels.indexOf(chars[i]) != -1) {
                    stack[cursor++] = i;
                }
            }
            int lo = 0, hi = --cursor;
            while (hi > lo) {
                exch(stack[lo++],stack[hi--]);
            }
            return new String(chars);
        }
        private void exch(int a, int b) {
            char temp = chars[a];
            chars[a] = chars[b];
            chars[b] = temp;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        private final String VOWELS = "aeiouAEIOU";
        private char[] chars = new char[0];
        public String reverseVowels(String s) {
            chars = s.toCharArray();
            int lo = 0, hi = chars.length-1;
            while (lo < hi) {
                if (VOWELS.indexOf(chars[lo]) == -1) {
                    ++lo;
                } else if (VOWELS.indexOf(chars[hi]) == -1) {
                    --hi;
                } else {
                    exch(lo++,hi--);
                }
            }
            return new String(chars);
        }
        private void exch(int a, int b) {
            char temp = chars[a];
            chars[a] = chars[b];
            chars[b] = temp;
        }
    }
    // you can expand more solutions HERE if you want...
    private class Solution4 extends Solution {
        { super.id = 4; }
        // implement your solution's method HERE...
        private final char[] VOWELS = new char[]{'A','E','I','O','U','a','e','i','o','u'};
        private char[] chars = new char[0];
        public String reverseVowels(String s) {
            chars = s.toCharArray();
            int lo = 0, hi = chars.length-1;
            while (lo < hi) {
                int il = Arrays.binarySearch(VOWELS,chars[lo]);
                int ih = Arrays.binarySearch(VOWELS,chars[hi]);
                if (il < 0 || il > 9 || VOWELS[il] != chars[lo]) {
                    ++lo;
                } else if (ih < 0 || ih > 9 || VOWELS[ih] != chars[hi]) {
                    --hi;
                } else {
                    exch(lo++,hi--);
                }
            }
            return new String(chars);
        }
        private void exch(int a, int b) {
            char temp = chars[a];
            chars[a] = chars[b];
            chars[b] = temp;
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
        private ReverseVowelsOfAString problem = new ReverseVowelsOfAString();
        private Solution solution = null;

        // call method in solution
        private void call(String s, String answer) {
            System.out.println("\"" + s + "\" -> \"" + solution.reverseVowels(s) + "\"\t[answer:\"" + answer + "\"]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "";
            String answer1 = "";

            String s2 = "hello";
            String answer2 = "holle";

            String s3 = "leetcode";
            String answer3 = "leotcede";

            String s4 = "aA";
            String answer4 = "Aa";

            /** involk call() method HERE */
            call(s1,answer1);
            call(s2,answer2);
            call(s3,answer3);
            call(s4,answer4);
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
