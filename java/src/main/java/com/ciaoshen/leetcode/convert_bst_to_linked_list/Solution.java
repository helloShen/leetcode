/**
 * Leetcode - convert_bst_to_linked_list
 */
package com.ciaoshen.leetcode.convert_bst_to_linked_list;

import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public Node treeToDoublyList(Node root);

}
