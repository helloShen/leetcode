/**
 * Leetcode - sentence_similarity
 */
package com.ciaoshen.leetcode.sentence_similarity;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs);

}
