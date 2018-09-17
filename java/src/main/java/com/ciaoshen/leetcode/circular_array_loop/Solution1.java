/**
 * Leetcode - circular_array_loop
 */
package com.ciaoshen.leetcode.circular_array_loop;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * fast & slow pointer version
 * 
 * O(n) in time
 * O(1) in space
 */
class Solution1 implements Solution {
    public boolean circularArrayLoop(int[] nums) {
        localNums = nums;
        size = nums.length;
        isForward = (nums[0] > 0);
        int slow = 0, fast = 0;
        do {
            for (int i = 0; i < 2; i++) {
                if (isCrossward(fast)) return false;
                int next = skip(fast);
                if (next == fast) return false; // one node loop is not allowed
                fast = next;
            }
            slow = skip(slow);
        } while (slow != fast);
        return fast == 0;
    }

    // environment
    private int[] localNums;
    private int size;
    private boolean isForward;

    private int skip(int index) {
        index += localNums[index];
        if (isForward && index >= size) {
            index %= size;
        } else if (!isForward && index < 0) {
            index = index % size + size;
        }
        return index;
    }        
    private boolean isCrossward(int index) {
        return (isForward && localNums[index] < 0) || (!isForward && localNums[index] > 0);
    }
}
