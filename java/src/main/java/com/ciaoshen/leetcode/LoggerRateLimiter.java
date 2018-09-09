/**
 * Leetcode - Algorithm - LoggerRateLimiter
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LoggerRateLimiter implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LoggerRateLimiter() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract void init();
        abstract public boolean shouldPrintMessage(int timestamp, String message); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public void init() {
            history = new HashMap<String,Integer>();
        }
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!history.containsKey(message)) {
                history.put(message,timestamp);
                return true;
            } else if (timestamp - history.get(message) >= 10) {
                    history.put(message,timestamp);
                    return true;
            }
            return false;
        }

        /** ============= 【私有成员】 ================ */

        private Map<String,Integer> history;

    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public void init() {

        }
        public boolean shouldPrintMessage(int timestamp, String message) {
            return false;
        }
        
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void init() {

        }
        public boolean shouldPrintMessage(int timestamp, String message) {
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
        private LoggerRateLimiter problem = new LoggerRateLimiter();
        private Solution solution = null;

        // call method in solution
        private void call(int[] timeStamp, String[] message) {
            solution.init();
            if (timeStamp.length != message.length) {
                System.out.println("Sorry!  Give me same countity of timeStampe & message!");
                return;
            }
            for (int i = 0; i < timeStamp.length; i++) {
                System.out.println(solution.shouldPrintMessage(timeStamp[i],message[i])? "1" : "0");
            }
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] timeStamps1 = new int[]{1,2,3,8,10,11};
            String[] messages1 = new String[]{"foo","bar","foo","bar","foo","foo"};

            /** involk call() method HERE */
            call(timeStamps1,messages1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
