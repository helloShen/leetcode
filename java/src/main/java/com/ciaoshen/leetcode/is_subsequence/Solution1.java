/**
 * Leetcode - #392 - Is Subsequence
 */
package com.ciaoshen.leetcode.is_subsequence;
import java.util.*;
import com.ciaoshen.leetcode.myUtils.*;

/**
 * 两个指针
 * O(Max(lenS, lenT))
 */
class Solution1 implements Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        int sp = 0, tp = 0;
        while (sp < s.length() && tp < t.length()) {
            if (s.charAt(sp) == t.charAt(tp)) {
                sp++;
            }
            tp++;
        }
        return sp == s.length();
    }
}