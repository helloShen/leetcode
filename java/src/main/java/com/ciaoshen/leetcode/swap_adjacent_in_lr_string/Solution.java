/**
 * Leetcode - swap_adjacent_in_lr_string
 */
package com.ciaoshen.leetcode.swap_adjacent_in_lr_string;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean canTransform(String start, String end);
    
}
