/**
 * Leetcode - swap_adjacent_in_lr_string
 */
package com.ciaoshen.leetcode.swap_adjacent_in_lr_string;
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
class Solution4 implements Solution {

    /**
     * 原理：L只可左移，且不可穿透任何L以及R。
     * 同样，R只可右移，且不可穿透任何L以及R。
     * 因此，有两个推论：
     *  1. 去掉所有X以后，L和R数量相等，且相对顺序保持不变
     *  2. start中每一个L的位置都大于等于end中的对应L。R的位置都小于等于对应R的位置。
     *
     * 不使用额外内存。直接逐个比较下一个`R`或者`L`。原则是：
     *  1. R和L的顺序必须一致
     *  2. start中R的位置只允许提前，L的位置只允许拖后。
     */
    public boolean canTransform(String start, String end) {
        // if (log.isDebugEnabled()) {
        //     log.debug("in canTransform() method!");
        // }
        if (start.length() != end.length()) return false;
        // if (log.isDebugEnabled()) {
        //     log.debug("start and end have the same size!");
        // }
        int len = start.length();
        for (int i = 0, j = 0; i < len && j < len; i++,j++) {
            while (i < len && start.charAt(i) == 'X') i++;
            while (j < len && end.charAt(j) == 'X') j++;
            if (i == len && j == len) return true;
            if (i == len || j == len) return false;
            char sc = start.charAt(i), ec = end.charAt(j);
            // if (log.isDebugEnabled()) {
            //     log.debug("i find {} at {}, j find {} at {}", sc, i, ec, j);
            // }
            if (sc != ec || (sc == 'R' && i > j) || (sc == 'L' && i < j)) return false;
        }
        return true;
    }
}
