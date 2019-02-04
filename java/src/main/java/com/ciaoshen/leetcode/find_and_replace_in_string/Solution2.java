/**
 * Leetcode - find_and_replace_in_string
 */
package com.ciaoshen.leetcode.find_and_replace_in_string;
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

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder(S);
        int[] offsets = new int[S.length()];
        Arrays.fill(offsets, 1001);
        for (int i = 0; i < indexes.length; i++) {
            int offset = 0;
            for (int idx = 0; idx < offsets.length; idx++) {
                if (offsets[idx] < 1001 && indexes[i] > idx) offset += offsets[idx];
            }
            StringBuilder newSb = replaceEachString(sb, indexes[i] + offset, sources[i], targets[i]);
            if (newSb != null) {
                sb = newSb;
                int newOffset = targets[i].length() - sources[i].length();
                offsets[indexes[i]] = newOffset;
            }
            // if (log.isDebugEnabled()) {
            //     log.debug("offsets = {}", Arrays.toString(offsets));
            // }
        }
        return new String(sb);
    }

    // return index of the end of target in original string, return -1 if not valid
    private int check(StringBuilder orig, int begin, String target) {
        int p = begin;
        for (int i = 0; i < target.length(); i++, p++) {
            if (p >= orig.length() || orig.charAt(p) != target.charAt(i)) return -1;
        }
        return p;
    }

    private StringBuilder replaceEachString(StringBuilder sb, int begin, String source, String target) {
        int end = check(sb, begin, source);
        if (end >= 0) {
            StringBuilder res = new StringBuilder();
            res.append(sb.substring(0, begin));
            res.append(target);
            res.append(sb.substring(end));
            return res;
        }
        return null;
    }

}
