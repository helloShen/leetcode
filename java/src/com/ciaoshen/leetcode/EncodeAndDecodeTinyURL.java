/**
 * Leetcode - Algorithm - EncodeAndDecodeTinyURL.java
 */
package com.ciaoshen.leetcode;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 *  Each problem is initialized with 3 solutions.
 *  You can expand more solutions.
 *  Before using your new solutions, don't forget to register them to the solution registry.
 */
class EncodeAndDecodeTinyURL implements Problem {
    private Map<Integer,Solution> solutions = new HashMap<>(); // solutions registry
    // register solutions HERE...
    private EncodeAndDecodeTinyURL() {
        register(new Solution1());
        register(new Solution2());
        register(new Solution3());
    }
    private abstract class Solution {
        private int id = 0;
        abstract public String encode(String longUrl); // 主方法接口
        abstract public String decode(String shortUrl); // 主方法接口
        protected void sometest() { return; } // 预留的一些小测试的接口
    }
    /* 记录在内存 */
    private class Solution1 extends Solution {
        { super.id = 1; }

        private Map<Integer,String> dic = new HashMap<>();
        private int count = 0;
        private final String PREFIX = "http://tinyurl.com/";

        public String encode(String longUrl) {
            int id = ++count;
            dic.put(id,longUrl);
            return PREFIX + String.valueOf(id);
        }
        public String decode(String shortUrl) {
            int id = Integer.parseInt(shortUrl.replace(PREFIX,""));
            return dic.remove(id);
        }
    }

    private class Solution2 extends Solution {
        { super.id = 2; }

        private Map<String,String> shortLong = new HashMap<>(); // short-long
        private Map<String,String> longShort = new HashMap<>(); // long-short
        private final String PREFIX = "http://tinyurl.com/";
        private final int LEN = 7;

        public String encode(String longUrl) {
            if (longShort.containsKey(longUrl)) { return longShort.get(longUrl); }
            String code = randomCode(LEN);
            while (shortLong.containsKey(code)) { code = randomCode(LEN); }
            String shortUrl = PREFIX + code;
            shortLong.put(shortUrl,longUrl);
            longShort.put(longUrl,shortUrl);
            return shortUrl;
        }
        public String decode(String shortUrl) {
            return shortLong.get(shortUrl);
        }

        private final char[] C = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};
        private final Random R = new Random();
        private String randomCode(int len) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(C[R.nextInt(C.length)]);
            }
            return sb.toString();
        }
    }

    private class Solution3 extends Solution {
        { super.id = 3; }
        public String encode(String longUrl) {
            return "";
        }
        public String decode(String shortUrl) {
            return "";
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
        private EncodeAndDecodeTinyURL problem = new EncodeAndDecodeTinyURL();
        private Solution solution = null;

        // call method in solution
        private void call(String url) {
            System.out.println("Original Url = " + url);
            String shortUrl = solution.encode(url);
            System.out.println("Short Url = " + shortUrl);
            String longUrl = solution.decode(shortUrl);
            System.out.println("Long Url = " + longUrl);
        }

        // public API of Test interface
        public void test(int id) {
            solution = problem.solution(id);
            if (solution == null) { System.out.println("Sorry, [id:" + id + "] doesn't exist!"); return; }
            System.out.println("\nCall Solution" + solution.id);

            // test cases
            String str1 = "https://leetcode.com/problems/design-tinyurl";
            // String str2 = "";
            // String str3 = "";

            call(str1);
            // call();
            // call();
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        // test.test(1);
        test.test(2);
        // test.test(3);
    }
}
