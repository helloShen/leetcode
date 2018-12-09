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
class Solution1 implements Solution {

    public int findRadius(int[] houses, int[] heaters) {
        if (houses.length == 0 || heaters.length == 0) return 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        if (log.isDebugEnabled()) {
            log.debug("houses = {}", Arrays.toString(houses));
            log.debug("heaters = {}", Arrays.toString(heaters));
        }
        localHouses = houses;
        localHeaters = heaters;
        int radius = 0;
        if (houses[0] <= heaters[0]) {
            if (log.isDebugEnabled() && (heaters[0] - houses[0]) > radius) {
                log.debug("At left edge, radius update to {}", heaters[0] - houses[0]);
            }
            radius = heaters[0] - houses[0];
        }
        int lastHouse = houses[houses.length - 1];
        int lastHeater = heaters[heaters.length - 1];
        if (lastHouse >= lastHeater) {
            if (log.isDebugEnabled() && (lastHouse - lastHeater) > radius) {
                log.debug("At right edge, radius update to {}", lastHouse - lastHeater);
            }
            radius = Math.max(radius, lastHouse - lastHeater);
        }
        if (log.isDebugEnabled()) {
            log.debug("After two edge test, radius = {}", radius);
        }
        for (int i = 1; i < heaters.length; i++) {
            int lo = heaters[i - 1], hi = heaters[i];
            int lowerMid = lo + (hi - lo) / 2;
            if (log.isDebugEnabled()) {
                log.debug("lowerMid = {}", lowerMid);
            }
            int left = leftOfMid(lowerMid);
            if (left >= 0 && left > heaters[i - 1]) {
                if (log.isDebugEnabled() && (left - heaters[i - 1]) > radius) {
                    log.debug("Heater at {}, to cover left edge room is {}, raidus update to {}", heaters[i - 1], left, left - heaters[i - 1]);
                }
                radius = Math.max(radius, left - heaters[i - 1]);
            }
            int upperMid = lo + (hi - lo + 1) / 2;
            if (log.isDebugEnabled()) {
                log.debug("upperMid = {}", upperMid);
            }
            int right = rightOfMid(upperMid);
            if (right >= 0 && right < heaters[i]) {
                if (log.isDebugEnabled() && (heaters[i] - right) > radius) {
                    log.debug("Heater at {}, to cover left edge room is {}, raidus update to {}", heaters[i], right, heaters[i] - right);
                }
                radius = Math.max(radius, heaters[i] - right);
            }
            if (heaters[i] > houses[houses.length - 1]) break;
            if (log.isDebugEnabled()) {
                log.debug("After [{}, {}] test, radius = {}", heaters[i - 1], heaters[i], radius);
            }
        }
        return radius;
    }

    private int[] localHouses;
    private int[] localHeaters;

    /**
     * @param  mid  lower median index
     * @return      return the left room number if exists, otherwise return -1
     */
    private int leftOfMid(int mid) {
        int leftIdx = Arrays.binarySearch(localHouses, mid);
        if (log.isDebugEnabled()) {
            log.debug("For mid = {}, leftIdx = {}", mid, leftIdx);
        }
        if (leftIdx >= 0) return localHouses[leftIdx];
        leftIdx = - (leftIdx + 1);
        if (log.isDebugEnabled()) {
            log.debug("For mid = {}, return left room number {}", mid, (leftIdx > 0)? localHouses[leftIdx - 1] : -1);
        }
        return (leftIdx > 0)? localHouses[leftIdx - 1] : -1;
    }

    /**
     * @param  mid  upper meidan index
     * @return      return the right room number if exists, otherwise return -1
     */
    private int rightOfMid(int mid) {
        int rightIdx = Arrays.binarySearch(localHouses, mid);
        if (log.isDebugEnabled()) {
            log.debug("For mid = {}, rightIdx = {}", mid, rightIdx);
        }
        if (rightIdx >= 0) return (rightIdx == localHouses.length)? -1 : localHouses[rightIdx];
        rightIdx = - (rightIdx + 1);
        if (log.isDebugEnabled()) {
            log.debug("For mid = {}, return right room number {}", mid, (rightIdx == localHouses.length)? -1 : localHouses[rightIdx]);
        }
        return (rightIdx == localHouses.length)? -1 : localHouses[rightIdx];
    }

    /**
     * @param heater [room number of heater]
     * @param room [room number]
     * @return      minimum radius so that input heater can cover the given room
     */
    private int radius(int heater, int room) {
        return Math.abs(heater - room) + 1;
    }

}
