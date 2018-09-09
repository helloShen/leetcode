/**
 * Leetcode - Algorithm - OneBitTwoBitsCharacter
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class OneBitTwoBitsCharacter implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private OneBitTwoBitsCharacter() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public boolean isOneBitCharacter(int[] bits); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        private int[] local = new int[0];
        private int len = 0;

        public boolean isOneBitCharacter(int[] bits) {
            local = bits;
            len = bits.length;
            return dp(0);
        }
        private boolean dp(int index) {
            if (index == len) { return false; }
            if (index == len - 1) { return true; } // assertion: always end with 0
            if (local[index] == 0) {
                return dp(index + 1);
            } else {
                return dp(index + 2);
            }
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        public boolean isOneBitCharacter(int[] bits) {
            int cur = 0;
            while (cur < bits.length - 1) {
                cur = cur + ((bits[cur] == 0)? 1 : 2);
            }
            return cur == bits.length - 1;
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public boolean isOneBitCharacter(int[] bits) {
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
        private OneBitTwoBitsCharacter problem = new OneBitTwoBitsCharacter();
        private Solution solution = null;

        private void call(int[] bits, boolean ans) {
            System.out.println(Arrays.toString(bits));
            System.out.println("Is One Bit? " + solution.isOneBitCharacter(bits));
            System.out.println("Answer should be " + ans + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** initialize your testcases HERE... */
            int[] bits1 = new int[]{1,0,0};
            boolean ans1 = true;
            int[] bits2 = new int[]{1,1,1,0};
            boolean ans2 = false;

            /** involk call() method HERE */
            call(bits1, ans1);
            call(bits2, ans2);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.test(1);
        test.test(2);
        // test.test(3);
    }
}
