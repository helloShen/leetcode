/**
 * Leetcode - reorganize_string
 */
package com.ciaoshen.leetcode.reorganize_string;
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

    public String reorganizeString(String S) {
        int size = S.length();
        if (size < 2) return S;
        int[][] counts = new int[26][2];
        for (int i = 0; i < 26; i++) {
            counts[i][1] = i + 'a';
        }
        char[] cs = S.toCharArray();
        for (char c : cs) {
            counts[c - 'a'][0]++;
        }
        Arrays.sort(counts, (int[] a, int[] b) -> b[0] - a[0]);
        if (log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append("[" + String.valueOf(counts[i][0]) + "," + (char)counts[i][1] + "]");
                if (i < 26) sb.append(", ");
            }
            log.debug("counts = {}", sb.toString());
        }
        int max = counts[0][0];
        if (log.isDebugEnabled()) {
            log.debug("max = {}, size = {}", max, size);
        }
        if (max > (size + 1) / 2) return "";
        char[] arr = new char[size];
        int idx = 0;
        char c = (char) counts[idx][1];
        int count = counts[idx][0];
        boolean repeat = false;
        for (int i = 0; i < size; i += 2) {
            if (count == 0) {
                c = (char) counts[++idx][1];
                count = counts[idx][0];
            }
            if (log.isDebugEnabled()) {
                log.debug("fill {}, and it remains {} times", c, count - 1);
            }
            arr[i] = c;
            count--;
            if (!repeat && i + 2 >= size) {
                i = -1;
                repeat = true;
            }
        }
        return new String(arr);
    }

}
