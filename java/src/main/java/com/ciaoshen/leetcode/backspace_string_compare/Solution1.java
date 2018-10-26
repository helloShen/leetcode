/**
 * Leetcode - backspace_string_compare
 */
package com.ciaoshen.leetcode.backspace_string_compare;
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

    public boolean backspaceCompare(String S, String T) {
        return decode(S).equals(decode(T));
    }

    private String decode(String s) {
        char[] stack = new char[200];
        int p = 0;
        for (char c : s.toCharArray()) {
            switch(c) {
                case '#':
                    if (p >0) p--;
                    break;
                default:
                    stack[p++] = c;
                    break;
            }
        }
        return new String(stack, 0, p);
    }

}
