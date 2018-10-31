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
class Solution1 implements Solution {

    private final String SEP = ":";
    private class Time {
        private int[] t;
        private Time(int id, String s) {
            t = new int[7];
            String[] segs = s.split(SEP);
            for (int i = 0; i < 6; i++) {
                t[i] = Integer.parseInt(segs[i]);
            }
            t[6] = id;
        }
        private int compareTo(Time another, int depth) {
            for (int i = 0; i <= depth; i++) {
                if (t[i] != (another.t)[i]) return t[i] - (another.t)[i];
            }
            return 0;
        }
    }

    private final int MAX = 300;
    private Time[] times;
    private int size;

    public Solution1() {
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
            if (t.compareTo(ts, depth) >= 0 && t.compareTo(te, depth) <= 0) res.add(t.t[6]);
        }
        return res;
    }

    private int depth(String gra) {
        switch(gra) {
            case "Year":
                return 0;
            case "Month":
                return 1;
            case "Day":
                return 2;
            case "Hour":
                return 3;
            case "Minute":
                return 4;
            case "Second":
                return 5;
            default:
                return -1;
        }
    }
}
