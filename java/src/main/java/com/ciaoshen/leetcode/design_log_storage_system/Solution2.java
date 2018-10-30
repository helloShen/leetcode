/**
 * Leetcode - design_log_storage_system
 */
package com.ciaoshen.leetcode.design_log_storage_system;
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

    private class Time {
        int id;
        String timestamp;
        private Time(int id, String s) {
            this.id = id;
            timestamp = s;
        }
        private int compareTo(Time another, int depth) {
            return timestamp.substring(0, depth).compareTo(another.timestamp.substring(0, depth));
        }
    }

    private final int MAX = 300;
    private Time[] times;
    private int size;

    public Solution2() {
        times = new Time[MAX];
        size = 0;
    }

    public void put(int id, String timestamp) {
        times[size++] = new Time(id, timestamp);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        Time ts = new Time(-1, s);
        Time te = new Time(-1, e);
        int depth = depth(gra);
        for (int i = 0; i < size; i++) {
            Time t = times[i];
            if (t.compareTo(ts, depth) >= 0 && t.compareTo(te, depth) <= 0) res.add(t.id);
        }
        return res;
    }

    private int depth(String gra) {
        switch(gra) {
            case "Year":
                return 4;
            case "Month":
                return 7;
            case "Day":
                return 10;
            case "Hour":
                return 13;
            case "Minute":
                return 16;
            case "Second":
                return 19;
            default:
                return -1;
        }
    }
}
