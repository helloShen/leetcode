/**
 * Leetcode - solve_the_equation
 */
package com.ciaoshen.leetcode.solve_the_equation;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Solution {

    // use this Object to print the log (call from slf4j facade)
    static Logger log = LoggerFactory.getLogger(Solution.class);
    
    public String solveEquation(String equation);

}
