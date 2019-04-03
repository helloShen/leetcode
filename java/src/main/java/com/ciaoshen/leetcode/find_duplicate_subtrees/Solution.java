/**
 * Leetcode - find_duplicate_subtrees
 */
package com.ciaoshen.leetcode.find_duplicate_subtrees;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<TreeNode> findDuplicateSubtrees(TreeNode root);
    
}
