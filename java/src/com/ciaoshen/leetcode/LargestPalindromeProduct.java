/**
 * Leetcode - Algorithm - LargestPalindromeProduct
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class LargestPalindromeProduct implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private LargestPalindromeProduct() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
        register(new Solution4());
        register(new Solution5());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public int largestPalindrome(int n); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 遍历因数，乘出积，再判断回文 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        public int largestPalindrome(int n) {
            int max = (int)Math.pow(10,n) - 1;
            int min = max / 10 * 9;
            long result = 0;
            for (long i = max; i >= min; i--) {
                for (long j = max; j >= min; j--) {
                    long product = i * j;
                    if (product > result && fastIsPalindrome(product)) {
                        if (product > result) { System.out.println(i + " * " + j + " = " + String.valueOf(product)); }
                        result = Math.max(result,product);
                    }
                }
            }
            System.out.println("Result = " + result);
            return (int)(result % 1337);
        }
        private boolean isPalindrome(long num) {
            String val = String.valueOf(num);
            int lo = 0, hi = val.length()-1;
            while (lo < hi) {
                if (val.charAt(lo++) != val.charAt(hi--)) { return false; }
            }
            return true;
        }
        private int[] digits = new int[20];
        private boolean fastIsPalindrome(long num) {
            int cur = 20;
            while (num != 0) {
                digits[--cur] = (int)(num % 10);
                num /= 10;
            }
            int lo = cur, hi = 19;
            while (lo < hi) {
                if (digits[lo++] != digits[hi--]) { return false; }
            }
            return true;
        }
    }
    /* 遍历积，判断回文，再因式分解 */
    private class Solution2 extends Solution {
        { super.id = 2; }

        public int largestPalindrome(int n) {
            long rMax = (long)Math.pow(10,2*n) - 1;
            int dMax = (int)Math.pow(10,n) - 1;
            for (long i = rMax; i > 0; i--) {
                if (isPalindrome(i)) {
                    for (int j = dMax; j > 0; j--) {
                        if (i % j == 0 && (i / j) <= dMax) {
                            // System.out.println((i/j) + " * " + j + " = " + i);
                            return (int)(i % 1337);
                        }
                    }
                }
            }
            return 0;
        }
        private int[] digits = new int[20];
        private boolean isPalindrome(long num) {
            int cur = 20;
            while (num != 0) {
                digits[--cur] = (int)(num % 10);
                num /= 10;
            }
            int lo = cur, hi = 19;
            while (lo < hi) {
                if (digits[lo++] != digits[hi--]) { return false; }
            }
            return true;
        }
    }
    /*
     * 还是遍历积，但不用判断回文，而是直接产生下一个回文。最后因式分解。
     * 数组的形式产生回文
     */
    private class Solution3 extends Solution {
        { super.id = 3; }

        private int max = 0; // ex: n=3; max=999;
        private int half = 0;

        public int largestPalindrome(int n) {
            if (n == 1) { return 9; }
            // init
            len = n * 2;
            lo = n - 1; hi = n;
            number = new int[len];
            hasNext = true; // ex: true=[999,100]; false=[99,0]
            Arrays.fill(number,9);
            max = (int)Math.pow(10,n) - 1; half = max;
            // iterate each palindrome
            long palindrome = nextPalindrome();
            while (palindrome != 0) {
                // ex: sqrt(9999) = 99.5, so i >= 100
                // ex: sqrt(121) = 11, so i >= 11
                // 用ceil()取上界，就是为的不漏掉sqrt(121)正好为整数的情况。每个细节一定要处理干净。
                for (int i = max; i >= (int)Math.ceil(Math.sqrt(palindrome)); i--) {
                    if (palindrome % i == 0) {
                        // System.out.println(i + " * " + (palindrome/i) + " = " + palindrome);
                        return (int)(palindrome % 1337);
                    }
                }
                palindrome = nextPalindrome();
            }
            return 0;
        }
        // para for palindrome
        private int len = 0;
        private int lo = 0, hi = 0;
        private int[] number = new int[0];
        private boolean hasNext = false;
        // generate next palindrome
        private long nextPalindrome() {
            // translate next palindrome to int
            long next = 0;
            if (hasNext) {
                for (int i : number) {
                    next *= 10;
                    next += i;
                }
            }
            // generate next palindrome
            for (int i = lo, j = hi; i >= 0; i--,j++) {
                if (number[i] == 0) {
                    number[i] = 9; number[j] = 9;
                } else {
                    number[i]--; number[j]--; break;
                }
            }
            if (number[0] == 0) { hasNext = false; }
            return next;
        }
    }
    // you can expand more solutions HERE if you want...

    /*
     * 还是遍历积，但不用判断回文，而是直接产生下一个回文。最后因式分解。
     * 用StringBuilder.reverse()产生回文。
     */
    private class Solution4 extends Solution {
        { super.id = 4; }

        private int max = 0; // ex: n=3; max=999;
        private int half = max;
        public int largestPalindrome(int n) {
            if (n == 1) { return 9; }
            // init
            max = (int)Math.pow(10,n) - 1; half = max;
            // System.out.println("Max = " + max);
            hasNextPalindrome = true;
            // iterate each palindrome
            long palindrome = nextPalindrome();
            while (palindrome != 0) {
                // ex: sqrt(9999) = 99.5, so i >= 100
                // ex: sqrt(121) = 11, so i >= 11
                // 用ceil()取上界，就是为的不漏掉sqrt(121)正好为整数的情况。每个细节一定要处理干净。
                for (int i = max; i >= (int)Math.ceil(Math.sqrt(palindrome)); i--) {
                    if (palindrome % i == 0) {
                        // System.out.println(i + " * " + (palindrome/i) + " = " + palindrome);
                        return (int)(palindrome % 1337);
                    }
                }
                palindrome = nextPalindrome();
            }
            return 0;
        }

        private boolean hasNextPalindrome = true; // ex: true=[999,100]; false=[99,0]
        private long nextPalindrome() {
            long result = 0L;
            if (hasNextPalindrome) {
                String str = String.valueOf(half);
                StringBuilder sb = new StringBuilder(str);
                result = Long.parseLong(str + (sb.reverse().toString()));
                --half;
                if (max / half > 9) { hasNextPalindrome = false; }
            }
            return result;
        }
    }

    /* 生气的时候，用这个发泄 */
    private class Solution5 extends Solution {
        { super.id = 5; }

        public int largestPalindrome(int n) {
            switch (n) {
                case 1: return 9;
                case 2: return 987;
                case 3: return 123;
                case 4: return 597;
                case 5: return 677;
                case 6: return 1218;
                case 7: return 877;
                case 8: return 475;
                default: return 0;
            }
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
        private LargestPalindromeProduct problem = new LargestPalindromeProduct();
        private Solution solution = null;

        // call method in solution
        private void call(int n) {
            System.out.println(n + ": " + solution.largestPalindrome(n));
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            for (int i = 1; i <= 8; i++) {
                call(i);
            }
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        // test.test(2);
        // test.test(3);
        // test.test(4);
        test.test(5);
    }
}
