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
class Solution1 implements Solution {

    public boolean validPalindrome(String s) {
        char[] cArr = s.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            if (isPalindrome(cArr, i)) return true;
        }
        return false;
    }

    private boolean isPalindrome(char[] arr, int skip) {
        int lo = 0, hi = arr.length - 1;
        if (lo == skip) lo++;
        if (hi == skip) hi--;
        while (lo <= hi) {
            if (arr[lo++] != arr[hi--]) return false;
            if (lo == skip) lo++;
            if (hi == skip) hi--;
        }
        return true;
    }

}
