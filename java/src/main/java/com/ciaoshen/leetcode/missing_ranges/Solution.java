/**
 * Leetcode - missing_ranges
 */
package com.ciaoshen.leetcode.missing_ranges;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<String> findMissingRanges(int[] nums, int lower, int upper);
    
}
