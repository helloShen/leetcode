/**
 * Leetcode - Algorithm - TaskSchedule
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class TaskSchedule implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private TaskSchedule() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int leastInterval(char[] tasks, int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int leastInterval(char[] tasks, int n) {
            int[] freq = new int[26];
            for (int i = 0; i < tasks.length; i++) {
                freq[tasks[i] - 'A']++;
            }
            Arrays.sort(freq);
            int cur = freq.length - 1;
            int maxSize = freq[cur], num = 0;
            while (cur >= 0 && freq[cur] == maxSize) {
                num++; cur--;
            }
            int standard = (maxSize - 1) * Math.max(num,n + 1) + num;
            return (tasks.length > standard)? tasks.length : standard;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int leastInterval(char[] tasks, int n) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int leastInterval(char[] tasks, int n) {
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
        private TaskSchedule problem = new TaskSchedule();
        private Solution solution = null;

        private void call(char[] tasks, int n) {
            System.out.println(Arrays.toString(tasks));
            System.out.println(solution.leastInterval(tasks,n) + "\n\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            char[] tasks1 = new char[]{'A','A','A','B','B','B'};
            int n1 = 2;

            /** involk call() method HERE */
            call(tasks1,n1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
