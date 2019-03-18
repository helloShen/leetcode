/**
 * Leetcode - longest_word_in_dictionary_through_deletion
 */
package com.ciaoshen.leetcode.longest_word_in_dictionary_through_deletion;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public String findLongestWord(String s, List<String> d);
    
}
