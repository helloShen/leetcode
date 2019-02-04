/**
 * Leetcode - find_and_replace_in_string
 */
package com.ciaoshen.leetcode.find_and_replace_in_string;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets);
    
}
