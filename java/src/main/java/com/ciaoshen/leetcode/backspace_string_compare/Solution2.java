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
class Solution2 implements Solution {

    public boolean backspaceCompare(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int sp = decode(s);
        int tp = decode(t);
        if (sp != tp) return false;
        while (sp > 0) {
            if (s[--sp] != t[--tp]) return false;
        }
        return true;
    }

    private int decode(char[] s) {
        int p = 0;
        for (int i = 0; i < s.length; i++) {
            switch(s[i]) {
                case '#':
                    if (p > 0) p--;
                    break;
                default:
                    s[p++] = s[i];
                    break;
            }
        }
        return p;
    }

}
