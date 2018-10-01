/**
 * Leetcode - minimum_ascii_delete_sum
 */
package com.ciaoshen.leetcode.minimum_ascii_delete_sum;
import java.util.*;
import com.ciaoshen.leetcode.util.*;
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Solution1 implements Solution {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public int minimumDeleteSum(String s1, String s2) {
        Map<String, Integer> shorter = new HashMap<>();
        Map<String, Integer> longer = new HashMap<>();
        if (s1.length() >= s2.length()) {
            longer.put(s1, 0);
            shorter.put(s2, 0);
            longer = removeNChar(s1.length() - s2.length(), longer);
        } else {
            longer.put(s2, 0);
            shorter.put(s1, 0);
            longer = removeNChar(s2.length() - s1.length(), longer);
        }
        int min = Math.min(s1.length(), s2.length());
        boolean matched = false;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= min; i++) {
            if (log.isDebugEnabled()) {
                log.debug("Shorter = {}", shorter);
                log.debug("Longer = {}", longer);
            }
            for (Map.Entry<String, Integer> entry : shorter.entrySet()) {
                if (longer.containsKey(entry.getKey())) {
                    res = Math.min(res, entry.getValue() + longer.get(entry.getKey()));
                    matched = true;
                }
            }
            if (matched) return res;
            shorter = removeOneChar(shorter);
            longer = removeOneChar(longer);
        }
        return 0; // never reached
    }
    private Map<String, Integer> removeNChar(int n, Map<String, Integer> table) {
        for (int i = 0; i < n; i++) {
            table = removeOneChar(table);
        }
        return table;
    }
    private Map<String, Integer> removeOneChar(Map<String, Integer> table) {
        Map<String, Integer> newTable = new HashMap<>();
        for (Map.Entry<String, Integer> entry : table.entrySet()) {
            StringBuilder sb = new StringBuilder(entry.getKey());
            int sum = entry.getValue();
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                sb.delete(i, i + 1);
                if (!newTable.containsKey(sb.toString())) {
                    newTable.put(sb.toString(), sum + (int) c);
                }
                sb.insert(i, c);
            }
        }
        return newTable;
    }
}
