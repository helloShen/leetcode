/**
 * Leetcode - repeated_string_match
 */
package com.ciaoshen.leetcode.repeated_string_match;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution2 implements Solution {

    public int repeatedStringMatch(String A, String B) {
        if (B.length() == 0) return 1;
        if (A.length() == 0) return -1;
        int limit = Math.max(A.length(), B.length()) * 2 - 1;
        StringBuilder sb = new StringBuilder(A);
        int k = 1;
        while (sb.length() < B.length()) {
            sb.append(A);
            k++;
        }
        while (!sb.toString().contains(B)) {
            if (sb.length() >= limit) return -1;
            sb.append(A);
            k++;
        }
        return k;
    }

}
