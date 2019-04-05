/**
 * Leetcode - design_circular_queue
 */
package com.ciaoshen.leetcode.design_circular_queue;
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

    private int[] table;
    private int size, head, curr;
    private boolean isFull, isEmpty;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public void init(int k) {
        table = new int[k];
        size = k;
        head = 0;
        curr = 0;
        isFull = (k == 0)? true : false;
        isEmpty = true;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()) {
            table[curr++] = value;
            curr %= size;
            if (curr == head) isFull = true;
            isEmpty = false;
            return true;
        } else {
            return false;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (!isEmpty()) {
            head++;
            head %= size;
            if (curr == head) isEmpty = true;
            ifFull = false;
            return true;
        } else {
            return false;
        }
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) return -1;
        return table[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) return -1;
        int last = (curr - 1 + size) % size;
        return table[last];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return isEmpty;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return isFull;
    }

}
