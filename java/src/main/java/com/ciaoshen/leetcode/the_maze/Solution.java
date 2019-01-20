/**
 * Leetcode - the_maze
 */
package com.ciaoshen.leetcode.the_maze;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean hasPath(int[][] maze, int[] start, int[] destination);
    
}
