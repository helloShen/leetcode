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
class Solution1 implements Solution {

    public String[] reorderLogFiles(String[] logs) {
        String[][][] mySegs = parseLogs(logs);
        Arrays.sort(mySegs[0], (String[] a, String[]b) -> {
            int pa = 1, pb = 1;
            while (pa < a.length && pb < b.length) {
                String sa = a[pa++];
                String sb = b[pb++];
                if (!sa.equals(sb)) return sa.compareTo(sb);
            }
            if (pa < a.length) return 1;
            if (pb < b.length) return -1;
            return a[0].compareTo(b[0]);
        });
        String[] res = new String[logs.length];
        int p = 0;
        for (String[][] letterOrDigit : mySegs) {
            for (String[] segs : letterOrDigit) {
                res[p++] = merge(segs);
            }
        }
        return res;
    }

    private String[][][] parseLogs(String[] logs) {
        String[][] letterLogs = new String[100][];
        String[][] digitLogs = new String[100][];
        int pl = 0, pd = 0;
        for (String log : logs) {
            String[] segs = log.split(" ");
            if (Character.isDigit(segs[1].charAt(0))) {
                digitLogs[pd++] = segs;
            } else {
                letterLogs[pl++] = segs;
            }
        }
        String[][][] res = new String[2][][];
        res[0] = Arrays.copyOfRange(letterLogs, 0, pl);
        res[1] = Arrays.copyOfRange(digitLogs, 0, pd);
        return res;
    }

    private String merge(String[] segs) {
        StringBuilder sb = new StringBuilder();
        for (String seg : segs) sb.append(seg + " ");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
