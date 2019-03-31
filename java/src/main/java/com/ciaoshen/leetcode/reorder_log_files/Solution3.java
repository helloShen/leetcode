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
class Solution3 implements Solution {

    public String[] reorderLogFiles(String[] logs) {
        String[] letterLogs = new String[100];
        String[] digitLogs = new String[100];
        int pl = 0, pd = 0;
        for (String log : logs) {
            String[] segs = log.split(" ");
            if (Character.isDigit(segs[1].charAt(0))) {
                digitLogs[pd++] = log;
            } else {
                letterLogs[pl++] = log;
            }
        }
        Arrays.sort(letterLogs, 0, pl, (String a, String b) -> {
            int pa = a.indexOf(' ') + 1;
            int pb = b.indexOf(' ') + 1;
            int nextSpaceA = a.indexOf(' ', pa);
            int nextSpaceB = b.indexOf(' ', pb);
            while (nextSpaceA >= 0 && nextSpaceB >= 0) {
                String nextA = a.substring(pa, nextSpaceA);
                String nextB = b.substring(pb, nextSpaceB);
                int diff = nextA.compareTo(nextB);
                if (diff != 0) return diff;
                pa = nextSpaceA + 1;
                pb = nextSpaceB + 1;
                nextSpaceA = a.indexOf(' ', pa);
                nextSpaceB = b.indexOf(' ', pb);
            }
            String nextA = (nextSpaceA >= 0)? a.substring(pa, nextSpaceA) : a.substring(pa);
            String nextB = (nextSpaceB >= 0)? b.substring(pb, nextSpaceB) : b.substring(pb);
            int diff = nextA.compareTo(nextB);
            if (diff != 0) return diff;
            if (nextSpaceA >= 0) return 1;
            if (nextSpaceB >= 0) return -1;
            return 0;
        });
        String[] res = new String[logs.length];
        int p = 0;
        for (int i = 0; i < pl; i++) {
            res[p++] = letterLogs[i];
        }
        for (int i = 0; i < pd; i++) {
            res[p++] = digitLogs[i];
        }
        return res;
    }

}
