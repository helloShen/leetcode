/**
 * Leetcode - valid_parenthesis_string
 */
package com.ciaoshen.leetcode.valid_parenthesis_string;
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

    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    private boolean check(String s, int start, int count) {
        if (start == s.length()) return count == 0;
        if (count < 0) return false;
        char c = s.charAt(start);
        if (c == '(') {
            return check(s, start + 1, count + 1);
        } else if (c == ')') {
            return check(s, start + 1, count - 1);
        } else { // c == '*'
            return check(s, start + 1, count + 1) || check(s, start + 1, count - 1) || check(s, start + 1, count);
        }
    }

}
