/**
 * Leetcode - rectangle_overlap
 */
package com.ciaoshen.leetcode.rectangle_overlap;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean isRectangleOverlap(int[] rec1, int[] rec2);
    
}
