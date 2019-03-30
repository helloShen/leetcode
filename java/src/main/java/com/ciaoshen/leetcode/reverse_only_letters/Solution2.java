/**
 * Leetcode - reverse_only_letters
 */
package com.ciaoshen.leetcode.reverse_only_letters;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution2 implements Solution {

    public String reverseOnlyLetters(String S) {
        washStr(S);
        StringBuilder reversed = cleanSb.reverse(); // 用Character自带的reverse()库函数
        for (int i = 0; i < poc; i++) {
            reversed.insert(otherCharsOffset[i], otherChars[i]);
        }
        return reversed.toString();
    }

    private StringBuilder cleanSb;
    private char[] otherChars;
    private int poc;
    private int[] otherCharsOffset;
    private int poco;

    private void washStr(String str) {
        cleanSb = new StringBuilder();
        int len = str.length();
        otherChars = new char[len];
        poc = 0;
        otherCharsOffset = new int[len];
        poco = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                cleanSb.append(c);
            } else {
                otherChars[poc++] = c;
                otherCharsOffset[poco++] = i;
            }
        }
    }

}
