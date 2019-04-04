/**
 * Leetcode - valid_tic_tac_toe_state
 */
package com.ciaoshen.leetcode.valid_tic_tac_toe_state;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean validTicTacToe(String[] board);
    
}
