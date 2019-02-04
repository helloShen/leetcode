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
class Solution1 implements Solution {

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder(S);
        List<int[]> offsets = new LinkedList<>();
        for (int i = 0; i < indexes.length; i++) {
            int offset = 0;
            for (int[] pair : offsets) {
                if (indexes[i] > pair[0]) offset += pair[1];
            }
            // if (log.isDebugEnabled()) {
            //     log.debug("\"{}\" replaced by \"{}\", should consider offset = {}", sources[i], targets[i], offset);
            // }
            StringBuilder newSb = replaceEachString(sb, indexes[i] + offset, sources[i], targets[i]);
            if (newSb != null) {
                sb = newSb;
                int newOffset = targets[i].length() - sources[i].length();
                // if (log.isDebugEnabled()) {
                //     log.debug("\"{}\" replaced by \"{}\", new offset = {}", sources[i], targets[i], newOffset);
                // }
                offsets.add(new int[]{indexes[i], newOffset});
                // if (log.isDebugEnabled()) {
                //     for (int[] pair : offsets) {
                //         log.debug("offsets -> [{},{}]", pair[0], pair[1]);
                //     }
                // }
            }
            // if (log.isDebugEnabled()) {
            //     log.debug("string becomes {}", sb);
            // }
        }
        return new String(sb);
    }

    // return index of the end of target in original string, return -1 if not valid
    private int check(StringBuilder orig, int begin, String target) {
        int p = begin;
        for (int i = 0; i < target.length(); i++, p++) {
            // if (log.isDebugEnabled()) {
            //     if (p >= orig.length()) {
            //         log.debug("{} out of range!", target);
            //     } else if (orig.charAt(p) != target.charAt(i)) {
            //         log.debug("{} not matched at {}", target, p);
            //     }
            // }
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
