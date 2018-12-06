/**
 * Leetcode - knight_probability_in_chessboard
 */
package com.ciaoshen.leetcode.knight_probability_in_chessboard;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public double knightProbability(int N, int K, int r, int c);
    
}
