/**
 * Leetcode - Algorithm - MegicalString
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class MegicalString implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private MegicalString() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int megicalString(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int megicalString(int n) {
            if (n == 0) { return 0; }
            List<Integer> list = new ArrayList<>();
            list.add(1);
            int remain = n - 1;
            int res = 1;
            while (remain > 0) {
                int num = list.get(0);
                // System.out.println(num);
                // System.out.println(remain);
                if (num == 1) {
                    if (list.get(list.size()-1) == 1) {
                        list.add(2);
                    } else {
                        list.add(1);
                        res++;
                    }
                    remain--;
                } else {
                    int last = list.get(list.size()-1);
                    list.add(last);
                    if (last == 1) { res++; }
                    remain--;
                    if (remain == 0) { return res; }
                    if (last == 1) {
                        list.add(2);
                    } else {
                        list.add(1);
                        res++;
                    }
                    remain--;
                }
                list.remove(0);
            }
            return res;
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private int[] memo = new int[100002];
        private int slow = 0, fast = 0;

        {
            memo[1] = 1;
            slow = 1;
            fast = 1;
        }
        public int megicalString(int n) {
            if (n > fast) { generate(n); }
            return count(n);
        }
        private int count(int n) {
            int count = 0;
            for (int i = 0; i <= n; i++) {
                if (memo[i] == 1) { count++; }
            }
            return count;
        }
        private void generate(int n) {
            int remain = n - fast;
            while (remain > 0) {
                int curr = memo[slow++];
                if (curr == 1) {
                    int last = memo[fast];
                    if (last == 1) {
                        memo[++fast] = 2;
                    } else {
                        memo[++fast] = 1;
                    }
                    remain--;
                } else {
                    int last = memo[fast];
                    if (last == 1) {
                        memo[++fast] = 1;
                        memo[++fast] = 2;
                    } else {
                        memo[++fast] = 2;
                        memo[++fast] = 1;
                    }
                    remain -= 2;
                }
            }
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }

        public int megicalString(int n) {
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
        private MegicalString problem = new MegicalString();
        private Solution solution = null;

        private void call(int n) {
            System.out.println("N = " + n + " -> " + "Count 1 = " + solution.megicalString(n) + "\n");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            for (int i = 1; i < 20; i++) {
                call(i);
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
