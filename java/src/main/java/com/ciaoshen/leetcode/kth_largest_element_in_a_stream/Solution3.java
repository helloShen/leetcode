/**
 * Leetcode - kth_largest_element_in_a_stream
 */
package com.ciaoshen.leetcode.kth_largest_element_in_a_stream;
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
class Solution3 implements Solution {

    private int k;
    private Queue<Integer> queue;

    public void init(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<Integer>(k);
        for (int n : nums) add(n);
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.offer(val);
        } else if (queue.peek() < val) {
            queue.poll();
            queue.offer(val);
        }
        return (queue.size() == k)? queue.peek() : 0;
    }

}
