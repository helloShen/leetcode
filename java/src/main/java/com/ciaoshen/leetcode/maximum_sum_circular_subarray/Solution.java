/**
 * Leetcode - maximum_sum_circular_subarray
 */
package com.ciaoshen.leetcode.maximum_sum_circular_subarray;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public int maxSubarraySumCircular(int[] A);
    
}
