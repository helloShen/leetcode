/**
 * Leetcode - #771 - Jewels and Stones
 */
package com.ciaoshen.leetcode.jewels_and_stones;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution1 implements Solution {
    public int numJewelsInStones(String J, String S) {
        boolean[] upper = new boolean[26];
        boolean[] lower = new boolean[26];
        for (char c : J.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upper[c - 'A'] = true;
            } else { // lower case
                lower[c - 'a'] = true;
            }
        }
        int count = 0;
        for (char c : S.toCharArray()) {
            if (Character.isUpperCase(c)) {
                if (upper[c - 'A']) {
                    count++;
                }
            } else if (lower[c - 'a']) {
                    count++;
            }
        }
        return count;
    }
}