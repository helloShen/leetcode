/**
 * Leetcode - Algorithm - DesignHitCounter
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DesignHitCounter implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DesignHitCounter() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public void init();
        abstract public void hit(int timestamp);
        abstract public int getHits(int timestamp); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }


        public void init() {
            counter = new HashMap<Integer,Integer>();
        }
        public void hit(int timestamp) {
            if (!counter.containsKey(timestamp)) {
                counter.put(timestamp,1);
            } else {
                counter.put(timestamp,counter.get(timestamp)+1);
            }
        }
        public int getHits(int timestamp) {
            int count = 0;
            int start = Math.max(0, timestamp - 300 + 1);
            System.out.println("Counter: " + counter);
            System.out.println("区间[" + start + "," + timestamp + "]");
            for (int i = start; i <= timestamp; i++) {
                if (counter.containsKey(i)) {
                    count += counter.get(i);
                }
            }
            return count;
        }
        /** ============================== 【以下为私有】 ================================= */
        private Map<Integer,Integer> counter;
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public void init() {
            time = new int[SIZE];
            hit = new int[SIZE];
        }
        public void hit(int timestamp) {
            int index = timestamp % SIZE;
            if (time[index] != timestamp) {
                time[index] = timestamp;
                hit[index] = 1;
            } else {
                hit[index]++;
            }
        }
        public int getHits(int timestamp) {
            int count = 0;
            for (int i = 0; i < SIZE; i++) {
                if (timestamp - time[i] < SIZE) {
                    count += hit[i];
                }
            }
            return count;
        }
        
        /** ============================== 【以下为私有】 ================================= */
        private final int SIZE = 300;
        private int[] time;
        private int[] hit;
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public void init() {
            
        }
        public void hit(int timestamp) {

        }
        public int getHits(int timestamp) {
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
        private DesignHitCounter problem = new DesignHitCounter();
        private Solution solution = null;

        // call method in solution
        private void call(int[] hits, int[] checks, int[] answer, int[] hits2, int[]checks2, int[] answer2) {
            solution.init();
            //初次添加hits
            for (int hit : hits) {
                solution.hit(hit);
            }
            for (int check : checks) {
                System.out.printf(solution.getHits(check) + " ");
            }
            System.out.println("");
            System.out.println("Answer = " + Arrays.toString(answer));
            //接着添加hits
            for (int hit : hits2) {
                solution.hit(hit);
            }
            for (int check : checks2) {
                System.out.printf("[" + solution.getHits(check) + "] ");
            }
            System.out.println("");
            System.out.println("Answer = " + Arrays.toString(answer2));
            System.out.println("\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] hits11 = new int[]{1,2,3};
            int[] checks11 = new int[]{4};
            int[] answer11 = new int[]{3};
            int[] hits12 = new int[]{300};
            int[] checks12 = new int[]{300,301};
            int[] answer12 = new int[]{4,3};

            int[] hits21 = new int[]{1,1,1,300};
            int[] checks21 = new int[]{300};
            int[] answer21 = new int[]{4};
            int[] hits22 = new int[]{};
            int[] checks22 = new int[]{};
            int[] answer22 = new int[]{};
            /** involk call() method HERE */
            call(hits11,checks11,answer11,hits12,checks12,answer12);
            call(hits21,checks21,answer21,hits22,checks22,answer22);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1); 
        test.test(2); 
        // test.test(3); 
    }
}
