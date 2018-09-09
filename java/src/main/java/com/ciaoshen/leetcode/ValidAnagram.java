/**
 * Leetcode - Algorithm - Valid Anagram
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ValidAnagram {
    public class SolutionV1 {
        private final int[] CODE = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,83,89,93,97,101};
        public boolean isAnagram(String s, String t) {
            return encode(s) == encode(t);
        }
        public long encode(String s) {
            long r = 1;
            for (int i = 0; i < s.length(); i++) {
                r *= CODE[s.charAt(i) - 'a'];
            }
            return r;
        }
    }
    public class SolutionV2 {
        public boolean isAnagram(String s, String t) {
            Map<Character,Integer> freq = new HashMap<>();
            int len = s.length();
            if (t.length() != len) { return false; }
            for (int i = 0; i < 26; i++) {
                freq.put((char)(i+'a'),0);
            }
            for (int i = 0; i < len; i++) {
                Character c = s.charAt(i);
                freq.put(c,freq.get(c)+1);
            }
            for (int i = 0; i < len; i++) {
                Character c = t.charAt(i);
                freq.put(c,freq.get(c)-1);
            }
            for (Map.Entry<Character,Integer> entry : freq.entrySet()) {
                if (entry.getValue() != 0) { return false; }
            }
            return true;
        }
    }
    public class Solution {
        public boolean isAnagram(String s, String t) {
            int len = s.length();
            if (t.length() != len) { return false; }
            int[] freq = new int[26];
            for (int i = 0; i < len; i++) {
                freq[s.charAt(i)-'a']++;
                freq[t.charAt(i)-'a']--;
            }
            for (int i = 0; i < 26; i++) {
                if (freq[i] != 0) { return false; }
            }
            return true;
        }
    }
    private class Test {
        private void callIsAnagram(String s, String t) {
            boolean result = solution.isAnagram(s,t);
            String keyWord = (result)? " are " : " are not ";
            System.out.println("[" + s + "]" + " and " + "[" + t + "]" + keyWord + "anagram!");
        }
        private void test() {
            String s1 = "anagram";
            String t1 = "nagaram";
            String s2 = "rat";
            String t2 = "car";
            // String s3 =;
            // String t3 =;
            callIsAnagram(s1,t1);
            callIsAnagram(s2,t2);
            // callIsAnagram(s3,t3);
        }
    }
    private static ValidAnagram problem = new ValidAnagram();
    private static Solution solution = problem.new Solution();
    private static Test test = problem.new Test();
    public static void main(String[] args) {
        test.test();
    }
}
