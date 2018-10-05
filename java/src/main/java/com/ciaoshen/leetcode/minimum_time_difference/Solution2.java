/**
 * Leetcode - minimum_time_difference
 */
package com.ciaoshen.leetcode.minimum_time_difference;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution2 implements Solution {

    private static final int SIZE = 1440;

    public int findMinDifference(List<String> timePoints) {
        boolean[] timeTable = new boolean[SIZE];
        for (String s : timePoints) {
            int time = timeToInt(s);
            if (timeTable[time]) return 0;
            timeTable[time] = true;
        }
        int first = Integer.MAX_VALUE, last = Integer.MIN_VALUE;
        int minGap = Integer.MAX_VALUE;
        int pre = 0;
        for (int i = 0; i < SIZE; i++) {
            if (timeTable[i]) {
                if (first != Integer.MAX_VALUE) minGap = Math.min(minGap, i - pre);
                first = Math.min(first, i);
                last = Math.max(last, i);
                pre = i;
            }
        }
        return Math.min(minGap, SIZE - (last - first));
    }

    private int timeToInt(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }

}
