/**
 * Leetcode - Implement strStr()
 */
package com.ciaoshen.leetcode;
import java.util.*;
import java.util.regex.*;

public class ImplementStrStr {
    public int strStrV1(String haystack, String needle) {
        Pattern p = Pattern.compile(needle);
        Matcher m = p.matcher(haystack);
        return (m.find())? m.start() : -1;
    }


    public int strStrV2(String haystack, String needle) {
        if (haystack.isEmpty() && needle.isEmpty()) { return 0; }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.substring(i,i+needle.length()).equals(needle)) { return i; }
        }
        return -1;
    }

    public int strStrV3(String haystack, String needle) {
        if (haystack.isEmpty() && needle.isEmpty()) { return 0; }
        char[] stackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();
        for (int i = 0; i < stackArray.length - needleArray.length + 1; i++) {
            int cursorNeedle = 0;
            while (cursorNeedle < needleArray.length && stackArray[i+cursorNeedle] == needleArray[cursorNeedle]) {
                cursorNeedle++;
            }
            if (cursorNeedle == needleArray.length) { return i; }
        }
        return -1;
    }

    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) { return 0; }
        for (int i = 0; i < haystack.length() - needle.length() +1; i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (j == needle.length()-1 && haystack.charAt(i+j) == needle.charAt(j)) { return i; }
                if (haystack.charAt(i+j) != needle.charAt(j)) { break; }
            }
        }
        return -1;
    }
    private static void testStrStr() {
        ImplementStrStr test = new ImplementStrStr();
        String haystack1 = "fuigugnkszlfaaahjioweujfakwjlefgklhweqpoi";
        String needle1 = "aaa"; // answer: 12
        System.out.println(test.strStr(haystack1,needle1));
        String haystack2 = "";
        String needle2 = ""; // answer: 0
        System.out.println(test.strStr(haystack2,needle2));
        String haystack3 = "fuigugnkszlfhjioweujfakwjlefgklhweqpoi";
        String needle3 = "aaa"; // answer: -1
        System.out.println(test.strStr(haystack3,needle3));
    }
    public static void main(String[] args) {
        testStrStr();
    }
}
