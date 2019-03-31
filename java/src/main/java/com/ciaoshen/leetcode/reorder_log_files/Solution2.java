/**
 * Leetcode - reorder_log_files
 */
package com.ciaoshen.leetcode.reorder_log_files;
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

    public String[] reorderLogFiles(String[] logs) {
        parseLogs(logs);
        Arrays.sort(letterLogs, 0, pl, (String[] a, String[] b) -> {
            int pa = 1, pb = 1;
            while (pa < a.length && pb < b.length) {
                String sa = a[pa++];
                String sb = b[pb++];
                int diff = sa.compareTo(sb);
                if (diff != 0) return diff;
            }
            if (pa < a.length) return 1;
            if (pb < b.length) return -1;
            return a[0].compareTo(b[0]);
        });
        String[] res = new String[logs.length];
        int p = 0;
        for (int i = 0; i < pl; i++) {
            res[p++] = merge(letterLogs[i]);
        }
        for (int i = 0; i < pd; i++) {
            res[p++] = digitLogs[i];
        }
        return res;
    }

    private String[][] letterLogs;
    private String[] digitLogs;
    private int pl, pd;

    private void parseLogs(String[] logs) {
        letterLogs = new String[100][];
        digitLogs = new String[100];
        pl = 0; pd = 0;
        for (String log : logs) {
            String[] segs = log.split(" ");
            if (Character.isDigit(segs[1].charAt(0))) {
                digitLogs[pd++] = log;
            } else {
                letterLogs[pl++] = segs;
            }
        }
    }

    private String merge(String[] segs) {
        StringBuilder sb = new StringBuilder();
        for (String seg : segs) sb.append(seg + " ");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
