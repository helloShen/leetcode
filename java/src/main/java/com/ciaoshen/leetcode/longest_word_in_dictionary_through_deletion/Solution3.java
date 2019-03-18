/**
 * Leetcode - longest_word_in_dictionary_through_deletion
 */
package com.ciaoshen.leetcode.longest_word_in_dictionary_through_deletion;
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
class Solution3 implements Solution {

    public String findLongestWord(String s, List<String> d) {
        int maxLen = 0;
        String maxString = "~"; // in ascii table "~" is larger than "a"
        for (String w : d) {
            if ((w.length() > maxLen || (w.length() == maxLen && w.compareTo(maxString) < 0)) && canReductTo(s, w)) {
                maxLen = w.length();
                maxString = w;
            }
        }
        return (maxString.equals("~"))? "" : maxString;
    }

    private boolean canReductTo(String from, String to) {
        int pf = 0, pt = 0;
        int lf = from.length(), lt = to.length();
        while (pt < lt) {
            char ct = to.charAt(pt);
            while (pf < lf && from.charAt(pf) != ct) pf++;
            if (pf == lf) return false;
            pf++; pt++;
        }
        return true;
    }

}
