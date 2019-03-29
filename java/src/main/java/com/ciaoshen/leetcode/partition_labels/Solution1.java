/**
 * Leetcode - partition_labels
 */
package com.ciaoshen.leetcode.partition_labels;
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

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[] table = parse(S);
        int p = 0, size = S.length();
        while (p < size) {
            int start = p, scope = p;
            while (p < size && p <= scope) {
                int newScope = table[S.charAt(p) - 'a'];
                if (newScope > scope) scope = newScope;
                p++;
            }
            res.add(p - start);
        }
        return res;
    }

    private int[] parse(String S) {
        int[] table = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            table[c - 'a'] = i;
        }
        return table;
    }

}
