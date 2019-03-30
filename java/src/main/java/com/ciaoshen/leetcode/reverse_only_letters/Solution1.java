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
class Solution1 implements Solution {

    public String reverseOnlyLetters(String S) {
        washStr(S);
        StringBuilder reversed = reverse(cleanSb);
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

    // 自己写递归反转
    // assertion: sb != null
    private StringBuilder reverse(StringBuilder sb) {
        int len = sb.length();
        if (len == 1) return sb;
        if (len == 2) {
            char last = sb.charAt(1);
            sb.deleteCharAt(1);
            sb.insert(0, last);
            return sb;
        }
        int mid = (sb.length() - 1) / 2;
        StringBuilder left = reverse(new StringBuilder(sb.substring(0, mid + 1)));
        StringBuilder right = reverse(new StringBuilder(sb.substring(mid + 1)));
        return right.append(left);
    }

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
