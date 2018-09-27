/**
 * Leetcode - repeated_string_match
 */
package com.ciaoshen.leetcode.repeated_string_match;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

class Solution3 implements Solution {

    public int repeatedStringMatch(String A, String B) {
        if (B.length() == 0) return 1;
        if (A.length() == 0) return -1;
        StringBuilder sb = new StringBuilder(A);
        int k = 1;
        while (sb.length() < B.length()) {
            sb.append(A);
            k++;
        }
        if (sb.toString().lastIndexOf(B) != -1) return k;
        if (sb.append(A).toString().lastIndexOf(B) != -1) return k + 1;
        return -1;
    }

}
