/**
 * Leetcode - card_flipping_game
 */
package com.ciaoshen.leetcode.card_flipping_game;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public int flipgame(int[] fronts, int[] backs);
    
}
