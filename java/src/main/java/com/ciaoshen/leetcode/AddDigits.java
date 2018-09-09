/**
 * Leetcode - Algorithm - AddDigits
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class AddDigits implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private AddDigits() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int addDigits(int num);
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int addDigits(int num) {
            while (num >= 10) {
                int sum = 0;
                while (num > 0) {
                    sum += (num % 10);
                    num /= 10;
                }
                num = sum;
            }
            return num;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int addDigits(int num) {
            int sum = 0;
            while (num > 0) {
                sum += (num % 10);
                num /= 10;
            }
            return (sum >= 10)? addDigits(sum) : sum;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int addDigits(int num) {
            if (num == 0) { return num; }
            int res = num % 9;
            return (res == 0)? 9 : res;
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
        private AddDigits problem = new AddDigits();
        private Solution solution = null;

        // call method in solution
        private void call(int num, String answer) {
            System.out.println(num + " -> " + solution.addDigits(num) + "       >>> [answer:" + answer + "]");
        }

        // call method in solution
        private void iteration(int num) {
            solution = problem.solution(2);
            for (int i = 0; i < 100; i++) {
                System.out.println(i + " -> " + solution.addDigits(i));
            }
        }

        // public API of Test interface
        public void test(int i) {
            solution = problem.solution(i);
            if (solution == null) { System.out.println("Sorry, [id:" + i + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            int digit1 = 38;
            // int digit2 = ;
            // int digit3 = ;
            call(digit1,"2");
            // call(digit2,"");
            // call(digit3,"");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        test.iteration(100);
    }
}
