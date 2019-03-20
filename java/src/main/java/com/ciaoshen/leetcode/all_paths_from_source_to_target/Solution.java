/**
 * Leetcode - all_paths_from_source_to_target
 */
package com.ciaoshen.leetcode.all_paths_from_source_to_target;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<List<Integer>> allPathsSourceTarget(int[][] graph);
    
}
