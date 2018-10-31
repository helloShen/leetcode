/**
 * Leetcode - permutation_in_string
 */
package com.ciaoshen.leetcode.permutation_in_string;
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

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        char[] c1 = s1.toCharArray();
        int[] count1 = new int[26];
        for (char c : c1) count1[c - 'a']++;
        char[] c2 = s2.toCharArray();
        int[] count2 = new int[26];
        for (int i = 0; i < c1.length; i++) count2[c2[i] - 'a']++;
        for (int lo = 0, hi = c1.length; hi < c2.length; lo++, hi++) {
            if (Arrays.equals(count1, count2)) return true;
            count2[c2[lo] - 'a']--;
            count2[c2[hi] - 'a']++;
        }
        if (Arrays.equals(count1, count2)) return true;
        return false;
    }

}
