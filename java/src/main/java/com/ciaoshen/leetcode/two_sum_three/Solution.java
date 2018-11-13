/**
 * Leetcode - two_sum_three
 */
package com.ciaoshen.leetcode.two_sum_three;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public void init();
    
    public void add(int number);

    public boolean find(int value);

}
