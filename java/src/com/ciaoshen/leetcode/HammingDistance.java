/**
 * Leetcode - Algorithm - HammingDistance
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class HammingDistance implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private HammingDistance() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int hammingDistance(int x, int y); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int hammingDistance(int x, int y) {
            int mix = x ^ y;
            int count = 0;
            while (mix != 0) {
                mix = mix & (mix - 1);
                ++count;
            }
            return count;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int hammingDistance(int x, int y) {
            return (Integer.bitCount(x ^ y));
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int hammingDistance(int x, int y) {
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
        private HammingDistance problem = new HammingDistance();
        private Solution solution = null;

        // call method in solution
        private void call(int x, int y, String answer) {
            System.out.println("Hamming Distance of " + x + " and " + y + " is: " + solution.hammingDistance(x,y) + "       [answer:" + answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.println(i + " = " + getBits(i));
                    System.out.println(j + " = " + getBits(j));
                    call(i,j,"?");
                    System.out.println("\n");
                }
            }
        }
        /* show bits as string */
        private String getBits(int n) {
            char[] bits = new char[32];
            Arrays.fill(bits,'0');
            int cur = 31;
            while (n != 0) {
                bits[cur--] = (char)((n & 1) + '0');
                n >>>= 1;
            }
            return new String(bits);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
