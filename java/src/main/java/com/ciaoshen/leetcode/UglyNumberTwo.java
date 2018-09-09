/**
 * Leetcode - Algorithm - UglyNumberTwo
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class UglyNumberTwo implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private UglyNumberTwo() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
        register(new Solution6());
        register(new Solution7());
        register(new Solution8());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int nthUglyNumber(int num);
    }
    /** 利用isUglyNumber()函数对每个自增的自然数判断 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int nthUglyNumber(int num) {
            if (num < 0) { return 0; }
            int count = 0;
            int val = 1;
            while (true) {
                if (isUglyNumber(val)) { ++count; }
                if (count == num) { return val; }
                ++val;
            }
        }
        /* 判断参数num是不是ugly number */
        private boolean isUglyNumber(int num) {
            if (num <= 0) { return false; }
            StringBuilder sb = new StringBuilder();
            sb.append(num + " = ");
            while (num > 1) {
                if (num % 2 == 0) { num /= 2; sb.append("2,"); continue; }
                if (num % 3 == 0) { num /= 3; sb.append("3,"); continue; }
                if (num % 5 == 0) { num /= 5; sb.append("5,"); continue; }
                break;
            }
            if (num == 1) { System.out.println(sb.toString()); }
            return num == 1;
        }
    }

    /** 还是利用isUglyNumber()函数，但增加了一个数组，记录曾经找到过的ugly number */
    private class Solution2 extends Solution {
        { super.id = 2; }
        private int[] table = new int[1691]; // lazy initialization
        private int max = 0;
        public int nthUglyNumber(int num) {
            if (num <= 0) { return 0; }
            if (num <= max) {
                return table[num];
            } else {
                return mthToNth(max,table[max],num);
            }
        }
        /* 给出第m个ugly number, 返回第n个ugly number. m < n */
        public int mthToNth(int m, int mth, int n) {
            int count = m;
            int val = mth + 1;
            while (true) {
                if (isUglyNumber(val++)) {
                    table[++count] = val-1;
                    max = count;
                }
                if (count == n) { return val-1; }
            }
        }
        /* 判断参数num是不是ugly number */
        private boolean isUglyNumber(int num) {
            if (num <= 0) { return false; }
            while (num > 1) {
                if (num % 2 == 0) { num /= 2; continue; }
                if (num % 3 == 0) { num /= 3; continue; }
                if (num % 5 == 0) { num /= 5; continue; }
                break;
            }
            return num == 1;
        }
    }
    /** 利用isUglyNumber()函数。递归版 */
    private class Solution3 extends Solution {
        { super.id = 3; }

        public int nthUglyNumber(int num) {
            if (num == 1) { return 1; }
            int lastIndex = num-1;
            int last = nthUglyNumber(num-1);
            while (true) {
                if (isUglyNumber(++last)) { return last; }
            }
        }
        /* 判断参数num是不是ugly number */
        private boolean isUglyNumber(int num) {
            if (num <= 0) { return false; }
            while (num > 1) {
                if (num % 2 == 0) { num /= 2; continue; }
                if (num % 3 == 0) { num /= 3; continue; }
                if (num % 5 == 0) { num /= 5; continue; }
                break;
            }
            return num == 1;
        }
    }
    // you can expand more solutions HERE if you want...
    /* 尽量想办法记录已经找到的ugly number */
    private class Solution4 extends Solution {
        { super.id = 4; }
        private int[] HELPER = new int[]{0,1536,16200,82944,311040,937500,2460375,5898240,12754584,26244000,51200000,97200000,174960000,306110016,516560652,859963392,1399680000};
        public int nthUglyNumber(int num) {
            if (num % 100 == 0) { return HELPER[num/100]; }
            int lastIndex = num-1;
            int last = nthUglyNumber(num-1);
            while (true) {
                if (isUglyNumber(++last)) { return last; }
            }
        }
        /* 判断参数num是不是ugly number */
        private Set<Integer> uglyNumbers = new HashSet<>();
        private boolean isUglyNumber(int num) {
            if (uglyNumbers.contains(num)) { return true; }
            if (num == 1) { uglyNumbers.add(1); return true; }
            if (num % 2 == 0) {
                boolean result = isUglyNumber(num/2);
                if (result) { uglyNumbers.add(num); }
                return result;
            }
            if (num % 3 == 0) {
                boolean result = isUglyNumber(num/3);
                if (result) { uglyNumbers.add(num); }
                return result;
            }
            if (num % 5 == 0) {
                boolean result = isUglyNumber(num/5);
                if (result) { uglyNumbers.add(num); }
                return result;
            }
            return false;
        }
        public String toString() {
            return uglyNumbers.toString();
        }
    }

    /* 尽量想办法记录已经找到的ugly number。不用递归版。*/
    private class Solution5 extends Solution {
        { super.id = 5; }
        private int[] HELPER = new int[]{0,1536,16200,82944,311040,937500,2460375,5898240,12754584,26244000,51200000,97200000,174960000,306110016,516560652,859963392,1399680000};
        public int nthUglyNumber(int num) {
            int startIndex = num / 100, index = startIndex * 100;
            int startNum = HELPER[startIndex];
            while (true) {
                if (isUglyNumber(++startNum)) {
                    ++index;
                    if (index == num) { return startNum; }
                }
            }
        }
        /* 判断参数num是不是ugly number */
        private Set<Integer> uglyNumbers = new HashSet<>();
        private boolean isUglyNumber(int num) {
            if (uglyNumbers.contains(num)) { return true; }
            if (num == 1) { uglyNumbers.add(1); return true; }
            if (num % 2 == 0) {
                boolean result = isUglyNumber(num/2);
                if (result) { uglyNumbers.add(num); }
                return result;
            }
            if (num % 3 == 0) {
                boolean result = isUglyNumber(num/3);
                if (result) { uglyNumbers.add(num); }
                return result;
            }
            if (num % 5 == 0) {
                boolean result = isUglyNumber(num/5);
                if (result) { uglyNumbers.add(num); }
                return result;
            }
            return false;
        }
        public String toString() {
            return uglyNumbers.toString();
        }
    }
    /* 用一个超大数组记录ugly number信息 */
    private class Solution6 extends Solution {
        { super.id = 6; }
        private int[] memo = new int[100000]; // 如果记录>0，说明是ugly number, 而且记录数字就代表是第几个ugly number。
        private int max = 0;
        public int nthUglyNumber(int num) {
            int val = 0;
            int count = 0;
            while (true) {
                if (isUglyNumber(++val)) { memo[val] = ++count; }
                max = val;
                if (count == num) { return val; }
            }
        }
        /* return if num is an ugly number */
        private boolean isUglyNumber(int num) {
            if (num <= max) { return memo[num] > 0; }
            if (num == 1) { return true; } // base case
            if (num % 2 == 0) { return isUglyNumber(num/2); }
            if (num % 3 == 0) { return isUglyNumber(num/3); }
            if (num % 5 == 0) { return isUglyNumber(num/5); }
            return false;
        }
    }
    /*
     * O(n)的最佳算法
     * 所有的ugly number都在这3条线上的数字里，
     * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     */
    private class Solution7 extends Solution {
        { super.id = 7; }

        public int nthUglyNumber(int num) {
            if (num <= 0) { return 0; }
            int[] uglyNums = new int[num+1];
            uglyNums[1] = 1;
            int index2 = 1, index3 = 1, index5 = 1;
            int factor2 = 2, factor3 = 3, factor5 = 5;
            for (int i = 2; i <= num; i++) {
                int min = Math.min(Math.min(factor2,factor3),factor5);
                uglyNums[i] = min;
                if (min == factor2) { factor2 = 2 * uglyNums[++index2]; }
                if (min == factor3) { factor3 = 3 * uglyNums[++index3]; }
                if (min == factor5) { factor5 = 5 * uglyNums[++index5]; }
            }
            return uglyNums[num];
        }
    }
    private class Solution8 extends Solution {
        { super.id = 8; }
        private Map<Integer,Integer> uglyNums = new HashMap<>(); // 记录目前找到的所有ugly number
        { uglyNums.put(1,1); } // base case
        private int maxIndex = 1; // 目前map里记录的最大的ugly number
        private int maxTest = 1; // 目前为止检测过的最大的数字（之前的数字都检查过）

        /* 以记录中最大的ugly number为起点，接着往下找 */
        public int nthUglyNumber(int num) {
            if (num <= 0) { return 0; }
            if (num <= maxIndex) {
                return uglyNums.get(num);
            } else {
                return mthToNth(maxIndex,uglyNums.get(maxIndex),num);
            }
        }
        /* 从第m个ugly number开始，找到第n个ugly number. m < n */
        public int mthToNth(int m, int mth, int n) {
            while (true) {
                if (isUglyNumber(++mth)) {
                    uglyNums.put(++m,mth); // 由mthToNth()函数维护map
                    maxIndex = m; //由mthToNth()函数维护maxIndex
                }
                maxTest = mth;
                if (m == n) { return mth; }
            }
        }
        /* 判断参数num是不是ugly number, 也可以利用过去的记录 */
        private boolean isUglyNumber(int num) {
            if (num <= 0) { return false; }
            if (num <= maxTest) { return uglyNums.values().contains(num); } // uglyNums里没记的就都不是
            if (num % 2 == 0) { return isUglyNumber(num/2); }
            if (num % 3 == 0) { return isUglyNumber(num/3); }
            if (num % 5 == 0) { return isUglyNumber(num/5); }
            return false;
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
        private UglyNumberTwo problem = new UglyNumberTwo();
        private Solution solution = null;

        private void call(int n, String answer) {
            System.out.println(n + "th ugly number is: " + solution.nthUglyNumber(n) + "        [answer:" +answer + "]");
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            for (int i = 1; i <= 1690; i++) {
                if (i % 70 == 0) { call(i,"?"); }
            }
            // System.out.println(solution);
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
        // test.test(7);
        test.test(8);
    }
}
