/**
 * Leetcode - #771 - Jewels and Stones
 */
package com.ciaoshen.leetcode.jewels_and_stones;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

class Solution2 implements Solution {
    public int numJewelsInStones(String J, String S) {
        boolean[] isJewel = new boolean[123];
        int count = 0;
        for (char c : J.toCharArray()) {
            isJewel[c] = true;
        }
        for (char c : S.toCharArray()) {
            if (isJewel[c]) count++;
        }
        return count;
    }
}