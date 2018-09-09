/**
 * Leetcode - Algorithm - Count Primes
 */
package com.ciaoshen.leetcode;
import java.util.*;

class CountPrimes {
    /**
     * 暴力验证
     */
    public class SolutionV1 {
        public int countPrimes(int n) {
            int count = 0;
            outFor:
            for (int i = 2; i < n; i++) {
                for (int j = 2; j < i/2; j++) {
                    if (i % j == 0) { continue outFor; }
                }
                count++;
            }
            return count;
        }
    }
    /**
     * 用费马小定理验证
     */
    public class SolutionV2 {
        private final Set<Integer> primes = new HashSet<>(Arrays.asList(new Integer[]{
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
            73, 79, 83, 89, 97 }));
        public int countPrimes(int n) {
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime(i)) { count++; }
            }
            return count;
        }
        public boolean isPrime(int n) {
            if (n < 2) { return false; }
            if (n < 100) { return primes.contains(n); }
            for (int prime : primes) {
                if ((n % prime) == 0) { return false; }
            }
            for (int i = 2; i < 12; i++) { // 30次费马测试
                if (!isFermat(n,i)) { return false; }
            }
            return true; // 至此，确定是素数
        }
        /**
         * 依据费马小定理检查: a是一个素数，
         * 如果 [a^(p-1)] % m = 1，则 m 有很大可能是一个素数。
         */
        public boolean isFermat(int p, int a) {
            return (montgomery(a,p-1,p) == 1);
        }
        /**
         * 蒙哥马利快速幂模算法
         * calculate (n^p)%m
         */
        public int montgomery(int n, int p, int m) {
            int ret = 1;
            long nextRemainder = n % m;
            while (p > 0) {
                if ((p & 1) == 1) { ret = (int)((ret * nextRemainder) % m); }
                nextRemainder = (nextRemainder * nextRemainder) % m;
                p >>>= 1;
            }
            return ret;
        }
    }
    public class SolutionV3 {
        public int countPrimes(int n) {
            int count = 0;
            boolean[] notPrime = new boolean[n];
            for (int i = 2; i < n; i++) {
                if (!notPrime[i]) {
                    count++;
                    for (int j = i*2; j < n; j += i) {
                        notPrime[j] = true;
                    }
                }
            }
            return count;
        }
    }
    public class Solution {
        public int countPrimes(int n) {
            int count = 0;
            boolean[] notPrime = new boolean[n];
            for (int i = 2; i < n; i++) {
                if (!notPrime[i]) {
                    count++;
                    if (i * i < n) {
                        for (int j = i*2; j < n; j += i) {
                            notPrime[j] = true;
                        }
                    }
                }
            }
            return count;
        }
    }
    // return all primes less than n
    private static List<Integer> allPrimesTill(int n) {
        List<Integer> ret = new ArrayList<>();
        outFor:
        for (int i = 2; i < n; i++) {
            for (int j = 2; j <= i/2; j++) {
                if (i % j == 0) { continue outFor; }
            }
            ret.add(i);
        }
        return ret;
    }
    private static void testAllPrimesTill(int n) {
        System.out.println("All primes before " + n + " is: ");
        System.out.println(allPrimesTill(n));
    }
    private static CountPrimes test = new CountPrimes();
    private static Solution solution = test.new Solution();
    private static void callCountPrimes(int n) {
        System.out.println("Before " + n + " has " + solution.countPrimes(n) + " primes!");
    }
    private static void test() {
        /*
        for (int i = 0; i < 10; i++) {
            callCountPrimes(i);
        }
        */
        callCountPrimes(5);
        System.out.println("  (should be " + 2 + ")");
        callCountPrimes(499979);
        System.out.println("  (should be " + 41537 + ")");
        callCountPrimes(1500000);
        System.out.println("  (should be " + 114155 + ")");
    }
    public static void main(String[] args) {
        // testAllPrimesTill(1000);
        test();
    }
}
