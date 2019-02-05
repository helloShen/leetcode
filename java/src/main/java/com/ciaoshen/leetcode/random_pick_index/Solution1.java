/**
 * Leetcode - random_pick_index
 */
package com.ciaoshen.leetcode.random_pick_index;
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

    private Map<Integer, List<Integer>> posMap;
    private Random r;
    public void init(int[] nums) {
        posMap = new HashMap<Integer, List<Integer>>();
        r = new Random();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!posMap.containsKey(num)) posMap.put(num, new LinkedList<Integer>());
            posMap.get(num).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = posMap.get(target);
        return list.get(r.nextInt(list.size()));
    }

}
