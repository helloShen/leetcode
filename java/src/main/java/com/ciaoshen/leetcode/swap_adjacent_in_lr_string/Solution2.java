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
class Solution2 implements Solution {

    /**
     * 原理：L只可左移，且不可穿透任何L以及R。
     * 同样，R只可右移，且不可穿透任何L以及R。
     * 因此，有两个推论：
     *  1. 去掉所有X以后，L和R数量相等，且相对顺序保持不变
     *  2. start中每一个L的位置都大于等于end中的对应L。R的位置都小于等于对应R的位置。
     *
     * 另：全部换成数组来做，效率更高。
     */
    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) return false;
        List<Integer> siL = new LinkedList<>();
        List<Integer> eiL = new LinkedList<>();
        List<Integer> siR = new LinkedList<>();
        List<Integer> eiR = new LinkedList<>();
        StringBuilder compressedStart = new StringBuilder();
        StringBuilder compressedEnd = new StringBuilder();
        for (int i = 0; i < start.length(); i++) {
            char si = start.charAt(i);
            if (si == 'L') {
                siL.add(i);
                compressedStart.append('L');
            } else if (si == 'R') {
                siR.add(i);
                compressedStart.append('R');
            }
            char ei = end.charAt(i);
            if (ei == 'L') {
                eiL.add(i);
                compressedEnd.append('L');
            } else if (ei == 'R') {
                eiR.add(i);
                compressedEnd.append('R');
            }
        }
        if (!compressedStart.toString().equals(compressedEnd.toString())) return false;
        for (int i = 0; i < siL.size(); i++) {
            if (siL.get(i) < eiL.get(i)) return false;
        }
        for (int i = 0; i < siR.size(); i++) {
            if (siR.get(i) > eiR.get(i)) return false;
        }
        return true;
    }

}
