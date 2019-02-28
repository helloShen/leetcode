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
class Solution1 implements Solution {

    public boolean canTransform(String start, String end) {
        Set<String> history = new HashSet<>();
        List<String> level = new LinkedList<>();
        level.add(start);
        while (!level.isEmpty()) {
            int size = level.size();
            for (int i = 0; i < size; i++) {
                String str = level.remove(0);
                char[] ca = str.toCharArray();
                if (str.equals(end)) return true;
                history.add(str);
                for (int j = 0; j < ca.length - 1; j++) {
                    if (ca[j] == 'R' && ca[j + 1] == 'X') {
                        ca[j] = 'X';
                        ca[j + 1] = 'R';
                        level.add(new String(ca));
                        ca[j] = 'R';
                        ca[j + 1] = 'X';
                    } else if (ca[j] == 'X' && ca[j + 1] == 'L') {
                        ca[j] = 'L';
                        ca[j + 1] = 'X';
                        level.add(new String(ca));
                        ca[j] = 'X';
                        ca[j + 1] = 'L';
                    }
                }
            }
        }
        return false;
    }

}
