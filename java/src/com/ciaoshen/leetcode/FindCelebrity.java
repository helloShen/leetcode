/**
 * Leetcode - Algorithm - FindCelebrity
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class FindCelebrity implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private FindCelebrity() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int findCelebrity(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口

        // Party Module
        private boolean[][] party = new boolean[0][0];
        private int celebrity = 0;
        private Random r = new Random();
        protected void init(int n) {
            party = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) { continue; }
                    party[i][j] = r.nextBoolean();
                }
            }
            celebrity = r.nextInt(n);
            for (int i = 0; i < n; i++) {
                if (i == celebrity) { continue; }
                party[celebrity][i] = true;     // 所有人必须认识名人
                party[i][celebrity] = false;    // 名人谁都不认识
            }
            for (int i = 0; i < n; i++) { // 只能有一个名人
                if (i == celebrity) { continue; }
                int notKnow = r.nextInt(n);
                while (notKnow == i) { notKnow = r.nextInt(n); }
                party[i][notKnow] = false;
            }
        }
        protected void setParty(boolean[][] p) {
            party = p;
            celebrity = -1;
        }
        protected void setCelebrity(int x) {
            celebrity = x;
        }
        /** return if a knows b */
        protected boolean knows(int a, int b) {
            return party[b][a];
        }
        protected void print() {
            Matrix.print(party);
            System.out.println("Celebrity is: " + celebrity + "\n");
        }
    }
    /**
     * 直观朴素的逻辑
     */
    private class Solution1 extends Solution {
        { super.id = 1; }
        /** have to call init() method first */
        public int findCelebrity(int n) {
            outFor:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == i) { continue; }
                    if (!knows(j,i)) { continue outFor; }   // 有人不认识此人，此人不是名人
                    if (knows(i,j)) { continue outFor; }    // 此人认识某个人，此人不是名人
                }
                return i;
            }
            return -1;
        }
    }

    /**
     * 排除法
     */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int findCelebrity(int n) {
            Set<Integer> remain = new HashSet<>();
            for (int i = 0; i < n ; i++) { remain.add(i); }
            outFor:
            for (int i = 0; i < n; i++) {
                if (!remain.contains(i)) { continue; } // 此人已经被剔除
                for (int j = 0; j < n; j++) {
                    if (j == i || !remain.contains(j)) { continue; }
                    if (knows(j,i)) {
                        remain.remove(j); // j认识i，j不可能是名人
                    } else {
                        remain.remove(i); // j不认识i，i不可能是名人
                        continue outFor;
                    }
                }
            }
            // 严格检查剩下的最后一人
            int candidate = remain.iterator().next();
            for (int i = 0; i < n; i++) {
                if (i == candidate) { continue; }
                if (knows(candidate,i)) { return -1; }  // 剩下的人认识某个人，此人不是名人
                if (!knows(i,candidate)) { return -1; } // 剩下的人有人不认识他，他也不是名人
            }
            return candidate;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        public int findCelebrity(int n) {
            int candidate = 0;
            for(int i = 1; i < n; i++){
                if(knows(candidate, i)) { candidate = i; }
            }
            for(int i = 0; i < n; i++){
                if (i < candidate && (knows(candidate,i) || !knows(i,candidate))) { return -1; }
                if (i > candidate && !knows(i,candidate)) { return -1; }
            }
            return candidate;
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
        private FindCelebrity problem = new FindCelebrity();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            solution.init(n);
            solution.print();
            System.out.println("I find the celebrity: " + solution.findCelebrity(n) + "\n");
        }
        private void call(boolean[][] p) {
            solution.setParty(p);
            solution.setCelebrity(1);
            solution.print();
            System.out.println("I find the celebrity: " + solution.findCelebrity(p.length) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            boolean[][] party1 = new boolean[2][2];
            party1[1][0] = true;
            /** involk call() method HERE */
            call(10);
            call(party1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        test.test(3);
    }
}
