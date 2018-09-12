/**
 * Leetcode - license_key_formatting
 */
package com.ciaoshen.leetcode.license_key_formatting;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * O(n) 2 pass
 *  1. calculate the number of letter-characters
 *  2. create an array of a certain length and fill that array
 * 6ms beats 100%
 */
class Solution4 implements Solution {

    private final char DASH = '-';
    public String licenseKeyFormatting(String S, int K) {
        char[] s = S.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == DASH) { continue; }
            count++;
        }
        if (count == 0) { return ""; }
        char[] arr = new char[count + (count / K) - ((count % K == 0)? 1 : 0)];
        int wp = 0;                 // points to the head of a window of K 
        int sp = s.length - 1;      // points to string s's last char
        int ap = arr.length - 1;    // points to string arr's last char
        while (sp >= 0) {
            if (wp == K && ap > 0) {
                arr[ap--] = DASH;
            }
            for (wp = 0; sp >= 0 && wp < K; wp++, sp--) {
                while (sp >= 0 && s[sp] == DASH) { sp--; }
                if (sp < 0) { break; }
                char c = s[sp];
                if (c >= 'a' && c <= 'z') { // to uppercase
                    c -= 32;
                }
                arr[ap--] = c;
            }
        }
        return new String(arr);
    }

}
