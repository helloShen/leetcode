/**
 * Leetcode - Algorithm - UncommentWordsFromTwoSentences
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class UncommentWordsFromTwoSentences implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private UncommentWordsFromTwoSentences() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String[] uncommonFromSentences(String A, String B); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public String[] uncommonFromSentences(String A, String B) {
            List<String> listA = new LinkedList<>(Arrays.asList(A.split(" ")));
            List<String> listB = new LinkedList<>(Arrays.asList(B.split(" ")));
            Set<String> duplicate = new HashSet<>();
            Set<String> set = new HashSet<>();
            for (String word : listA) {
                if (!set.add(word)) {
                    duplicate.add(word);
                }
            }
            for (String word : listB) {
                if (!set.add(word)) {
                    duplicate.add(word);
                }
            }
            set.removeAll(duplicate);
            return set.toArray(new String[set.size()]);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public String[] uncommonFromSentences(String A, String B) {
            String[] wordsA = A.split(" ");
            String[] wordsB = B.split(" ");
            Set<String> duplicate = new HashSet<>();
            Set<String> set = new HashSet<>();
            for (String word : wordsA) {
                if (!set.add(word)) {
                    duplicate.add(word);
                }
            }
            for (String word : wordsB) {
                if (!set.add(word)) {
                    duplicate.add(word);
                }
            }
            set.removeAll(duplicate);
            return set.toArray(new String[set.size()]);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public String[] uncommonFromSentences(String A, String B) {
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
        private UncommentWordsFromTwoSentences problem = new UncommentWordsFromTwoSentences();
        private Solution solution = null;

        // call method in solution
        private void call(String A, String B) {
            System.out.println(Arrays.toString(solution.uncommonFromSentences(A,B)));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String A1 = "this apple is sweet";
            String B1 = "this apple is sour";
            String A2 = "apple apple";
            String B2 = "banana";
            String A3 = "s z z z s";
            String B3 = "s z ejt";

            /** involk call() method HERE */
            call(A1,B1);
            call(A2,B2);
            call(A3,B3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
    }
}
