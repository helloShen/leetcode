/**
 * Leetcode - Algorithm - DistributeCandies
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class DistributeCandies implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private DistributeCandies() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int distributeCandies(int[] candies); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int distributeCandies(int[] candies) {
            Set<Integer> set = new HashSet<>();
            for (int candy : candies) {
                set.add(candy);
            }
            int kinds = set.size();
            int half = candies.length / 2;
            return (kinds >= half)? half : kinds;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int distributeCandies(int[] candies) {
            if (candies.length == 0) { return 0; }
            Arrays.sort(candies);
            int cur = 1, kinds = 1;
            while (cur < candies.length) {
                if (candies[cur-1] < candies[cur]) {
                    kinds++;
                }
                cur++;
            }
            int half = candies.length / 2;
            return (kinds > half)? half : kinds;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int distributeCandies(int[] candies) {
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
        private DistributeCandies problem = new DistributeCandies();
        private Solution solution = null;

        // call method in solution
        private void call(int[] candies, int ans) {
            System.out.println(Arrays.toString(candies));
            System.out.println("Max kind = " + solution.distributeCandies(candies) + "\t [answer = " + ans + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] candies1 = new int[] {1,1,2,2,3,3};
            int ans1 = 3;
            int[] candies2 = new int[] {1,1,2,3};
            int ans2 = 2;

            /** involk call() method HERE */
            call(candies1, ans1);
            call(candies2, ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
