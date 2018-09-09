/**
 * Leetcode - Algorithm - ArithmeticSlices
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class ArithmeticSlices implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private ArithmeticSlices() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int numberOfArithmeticSlices(int[] A); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int numberOfArithmeticSlices(int[] A) {
            if (A.length < 3) { return 0; }
            for (int i = 1, pre = A[0]; i < A.length; i++) {
                int diff = A[i] - pre;
                pre = A[i];
                A[i] = diff;
            }
            // System.out.println("Diff Array = " + Arrays.toString(A));
            int cur = 1, res = 0;
            while (cur < A.length) {
                int first = A[cur];
                int begin = (cur++);
                while (cur < A.length && A[cur] == first) { cur++; }
                res += sub(cur - begin + 1);
            }
            return res;
        }
        private int sub(int size) {
            if (size < 3) { return 0; }
            int max = size - 3 + 1;
            int sum = 0;
            while (max > 0) {
                sum += (max--);
            }
            return sum;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private int[] diff = new int[0];

        public int numberOfArithmeticSlices(int[] A) {
            if (A.length < 3) { return 0; }
            for (int i = 1, pre = A[0]; i < A.length; i++) {
                int diff = A[i] - pre;
                pre = A[i];
                A[i] = diff;
            }
            diff = A;
            return dp(1);
        }
        private int dp(int cur) {
            if (cur == diff.length - 1) { return 0; }
            int sub = dp(cur+1);
            int begin = cur, targetDiff = diff[begin];
            while (cur < diff.length && diff[cur] == targetDiff) { cur++; }
            return sub + (cur - begin - 1);
        }

    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int numberOfArithmeticSlices(int[] A) {
            int curr = 0, sum = 0;
            for (int i=2; i<A.length; i++)
                if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                    curr += 1;
                    sum += curr;
                } else {
                    curr = 0;
                }
            return sum;
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
        private ArithmeticSlices problem = new ArithmeticSlices();
        private Solution solution = null;

        // call method in solution
        private void call(int[] A, int ans) {
            System.out.println(Arrays.toString(A));
            System.out.println("Number of Slices = " + solution.numberOfArithmeticSlices(A) + "\t[Answer:" + ans + "]\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] A1 = new int[]{1, 2, 3, 4};
            int ans1 = 3;
            int[] A2 = new int[]{1,2,3,4,5};
            int ans2 = 6;

            /** involk call() method HERE */
            call(A1,ans1);
            call(A2,ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
