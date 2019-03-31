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
class Solution4 implements Solution {

    public String[] reorderLogFiles(String[] logs) {
        String[] letterLogs = new String[100];
        String[] digitLogs = new String[100];
        int pl = 0, pd = 0;
        for (String log : logs) {
            if (Character.isDigit(log.charAt(log.indexOf(' ') + 1))) {
                digitLogs[pd++] = log;
            } else {
                letterLogs[pl++] = log;
            }
        }
        // lambda表达式效率很低
        // Arrays.sort(letterLogs, 0, pl, (String a, String b) -> {
        //     int pa = a.indexOf(' ') + 1;
        //     int pb = b.indexOf(' ') + 1;
        //     return a.substring(pa).compareTo(b.substring(pb));
        // });
        Arrays.sort(letterLogs, 0, pl, new Comparator<String>(){
            public int compare(String a, String b) {
                int pa = a.indexOf(' ') + 1;
                int pb = b.indexOf(' ') + 1;
                return a.substring(pa).compareTo(b.substring(pb));
            }
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
