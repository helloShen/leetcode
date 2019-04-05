/**
 * Leetcode - design_circular_queue
 */
package com.ciaoshen.leetcode.design_circular_queue;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    /** perform as constructor */
    public void init(int k);

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value);

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue();

    /** Get the front item from the queue. */
    public int Front();

    /** Get the last item from the queue. */
    public int Rear();

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty();

    /** Checks whether the circular queue is full or not. */
    public boolean isFull();

}
