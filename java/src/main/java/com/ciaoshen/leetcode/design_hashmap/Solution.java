/**
 * Leetcode - design_hashmap
 */
package com.ciaoshen.leetcode.design_hashmap;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public void init();
    public void put(int key, int value);
    public int get(int key);
    public void remove(int key);

}
