/**
 * Leetcode - pacific_atlantic_water_flow
 */
package com.ciaoshen.leetcode.pacific_atlantic_water_flow;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<int[]> pacificAtlantic(int[][] matrix);
    
}
