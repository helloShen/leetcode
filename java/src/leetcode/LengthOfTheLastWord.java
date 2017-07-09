/**
 * Leetcode - Algorithm - Length of the Last Word
 */
package com.ciaoshen.leetcode;
import java.util.*;

class LengthOfTheLastWord {

    public int lengthOfLastWordV1(String s) {
        boolean begin = false;
        int len = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            if (!begin && s.charAt(i) != ' ') { begin = true; }
            if (begin && s.charAt(i) != ' ') { len++; }
            if (begin && s.charAt(i) == ' ') { break; }
        }
        return len;
    }
    public int lengthOfLastWord(String s) {
        String trimed = s.trim();
        return trimed.length() - 1 - trimed.lastIndexOf(' ');
    }
    private static LengthOfTheLastWord test = new LengthOfTheLastWord();
    private static String[] strs = new String[] {
        "   ", "Hello Ronald", "Hello Ronald   ", "Hello"
    };
    private static void testLengthOfLastWord() {
        for (String str : strs) {
            System.out.println("Length of \"" + str + "\" = " + test.lengthOfLastWord(str));
        }
    }
    public static void main(String[] args) {
        testLengthOfLastWord();
    }
}
