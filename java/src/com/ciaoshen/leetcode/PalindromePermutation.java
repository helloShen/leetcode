/**
 * Leetcode - Algorithm - PalindromePermutation
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PalindromePermutation implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PalindromePermutation() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean canPermutePalindrome(String s);
        protected void sometest() { return; }
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public boolean canPermutePalindrome(String s) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!set.add(c)) { set.remove(c); }
            }
            return set.size() < 2;
        }
        protected void sometest() {
            for (char i = 'a'; i <= 'z'; i++) {
                for (char j = 'a'; j <= 'z'; j++) {
                    System.out.println("\"" + i + "\" XOR \"" + j + "\" = " + (i ^ j));
                }
            }
            for (char i = 'A'; i <= 'Z'; i++) {
                for (char j = 'A'; j <= 'Z'; j++) {
                    System.out.println("\"" + i + "\" XOR \"" + j + "\" = " + (i ^ j));
                }
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean canPermutePalindrome(String s) {
            return false;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean canPermutePalindrome(String s) {
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
        private PalindromePermutation problem = new PalindromePermutation();
        private Solution solution = null;

        // call method in solution
        private void call(String s, String answer) {
            System.out.println(s + "? " + solution.canPermutePalindrome(s) + "          [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            // some small tests
            solution.sometest();

            // call main method
            call("code","false");
            call("aab","true");
            call("carerac","true");
            call("abdg","false");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
