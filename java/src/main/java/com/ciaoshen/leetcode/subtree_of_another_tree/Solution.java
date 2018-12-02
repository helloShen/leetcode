
/**
 * Leetcode - subtree_of_another_tree
 */
package com.ciaoshen.leetcode.subtree_of_another_tree;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean isSubtree(TreeNode s, TreeNode t);
    
}
