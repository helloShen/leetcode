/**
 * Leetcode - minimum_time_difference
 */
package com.ciaoshen.leetcode.minimum_time_difference;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {

    private static final int SIZE = 1440;
    private static final int HALF = 719;

    /** time gap is not order sensitive */
    public int findMinDifference(List<String> timePoints) {
        boolean[] timeTable = new boolean[SIZE];
        for (String s : timePoints) {
            int time = timeToInt(s);
            if (timeTable[time]) return 0;
            timeTable[time] = true;
        }
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < SIZE; i++) {
            if (timeTable[i]) {
                int forward = (i + 1) % SIZE;
                int backward = (i - 1);
                if (backward < 0) backward += SIZE;
                int gap = 1;
                while (gap < minGap && !timeTable[forward] && !timeTable[backward]) {
                    forward++; backward--;
                    if (forward >= SIZE) forward %= forward;
                    if (backward < 0) backward += SIZE;
                    gap++;
                }
                minGap = Math.min(minGap, gap);
            }
        }
        return minGap;
    }

    private int timeToInt(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }

}
