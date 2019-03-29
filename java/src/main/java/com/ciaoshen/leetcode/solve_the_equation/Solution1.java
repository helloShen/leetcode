/**
 * Leetcode - solve_the_equation
 */
package com.ciaoshen.leetcode.solve_the_equation;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution1 implements Solution {

    public String solveEquation(String equation) {
        int[][] coefficients = new int[2][2];
        String[] twoParts = equation.split("=");
        // if (log.isDebugEnabled()) {
        //     log.debug("size of twoParts = {}, twoParts = {}", twoParts.length, Arrays.toString(twoParts));
        // }
        for (int i = 0; i < 2; i++) coefficients[i] = parseHalfEquation(twoParts[i]);
        // if (log.isDebugEnabled()) {
        //     log.debug("{}x {} {} = {}x {} {}", coefficients[0][0], (coefficients[0][1] >= 0)? "+" : "-", coefficients[0][1],
        //         coefficients[1][0], (coefficients[1][1] >= 0)? "+" : "-", coefficients[1][1]);
        // }
        int coeffX = coefficients[0][0] - coefficients[1][0];
        int coeffConst = coefficients[1][1] - coefficients[0][1];
        if (coeffX == 0) {
            return (coeffConst == 0)? "Infinite solutions" : "No solution";
        } else {
            return "x=" + (coeffConst / coeffX);
        }
    }

    private int[] parseHalfEquation(String half) {
        int[] ab = new int[2];
        char[] arr = half.toCharArray();
        // if (log.isDebugEnabled()) {
        //     log.debug("half equation array = {}", Arrays.toString(arr));
        // }
        int p = 0;
        while (p < arr.length) {
            int start = p;
            if (arr[p] == '-' || arr[p] == '+') p++;
            while (p < arr.length && arr[p] != '-' && arr[p] != '+') p++;
            int isX = 1, end = p;
            // if (log.isDebugEnabled()) {
            //     log.debug("segment = {}", Arrays.toString(Arrays.copyOfRange(arr, start, end)));
            // }
            if (arr[p - 1] == 'x') {
                isX = 0;
                end = p - 1;
                if (start == end || ((start + 1 == end) && arr[start] == '+')) {
                    ab[0]++;
                    continue;
                } else if ((start + 1 == end) && arr[start] == '-') {
                    ab[0]--;
                    continue;
                }
            }
            int val = Integer.parseInt(new String(Arrays.copyOfRange(arr, start, end)));
            ab[isX] += val;
        }
        return ab;
    }

}
