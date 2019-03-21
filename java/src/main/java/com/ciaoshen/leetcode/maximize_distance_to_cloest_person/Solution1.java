/**
 * Leetcode - maximize_distance_to_cloest_person
 */
package com.ciaoshen.leetcode.maximize_distance_to_cloest_person;
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

    public int maxDistToClosest(int[] seats) {
        int[] triple = countConsecutiveZeros(seats);
        if (log.isDebugEnabled()) {
            log.debug("triple = {}", Arrays.toString(triple));
        }
        int max = (triple[1] + 1) / 2;
        return Math.max(max, Math.max(triple[0], triple[2]));
    }

    /**
     * return int[3] res such that:
     *  res[0]: length of leading consecutive zeros
     *  res[0]: maximum length of internal consecutive zeros
     *  res[2]: length of ending consecutive zeros
     */
    private int[] countConsecutiveZeros(int[] seats) {
        int[] res = new int[3];
        int head = 0;
        while (head < seats.length && seats[head] == 0) {
            res[0]++; head++;
        }
        int tail = seats.length - 1;
        while (tail >= 0 && seats[tail] == 0) {
            res[2]++; tail--;
        }
        int count = 0;
        for (int i = head; i <= tail; i++) {
            if (seats[i] == 1) {
                if (count > 0) {
                    res[1] = Math.max(res[1], count);
                    count = 0;
                }
            } else {
                count++;
            }
        }
        return res;
    }

}
