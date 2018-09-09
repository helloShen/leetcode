/**
 * Leetcode - Algorithm - FirstUniqueCharacterInAString
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FirstUniqueCharacterInAString implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FirstUniqueCharacterInAString() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int firstUniqChar(String s); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int firstUniqChar(String s) {
            Map<Character,Integer> dic = loadDictionary(s);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (dic.get(c) == 1) { return i; }
            }
            return -1;
        }
        private Map<Character,Integer> loadDictionary(String s) {
            Map<Character,Integer> map = new HashMap<>();
            Character c = null;
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                map.put(c,(map.containsKey(c))? map.get(c)+1 : 1);
            }
            return map;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int firstUniqChar(String s) {
            int len = s.length();
            int[] charOffset = new int[len];
            int[] charFreq = new int[26];
            for (int i = 0; i < len; i++) {
                int offset = s.charAt(i) - 'a';
                charOffset[i] = offset;
                charFreq[offset]++;
            }
            int min = 26;
            for (int i = 0; i < len; i++) {
                if (charFreq[charOffset[i]] == 1) { return i; }
            }
            return -1;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int firstUniqChar(String s) {
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
        private FirstUniqueCharacterInAString problem = new FirstUniqueCharacterInAString();
        private Solution solution = null;

        // call method in solution
        private void call(String s) {
            System.out.println("\"" + s + "\"" + " -> " + solution.firstUniqChar(s));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String s1 = "";
            String s2 = "leetcode";
            String s3 = "loveleetcode";
            /** involk call() method HERE */
            call(s1);
            call(s2);
            call(s3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        // test.test(3);
    }
}
