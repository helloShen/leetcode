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
class Solution2 implements Solution {

    private int k;
    private Queue<Integer> queue;

    public void init(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<Integer>((Integer a, Integer b) -> b - a);
        for (int n : nums) queue.add(n);
    }

    public int add(int val) {
        queue.add(val);
        if (queue.size() < k) return 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < k; i++) {
            stack.push(queue.poll());
        }
        int res = stack.peek();
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        return res;
    }

}
