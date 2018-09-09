/**
 * Leetcode - Algorithm - UniqueWordAbbreviation
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class UniqueWordAbbreviation implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private UniqueWordAbbreviation() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void prePharse(String[] dictionary); // 主方法接口
        abstract public boolean isUnique(String word);       // 主方法接口
        abstract public void show();                         // 打印Map看看
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private Map<String,Set<String>> abbrs = new HashMap<>();

        public void prePharse(String[] dictionary) {
            abbrs.clear();
            for (String s : dictionary) {
                String abbr = abbr(s);
                System.out.println("Abbr of [" + s + "] is: " + abbr + "");
                if (abbrs.containsKey(abbr)) {
                    abbrs.get(abbr).add(s);
                } else {
                    abbrs.put(abbr,new HashSet<>(Arrays.asList(new String[]{s})));
                }
            }
        }
        public boolean isUnique(String word) {
            String abbr = abbr(word);
            if (!abbrs.containsKey(abbr)) { return true; }      // 没有这个缩写，肯定是唯一的
            Set<String> words = abbrs.get(abbr);
            if (words.size() > 1) { return false; }             // 有多个词是这个缩写，不是唯一
            return words.contains(word);                        // 最后就看唯一是这个缩写的单词，是不是我们要找的
        }
        private String abbr(String word) {
            int len = word.length();
            return (len < 3)? word : (word.substring(0,1) +
                                     String.valueOf(len-2) +
                                     word.substring(len-1,len));
        }
        public void show() { }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private Map<String,String> abbrs = new HashMap<>();

        public void prePharse(String[] dictionary) {
            abbrs.clear();
            for (String s : dictionary) {
                String abbr = abbr(s);
                if (abbrs.containsKey(abbr)) {
                    String word = abbrs.get(abbr);
                    if (word != null && !word.equals(s)) {      // 有过相同缩写的其他单词，标为null
                        abbrs.put(abbr,null);
                    }
                } else {
                    abbrs.put(abbr,s);
                }
            }
        }

        public boolean isUnique(String word) {
            String abbr = abbr(word);
            if (!abbrs.containsKey(abbr)) { return true; }
            String s = abbrs.get(abbr);
            return (s != null && s.equals(word));
        }

        private String abbr(String word) {
            int len = word.length();
            return (len < 3)? word : (word.substring(0,1) +
                                     String.valueOf(len-2) +
                                     word.substring(len-1,len));
        }
        public void show() {
            System.out.println(abbrs);
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void prePharse(String[] dictionary) { }

        public boolean isUnique(String word) {
            return false;
        }

        public void show() { }
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
        private UniqueWordAbbreviation problem = new UniqueWordAbbreviation();
        private Solution solution = null;

        private void call(String[] dictionary, String[] testCases) {
            solution.prePharse(dictionary);
            solution.show();
            for (String s : testCases) {
                System.out.println("Word [" + s + "] --> " + ((solution.isUnique(s))? "Yes" : "No"));
            }
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String[] dictionary1 = new String[]{ "deer", "door", "cake", "card" };
            String[] testCases1 = new String[]{ "deer", "cart", "cane", "make" };


            String[] dictionary2 = new String[]{ "hello" };
            String[] testCases2 = new String[]{ "hello" };

            String[] dictionary3 = new String[]{ "a", "a" };
            String[] testCases3 = new String[]{ "a" };

            /** involk call() method HERE */
            call(dictionary1, testCases1);
            call(dictionary2, testCases2);
            call(dictionary3, testCases3);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
