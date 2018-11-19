/**
 * Leetcode - partition_to_k_equal_sum_subsets
 */
package com.ciaoshen.leetcode.partition_to_k_equal_sum_subsets;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean canPartitionKSubsets(int[] nums, int k);
        
}
