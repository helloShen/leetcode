/**
 * Leetcode - two_sum_three
 */
package com.ciaoshen.leetcode.two_sum_three;
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

    private Map<Integer, Integer> table;

    public void init() {
        table = new HashMap<Integer, Integer>();
    }

    public void add(int number) {
        Integer prevTimes = table.get(number);
        table.put(number, (prevTimes == null)? 1 : prevTimes + 1);
    }

    public boolean find(int value) {
        Iterator<Map.Entry<Integer, Integer>> ite = table.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<Integer, Integer> next = ite.next();
            int num = next.getKey();
            int target = value - num;
            if (target == num) {
                if (next.getValue() > 1) return true;
            } else {
                if (table.containsKey(target)) return true;
            }
        }
        return false;
    }

}
