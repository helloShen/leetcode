/**
 * Leetcode - Algorithm - BoatsToSavePeople
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class BoatsToSavePeople implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private BoatsToSavePeople() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int numRescueBoats(int[] people, int limit); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        // implement your solution's method HERE...
        public int numRescueBoats(int[] people, int limit) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < people.length; i++) {
                list.add(people[i]);
            }
            list.sort(new Comparator<Integer>(){
                public int compare(Integer a, Integer b) {
                    return b - a;
                }
            });

            int numBoats = 0;
            while (!list.isEmpty()) {
                int remain = limit - list.remove(0);
                for (int i = 0; remain > 0 && i <list.size(); i++) {
                    if (list.get(i) <= remain) {
                        remain -= list.remove(i); break;
                    }
                }
                numBoats++;
            }
            return numBoats;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people);
            int numBoats = 0;
            for (int i = people.length - 1, j = 0; i >= 0 && i >= j; i--) {
                if (i > j && people[i] + people[j] <= limit) {
                    j++;
                }
                numBoats++;
            }
            return numBoats;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        public int numRescueBoats(int[] people, int limit) {
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
        private BoatsToSavePeople problem = new BoatsToSavePeople();
        private Solution solution = null;

        // call method in solution
        private void call(int[] people, int limit) {
            System.out.println(Arrays.toString(people));
            System.out.println(solution.numRescueBoats(people,limit));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] people1 = new int[]{5,4,3,2,1,3,4};
            int limit1 = 5;
            int[] people2 = new int[]{3,2,3,2,2};
            int limit2 = 6;

            /** involk call() method HERE */
            call(people1,limit1);
            call(people2,limit2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
    }
}
