/**
 * Leetcode - Algorithm - Reverse Words in a String
 */
package com.ciaoshen.leetcode;
import java.util.*;

class ReverseWordsInAString {
    /**
     * 完全不用容器，全基于数组。效率比较高。
     */
    public class SolutionV1 {
        public String reverseWords(String s) {
            int size = s.length();
            char[] rev = new char[size];
            int revCur = 0;
            int slow = size, fast = size-1;
            while (fast >= 0) {
                while (fast >= 0 && s.charAt(fast) != ' ') { fast--; }
                for (int cur = fast+1; cur < slow; cur++) {
                    rev[revCur++] = s.charAt(cur);
                }
                if (revCur < size && slow - fast > 1) { // 只有找到一个word，并且不是最后一个单词时，在后面加一个空格
                    rev[revCur++] = ' ';
                }
                slow = fast;
                fast--;
            }
            if (revCur-1 < size && revCur > 0 && rev[revCur-1] == ' ') { revCur--; }
            return new String(rev,0,revCur);
        }
    }
    /**
     * 使用容器，以及用`split()`分割单词，的对照组。
     */
    public class SolutionV2 {
        public String reverseWords(String s) {
            String[] words = s.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = words.length-1; i >= 0; i--) {
                if (words[i] != null && !words[i].isEmpty()) {
                    sb.append(words[i]+ " ");
                }
            }
            return sb.toString().trim();
        }
    }
    /**
     * 用正则表达式切割
     */
    public class Solution {
        public String reverseWords(String s) {
            String[] words = s.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (int i = words.length-1; i >= 0; i--) {
                sb.append(words[i] + " ");
            }
            return sb.toString().trim();
        }
    }
    private static ReverseWordsInAString test = new ReverseWordsInAString();
    private static Solution solution = test.new Solution();
    private static void testReverseWords(String s) {
        System.out.println("Original String: \"" + s + "\"");
        System.out.println("Reverse String: \"" + solution.reverseWords(s) + "\"");
    }
    public static void main(String[] args) {
        String str1 = "the sky is blue";
        String str2 = " ";
        String str3 = "    a    b";
        testReverseWords(str1);
        testReverseWords(str2);
        testReverseWords(str3);
    }
}
