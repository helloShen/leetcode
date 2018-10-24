/**
 * Leetcode - minimum_absolute_difference_in_bst
 */
package com.ciaoshen.leetcode.minimum_absolute_difference_in_bst;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public int getMinimumDifference(TreeNode root);
    
}
