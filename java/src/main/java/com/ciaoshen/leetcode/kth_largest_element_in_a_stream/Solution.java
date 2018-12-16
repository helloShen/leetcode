/**
 * Leetcode - kth_largest_element_in_a_stream
 */
package com.ciaoshen.leetcode.kth_largest_element_in_a_stream;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public void init(int k, int[] nums);

    public int add(int val);

}
