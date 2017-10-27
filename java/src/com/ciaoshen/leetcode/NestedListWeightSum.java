/**
 * Leetcode - Algorithm - NestedListWeightSum
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class NestedListWeightSum implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private NestedListWeightSum() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int depthSum(List<NestedInteger> nestedList); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int depthSum(List<NestedInteger> nestedList) {
            return helper(nestedList,1);
        }
        public int helper(List<NestedInteger> nestedList, int depth) {
            int sum = 0;
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    sum += ni.getInteger() * depth;
                } else {
                    sum += helper(ni.getList(),depth+1);
                }
            }
            return sum;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public int depthSum(List<NestedInteger> nestedList) {
            return 2;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        public int depthSum(List<NestedInteger> nestedList) {
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
        private NestedListWeightSum problem = new NestedListWeightSum();
        private Solution solution = null;

        // call method in solution
        private void call(List<NestedInteger> nestedList) {
            System.out.println("\nNested Integer = " + nestedList);
            System.out.println(solution.depthSum(nestedList));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            NestedInteger oneone = new NestedInteger(new int[]{1,1});
            NestedInteger two = new NestedInteger(2);
            List<NestedInteger> testcase1 = new ArrayList<>();
            testcase1.add(oneone);
            testcase1.add(two);
            testcase1.add(oneone);
            /** involk call() method HERE */
            call(testcase1);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        // test.test(2);
        // test.test(3);
    }
}
