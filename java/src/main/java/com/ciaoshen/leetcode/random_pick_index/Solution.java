/**
 * Leetcode - random_pick_index
 */
package com.ciaoshen.leetcode.random_pick_index;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public void init(int[] nums);
    
    public int pick(int target);

}
