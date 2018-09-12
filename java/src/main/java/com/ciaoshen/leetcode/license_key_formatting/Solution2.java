/**
 * Leetcode - license_key_formatting
 */
package com.ciaoshen.leetcode.license_key_formatting;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * O(n) and only 1 pass
 * 560ms
 */
class Solution2 implements Solution {

    private final char DASH = '-';
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int wp = 0;             // points to the head of a window of K 
        int p = S.length() - 1;     // points to the last char of string S
        char[] arr = S.toCharArray();
        while (p >= 0) {
            if (wp == K) {
                sb.insert(0, DASH);
            }
            for (wp = 0; p >= 0 && wp < K; wp++, p--) {
                while (p >= 0 && arr[p] == DASH) { p--; }
                if (p < 0) { break; }
                char c = arr[p];
                if (c >= 'a' && c <= 'z') { // to uppercase
                    c -= 32;
                }
                sb.insert(0, c);
            }
            if (wp == 0 && sb.length() > 0) {
                sb.deleteCharAt(0);
            }
        }
        return sb.toString();
    }

}
