/**
 * Leetcode - to_lower_case
 */
package com.ciaoshen.leetcode.to_lower_case;
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

    public String toLowerCase(String str) {
        char[] sa = str.toCharArray();
        for (int i = 0; i < sa.length; i++) {
            if (sa[i] >= 'A' && sa[i] <= 'Z') sa[i] += 32;
        }
        return new String(sa);
    }

}
