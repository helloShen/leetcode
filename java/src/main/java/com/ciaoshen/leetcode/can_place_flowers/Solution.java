/**
 * Leetcode - can_place_flowers
 */
package com.ciaoshen.leetcode.can_place_flowers;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);

    public boolean canPlaceFlowers(int[] flowerbed, int n);

}