/**
 * Leetcode - search_in_a_binary_search_tree
 */
package com.ciaoshen.leetcode.search_in_a_binary_search_tree;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public TreeNode searchBST(TreeNode root, int val);
    
}
