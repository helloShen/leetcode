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
class Solution2 implements Solution {

    public String findLongestWord(String s, List<String> d) {
        String[] da = new String[d.size()];
        da = d.toArray(da);
        // if (log.isDebugEnabled()) {
        //     log.debug("Dictionary Array = {}", Arrays.toString(da));
        // }
        Arrays.sort(da); // sort by lexical order
        // if (log.isDebugEnabled()) {
        //     log.debug("After sortByLexicalOrder() = {}", Arrays.toString(da));
        // }
        Arrays.sort(da, (String a, String b) -> b.length() - a.length()); // sort by string length
        // if (log.isDebugEnabled()) {
        //     log.debug("After sortByStringLength() = {}", Arrays.toString(da));
        // }
        for (int i = 0; i < d.size(); i++) {
            String w = da[i];
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
