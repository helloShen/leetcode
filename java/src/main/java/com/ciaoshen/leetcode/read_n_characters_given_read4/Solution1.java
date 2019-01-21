/**
 * Leetcode - read_n_characters_given_read4
 * This problem has no test case
 */
package com.ciaoshen.leetcode.read_n_characters_given_read4;
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

    public int read(char[] buf, int n) {
        if (n <= 0) return 0;
        char[] localBuf = new char[4];
        int total = 0;
        while (true) {
            int localLen = read4(localBuf);
            if (localLen == 0) break;
            for (int i = 0; i < localLen; i++) {
                if (total == n) break;
                buf[total++] = localBuf[i];
            }
        }
        return total;
    }

}
