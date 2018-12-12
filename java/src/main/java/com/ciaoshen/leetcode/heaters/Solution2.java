/**
 * Leetcode - heaters
 */
package com.ciaoshen.leetcode.heaters;
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

    public int findRadius(int[] houses, int[] heaters) {
        if (houses.length == 0 || heaters.length == 0) return 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        for (int house : houses) {
            int idx = Arrays.binarySearch(heaters, house);
            if (idx >= 0) continue;
            idx = - (idx + 1);
            if (log.isDebugEnabled()) {
                log.debug("House {} should insert at {} in heater array.", house, idx);
            }
            int leftRadius = (idx == 0)? -1 : house - heaters[idx - 1];
            int rightRadius = (idx == heaters.length)? -1 : heaters[idx] - house;
            if (log.isDebugEnabled()) {
                log.debug("So left radius = {}, right radius = {}", leftRadius, rightRadius);
            }
            if (leftRadius == -1) {
                radius = Math.max(radius, rightRadius);
                continue;
            }
            if (rightRadius == -1) {
                radius = Math.max(radius, leftRadius);
                continue;
            }
            int currRadius = Math.min(leftRadius, rightRadius);
            radius = Math.max(radius, currRadius);
        }
        return radius;
    }

}
