/**
 * Leetcode - valid_palindrome_two
 */
package com.ciaoshen.leetcode.valid_palindrome_two;
import java.util.*;
import com.ciaoshen.leetcode.util.*;

/**
 * log instance is defined in Solution interface
 * this is how slf4j will work in this class:
 * =============================================
 *     if (log.isDebugEnabled()) {
 *         log.debug("a + b = {}", sum);
 *     }
 * =============================================
 */
class Solution2 implements Solution {

    public boolean validPalindrome(String s) {
        char[] cArr = s.toCharArray();
        return isPalindrome(cArr, true) || isPalindrome(cArr, false);
    }

    private boolean isPalindrome(char[] arr, boolean inFirstHalf) {
        int len = arr.length;
        boolean ignored = false;
        int lo = -2, hi = -2;
        if (len % 2 == 0) { // palindrome is odd, 1 core
            lo = (len - 1) / 2;
            hi = lo;
        } else { // palindrome is even, 2 core
            lo = (len - 1) / 2 - 1;
            hi = lo + 1;
        }
        if (inFirstHalf) {
            lo++;
            hi++;
        }
        if (log.isDebugEnabled()) {
            log.debug("array = {}", Arrays.toString(arr));
            log.debug("At begining: lo = {}, hi = {}", lo, hi);
        }
        while (lo >= 0 && hi < len) {
            if (arr[lo--] != arr[hi++]) {
                if (!ignored) {
                    if (inFirstHalf) {
                        hi--;
                        if (log.isDebugEnabled()) {
                            log.debug("ignore [{} at {}]", arr[lo + 1], lo + 1);
                        }
                    } else {
                        lo++;
                        if (log.isDebugEnabled()) {
                            log.debug("ignore [{} at {}]", arr[hi - 1], hi - 1);
                        }
                    }
                    ignored = true;

                } else {
                    if (log.isDebugEnabled()) {
                        log.debug("meet conflit again [lo = {} at {}]", arr[lo + 1], lo + 1);
                        log.debug("meet conflit again [hi = {} at {}]", arr[hi - 1], hi - 1);
                    }
                    return false;
                }
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("finally lo = {}, hi = {}", lo, hi);
        }
        if (!ignored) {
            if (inFirstHalf) {
                lo--;
            } else {
                hi++;
            }
        }
        return (lo == -1 && hi == len);
    }

}
