/**
 * Leetcode - Algorithm - RansomNote
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class RansomNote implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private RansomNote() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean canConstruct(String ransomNote, String magazine); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] freq = countFreq(magazine);
            for (int i = 0; i < ransomNote.length(); i++) {
                int offset = ransomNote.charAt(i) - 'a';
                if (freq[offset] > 0) {
                    --freq[offset];
                } else {
                    return false;
                }
            }
            return true;
        }
        private int[] countFreq(String magazine) {
            int[] freq = new int[26];
            for (int i = 0; i < magazine.length(); i++) {
                ++freq[magazine.charAt(i)-'a'];
            }
            return freq;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public boolean canConstruct(String ransomNote, String magazine) {
            List<Character> mgz = new LinkedList<>();
            for (int i = 0; i < magazine.length(); i++) {
                mgz.add(magazine.charAt(i));
            }
            for (int i = 0; i < ransomNote.length(); i++) {
                if (!mgz.remove((Character)ransomNote.charAt(i))) { return false; }
            }
            return true;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public boolean canConstruct(String ransomNote, String magazine) {
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
        private RansomNote problem = new RansomNote();
        private Solution solution = null;

        // call method in solution
        private void call(String ransomNote, String magazine) {
            System.out.println("Ransom Note: " + ransomNote);
            System.out.println("Magazine: " + magazine);
            System.out.println("Result: " + solution.canConstruct(ransomNote,magazine) + "\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            String ransomNote1 = "a";
            String magazine1 = "b";

            String ransomNote2 = "ab";
            String magazine2 = "aa";

            String ransomNote3 = "aa";
            String magazine3 = "aab";

            /** involk call() method HERE */
            call(ransomNote1,magazine1);
            call(ransomNote2,magazine2);
            call(ransomNote3,magazine3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
