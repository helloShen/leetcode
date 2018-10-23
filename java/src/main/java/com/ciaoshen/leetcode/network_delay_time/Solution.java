/**
 * Leetcode - network_delay_time
 */
package com.ciaoshen.leetcode.network_delay_time;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public int networkDelayTime(int[][] times, int N, int K);
    
}
