/**
 * Leetcode - continuous_subarray_sum
 */
package com.ciaoshen.leetcode.continuous_subarray_sum;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean checkSubarraySum(int[] nums, int k);

}
