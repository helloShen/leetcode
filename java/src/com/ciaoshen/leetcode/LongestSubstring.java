/**
 * Leetcode - Longest Substring Without Repeating Characters
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.lang.Math;

public class LongestSubstring {
    public static int lengthOfLongestSubstringNaive(String s) {
        char[] chars = s.toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        int maxSize = 0;
        outerIter:
        for (int i = 0; i < chars.length; i++) {
            innerIter:
            for (int j = i; j < chars.length; j++) {
                if (charSet.contains(chars[j])) {
                    // System.out.println("Size = " + maxSize + ": " + charSet + " " + chars[j] + " repeat!");
                    if (charSet.size() > maxSize) {
                        maxSize = charSet.size();
                    }
                    charSet.clear();
                    break innerIter;
                }
                charSet.add(chars[j]);
            }
            if (charSet.size() > maxSize) {
                maxSize = charSet.size();
            }
            charSet.clear();
        }
        return maxSize;
    }
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Queue<Character> charQueue = new LinkedList<>();
        int maxSize = 0;
        int cursorJ = 0;
        outerFor:
        for (int i = 0; i < chars.length; i++) {
            charQueue.poll(); // poll don't throw Exception if it's empty.
            innerWhile:
            while (true) {
                if (cursorJ == chars.length) { // finish all work
                    break outerFor;
                }
                if (charQueue.contains(chars[cursorJ])) {
                    break innerWhile;
                }
                charQueue.offer(chars[cursorJ]);
                cursorJ++;
            }
            if (charQueue.size() > maxSize) {
                maxSize = charQueue.size();
            }
        }
        if (charQueue.size() > maxSize) {
            maxSize = charQueue.size();
        }
        return maxSize;
    }
    public static int lengthOfLongestSubstringV3(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> charQueue = new LinkedList<>();
        int maxSize = 0;
        int cursorJ = 0;
        outerFor:
        for (int i = 0; i < chars.length; i++) {
            charQueue.poll(); // poll don't throw Exception if it's empty.
            int offset = 0; //重复字符在LinkedList里的偏移值
            innerWhile:
            while (true) {
                if (cursorJ == chars.length) { // 出口1，遍历完成，结束所有工作
                    break outerFor;
                }
                if (charQueue.contains(chars[cursorJ])) { // 出口2，出现重复字符
                    offset = charQueue.indexOf(chars[cursorJ]); // 跳过重复字符前的所有字符
                    break innerWhile;
                }
                charQueue.offer(chars[cursorJ]);
                cursorJ++;
            }
            if (charQueue.size() > maxSize) { // 每次循环之后结算
                maxSize = charQueue.size();
            }
            // 2号出口出来到这里。跳过重复字符之前的所有字符
            if (offset > 0) {
                i += offset;
                for (int x =0; x < offset; x++) {
                    charQueue.poll();
                }
            }
        }
        // 1号出口出来到这里。
        if (charQueue.size() > maxSize) { // 返回前再结算一次。
            maxSize = charQueue.size();
        }
        return maxSize;
    }
    public static int lengthOfLongestSubstringV4(String s) {
        int fastCursor = 0;
        int slowCursor = 0;
        Set<Character> set = new HashSet<>();
        int max = 0;
        while (fastCursor < s.length()) {
            if (!set.contains(s.charAt(fastCursor))) {
                set.add(s.charAt(fastCursor++));
                max = Math.max(max,set.size());
            } else {
                set.remove(s.charAt(slowCursor++));
            }
        }
        return max;
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(new String[] {"abcabcbb","bbbbb","pwwkew","c","qrsvbspk","abcabcbbbbbbbpwwkewcqrsvbspkThe character with hexadecimal value 0xh...h"}));
        for (String str : list) {
            System.out.println(str + ": " + lengthOfLongestSubstringV4(str));
        }
    }
}
