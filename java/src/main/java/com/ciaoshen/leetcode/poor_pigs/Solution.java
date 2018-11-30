/**
 * Leetcode - poor_pigs
 */
package com.ciaoshen.leetcode.poor_pigs;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest);
    
}
