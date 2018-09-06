/**
 * Leetcode - #392 - Is Subsequence
 */
package com.ciaoshen.leetcode.is_subsequence;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * 利用String.indexOf()的Binary Search
 * O(lenS * log(lenT))
 * 
 * 如果t的长度远大于s，这个方法比Solution1快很多
 */
class Solution2 implements Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        char c = 0;
        int prev = 0;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            prev = t.indexOf(c, prev);
            if (prev < 0) {
                return false;
            }
            prev++;
        }
        return true;
    }
}