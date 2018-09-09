/**
 * Leetcode - Algorithm - DailyTemperatures
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DailyTemperatures implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DailyTemperatures() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int[] dailyTemperatures(int[] temperatures); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] memo = new int[101];

        public int[] dailyTemperatures(int[] temperatures) {
            Arrays.fill(memo,0);
            for (int i = temperatures.length-1; i >= 0; i--) {
                int temperature = temperatures[i];
                int wait = Integer.MAX_VALUE;
                for (int j = temperature + 1; j < 101; j++) {
                    int future = memo[j];
                    if (future > i) {
                        wait = Math.min(wait,future - i);
                    }
                }
                temperatures[i] = (wait == Integer.MAX_VALUE)? 0 : wait;
                memo[temperature] = i;
            }
            return temperatures;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int[] dailyTemperatures(int[] temperatures) {
            int[] stack = new int[temperatures.length];
            int[] res = new int[temperatures.length];
            int top = -1;
            for (int i = 0; i < temperatures.length; i++) {
                while (top > -1 && temperatures[i] > temperatures[stack[top]]) {
                    int index = stack[top--];
                    res[index] = i - index;
                }
                stack[++top] = i;
            }
            return res;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int[] dailyTemperatures(int[] temperatures) {
            return new int[]{3};
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
        private DailyTemperatures problem = new DailyTemperatures();
        private Solution solution = null;

        private void call(int[] temperatures, int[] ans) {
            System.out.println("Temperatures: " + Arrays.toString(temperatures));
            System.out.println("Wait Days: " + Arrays.toString(solution.dailyTemperatures(temperatures)));
            System.out.println("Answer should be: " + Arrays.toString(ans) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] temperatures1 = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
            int[] ans1 = new int[]{1, 1, 4, 2, 1, 1, 0, 0};

            /** involk call() method HERE */
            call(temperatures1,ans1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
