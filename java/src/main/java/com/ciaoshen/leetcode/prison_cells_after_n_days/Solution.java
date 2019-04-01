/**
 * Leetcode - prison_cells_after_n_days
 */
package com.ciaoshen.leetcode.prison_cells_after_n_days;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public int[] prisonAfterNDays(int[] cells, int N);

}
