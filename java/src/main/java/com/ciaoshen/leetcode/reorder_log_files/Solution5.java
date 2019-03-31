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
class Solution5 implements Solution {

    public String[] reorderLogFiles(String[] logs) {
        List<String> letterLogs = new LinkedList<String>();
        List<String> digitLogs = new LinkedList<String>();
        for (String log : logs) {
            if (Character.isDigit(log.charAt(log.indexOf(' ') + 1))) {
                digitLogs.add(log);
            } else {
                letterLogs.add(log);
            }
        }
        // lambda表达式严重影响效率
        // Collections.sort(letterLogs, (String a, String b) -> (a.substring(a.indexOf(' ') + 1)).compareTo(b.substring(b.indexOf(' ') + 1)));
        Collections.sort(letterLogs, new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return (a.substring(a.indexOf(' ') + 1)).compareTo(b.substring(b.indexOf(' ') + 1));
            }
        });
        letterLogs.addAll(digitLogs);
        String[] res = new String[logs.length];
        return letterLogs.toArray(res);
    }

}
