/**
 * Leetcode - kill_process
 */
package com.ciaoshen.leetcode.kill_process;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill);
    
}
