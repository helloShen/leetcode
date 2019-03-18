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
class Solution1 implements Solution {

    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d); // sort by lexical order
        Collections.sort(d, (String a, String b) -> b.length() - a.length()); // sort by string length
        for (String w : d) {
            if (canReductTo(s, w)) return w;
        }
        return "";
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
