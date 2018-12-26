/**
 * Leetcode - ip_to_cidr
 */
package com.ciaoshen.leetcode.ip_to_cidr;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<String> ipToCIDR(String ip, int n);
        
}
