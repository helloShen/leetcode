/**
 * Leetcode - license_key_formatting
 */
package com.ciaoshen.leetcode.license_key_formatting;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/** 
 * O(n) but parse three times 
 *  1. split by dash
 *  2. merge snippets
 *  3. insert dash
 */
class Solution1 implements Solution {

    public String licenseKeyFormatting(String S, int K) {
        String[] segs = S.toUpperCase().split("-");
        StringBuilder sb = new StringBuilder();
        for (String seg : segs) {
            sb.append(seg);
        }
        for (int end = sb.length(), start = end - K; start > 0; end = start, start = end - K) {
            sb.insert(start, '-');
        }
        return sb.toString();
    }

}
