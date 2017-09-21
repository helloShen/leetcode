/**
 * Leetcode - Algorithm - FizzBuzz
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FizzBuzz implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FizzBuzz() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }

    private abstract class Solution {
        private int id = 0;
        abstract public List<String> fizzBuzz(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 用 % 求余数. 用StringBuilder构建字符串 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public List<String> fizzBuzz(int n) {
            List<String> res = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                StringBuilder sb = new StringBuilder();
                if (i % 3 == 0) { sb.append("Fizz"); }
                if (i % 5 == 0) { sb.append("Buzz"); }
                if (sb.length() == 0) { sb.append(i); }
                res.add(sb.toString());
            }
            return res;
        }
    }
    /* 用 % 求余数。 预存唯一的三个字符串。 */
    private class Solution2 extends Solution {
        { super.id = 2; }

        private final String FIZZ = "Fizz";
        private final String BUZZ = "Buzz";
        private final String FIZZ_BUZZ = "FizzBuzz";

        public List<String> fizzBuzz(int n) {
            List<String> res = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (i % 15 == 0) {
                    res.add(FIZZ_BUZZ);
                } else if (i % 3 == 0) {
                    res.add(FIZZ);
                } else if (i % 5 == 0) {
                    res.add(BUZZ);
                } else {
                    res.add("" + i);
                }
            }
            return res;
        }
    }
    /* 用两个计步器替代 % 求余。 */
    private class Solution3 extends Solution {
        { super.id = 3; }

        private final String FIZZ = "Fizz";
        private final String BUZZ = "Buzz";
        private final String FIZZ_BUZZ = "FizzBuzz";

        public List<String> fizzBuzz(int n) {
            List<String> res = new ArrayList<>();
            int f = 0, b = 0;
            for (int i = 1; i <= n; i++) {
                ++f; ++b;
                if (f == 3 && b == 5) {
                    res.add(FIZZ_BUZZ);
                    f = 0; b = 0;
                } else if (f == 3) {
                    res.add(FIZZ);
                    f = 0;
                } else if (b == 5) {
                    res.add(BUZZ);
                    b = 0;
                } else {
                    res.add("" + i);
                }
            }
            return res;
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
        private FizzBuzz problem = new FizzBuzz();
        private Solution solution = null;

        private void call(int n) {
            System.out.println(n);
            System.out.println(solution.fizzBuzz(n));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            call(15);
            call(100);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
