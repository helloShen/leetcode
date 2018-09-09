/**
 * Leetcode - Algorithm - PerfectSquares
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class PerfectSquares implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private PerfectSquares() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
        register(new Solution6());
        register(new Solution7());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int numSquares(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    private class Solution1 extends Solution {
        { super.id = 1; }
        private Map<Integer,Integer> memo = new HashMap<>();
        public int numSquares(int n) {
            memo = new HashMap<>();
            return dfs(n);
        }
        private int dfs(int remain) {
            if (remain == 0) { return 0; }
            Integer min = memo.get(remain);
            if (min != null) { return min; }
            min = Integer.MAX_VALUE;
            for (int i = 1; i <= (int)Math.sqrt(remain); i++) {
                min = Math.min(min,1+dfs(remain-i*i));
            }
            memo.put(remain,min);
            return min;
        }
    }

    /* 带备忘录的dfs */
    private class Solution2 extends Solution {
        { super.id = 2; }
        // implement your solution's method HERE...
        private Map<Integer,Integer> memo = new HashMap<>();
        private int globalMin = Integer.MAX_VALUE;
        public int numSquares(int n) {
            memo = new HashMap<>();
            globalMin = Integer.MAX_VALUE;
            return dfs(n,n,0);
        }
        /* count监控每一层递归的数字数量，一旦超标立刻放弃递归 */
        private Integer dfs(int orig, int remain, int count) {
            // base case
            if (remain == 0) { return 0; }
            // eliminate branch
            if (count == globalMin) { return null; }
            // table driven
            Integer res = memo.get(remain);
            if (res != null) { return res; }
            // dfs recursion
            Integer min = null;
            for (int i = (int)Math.sqrt(remain); i > 0; i--) {
                Integer sub = dfs(orig,remain-i*i,count+1);
                if (sub == null) { continue; }
                min = (min == null)? 1+sub : Math.min(min,1+sub);
                if (orig == remain) { globalMin = Math.min(globalMin,min); }
            }
            if (min != null) { memo.put(remain,min); }
            return min;
        }
        protected void sometest() {
            for (int i = 0; i < 101; i++) {
                System.out.println("sqrt(" + i + ") = " + (int)Math.sqrt(i));
            }
        }
    }

    /*
     * 还是带备忘录的dfs
     * 两部分优化：
     *      1. 用数组代替Map记录历史结果
     *      2. 用另一个表记录所有平方数
     */
    private class Solution3 extends Solution {
        { super.id = 3; }
        // implement your solution's method HERE...
        private int[] sqrt = new int[0];
        private int[] memo = new int[0];
        private int target = 0;
        public int numSquares(int n) {
            memo = new int[n+1];
            target = n;
            sqrt = new int[(int)Math.sqrt(n)];
            for (int i = (int)Math.sqrt(n), j = 0; i > 0; i--,j++) {
                sqrt[j] = i * i;
            }
            return dfs(n,0);
        }
        /*
         * dfs递归。
         * 如果已经大于现有最小值，则迅速放弃，返回null
         * 注意：尾递归不行，因为最后不能回归每层递归去维护memo表
         */
        public Integer dfs(int remain, int prefix) {
            // base case
            if (remain == 0) { return 0; }
            // kill
            int history = memo[target];
            if (history != 0 && prefix == history) { return null; }
            // check table
            if (memo[remain] != 0) { return memo[remain]; }
            // dfs recursion
            Integer min = null;
            for (int i = 0; i < sqrt.length; i++) {
                if (sqrt[i] > remain) { continue; }
                Integer sub = dfs(remain - sqrt[i],prefix+1);
                if (sub != null) {
                    min = (min == null)? sub+1 : Math.min(min,sub+1);
                    memo[remain] = (memo[remain] == 0)? min : Math.min(memo[remain],min);
                }
            }
            return min;
        }
    }
    // you can expand more solutions HERE if you want...
    /*
     * 标准自底向上动态规划
     * f(n) = min(f(n-1*1)+1, f(n-2*2)+1, f(n-3*3)+1, ... ...)
     */
    private class Solution4 extends Solution {
        { super.id = 4; }
        public int numSquares(int n) {
            if (n < 1) { return 0; }
            int[] res = new int[n+1];
            // base case 0->0, 1->1
            res[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= Math.sqrt(i); j++) {
                    res[i] = (res[i] == 0)? res[i-j*j] + 1 : Math.min(res[i],res[i-j*j]+1);
                }
            }
            return res[n];
        }
    }
    private class Solution5 extends Solution {
        { super.id = 5; }
        private int[] res = new int[16];
        private int cursor = 2;
        { res[1] = 1; }
        public int numSquares(int n) {
            if (n < 1) { return 0; }
            if (cursor > n) { return res[n]; }                          // result exists
            if (res.length < n+1) { res = Arrays.copyOf(res,n*2); }     // result array not long enough
            for (int i = cursor; i <= n; i++) {
                for (int j = 1; j <= Math.sqrt(i); j++) {
                    res[i] = (res[i] == 0)? res[i-j*j] + 1 : Math.min(res[i],res[i-j*j]+1);
                }
            }
            return res[n];
        }
    }
    private class Solution7 extends Solution {
        { super.id = 7; }
        private Map<Integer,Integer> map = new HashMap<>();
        { map.put(1,1); } // base case
        public int numSquares(int n) {
            if (n <= map.size()) { return map.get(n); }
            for (int i = map.size()+1; i <=n; i++) {
                for (int j = 1; j <= Math.sqrt(i); j++) {
                    Integer sub = map.get(i-j*j);
                    Integer local = (sub == null)? 1 : sub + 1;
                    Integer global = map.get(i);
                    map.put(i,(global == null)? local : Math.min(local,global));
                }
            }
            return map.get(n);
        }
    }


    /* 数学法 */
    private class Solution6 extends Solution {
        { super.id = 6; }
        public int numSquares(int n) {
            // is 1?
            if (isSquare(n)) { return 1; }
            // is 4?
            int copy = n;
            while ((copy & 3) == 0) { // n % 4 = 0
                copy >>= 2;
            }
            if ((copy & 7) == 7) { // n % 8 = 7
                return 4;
            }
            // is 2?
            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (isSquare(n-i*i)) { return 2; }
            }
            // is 3!
            return 3;
        }
        private boolean isSquare(int n) {
            int sqrt = (int)Math.sqrt(n);
            return sqrt * sqrt == n;
        }
    }


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
        private PerfectSquares problem = new PerfectSquares();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            // System.out.println("\"" + n + "\"\t -> \t" + solution.numSquares(n));
            System.out.print(solution.numSquares(n) + ",");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            /** involk call() method HERE */
            for (int i = 1; i < 1000; i++) {
                call(i);
            }
            // solution.sometest();
        }
        public void test(int id, int n) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            call(n);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        // test.test(5);
        // test.test(6);
        test.test(7);
        // test.test(3,23);
    }
}
