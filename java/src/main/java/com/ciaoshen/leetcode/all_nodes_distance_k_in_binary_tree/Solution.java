/**
 * Leetcode - all_nodes_distance_k_in_binary_tree
 */
package com.ciaoshen.leetcode.all_nodes_distance_k_in_binary_tree;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K);

}
