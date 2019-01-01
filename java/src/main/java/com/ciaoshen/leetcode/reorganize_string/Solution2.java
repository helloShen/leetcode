/**
 * Leetcode - reorganize_string
 */
package com.ciaoshen.leetcode.reorganize_string;
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

    public String reorganizeString(String S) {
        int size = S.length();
        if (size < 2) return S;
        char[] arr = S.toCharArray();
        for (int pre = 0, curr = 1; curr < size; pre++, curr++) {
            if (arr[pre] == arr[curr]) {
                int idx = curr + 1;
                while (idx < size && arr[idx] == arr[curr]) idx++;
                if (idx == size) {
                    if (log.isDebugEnabled()) {
                        log.debug("need to insert! arr = {}", Arrays.toString(arr));
                    }
                    char[] filled = fill(Arrays.copyOfRange(arr, 0, pre), arr[pre], size - pre);
                    if (log.isDebugEnabled()) {
                        log.debug("array to fill = {}, , char = {}, count = {}", Arrays.toString(Arrays.copyOfRange(arr, 0, pre)), arr[pre], size - pre);
                        log.debug("filled array = {}", Arrays.toString(filled));
                    }
                    return (filled == null)? "" : new String(filled);
                }
                arr[curr] = arr[idx];
                arr[idx] = arr[pre];
            }
        }
        return new String(arr);
    }

    private char[] fill(char[] arr, char c, int count) {
        char[] res = new char[arr.length + count];
        int i = 0;
        int idx = 0;
        for (; i < arr.length && count > 0; i++) {
            if (arr[i] != c) {
                res[idx++] = c;
                res[idx++] = arr[i];
                count--;
                if (log.isDebugEnabled()) {
                    log.debug("fill letter {}@{}, and letter {}@{}", c, idx - 2, arr[i], idx - 1);
                }
            } else {
                if (log.isDebugEnabled()) {
                    log.debug("fill letter {}@{}", c, idx);
                }
                res[idx++] = c;
                if (i + 1 < arr.length) {
                    res[idx++] = arr[++i];
                    if (log.isDebugEnabled()) {
                        log.debug("fill letter {}@{}", arr[i], idx - 1);
                    }
                }
            }
        }
        if (idx < res.length && count > 0) {
            res[idx++] = c;
            count--;
        }
        if (count > 0) return null;
        while (i < arr.length) {
            res[idx++] = arr[i++];
        }
        return res;
    }

}
