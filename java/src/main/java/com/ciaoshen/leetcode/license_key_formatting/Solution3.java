/**
 * Leetcode - license_key_formatting
 */
package com.ciaoshen.leetcode.license_key_formatting;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * O(n) but also 3 pass
 * 193ms
 */
class Solution3 implements Solution {

    public String licenseKeyFormatting(String S, int K) {
        S = S.toUpperCase();
        S = S.replaceAll("-","");
        StringBuilder sb = new StringBuilder(S);
        for (int end = sb.length(), start = end - K; start > 0; end = start, start = end - K) {
            sb.insert(start, '-');
        }
        return sb.toString();
    }

}
