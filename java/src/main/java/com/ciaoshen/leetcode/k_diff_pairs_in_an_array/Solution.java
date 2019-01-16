/**
 * Leetcode - k_diff_pairs_in_an_array
 */
package com.ciaoshen.leetcode.k_diff_pairs_in_an_array;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public int findPairs(int[] nums, int k);
    
}
