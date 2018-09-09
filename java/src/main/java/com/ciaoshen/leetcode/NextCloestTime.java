/**
 * Leetcode - Algorithm - NextCloestTime
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class NextCloestTime implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private NextCloestTime() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String nextClosestTime(String time); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public String nextClosestTime(String time) {
            char[] charTime = time.toCharArray();   // [1, 9, :, 3, 4]
            // System.out.println("chaTime = " + Arrays.toString(charTime));
            char[] nums = Arrays.copyOf(charTime,charTime.length);
            Arrays.sort(nums);                      // [1, 3, 4, 9, :]
            // System.out.println("nums = " + Arrays.toString(nums));
            // 找到需要进位的那一位
            int i = 0;
            outFor:
            for (i = 4; i >= 0; i--) {
                if (i == 2) { continue; } // skip ":" in the middle
                char c = charTime[i];
                // System.out.println("Char = " + c);
                int next = 0;
                while (next < 4 && nums[next] <= c) { next++; }
                char max = '9';
                switch (i) {
                    case 3: max = '5'; break;
                    case 1: if (charTime[0] == '2') { max = '4'; } break;
                    case 0: max = '2'; break;
                }
                if (next == 4 || nums[next] > max) { continue outFor; }
                // System.out.println(charTime[i] + " -> " + nums[next]);
                charTime[i] = nums[next];
                break;
            }
            // 用最小数填满进位的后续部分
            // 如果没有需要进位的，就填满所有数字
            for (int j = i + 1; j <= 4; j++) {
                if (j == 2) { continue; }
                charTime[j] = nums[0]; // 至少会有一个[0,1,2]
            }
            return new String(charTime);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public String nextClosestTime(String time) {
            return "";
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public String nextClosestTime(String time) {
            return "";
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
        private NextCloestTime problem = new NextCloestTime();
        private Solution solution = null;

        // call method in solution
        private void call(String time) {
            System.out.println(time + "\t -> \t" + solution.nextClosestTime(time));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            String time1 = "19:34";
            String time2 = "23:59";

            /** involk call() method HERE */
            call(time1);
            call(time2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
