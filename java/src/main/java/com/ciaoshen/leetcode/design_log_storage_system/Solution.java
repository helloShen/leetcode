/**
 * Leetcode - design_log_storage_system
 */
package com.ciaoshen.leetcode.design_log_storage_system;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public void put(int id, String timestamp);

    public List<Integer> retrieve(String s, String e, String gra);

}
