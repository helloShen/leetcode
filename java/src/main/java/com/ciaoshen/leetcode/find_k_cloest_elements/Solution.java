/**
 * Leetcode - find_k_cloest_elements
 */
package com.ciaoshen.leetcode.find_k_cloest_elements;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<Integer> findClosestElements(int[] arr, int k, int x);

}
